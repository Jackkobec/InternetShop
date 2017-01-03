package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.servlet.PageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>NoCommand class for action if came incorrect command.
 * Created by Jack on 28.12.2016.
 */
public class NoCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(NoCommand.class);

    private static final String COMMAND = "command";
    private static final String ERROR_INFO = "errorInfo";

    /**
     *
     * @param request
     * @param response
     * @return page for forward in the servlet.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        request.setAttribute("errorInfo", "Ошибка");
        page = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);
        LOGGER.error("Incorrect command " + request.getParameter(COMMAND));

        return page;
    }
}
