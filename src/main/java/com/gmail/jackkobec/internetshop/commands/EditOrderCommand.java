package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>EditOrderCommand class execute command for return to the edit order form.
 */
public class EditOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(EditOrderCommand.class);

    private static final String CURRENT_USER_ORDER_FOR_PAYMENT_ID = "currentUserOrderForPaymentId";
    private static final String CURRENT_ORDER_FORMATTED_DATE = "currentOrderFormattedDate";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";

    private static final String DATE_AND_TIME_FORMAT_PATTERN = "Date %1$td.%1$tm.%1$ty Time %1$tH:%1$tM:%1$tS";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for return to the edit order form.
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

        final Integer currentUserOrderForPaymentId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_FOR_PAYMENT_ID));

        Order currentUserOrder = iClientService.getOrderById(currentUserOrderForPaymentId);
        List<Item> itemsInOrder = iClientService.getItemsFromOrderByOrderId(currentUserOrderForPaymentId);
        currentUserOrder.setItemList(itemsInOrder);

        request.setAttribute(CURRENT_ORDER_FORMATTED_DATE, String.format(
                DATE_AND_TIME_FORMAT_PATTERN, currentUserOrder.getOrderDateAndTime()));
        request.setAttribute(CURRENT_USER_ORDER, currentUserOrder);
        LOGGER.info("Edit order with id: " + currentUserOrderForPaymentId);

        return orderPage;
    }
}
