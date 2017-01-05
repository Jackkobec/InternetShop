package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.validation.InputDataValidation;
import com.gmail.jackkobec.internetshop.validation.ValidationFeedbackManager;
import com.gmail.jackkobec.internetshop.validation.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Jack on 04.01.2017.
 */
public class UserLoginCommand implements ICommand {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String LANGUAGE_SELECTION = "language_selection";
    private static final String CHECKBOX = "remember_me";

    private Validation validation = InputDataValidation.getInputDataValidation();
    private ValidationFeedbackManager validationFeedbackManager = ValidationFeedbackManager.getValidationFeedbackManager();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = PageManager.getPageManager().getPage(PageManager.INDEX_PAGE);

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String languageSelection = request.getParameter(LANGUAGE_SELECTION);
        String checkbox = request.getParameter(CHECKBOX);

        System.out.println(languageSelection);
        String local = (Integer.parseInt(languageSelection)) == 1 ? "ru_RU" : "en_EN";

//        HttpSession session = request.getSession(true);
//        session.setAttribute("selectedLocale", local);


        request.setAttribute("selectedLocale", local);

        boolean isEmailValid = validation.emailValidator(email);
        boolean isPasswordValid = validation.passwordValidator(password);

        if (email.isEmpty() && password.isEmpty()) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid,
                    validationFeedbackManager.EMAIL_AND_PASSWORD_IS_EMPTY);

            return page;
        }

        if (email.isEmpty() || password.isEmpty()) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid,
                    validationFeedbackManager.EMAIL_OR_PASSWORD_IS_EMPTY);

            return page;
        }

        if (!isEmailValid) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid,
                    validationFeedbackManager.ONLY_EMAIL);

            return page;
        }

        if (!isPasswordValid) {

            validationFeedbackManager.createFeedBack(request, isEmailValid, isPasswordValid,
                    validationFeedbackManager.ONLY_PASSWORD);

            return page;
        }


        return null;
    }
}
