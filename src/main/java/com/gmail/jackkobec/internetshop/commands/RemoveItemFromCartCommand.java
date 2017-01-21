package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>RemoveItemFromCartCommand class execute command for remove item by id from cart.
 */
public class RemoveItemFromCartCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(RemoveItemFromCartCommand.class);

    private static final String ITEM_ID = "item_id";
    private static final String FROM_PAGE = "from_page";
    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";

    private static final String SUMMARY_CART_PRICE = "summaryCartPrice";
    private static final String CURRENT_USER_CART = "currentUserCart";
    private static final String ERROR_INFO = "errorInfo";

    IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for remove item by id from cart.
     *
     * @param request
     * @param response
     * @return page for Controller
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorPage = PageManager.getPageManager().getPage(PageManager.ERROR_PAGE);

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));
        final String fromPage = request.getParameter(FROM_PAGE);

        HttpSession session = request.getSession(false);
        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);
        List<Item> currentUserCart = (List<Item>) session.getAttribute(CURRENT_USER_CART);

        BigDecimal summaryCartPrice = new BigDecimal(0.00);

        for (Item item : currentUserCart) {
            summaryCartPrice = summaryCartPrice.add(item.getItemPrice());
        }
        //correct decision
       /* currentUserCart.removeIf(i -> i.getId().equals(itemId));*/

        //temporally decision
        for (Item item : currentUserCart) {

            if (item.getId().equals(itemId)) {
                currentUserCart.remove(item);
                summaryCartPrice = summaryCartPrice.subtract(item.getItemPrice());
                break;
            }
        }

        if (iClientService.removeItemFromCart(itemId, currentUserInSystem.getId())) {

            session.setAttribute(SUMMARY_CART_PRICE, summaryCartPrice);
            session.setAttribute(CURRENT_USER_CART, currentUserCart);

            return PageManager.getPageManager().getPage(fromPage);
        } else {
            request.setAttribute(ERROR_INFO, "Item with id: " + itemId + " not removed from cart. ClientService response false.");
            LOGGER.error("Item with id: " + itemId + " not removed from cart. ClientService response false.");

            return errorPage;
        }
    }
}
