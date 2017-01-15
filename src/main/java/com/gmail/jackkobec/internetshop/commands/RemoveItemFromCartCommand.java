package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jack on 15.01.2017.
 */
public class RemoveItemFromCartCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(RemoveItemFromCartCommand.class);

    private static final String ITEM_ID = "item_id";
    private static final String FROM_PAGE = "from_page";

    IClientService iClientService = ClientServiceImpl.getClientServiceImpl();


    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));
        final String fromPage = request.getParameter(FROM_PAGE);

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute("currentUserInSystem");

        List<Item> currentUserCart = (List<Item>) session.getAttribute("currentUserCart");
//        BigDecimal summaryCartPrice = (BigDecimal) session.getAttribute("summaryCartPrice");
        BigDecimal summaryCartPrice = new BigDecimal(0.00);

        for (Item item : currentUserCart) {
            summaryCartPrice = summaryCartPrice.add(item.getItemPrice());
        }

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

        session.setAttribute("summaryCartPrice", summaryCartPrice);
        session.setAttribute("currentUserCart", currentUserCart);
        iClientService.removeItemFromCart(itemId, currentUserInSystem.getId());

        return PageManager.getPageManager().getPage(fromPage);
    }
}
