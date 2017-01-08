package com.gmail.jackkobec.internetshop.filters;


import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientService;
import com.gmail.jackkobec.internetshop.service.IClientService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.jackkobec.internetshop.persistence.model.UserType.BANNED;

/**
 * Created by Jack on 08.01.2017.
 */
@WebFilter(urlPatterns = {"/*"})
public class BlockedUserFilter implements Filter {

    List<User> blacklist = new ArrayList<>();
    IClientService iClientService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        User banned = new User("forlabs@mail.ru", "2222");
        blacklist.add(banned);
        iClientService = new ClientService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);


        try {

            User currentUserInSystem = (User) session.getAttribute("currentUserInSystem");
            User finded = iClientService.findByEmail(currentUserInSystem.getEmail());

            if (finded.getEmail() != null) {

                if (finded.getUserType().equals(BANNED)) {

                    request.setAttribute("errorInfo", "You are in the black list!");
                    RequestDispatcher dispatcher = request.getServletContext().
                            getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.ERROR_PAGE));
                    dispatcher.forward(request, response);
                }
            }

            chain.doFilter(request, response);

        } catch (NullPointerException e) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
