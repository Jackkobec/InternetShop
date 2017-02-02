package com.gmail.jackkobec.internetshop.filters;


import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.gmail.jackkobec.internetshop.persistence.model.UserType.BANNED;

/**
 * <p>BlockedUserFilter class for filter blocked during session users.
 */
@WebFilter(urlPatterns = {"/*"})
public class BlockedUserFilter implements Filter {

    private static final String ERROR_INFO = "errorInfo";

    private IClientService iClientService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        iClientService = ClientServiceImpl.getClientServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        User currentUserInSystem = null;

        if (session != null) {
            currentUserInSystem = (User) session.getAttribute("currentUserInSystem");
        }

        if (currentUserInSystem != null) {

            User finded = iClientService.findByEmail(currentUserInSystem.getEmail());

            if (finded.getUserType().equals(BANNED)) {

                request.setAttribute(ERROR_INFO, "You are in the black list!");
                RequestDispatcher dispatcher = request.getServletContext().
                        getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.ERROR_PAGE));
                dispatcher.forward(request, response);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
