package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.exceptions.UserNotFoundException;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.AdminServiceImpl;
import com.gmail.jackkobec.internetshop.service.IAdminService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jack on 16.01.2017.
 */
public class AddUserToBlockList implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(AddUserToBlockList.class);

    private static final String USER_ID = "userId";
    private static final String ALL_NOT_BANNED_USERS = "notBannedUsers";
    private static final String ALL_BANNED_USERS = "bannedUsers";
    private static final String ERROR_INFO = "errorInfo";

    private IAdminService iAdminService = AdminServiceImpl.getAdminServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userManagementPage = PageManager.getPageManager().getPage(PageManager.USER_MANAGEMENT_PAGE);
        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer userId = Integer.valueOf(request.getParameter(USER_ID));

        HttpSession session = request.getSession(false);
        List<User> notBannedUsers = (List<User>) session.getAttribute(ALL_NOT_BANNED_USERS);
        List<User> bannedUsers = (List<User>) session.getAttribute(ALL_BANNED_USERS);

        User forBlockList = null;

        try {
            forBlockList = notBannedUsers.stream().filter(user -> user.getId().equals(userId)).findFirst().orElseThrow(UserNotFoundException::new);
            forBlockList.setUserType(3);
        } catch (UserNotFoundException e) {
            LOGGER.warn("Повторная отправка формы или пользователя с id " + userId + " отсуствует в списке notBannedUsers!");

            return userManagementPage;
        }

        if (iAdminService.addUserToBlockListById(userId)) {
            notBannedUsers.remove(forBlockList);
            bannedUsers.add(forBlockList);

            session.setAttribute(ALL_NOT_BANNED_USERS, notBannedUsers);
            session.setAttribute(ALL_BANNED_USERS, bannedUsers);

            return userManagementPage;

        } else {
            request.setAttribute(ERROR_INFO, "Cat't add to the blocklist user with id = " + userId);

            return errorPage;
        }
    }
}
