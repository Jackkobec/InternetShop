package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>GoToMainPageCommand class execute command for go to the main page.
 */
public class GoToMainPageCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoToMainPageCommand.class);

    /**
     * Method execute command for go to the main page.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);
    }
}
