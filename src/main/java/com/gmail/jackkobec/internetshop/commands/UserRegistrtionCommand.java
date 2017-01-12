package com.gmail.jackkobec.internetshop.commands;


import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientService;
import com.gmail.jackkobec.internetshop.service.IClientService;
import com.gmail.jackkobec.internetshop.service.LanguageService;
import com.gmail.jackkobec.internetshop.validation.ValidationFeedbackManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 02.01.2017.
 */
public class UserRegistrtionCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserRegistrtionCommand.class);

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_CONFIRMATION = "password_confirmation";
    private static final String NAME = "name";
    private static final String LANGUAGE_SELECTION = "language_selection";

    private ValidationFeedbackManager validationFeedbackManager = ValidationFeedbackManager.getValidationFeedbackManager();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String registrationPage = PageManager.getPageManager().getPage(PageManager.REGISTRATION_PAGE);
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

        IClientService iClientService = new ClientService();
        User forRegister = new User(email, password, name, language);
        User finded = iClientService.findByEmail(email);

        if (finded.getEmail() != null) {

            validationFeedbackManager.createFeedBack(request, true, true, true, validationFeedbackManager.USER_WITH_THIS_EMAIL_ALREADY_EXIST);
            return registrationPage;
        } else {
            request.getSession().setAttribute("currentUserInSystem", forRegister);

            return (iClientService.userRegistration(forRegister))
                    ? PageManager.getPageManager().getPage(PageManager.MAIN_PAGE)
                    : errorPage;

        }
    }
}
