package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 16.01.2017.
 */
public class EditItemCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(EditItemCommand.class);

    private static final String ITEM_ID = "item_id";
    private static final String ITEM_EDIT_MODE = "itemEditMode";

    private static final String ITEM_FOR_EDIT = "itemForEdit";
    private static final String FORM_ACTION = "formAction";

    private static final String DEFAULT_FORM_ACTION = "additem";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String itemForEditParameter = request.getParameter(ITEM_ID);
        String itemEditModeParameter = request.getParameter(ITEM_EDIT_MODE);

        if (itemForEditParameter != null && itemEditModeParameter != null) {

            final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));
            final String itemEditMode = request.getParameter(ITEM_EDIT_MODE);

            Item itemForEdit = iClientService.getItemById(itemId);

            request.setAttribute(ITEM_FOR_EDIT, itemForEdit);
            request.setAttribute(FORM_ACTION, itemEditMode);
        } else {

            request.setAttribute(FORM_ACTION, DEFAULT_FORM_ACTION);
        }

        return "/WEB-INF/pages/edit_item_page.jsp";
    }
}
