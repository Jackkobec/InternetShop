package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.persistence.model.UserType;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
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

/**
 * <p>UserLoginCommand class execute command for user sign in.
 */
public class UserLoginCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserLoginCommand.class);

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String LANGUAGE_SELECTION = "language_selection";
    private static final String CHECKBOX = "remember_me";
    private static final String SELECTED_LOCALE = "selectedLocale";

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String SIX_ITEM_CAROUSEL = "sixItemCarousel";
    private static final String CURRENT_USER_CART = "currentUserCart";
    private static final String SUMMARY_CART_PRICE = "summaryCartPrice";
    private static final String USER_EMAIL_COOKIE = "userEmail";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();
    private ValidationFeedbackManager validationFeedbackManager = ValidationFeedbackManager.getValidationFeedbackManager();
    private AppDataInitializer appDataInitializer = AppDataInitializer.getAppDataInitializer();

    /**
     * Method execute command for user sign in.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginPage = PageManager.getPageManager().getPage(PageManager.INDEX_PAGE);
        String mainPage = PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);

        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);
        final String languageSelection = request.getParameter(LANGUAGE_SELECTION);
        final String checkbox = request.getParameter(CHECKBOX);
        final String selectedLanguage = new LanguageService().getLanguageBySelect(Integer.parseInt(languageSelection));

        request.setAttribute(SELECTED_LOCALE, selectedLanguage);

        if (validationFeedbackManager.preValidation(request, email, password, null)) return loginPage;

        User finded = iClientService.findByEmailAndPassword(email, password);

        if (finded.getEmail() != null && finded.getPassword() != null && !(isUserInTheBlockList(finded))) {

            finded.setLanguage(selectedLanguage);

            HttpSession session = request.getSession(true);
            session.setAttribute(CURRENT_USER_IN_SYSTEM, finded);
            session.setAttribute(SELECTED_LOCALE, selectedLanguage);

            Cookie cookie = new Cookie(USER_EMAIL_COOKIE, finded.getEmail());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            appDataInitializer.initItemCarousel(session);
            appDataInitializer.initUserCart(session, finded.getId());
            LOGGER.info("Sign in user with email: " + email);

            return mainPage;

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
}
