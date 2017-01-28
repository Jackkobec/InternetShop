package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
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
 * <p>RemoveAllNotPaidOrders class execute command for remove all not paid orders.
 */
public class RemoveAllNotPaidOrders implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(RemoveAllNotPaidOrders.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String MAIN_PAGE_ALERT_CLASS = "mainPageAlertClass";
    private static final String ALERT_ALERT_SUCCESS_CLASS = "alert alert-success alert-dismissible";
    private static final String MAIN_PAGE_ALERT_FLAG = "mainPageAlertFlag";
    private static final String MAIN_PAGE_ALERT_MESSAGE = "mainPageAlertMessage";

    private static final String ERROR_INFO = "errorInfo";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();
    private AppDataInitializer appDataInitializer = AppDataInitializer.getAppDataInitializer();

    /**
     * Method execute command for remove all not paid orders.
     *
     * @param request
     * @param response
     * @return page for Controller.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mainPage = PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);
        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);

        if (iClientService.removeAllNotPaidOrders(currentUserInSystem.getId())) {

            request.setAttribute(MAIN_PAGE_ALERT_FLAG, true);
            request.setAttribute(MAIN_PAGE_ALERT_CLASS, ALERT_ALERT_SUCCESS_CLASS);
            request.setAttribute(MAIN_PAGE_ALERT_MESSAGE, "All not paid orders removed.");

            appDataInitializer.initUserOrders(session, currentUserInSystem.getId());

            LOGGER.info("All not paid orders removed.");

            return mainPage;
        } else {
            request.setAttribute(ERROR_INFO, "Not paid orders not removed!");
            LOGGER.error("Not paid orders not removed!");

            return errorPage;
        }
    }
}
