package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.ItemCategory;
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
 * <p>GoCategoryCommand class execute command for move to the selected category.
 */
public class GoCategoryCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoCategoryCommand.class);

    private static final String CATEGORY_ID = "category_id";
    private static final String CURRENT_CATEGORY_NAME = "currentCategoryName";
    private static final String CURRENT_CATEGORY_ITEM_LIST = "currentCategoryItemList";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for move to the selected category.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String chosenCategoryPage = PageManager.getPageManager().getPage(PageManager.CHOSEN_CATEGORY_PAGE);

        final Integer categoryId = Integer.valueOf(request.getParameter(CATEGORY_ID));

        String categoryNameBundle = ItemCategory.getItemCategoryResourceBundleByID(categoryId);
        List<Item> currentCategoryItemList = iClientService.getItemsByCategoryId(categoryId);

        request.setAttribute(CATEGORY_ID, categoryId);
        request.setAttribute(CURRENT_CATEGORY_NAME, categoryNameBundle);
        request.setAttribute(CURRENT_CATEGORY_ITEM_LIST, currentCategoryItemList);
        LOGGER.info("GoCategoryCommand, categoryId: " + categoryId);

        return chosenCategoryPage;
    }
}
