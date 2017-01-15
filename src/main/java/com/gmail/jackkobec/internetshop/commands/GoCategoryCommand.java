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
 * Created by Jack on 12.01.2017.
 */
public class GoCategoryCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoCategoryCommand.class);

    private static final String CATEGORY_ID = "category_id";
    IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String chosenCategoryPage = PageManager.getPageManager().getPage(PageManager.CHOSEN_CATEGORY_PAGE);

        final Integer categoryId = Integer.valueOf(request.getParameter(CATEGORY_ID));

        List<Item> currentCategoryItemList = iClientService.getItemsByCategoryId(categoryId);
        request.setAttribute("currentCategoryItemList", currentCategoryItemList);

        return chosenCategoryPage;
    }
}
