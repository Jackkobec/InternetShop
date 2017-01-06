package com.gmail.jackkobec.internetshop.commands;


import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
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

    private Validation validation = InputDataValidation.getInputDataValidation();
    private ValidationFeedbackManager validationFeedbackManager = ValidationFeedbackManager.getValidationFeedbackManager();

    ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromJNDI();
    //UserDao userDao = new UserDao(connectionManager);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        String registrationPage = PageManager.getPageManager().getPage(PageManager.REGISTRATION_PAGE);

        //mandatory
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");
        //optional
        String name = request.getParameter("name");

        if (preValidation(request, email, password, passwordConfirmation)) return registrationPage;

        return page;
    }

    private boolean preValidation(HttpServletRequest request, String email, String password, String passwordConfirmation) {

        boolean isEmailValid = validation.emailValidator(email);
        boolean isPasswordValid = validation.passwordValidator(password);

        if (email.isEmpty() && password.isEmpty()) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid, false,
                    validationFeedbackManager.EMAIL_AND_PASSWORD_IS_EMPTY);

            return true;
        }

        if (email.isEmpty() || password.isEmpty()) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid, false,
                    validationFeedbackManager.EMAIL_OR_PASSWORD_IS_EMPTY);

            return true;
        }

        if (!isEmailValid) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid, false,
                    validationFeedbackManager.ONLY_EMAIL);

            return true;
        }

        if (!isPasswordValid) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid, false,
                    validationFeedbackManager.ONLY_PASSWORD);

            return true;
        }

        if (!(password.equals(passwordConfirmation))) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid, true,
                    validationFeedbackManager.PASSWORDS_NOT_EQUALS_CONFIRM_PASSWORD);

            return true;
        }
        return false;
    }
}
