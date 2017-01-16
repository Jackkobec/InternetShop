package com.gmail.jackkobec.internetshop.commands;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 16.01.2017.
 */
public class UpdateItemCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UpdateItemCommand.class);

    private static final String ITEM_ID = "item_id";
    private static final String ITEM_FOR_EDIT = "itemForEdit";

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
