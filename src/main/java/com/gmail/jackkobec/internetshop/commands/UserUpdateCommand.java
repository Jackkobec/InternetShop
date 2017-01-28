package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import com.gmail.jackkobec.internetshop.service.LanguageService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>UserUpdateCommand class execute command for update user info.
 */
public class UserUpdateCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserUpdateCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String NAME = "name";
    private static final String LANGUAGE_SELECTION = "language_selection";
    private static final String MAIN_PAGE_ALERT_CLASS = "mainPageAlertClass";
    private static final String ALERT_ALERT_SUCCESS_CLASS = "alert alert-success alert-dismissible";
    private static final String MAIN_PAGE_ALERT_FLAG = "mainPageAlertFlag";
    private static final String MAIN_PAGE_ALERT_MESSAGE = "mainPageAlertMessage";
    private static final String SELECTED_LOCALE = "selectedLocale";

    private static final String ERROR_INFO = "errorInfo";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for update user info.
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

        final String newName = request.getParameter(NAME);
        final String language = new LanguageService()
                .getLanguageBySelect(Integer.parseInt(request.getParameter(LANGUAGE_SELECTION)));

        User forUpdate = new User(currentUserInSystem.getId(), currentUserInSystem.getEmail(),
                currentUserInSystem.getPassword(), newName, language, currentUserInSystem.getUserType());

        if (iClientService.updateUser(forUpdate).equals(currentUserInSystem.getId())) {

            request.setAttribute(MAIN_PAGE_ALERT_FLAG, true);
            request.setAttribute(MAIN_PAGE_ALERT_CLASS, ALERT_ALERT_SUCCESS_CLASS);
            request.setAttribute(MAIN_PAGE_ALERT_MESSAGE, "User info updated.");
            session.setAttribute(CURRENT_USER_IN_SYSTEM, forUpdate);
            session.setAttribute(SELECTED_LOCALE, language);
            LOGGER.info("User info updated.");

            return mainPage;
        } else {
            request.setAttribute(ERROR_INFO, "User info not updated.");
            LOGGER.error("User info not updated.");

            return errorPage;
        }
    }
}
