package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jack on 16.01.2017.
 */
public class GoToItemManagementPage implements ICommand {



    IClientService iClientService = ClientServiceImpl.getClientServiceImpl();


    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Item> itemListByCategory = iClientService.getItemsByCategoryId(1);
        request.setAttribute("itemListByCategory", itemListByCategory);

        return "/WEB-INF/pages/item_management_page.jsp";
    }
}
