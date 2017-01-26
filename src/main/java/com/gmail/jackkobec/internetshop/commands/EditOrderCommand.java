package com.gmail.jackkobec.internetshop.commands;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 26.01.2017.
 */
public class EditOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(EditOrderCommand.class);



    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
