package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
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
 * Created by Jack on 04.01.2017.
 */
public class UserLoginCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserLoginCommand.class);

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String LANGUAGE_SELECTION = "language_selection";
    private static final String CHECKBOX = "remember_me";

    private ValidationFeedbackManager validationFeedbackManager = ValidationFeedbackManager.getValidationFeedbackManager();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        String loginPage = PageManager.getPageManager().getPage(PageManager.INDEX_PAGE);

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String languageSelection = request.getParameter(LANGUAGE_SELECTION);
        String checkbox = request.getParameter(CHECKBOX);

        System.out.println(languageSelection);
        String local = (Integer.parseInt(languageSelection)) == 1 ? "ru_RU" : "en_EN";

//        HttpSession session = request.getSession(true);
//        session.setAttribute("selectedLocale", local);

        request.setAttribute("selectedLocale", local);

        if (validationFeedbackManager.preValidation(request, email, password, null)) return loginPage;

        return "/basepage.jsp";
    }
}
