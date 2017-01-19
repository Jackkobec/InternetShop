package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 18.01.2017.
 */
public class RemoveItemFromOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(RemoveItemFromOrderCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String CURRENT_USER_ORDER_ID = "currentUserOrderId";
    private static final String ITEM_FOR_REMOVE_ID = "itemForRemoveId";
    private static final String CURRENT_ORDER_FORMATTED_DATE = "currentOrderFormattedDate";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);

        final Integer currentUserOrderId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_ID));
        final Integer itemForRemoveId = Integer.valueOf(request.getParameter(ITEM_FOR_REMOVE_ID));

        Order order = iClientService.getOrderById(currentUserOrderId);
        iClientService.removeItemFromOrder(order.getId(), itemForRemoveId);

        List<Item> itemsInOrder = iClientService.getItemsFromOrderByOrderId(order.getId());
        BigDecimal summaryOrderPrice = itemsInOrder.stream().map(Item::getItemPrice).reduce(BigDecimal::add)
                .orElseGet(()-> new BigDecimal(0.00));
        order.setItemList(itemsInOrder);
        order.setSummaryPrice(summaryOrderPrice);

        String currentOrderFormattedDate = String.format(
                "Date %1$td.%1$tm.%1$ty Time %1$tH:%1$tM:%1$tS", order.getOrderDateAndTime());

        request.setAttribute(CURRENT_ORDER_FORMATTED_DATE, currentOrderFormattedDate);
        request.setAttribute(CURRENT_USER_ORDER, order);


        return "/WEB-INF/pages/order_page.jsp";
    }
}
