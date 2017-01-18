package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.persistence.model.OrderStatus;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 15.01.2017.
 */
public class MakeOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(MakeOrderCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String CURRENT_USER_CART = "currentUserCart";
    private static final String CURRENT_ORDER_FORMATTED_DATE = "currentOrderFormattedDate";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);
        List<Item> currentUserCart = (List<Item>) session.getAttribute(CURRENT_USER_CART);

        List<Item> itemsInOrder = new ArrayList<>();

        if (currentUserCart.size() != 0) {

            itemsInOrder.addAll(currentUserCart);
            Date orderDateAndTime = new Date();
            String currentOrderFormattedDate = String.format(
                    "year %1$ty, month %1$tm, day %1$td, time %1$tH:%1$tM", orderDateAndTime);

            BigDecimal summaryOrderPrice = itemsInOrder.stream().map(Item::getItemPrice).reduce(BigDecimal::add).get();

            Order newOrder = new Order(currentUserInSystem.getId(), orderDateAndTime,
                    summaryOrderPrice, OrderStatus.NEW.getOrderStatusId(), itemsInOrder);

            Integer newOrderId = iClientService.createNewOrder(newOrder);
            newOrder.setId(newOrderId);
            System.out.println("newOrder" + newOrder);
            itemsInOrder.forEach(i -> iClientService.addItemToOrder(newOrderId, i.getId()));

            request.setAttribute(CURRENT_ORDER_FORMATTED_DATE, currentOrderFormattedDate);
            request.setAttribute(CURRENT_USER_ORDER, newOrder);
        }


        return "/WEB-INF/pages/order_page.jsp";
    }
}
