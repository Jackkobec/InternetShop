package com.gmail.jackkobec.internetshop.commands;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 12.01.2017.
 */
public class GoCategoryCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(GoCategoryCommand.class);

    private static final String CATEGORY_ID = "category_id";

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Integer categoryID = (Integer) request.getAttribute(CATEGORY_ID);




        return "/WEB-INF/pages/chosen_category_page.jsp";
    }
}
