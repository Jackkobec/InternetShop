package com.gmail.jackkobec.internetshop.commands;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 15.01.2017.
 */
public class MakeOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(MakeOrderCommand.class);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        return "/WEB-INF/pages/order_page.jsp";
    }
}
