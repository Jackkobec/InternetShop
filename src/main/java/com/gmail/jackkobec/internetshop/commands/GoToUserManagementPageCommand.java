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
 * <p>GoToUserManagementPageCommand class execute command for go to the user management page.
 */
public class GoToUserManagementPageCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoToUserManagementPageCommand.class);

    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String ERROR_INFO = "errorInfo";

    private static final String ALL_NOT_BANNED_USERS = "notBannedUsers";
    private static final String ALL_BANNED_USERS = "bannedUsers";

    private IAdminService iAdminService = AdminServiceImpl.getAdminServiceImpl();

    /**
     * Method execute command for go to the user management page.
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

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);

        if (!currentUserInSystem.getUserType().equals(UserType.ADMIN)) {
            request.setAttribute(ERROR_INFO, "User is not Admin.");
            LOGGER.error("User is not Admin.");

            return errorPage;
        }

        List<User> notBannedUsers = iAdminService.getAllNotBannedUsers();
        List<User> bannedUsers = iAdminService.getAllBannedUsers();

        session.setAttribute(ALL_NOT_BANNED_USERS, notBannedUsers);
        session.setAttribute(ALL_BANNED_USERS, bannedUsers);
        LOGGER.info("GoToUserManagementPageCommand command.");

        return userManagementPage;
    }
}
