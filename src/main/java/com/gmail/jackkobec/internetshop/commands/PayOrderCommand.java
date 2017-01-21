package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>PayOrderCommand class execute command for pay order.
 */
public class PayOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(PayOrderCommand.class);

    /**
     * Method execute command for pay order.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paymentPage = PageManager.getPageManager().getPage(PageManager.PAYMENT_PAGE);

        return paymentPage;
    }
}
