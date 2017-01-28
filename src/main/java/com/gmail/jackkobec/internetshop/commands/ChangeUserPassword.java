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
 * <p>UserUpdateCommand class execute command for change user password.
 */
public class ChangeUserPassword implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(ChangeUserPassword.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String PASSWORD = "password";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String MAIN_PAGE_ALERT_CLASS = "mainPageAlertClass";
    private static final String ALERT_ALERT_SUCCESS_CLASS = "alert alert-success alert-dismissible";
    private static final String ALERT_ALERT_WARNING_CLASS = "alert alert-warning alert-dismissible";
    private static final String MAIN_PAGE_ALERT_FLAG = "mainPageAlertFlag";
    private static final String MAIN_PAGE_ALERT_MESSAGE = "mainPageAlertMessage";

    private static final String ERROR_INFO = "errorInfo";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for change user password.
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

        final String currentPassword = request.getParameter(PASSWORD);
        final String newPassword = request.getParameter(NEW_PASSWORD);

        if (currentPassword.equals(currentUserInSystem.getPassword())) {

            User forUpdate = new User(currentUserInSystem.getId(), currentUserInSystem.getEmail(), newPassword,
                    currentUserInSystem.getName(), currentUserInSystem.getLanguage(), currentUserInSystem.getUserType());

            if (iClientService.updateUser(forUpdate).equals(currentUserInSystem.getId())) {

                request.setAttribute(MAIN_PAGE_ALERT_FLAG, true);
                request.setAttribute(MAIN_PAGE_ALERT_CLASS, ALERT_ALERT_SUCCESS_CLASS);
                request.setAttribute(MAIN_PAGE_ALERT_MESSAGE, "User password changed.");
                session.setAttribute(CURRENT_USER_IN_SYSTEM, forUpdate);
                LOGGER.info("User password changed.");

                return mainPage;
            } else {
                request.setAttribute(ERROR_INFO, "User password not changed.");
                LOGGER.error("User password not changed.");

                return errorPage;
            }

        } else {
            request.setAttribute(MAIN_PAGE_ALERT_FLAG, true);
            request.setAttribute(MAIN_PAGE_ALERT_CLASS, ALERT_ALERT_WARNING_CLASS);
            request.setAttribute(MAIN_PAGE_ALERT_MESSAGE, "Incorrect current password.");

            return mainPage;
        }
    }
}
