package com.gmail.jackkobec.internetshop.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>interface ICommand.
 * Created by Jack on 28.12.2016.
 */
public interface ICommand {

    String executeCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
