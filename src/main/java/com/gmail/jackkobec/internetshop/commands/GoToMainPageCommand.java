package com.gmail.jackkobec.internetshop.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 13.01.2017.
 */
public class GoToMainPageCommand implements ICommand {


    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return "/WEB-INF/pages/main_page.jsp";
    }
}
