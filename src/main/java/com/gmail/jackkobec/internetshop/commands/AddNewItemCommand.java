package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.ItemCategory;
import com.gmail.jackkobec.internetshop.persistence.model.ItemStatus;
import com.gmail.jackkobec.internetshop.service.AdminServiceImpl;
import com.gmail.jackkobec.internetshop.service.IAdminService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Jack on 17.01.2017.
 */
public class AddNewItemCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(AddNewItemCommand.class);

    private static final String ITEM_ID = "item_id";
    private static final String ITEM_NAME = "itemName";
    private static final String ITEM_SMALL_DESCRIPTION = "itemSmallDescription";
    private static final String ITEM_FULL_DESCRIPTION = "itemFullDescription";
    private static final String ITEM_PRODUCT_INFO = "itemProductInfo";
    private static final String ITEM_PRICE = "itemPrice";
    private static final String ITEM_BIG_PICTURE_PATH = "itemBigPicturePath800x600";
    private static final String ITEM_SMALL_PICTURE_PATH = "itemSmallPicturePath350x260";
    private static final String ITEM_RATING = "itemRating";
    private static final String iTEM_CATEGOTY = "itemCategory";
    private static final String ITEM_STATUS = "itemStatus";

    private static final String ITEM_MANAGEMENT_MESSAGE = "itemManagementMessage";
    private static final String ERROR_INFO = "errorInfo";

    private IAdminService iAdminService = AdminServiceImpl.getAdminServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final String itemName = request.getParameter(ITEM_NAME);
        final String itemSmallDescription = request.getParameter(ITEM_SMALL_DESCRIPTION);
        final String itemFullDescription = request.getParameter(ITEM_FULL_DESCRIPTION);
        final String itemProductInfo = request.getParameter(ITEM_PRODUCT_INFO);
        final BigDecimal itemPrice = new BigDecimal(Double.valueOf(request.getParameter(ITEM_PRICE)));
        final String itemBigPicturePath800x600 = request.getParameter(ITEM_BIG_PICTURE_PATH);
        final String itemSmallPicturePath350x260 = request.getParameter(ITEM_SMALL_PICTURE_PATH);
        final Integer itemRating = Integer.valueOf(request.getParameter(ITEM_RATING));
        final ItemCategory itemCategory = ItemCategory.getItemCategoryByID(Integer.valueOf(request.getParameter(iTEM_CATEGOTY)));
        final ItemStatus itemStatus = ItemStatus.getItemStatus(Integer.valueOf(request.getParameter(ITEM_STATUS)));

        Item forCreate = new Item(itemName, itemSmallDescription, itemFullDescription, itemProductInfo,
                itemPrice, itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating, itemCategory, itemStatus);

        if (iAdminService.addNewItem(forCreate)) {

            request.setAttribute(ITEM_MANAGEMENT_MESSAGE, "Item " + itemName + " added successfully.");
            return "/WEB-INF/pages/item_management_page.jsp";
        } else {

            return errorPage;
        }
    }
}
