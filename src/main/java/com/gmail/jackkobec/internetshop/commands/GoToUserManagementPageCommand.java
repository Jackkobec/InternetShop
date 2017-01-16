package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.persistence.model.UserType;
import com.gmail.jackkobec.internetshop.service.AdminServiceImpl;
import com.gmail.jackkobec.internetshop.service.IAdminService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jack on 15.01.2017.
 */
public class GoToUserManagementPageCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoToUserManagementPageCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String ERROR_INFO = "errorInfo";

    private static final String ALL_NOT_BANNED_USERS = "notBannedUsers";
    private static final String ALL_BANNED_USERS = "bannedUsers";

    IAdminService iAdminService = AdminServiceImpl.getAdminServiceImpl();


    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        User currentUserInSystem  = (User) request.getSession(false).getAttribute(CURRENT_USER_IN_SYSTEM);

        if(!currentUserInSystem.getUserType().equals(UserType.ADMIN)){
            request.setAttribute(ERROR_INFO, "User is not Admin.");

            return errorPage;
        }

        List<User> notBannedUsers = iAdminService.getAllNotBannedUsers();
        System.out.println("notBannedUsers" + notBannedUsers);
        List<User> bannedUsers = iAdminService.getAllBannedUsers();

        HttpSession session = request.getSession(false);
        session.setAttribute(ALL_NOT_BANNED_USERS, notBannedUsers);
        session.setAttribute(ALL_BANNED_USERS, bannedUsers);


        return "/WEB-INF/pages/user_management_page.jsp";
    }
}
