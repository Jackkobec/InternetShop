package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.persistence.model.UserType;
import com.gmail.jackkobec.internetshop.service.ClientService;
import com.gmail.jackkobec.internetshop.service.IClientService;
import com.gmail.jackkobec.internetshop.service.LanguageService;
import com.gmail.jackkobec.internetshop.validation.ValidationFeedbackManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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

        String loginPage = PageManager.getPageManager().getPage(PageManager.INDEX_PAGE);

        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);
        final String languageSelection = request.getParameter(LANGUAGE_SELECTION);
        final String checkbox = request.getParameter(CHECKBOX);

        System.out.println(languageSelection);
        final String selectedLanguage = new LanguageService().getLanguageBySelect(Integer.parseInt(languageSelection));

        request.setAttribute("selectedLocale", selectedLanguage);

        if (validationFeedbackManager.preValidation(request, email, password, null)) return loginPage;

        IClientService iClientService = new ClientService();
        User finded = iClientService.findByEmailAndPassword(email, password);

        if (finded.getEmail() != null && finded.getPassword() != null && !(isUserInTheBlockList(finded))) {

            finded.setLanguage(selectedLanguage);

            HttpSession session = request.getSession(true);
            session.setAttribute("currentUserInSystem", finded);
            session.setAttribute("selectedLocale", selectedLanguage);

            Cookie cookie = new Cookie("userEmail", finded.getEmail());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            initItemCarousel(request);

            return PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);

        } else {
            validationFeedbackManager.createFeedBack(request, true, true, true, (!isUserInTheBlockList(finded))
                    ? validationFeedbackManager.USER_NOT_FOUND_OR_INCORRECT_DATA
                    : validationFeedbackManager.USER_IS_BANNED);

            return loginPage;
        }
    }

    private boolean isUserInTheBlockList(User user) {

        return user.getUserType() != null && user.getUserType().equals(UserType.BANNED);
    }

    private void initItemCarousel(HttpServletRequest request) {

        IClientService iClientService = new ClientService();
        List<Item> carouselItems = iClientService.initSixItemCarousel();
        System.out.println(carouselItems.size());
        //carouselItems.forEach(System.out::print);

        request.setAttribute("sixItemCarousel", carouselItems);
    }
}
