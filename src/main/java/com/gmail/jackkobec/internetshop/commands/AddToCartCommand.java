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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jack on 14.01.2017.
 */
public class AddToCartCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(AddToCartCommand.class);

    private static final String ITEM_ID = "item_id";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemPage = PageManager.getPageManager().getPage(PageManager.ITEM_PAGE);

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));

        User currentUserInSystem = (User) request.getSession(false).getAttribute("currentUserInSystem");

        List<Item> currentUserCart = (List<Item>) request.getSession(false).getAttribute("currentUserCart");
        Item currentItemForShow = (Item) request.getSession(false).getAttribute("currentItemForShow");

        BigDecimal summaryCartPrice = currentItemForShow.getItemPrice();

        if (currentUserCart.size() != 0) {
//            BigDecimal summaryCartPrice = currentUserCart.stream().map(Item::getItemPrice).reduce(BigDecimal::add).get();
            for (Item item : currentUserCart) {
                summaryCartPrice = summaryCartPrice.add(item.getItemPrice());
            }

            request.getSession(false).setAttribute("summaryCartPrice", summaryCartPrice);
        } else if (currentUserCart.size() == 0) {

            request.getSession(false).setAttribute("summaryCartPrice", currentItemForShow.getItemPrice());
        }

        currentUserCart.add(currentItemForShow);
        iClientService.addItemToCart(itemId, currentUserInSystem.getId());
        request.getSession(false).setAttribute("currentUserCart", currentUserCart);

        return itemPage;
    }
}
