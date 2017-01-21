package com.gmail.jackkobec.internetshop.commands;


import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import com.gmail.jackkobec.internetshop.service.LanguageService;
import com.gmail.jackkobec.internetshop.validation.ValidationFeedbackManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>UserRegistrationCommand class execute command for register new user.
 */
public class UserRegistrationCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserRegistrationCommand.class);

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_CONFIRMATION = "password_confirmation";
    private static final String NAME = "name";
    private static final String LANGUAGE_SELECTION = "language_selection";
    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String SIX_ITEM_CAROUSEL = "sixItemCarousel";
    private static final String CURRENT_USER_CART = "currentUserCart";
    private static final String SUMMARY_CART_PRICE = "summaryCartPrice";
    private static final String ERROR_INFO = "errorInfo";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();
    private ValidationFeedbackManager validationFeedbackManager = ValidationFeedbackManager.getValidationFeedbackManager();
    private AppDataInitializer appDataInitializer = AppDataInitializer.getAppDataInitializer();

    /**
     * Method execute command for register new user.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String registrationPage = PageManager.getPageManager().getPage(PageManager.REGISTRATION_PAGE);
        String mainPage = PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);
        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        //mandatory
        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);
        final String passwordConfirmation = request.getParameter(PASSWORD_CONFIRMATION);
        //optional
        final String name = request.getParameter(NAME);
        final String language = new LanguageService()
                .getLanguageBySelect(Integer.parseInt(request.getParameter(LANGUAGE_SELECTION)));

        if (validationFeedbackManager.preValidation(request, email, password, passwordConfirmation))

            return registrationPage;

        User forRegister = new User(email, password, name, language);
        User finded = iClientService.findByEmail(email);

        if (finded.getEmail() != null) {

            validationFeedbackManager.createFeedBack(request, true, true, true,
                    validationFeedbackManager.USER_WITH_THIS_EMAIL_ALREADY_EXIST);

            return registrationPage;
        }

        Integer newRegisteredUserId = iClientService.userRegistration(forRegister);

        if (newRegisteredUserId != null) {

            HttpSession session = request.getSession(true);
            forRegister.setId(newRegisteredUserId);
            session.setAttribute(CURRENT_USER_IN_SYSTEM, forRegister);

            appDataInitializer.initItemCarousel(session);
            appDataInitializer.initUserCart(session, newRegisteredUserId);
            LOGGER.info("Registered user with email: " + email);

            return mainPage;

        } else {
            request.setAttribute(ERROR_INFO, "User with email: " + email + " not register.");
            LOGGER.error("User with email: " + email + " not register. ClientService response false.");

            return errorPage;
        }
    }
}
