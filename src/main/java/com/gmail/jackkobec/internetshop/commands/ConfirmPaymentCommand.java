package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.persistence.model.OrderStatus;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 26.01.2017.
 */
public class ConfirmPaymentCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(ConfirmPaymentCommand.class);

    private static final String CURRENT_USER_ORDER_FOR_PAYMENT_ID = "currentUserOrderForPaymentId";
    private static final String CURRENT_ORDER_FORMATTED_DATE = "currentOrderFormattedDate";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";
    private static final String MAIN_PAGE_ALERT_CLASS = "mainPageAlertClass";
    private static final String ERROR_INFO = "errorInfo";

    private static final String DATE_AND_TIME_FORMAT_PATTERN = "Date %1$td.%1$tm.%1$ty Time %1$tH:%1$tM:%1$tS";
    private static final String ALERT_ALERT_SUCCESS_CLASS = "alert alert-success alert-dismissible";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer currentUserOrderForPaymentId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_FOR_PAYMENT_ID));

        Order paidOrder = iClientService.getOrderById(currentUserOrderForPaymentId);
        paidOrder.setOrderStatus(OrderStatus.DONE.getOrderStatusId());

        final Integer paidOrderId = iClientService.updateOrderInfo(paidOrder);

        if (paidOrderId.equals(paidOrder.getId())) {

            request.setAttribute("mainPageAlertFlag", true);
            request.setAttribute(MAIN_PAGE_ALERT_CLASS, ALERT_ALERT_SUCCESS_CLASS);
            request.setAttribute("mainPageAlertMessage", "Order with id: " + paidOrderId + " paid.");

            LOGGER.info("Order with id: " + paidOrderId + " paid!");

            return PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);
        } else {
            request.setAttribute(ERROR_INFO, "Order not paid!");
            LOGGER.error("Order not canceled! ClientService response different id.");

            return errorPage;
        }
    }
}
