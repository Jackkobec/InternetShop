package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 30.12.2016.
 */
public class RegisterCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String registrationPage = PageManager.getPageManager().getPage(PageManager.REGISTRATION_PAGE);
        LOGGER.info("RegisterCommand");

        return registrationPage;
    }
}
