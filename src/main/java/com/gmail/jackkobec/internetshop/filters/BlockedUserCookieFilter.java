package com.gmail.jackkobec.internetshop.filters;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.AdminServiceImpl;
import com.gmail.jackkobec.internetshop.service.IAdminService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * <p>BlockedUserCookieFilter class for filter blocked users by cookie.
 */
@WebFilter(urlPatterns = {"/*"})
public class BlockedUserCookieFilter implements Filter {

    private static final String ERROR_INFO = "errorInfo";

    private IAdminService iAdminService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        iAdminService = AdminServiceImpl.getAdminServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        List<User> bannedUsers = iAdminService.getAllBannedUsers();

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {

            for (int i = 0; i < cookies.length; i++) {

                Cookie cookie = cookies[i];

                if (bannedUsers.stream().anyMatch(el -> el.getEmail().equals(cookie.getValue()))) {

                    request.setAttribute(ERROR_INFO, "You are in the black list!");
                    RequestDispatcher dispatcher = request.getServletContext().
                            getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.ERROR_PAGE));
                    dispatcher.forward(request, response);
                }
                System.out.println("cookie " + i + " " + cookie.getValue());
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
