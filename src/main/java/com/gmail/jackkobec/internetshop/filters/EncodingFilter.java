package com.gmail.jackkobec.internetshop.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p>EncodingFilter class for use  utf-8 encoding in the jsp pages.
 * Created by Jack on 03.01.2017.
 */
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    public void destroy() {
        // nothing todo
    }
}