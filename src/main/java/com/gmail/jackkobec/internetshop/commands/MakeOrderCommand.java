package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>MakeOrderCommand class execute command for create new order.
 */
public class MakeOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(MakeOrderCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String CURRENT_USER_CART = "currentUserCart";
    private static final String CURRENT_ORDER_FORMATTED_DATE = "currentOrderFormattedDate";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";

    private static final String DATE_AND_TIME_FORMAT_PATTERN = "Date %1$td.%1$tm.%1$ty Time %1$tH:%1$tM:%1$tS";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for create new order.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderPage = PageManager.getPageManager().getPage(PageManager.ORDER_PAGE);

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);
        List<Item> currentUserCart = (List<Item>) session.getAttribute(CURRENT_USER_CART);

        List<Item> itemsInOrder = new ArrayList<>();

        if (currentUserCart.size() != 0) {

            itemsInOrder.addAll(currentUserCart);
            Date orderDateAndTime = new Date();
            String currentOrderFormattedDate = String.format(
                    DATE_AND_TIME_FORMAT_PATTERN, orderDateAndTime);

            BigDecimal summaryOrderPrice = itemsInOrder.stream().map(Item::getItemPrice).reduce(BigDecimal::add)
                    .orElse(new BigDecimal(0.00));

            Order newOrder = new Order(currentUserInSystem.getId(), orderDateAndTime,
                    summaryOrderPrice, OrderStatus.NEW.getOrderStatusId(), itemsInOrder);

            Integer newOrderId = iClientService.createNewOrder(newOrder);
            newOrder.setId(newOrderId);
            //add data to the order_item data
            itemsInOrder.forEach(i -> iClientService.addItemToOrder(newOrderId, i.getId()));

            request.setAttribute(CURRENT_ORDER_FORMATTED_DATE, currentOrderFormattedDate);
            request.setAttribute(CURRENT_USER_ORDER, newOrder);
            LOGGER.info("Created new order with id: " + newOrderId);
        }

        return orderPage;
    }
}
