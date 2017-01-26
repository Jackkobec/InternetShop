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
 * <p>PayOrderCommand class execute command for pay order.
 */
public class PayOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(PayOrderCommand.class);

    private static final String CURRENT_USER_ORDER_ID = "currentUserOrderId";
    private static final String CURRENT_USER_ORDER_FOR_PAYMENT = "currentUserOrderForPayment";
    private static final String CURRENT_ORDER_FOR_PAYMENT_FORMATTED_DATE = "currentOrderForPaymentFormattedDate";
    private static final String ERROR_INFO = "errorInfo";

    private static final String DATE_AND_TIME_FORMAT_PATTERN = "Date %1$td.%1$tm.%1$ty Time %1$tH:%1$tM:%1$tS";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();


    /**
     * Method execute command for pay order.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paymentPage = PageManager.getPageManager().getPage(PageManager.PAYMENT_PAGE);
        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer currentUserOrderId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_ID));

        Order currentUserOrderForPayment = iClientService.getOrderById(currentUserOrderId);
        List<Item> itemsInOrder = iClientService.getItemsFromOrderByOrderId(currentUserOrderId);
        currentUserOrderForPayment.setItemList(itemsInOrder);

        request.setAttribute(CURRENT_ORDER_FOR_PAYMENT_FORMATTED_DATE, String.format(
                DATE_AND_TIME_FORMAT_PATTERN, currentUserOrderForPayment.getOrderDateAndTime()));
        request.setAttribute(CURRENT_USER_ORDER_FOR_PAYMENT, currentUserOrderForPayment);

        return paymentPage;
    }
}
