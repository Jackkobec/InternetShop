package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jack on 15.01.2017.
 */
public class RemoveItemFromCartCommand implements ICommand {


    private static final String ITEM_ID = "item_id";
    private static final String FROM_PAGE = "from_page";


    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));
        final String fromPage = request.getParameter(FROM_PAGE);
        System.out.println("FROM_PAGE + " + fromPage);

        List<Item> currentUserCart = (List<Item>) request.getSession(false).getAttribute("currentUserCart");
        BigDecimal summaryCartPrice = (BigDecimal) request.getSession(false).getAttribute("summaryCartPrice");

        //correct decision
       /* currentUserCart.removeIf(i -> i.getId().equals(itemId));*/

        //temporally decision
        for (Item item : currentUserCart) {

            if(item.getId().equals(itemId)){
                currentUserCart.remove(item);
                summaryCartPrice = summaryCartPrice.subtract(item.getItemPrice());
                break;
            }
        }

        request.getSession(false).setAttribute("summaryCartPrice", summaryCartPrice);
        request.getSession(false).setAttribute("currentUserCart", currentUserCart);

        return PageManager.getPageManager().getPage(fromPage);
    }
}
