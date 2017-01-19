package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 19.01.2017.
 */
public class CancelOrderCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(CancelOrderCommand.class);

    private static final String CURRENT_USER_ORDER_ID = "currentUserOrderId";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Integer currentUserOrderId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_ID));

        Order orderForCancel = iClientService.getOrderById(currentUserOrderId);

        List<Item> itemsInOrder = new ArrayList<>();

        System.out.println(iClientService.cancelOrder(orderForCancel.getId()));




        return PageManager.getPageManager().getPage(PageManager.MAIN_PAGE);
    }
}
