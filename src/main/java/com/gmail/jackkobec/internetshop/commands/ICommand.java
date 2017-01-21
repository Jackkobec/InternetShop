package com.gmail.jackkobec.internetshop.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Interface ICommand for provide executeCommand method.
 */
public interface ICommand {

    /**
     * Method for execute commands.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    String executeCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
