package com.gmail.jackkobec.internetshop.commands;



import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.servlet.PageManager;
import com.gmail.jackkobec.internetshop.validation.LoginFormValidation;
import com.gmail.jackkobec.internetshop.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 02.01.2017.
 */
public class UserRegistrtionCommand implements ICommand {

    Validator validator = new LoginFormValidation();
    ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromJNDI();
    //UserDao userDao = new UserDao(connectionManager);

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String login = request.getParameter("name");
        String pass = request.getParameter("pass");

        if (login.isEmpty() || pass.isEmpty()) {
            request.getSession().setAttribute("validation", false);
            request.setAttribute("validationMessage", "Login or Password is empty!");

            page = PageManager.getPageManager()
                    .getPage(PageManager.REGISTRATION_PAGE);
            return page;
        }

        if (!validator.loginValidator(login)) {
            request.getSession().setAttribute("validation", false);
            request.setAttribute("validationMessage", "Incorrect Login.Use chars A-Za-z0-9_ , length 3-15");
            page = PageManager.getPageManager()
                    .getPage(PageManager.REGISTRATION_PAGE);
            return page;
        }

        if (!validator.passwordValidator(pass)) {
            request.getSession().setAttribute("validation", false);
            request.setAttribute("validationMessage", "Incorrect Password.Use chars A-Za-z0-9_ , length 3-15");
            page = PageManager.getPageManager()
                    .getPage(PageManager.REGISTRATION_PAGE);
            return page;
        }
       // User forAdd = new User(login, pass);

//        if(userDao.addNewEntity(forAdd)){
//            request.getSession().setAttribute("registred", true);
//            request.setAttribute("info", "Registration successfully. Please sign in.");
//            page = "/index.jsp";
//        }

        return page;
    }
}
