package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
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

/**
 * <p>CancelOrderCommand class execute command for cancel order.
 */
public class CancelOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(CancelOrderCommand.class);

    private static final String CURRENT_USER_ORDER_ID = "currentUserOrderId";
    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String ERROR_INFO = "errorInfo";
    private static final String MAIN_PAGE_ALERT_CLASS = "mainPageAlertClass";
    private static final String ALERT_ALERT_WARNING_CLASS = "alert alert-warning alert-dismissible";
    private static final String MAIN_PAGE_ALERT_FLAG = "mainPageAlertFlag";
    private static final String MAIN_PAGE_ALERT_MESSAGE = "mainPageAlertMessage";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();
    private AppDataInitializer appDataInitializer = AppDataInitializer.getAppDataInitializer();

    /**
     * Method execute command for cancel order.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer currentUserOrderId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_ID));

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);

        Order orderForCancel = iClientService.getOrderById(currentUserOrderId);

        if (iClientService.cancelOrder(orderForCancel.getId())) {

            request.setAttribute(MAIN_PAGE_ALERT_FLAG, true);
            request.setAttribute(MAIN_PAGE_ALERT_CLASS, ALERT_ALERT_WARNING_CLASS);
            request.setAttribute(MAIN_PAGE_ALERT_MESSAGE, "Order with id: " + orderForCancel.getId() + " canceled.");

            appDataInitializer.initUserOrders(session, currentUserInSystem.getId());

            LOGGER.info("Order with id: " + currentUserOrderId + " canceled!");

            return PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);
        } else {
            request.setAttribute(ERROR_INFO, "Order not canceled!");
            LOGGER.error("Order not canceled! ClientService response false.");

            return errorPage;
        }
    }
}
