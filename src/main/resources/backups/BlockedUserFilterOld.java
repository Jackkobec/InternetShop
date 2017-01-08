/*
package com.gmail.jackkobec.internetshop.filters;


import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

*/
/**
 * Created by Jack on 08.01.2017.
 *//*

@WebFilter(urlPatterns = {"*/
/*"})
public class BlockedUserFilterOld implements Filter {

    List<User> blacklist = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        User banned = new User("forlabs@mail.ru", "2222");
        blacklist.add(banned);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);


        try {
//            Cookie[] cookies = req.getCookies();
//            for (int i = 0; i < cookies.length; i++) {
//
//                Cookie cookie = cookies[i];
//                System.out.println("cocie " + i + " " + cookie.getValue());
//            }
//            for (User user2 : blacklist) {
//                if (Stream.of(cookies).anyMatch(c -> c.getValue().equals(user2.getEmail()))) {
//                    request.setAttribute("errorInfo", "You are in the black list!");
//                    RequestDispatcher dispatcher = request.getServletContext().
//                            getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.ERROR_PAGE));
//                    dispatcher.forward(request, response);
//                }
//            }
//
//            User logined = (User) session.getAttribute("logined");
//            for (User user : blacklist) {
//                if (user.getEmail().equals(logined.getEmail())) {
//                    request.setAttribute("errorInfo", "You are in the black list!");
//                    RequestDispatcher dispatcher = request.getServletContext().
//                            getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.ERROR_PAGE));
//                    dispatcher.forward(request, response);
//                }
//            }

            //
            Cookie[] cookies = req.getCookies();
            User logined = (User) session.getAttribute("logined");
            for (int i = 0; i < cookies.length; i++) {

                Cookie cookie = cookies[i];
                System.out.println("cocie " + i + " " + cookie.getValue());
            }
            for (User user2 : blacklist) {
                if (Stream.of(cookies).anyMatch(c -> c.getValue().equals(user2.getEmail()))
                        || user2.getEmail().equals(logined.getEmail())) {
                    request.setAttribute("errorInfo", "You are in the black list!");
                    RequestDispatcher dispatcher = request.getServletContext().
                            getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.ERROR_PAGE));
                    dispatcher.forward(request, response);
                }
            }

//            User logined = (User) session.getAttribute("logined");
//            for (User user : blacklist) {
//                if (user.getEmail().equals(logined.getEmail())) {
//                    request.setAttribute("errorInfo", "You are in the black list!");
//                    RequestDispatcher dispatcher = request.getServletContext().
//                            getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.ERROR_PAGE));
//                    dispatcher.forward(request, response);
//                }
//            }
            //
            chain.doFilter(request, response);

        } catch (NullPointerException e) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
*/
