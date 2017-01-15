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

/**
 * Created by Jack on 12.01.2017.
 */
public class ShowItemCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(ShowItemCommand.class);

    private static final String ITEM_ID = "item_id";
    private static final String ITEM_COUNT = "item_count";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemPage = PageManager.getPageManager().getPage(PageManager.ITEM_PAGE);
        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));

        Item currentItemForShow = iClientService.getItemById(itemId);

        if (currentItemForShow != null) {
            request.getSession(false).setAttribute("currentItemForShow", currentItemForShow);

            return itemPage;

        } else {
            request.setAttribute("errorInfo", "Item with " + itemId + " doesn't exist!");

            return errorPage;
        }
    }
}
