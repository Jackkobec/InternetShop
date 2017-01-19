package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.service.AdminServiceImpl;
import com.gmail.jackkobec.internetshop.service.IAdminService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>DeleteItemCommand class execute command for delete item.
 */
public class DeleteItemCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(DeleteItemCommand.class);

    private static final String ITEM_ID = "for_delete_item_id";

    private static final String ITEM_MANAGEMENT_MESSAGE = "itemManagementMessage";
    private static final String ERROR_INFO = "errorInfo";

    private IAdminService iAdminService = AdminServiceImpl.getAdminServiceImpl();

    /**
     * Method execute command for delete item.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);
        String itemManagementPage = PageManager.getPageManager().getPage(PageManager.ITEM_MANAGEMENT_PAGE);

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));

        if (iAdminService.deleteItemById(itemId)) {
            LOGGER.info("Item with id: " + itemId + " was delete.");
            request.setAttribute(ITEM_MANAGEMENT_MESSAGE, "Item with id: " + itemId + " was delete.");

            return itemManagementPage;

        } else {
            LOGGER.error("Item with id: " + itemId + " not delete. AdminService response = false.");
            request.setAttribute(ERROR_INFO, "Error during item deleting. AdminService response = false.");

            return errorPage;
        }
    }
}
