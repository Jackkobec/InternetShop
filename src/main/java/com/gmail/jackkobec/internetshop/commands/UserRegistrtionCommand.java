package com.gmail.jackkobec.internetshop.commands;


import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientService;
import com.gmail.jackkobec.internetshop.service.IClientService;
import com.gmail.jackkobec.internetshop.service.LanguageService;
import com.gmail.jackkobec.internetshop.validation.InputDataValidation;
import com.gmail.jackkobec.internetshop.validation.Validation;
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

    private static final String LANGUAGE_SELECTION = "language_selection";
    private ValidationFeedbackManager validationFeedbackManager = ValidationFeedbackManager.getValidationFeedbackManager();

    ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromJNDI();
    //UserDao userDao = new UserDao(connectionManager);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String page = null;
        String registrationPage = PageManager.getPageManager().getPage(PageManager.REGISTRATION_PAGE);
        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        //mandatory
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");
        //optional
        String name = request.getParameter("name");
        String language = new LanguageService()
                .getLanguageBySelect(Integer.parseInt(request.getParameter(LANGUAGE_SELECTION)));

        if (validationFeedbackManager.preValidation(request, email, password, passwordConfirmation))
            return registrationPage;


        IClientService iClientService = new ClientService();
        User forRegister = new User(email, password, name, language);

        User finded = iClientService.findByEmail(email);

        if (finded.getEmail() != null) {

            return registrationPage;
        } else {
        request.getSession().setAttribute("currentUserInSystem", forRegister);
            return (iClientService.userRegistrer(forRegister))
                    ? PageManager.getPageManager().getPage(PageManager.MAIN_PAGE)
                    : errorPage;

        }


    }

}
