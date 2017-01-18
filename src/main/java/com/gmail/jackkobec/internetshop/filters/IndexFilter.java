package com.gmail.jackkobec.internetshop.filters;


import com.gmail.jackkobec.internetshop.controller.PageManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Jack on 08.01.2017.
 */
@WebFilter(urlPatterns = {"/index.jsp"})
public class IndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        Object currentUserInSystemAttribute = null;

        if(session != null){
            currentUserInSystemAttribute = session.getAttribute("currentUserInSystem");
        }

        if (currentUserInSystemAttribute != null) {

            RequestDispatcher dispatcher = request.getServletContext().
                    getRequestDispatcher(PageManager.getPageManager().getPage(PageManager.MAIN_PAGE));
            dispatcher.forward(request, response);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
