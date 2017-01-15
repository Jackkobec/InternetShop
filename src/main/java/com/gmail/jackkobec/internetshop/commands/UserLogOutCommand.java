package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 08.01.2017.
 */
public  class UserLogOutCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserLogOutCommand.class);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String indexPage = PageManager.getPageManager().getPage(PageManager.INDEX_PAGE);
        
        request.getSession(false).invalidate();

        return indexPage;
    }
}
