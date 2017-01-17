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
 * Created by Jack on 17.01.2017.
 */
public class DeleteItemCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(DeleteItemCommand.class);

    private static final String ITEM_ID = "for_delete_item_id";

    private static final String ITEM_MANAGEMENT_MESSAGE = "itemManagementMessage";
    private static final String ERROR_INFO = "errorInfo";

    private IAdminService iAdminService = AdminServiceImpl.getAdminServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));

        if(iAdminService.deleteItemById(itemId)){

            request.setAttribute(ITEM_MANAGEMENT_MESSAGE, "Item with id: " + itemId + " was delete.");

            return "/WEB-INF/pages/item_management_page.jsp";
        }

        return errorPage;
    }
}
