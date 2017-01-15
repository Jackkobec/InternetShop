package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.service.ClientService;
import com.gmail.jackkobec.internetshop.service.IClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jack on 15.01.2017.
 */
public class RemoveItemFromCartCommand implements ICommand {


    private static final String ITEM_ID = "item_id";

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));

        List<Item> currentUserCart = (List<Item>) request.getSession(false).getAttribute("currentUserCart");

        //correct decision
       /* currentUserCart.removeIf(i -> i.getId().equals(itemId));*/

        //temporally decision
        for (Item item : currentUserCart) {
            if(item.getId().equals(itemId)){
                currentUserCart.remove(item);
                break;
            }
        }

        IClientService iClientService = new ClientService();
        Item currentItemForShow = iClientService.getItemById(itemId);

        request.getSession(false).setAttribute("currentUserCart", currentUserCart);

        return "/WEB-INF/pages/item_page.jsp";
    }
}
