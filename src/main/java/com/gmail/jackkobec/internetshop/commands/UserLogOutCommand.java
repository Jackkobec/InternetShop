package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>UserLogOutCommand class execute command for user log out.
 */
public class UserLogOutCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserLogOutCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";

    /**
     * Method execute command for user log out.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String indexPage = PageManager.getPageManager().getPage(PageManager.INDEX_PAGE);

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);

        session.invalidate();
        LOGGER.info("Log out user with email: " + currentUserInSystem.getEmail());

        return indexPage;
    }
}
