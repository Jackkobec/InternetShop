package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.service.ClientService;
import com.gmail.jackkobec.internetshop.service.IClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jack on 14.01.2017.
 */
public class AddToCartCommand implements ICommand {

    private static final String ITEM_ID = "item_id";

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));



        List<Item> currentUserCart = (List<Item>) request.getSession(false).getAttribute("currentUserCart");
        System.out.println("currentUserCart " + currentUserCart);
        IClientService iClientService = new ClientService();
        Item currentItemForShow = iClientService.getItemById(itemId);
        currentUserCart.add(currentItemForShow);
        System.out.println("currentUserCart.add " + currentUserCart);
        request.getSession(false).setAttribute("currentUserCart", currentUserCart);
        request.setAttribute("currentItemForShow", currentItemForShow);
        return "/WEB-INF/pages/item_page.jsp";
    }
}
