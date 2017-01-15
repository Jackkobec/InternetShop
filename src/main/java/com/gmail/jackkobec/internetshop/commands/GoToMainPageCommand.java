package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 13.01.2017.
 */
public class GoToMainPageCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoToMainPageCommand.class);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mainPage = PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);

        return mainPage;
    }
}
