package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
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
     * Method execute command if command is incorrect or does't exist.
     *
     * @param request
     * @param response
     * @return page for forward in the controller.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        request.setAttribute(ERROR_INFO, "NoCommand");
        LOGGER.error("Incorrect command " + request.getParameter(COMMAND));

        return errorPage;
    }
}
