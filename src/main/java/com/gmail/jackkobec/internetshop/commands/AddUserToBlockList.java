package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.exceptions.UserNotFoundException;
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
 * <p>AddUserToBlockList class execute command for add user to the block list.
 */
public class AddUserToBlockList implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(AddUserToBlockList.class);

    private static final String USER_ID = "userId";
    private static final String ALL_NOT_BANNED_USERS = "notBannedUsers";
    private static final String ALL_BANNED_USERS = "bannedUsers";
    private static final String ERROR_INFO = "errorInfo";

    private IAdminService iAdminService = AdminServiceImpl.getAdminServiceImpl();

    /**
     * Method execute command for add user to the block list.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
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
            forBlockList.setUserType(UserType.BANNED.getUserTypeId());
        } catch (UserNotFoundException e) {
            LOGGER.warn("Repeat form send or user with id " + userId + " not in the notBannedUsers!");

            return userManagementPage;
        }

        if (iAdminService.addUserToBlockListById(userId)) {
            notBannedUsers.remove(forBlockList);
            bannedUsers.add(forBlockList);

            session.setAttribute(ALL_NOT_BANNED_USERS, notBannedUsers);
            session.setAttribute(ALL_BANNED_USERS, bannedUsers);
            LOGGER.info("User id: " + userId + " added to the block list.");

            return userManagementPage;

        } else {
            request.setAttribute(ERROR_INFO, "Cat't add to the block list user with id = " + userId);
            LOGGER.error("Cat't add to the block list user with id = " + userId);

            return errorPage;
        }
    }
}
