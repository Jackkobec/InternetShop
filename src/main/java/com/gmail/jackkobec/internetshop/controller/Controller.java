package com.gmail.jackkobec.internetshop.controller;


import com.gmail.jackkobec.internetshop.commands.ICommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Application controller for processing requests.
 * Created by Jack on 28.12.2016.
 */
@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(Controller.class);

    private ControllerHelper controllerHelper = ControllerHelper.getControllerHelper();

    public Controller() {
        super();
    }

    /**
     * Process incoming requests.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = null;

        try {
            ICommand command = controllerHelper.getCommand(request);
            page = command.executeCommand(request, response);

        } catch (ServletException e) {
            request.setAttribute("errorInfo", "ServletException");
            LOGGER.error(e.getMessage());
            page = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);
        } catch (IOException e) {
            request.setAttribute("errorInfo", "IOException");
            LOGGER.error(e.getMessage());
            page = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);

//        response.sendRedirect("/index_old.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Calls processRequest method.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Calls processRequest method.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
