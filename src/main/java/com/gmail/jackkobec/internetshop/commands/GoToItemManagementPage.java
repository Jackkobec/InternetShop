package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>AddNewItemCommand class execute command for management items by category.
 */
public class GoToItemManagementPage implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoToItemManagementPage.class);

    private static final String ITEM_CATEGORY_ID = "itemCategoryId";
    private static final String ITEM_LIST_BY_CATEGORY = "itemListByCategory";
    private static final String ITEM_MANAGEMENT_MESSAGE = "itemManagementMessage";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for management items by category.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemCategoryIdParameter = request.getParameter(ITEM_CATEGORY_ID);

        if (itemCategoryIdParameter != null) {

            final Integer itemCategoryId = Integer.valueOf(itemCategoryIdParameter);

            List<Item> itemListByCategory = iClientService.getItemsByCategoryId(itemCategoryId);

            if (itemListByCategory.size() != 0) {

                request.setAttribute(ITEM_LIST_BY_CATEGORY, itemListByCategory);
                LOGGER.info("Show items by category with id " + itemCategoryId);

            } else {
                request.setAttribute(ITEM_MANAGEMENT_MESSAGE, "No items.");
                LOGGER.warn("No items in category with id " + itemCategoryId);
            }
        }

        return PageManager.getPageManager().getPage(PageManager.ITEM_MANAGEMENT_PAGE);
    }
}
