package com.gmail.jackkobec.internetshop.commands;


import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 01.01.2017.
 */
public class UserInfoCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(UserInfoCommand.class);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page;
        Integer idForFind = Integer.valueOf(request.getParameter("id"));//берем с формы

        ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromJNDI();
        //User finded = new UserDaoJdbcImpl(connectionManager).getOneByID(idForFind);

        //request.setAttribute("user", finded);
        page = "/WEB-INF/pages/user-info.jsp";
        //System.out.println(finded);
        LOGGER.info("UserInfoCommand");
        return page;
    }
}

