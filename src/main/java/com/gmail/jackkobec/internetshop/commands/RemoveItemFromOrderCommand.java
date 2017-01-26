package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
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
import java.util.List;

/**
 * <p>RemoveItemFromOrderCommand class execute command for remove item from the order.
 */
public class RemoveItemFromOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(RemoveItemFromOrderCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String CURRENT_USER_ORDER_ID = "currentUserOrderId";
    private static final String ITEM_FOR_REMOVE_ID = "itemForRemoveId";
    private static final String CURRENT_ORDER_FORMATTED_DATE = "currentOrderFormattedDate";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";
    private static final String ERROR_INFO = "errorInfo";

    private static final String DATE_AND_TIME_FORMAT_PATTERN = "Date %1$td.%1$tm.%1$ty Time %1$tH:%1$tM:%1$tS";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for remove item from the order.
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
        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer currentUserOrderId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_ID));
        final Integer itemForRemoveId = Integer.valueOf(request.getParameter(ITEM_FOR_REMOVE_ID));

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);

        Order order = iClientService.getOrderById(currentUserOrderId);
        if (!iClientService.removeItemFromOrder(order.getId(), itemForRemoveId)) {

            request.setAttribute(ERROR_INFO, "Order with id: " + order.getId() + " was not removed.");
            LOGGER.error("Order with id: " + order.getId() + " was not removed.");

            return errorPage;
        }

        List<Item> itemsInOrder = iClientService.getItemsFromOrderByOrderId(order.getId());

        if (itemsInOrder.size() == 0) {

            return new CancelOrderCommand().executeCommand(request, response);
        }

        BigDecimal summaryOrderPrice = itemsInOrder.stream().map(Item::getItemPrice).reduce(BigDecimal::add)
                .orElseGet(() -> new BigDecimal(0.00));
        System.out.println("summaryOrderPrice : " + summaryOrderPrice);
        order.setItemList(itemsInOrder);
        order.setSummaryPrice(summaryOrderPrice);
        iClientService.updateOrderInfo(order);

        String currentOrderFormattedDate = String.format(
                DATE_AND_TIME_FORMAT_PATTERN, order.getOrderDateAndTime());

        request.setAttribute(CURRENT_ORDER_FORMATTED_DATE, currentOrderFormattedDate);
        request.setAttribute(CURRENT_USER_ORDER, order);

        return orderPage;
    }
}
