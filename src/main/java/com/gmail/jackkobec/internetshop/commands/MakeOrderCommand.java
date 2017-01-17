package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.persistence.model.OrderStatus;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 15.01.2017.
 */
public class MakeOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(MakeOrderCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String CURRENT_USER_CART = "currentUserCart";



    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);
        List<Item> currentUserCart = (List<Item>) session.getAttribute(CURRENT_USER_CART);

        List<Item> itemsInOrder = new ArrayList<>();

        if (currentUserCart.size() != 0) {

            itemsInOrder.addAll(currentUserCart);
            LocalDateTime orderDateAndTime = LocalDateTime.now();
            System.out.println(orderDateAndTime.format(DateTimeFormatter.ofPattern("d MMMM uuuu H-m-s")));

            BigDecimal summaryOrderPrice= itemsInOrder.stream().map(Item::getItemPrice).reduce(BigDecimal::add).get();

            Order newOrder = new Order(currentUserInSystem.getId(), orderDateAndTime,
                    summaryOrderPrice, OrderStatus.NEW.getOrderStatusId(), itemsInOrder);
        }


        return "/WEB-INF/pages/order_page.jsp";
    }
}
