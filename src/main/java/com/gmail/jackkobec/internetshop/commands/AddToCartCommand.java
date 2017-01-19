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
 * <p>AddToCartCommand class execute command for add new item to the cart.
 */
public class AddToCartCommand implements ICommand {
    public static final Logger LOGGER = LogManager.getLogger(AddToCartCommand.class);

    private static final String ITEM_ID = "item_id";
    private static final String CURRENT_USER_IN_SYSTEM = "currentUserInSystem";
    private static final String CURRENT_ITEM_FOR_SHOW = "currentItemForShow";
    private static final String SUMMARY_CART_PRICE = "summaryCartPrice";
    private static final String CURRENT_USER_CART = "currentUserCart";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    /**
     * Method execute command for add new item to the cart.
     * @param request
     * @param response
     * @return page for Controller.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemPage = PageManager.getPageManager().getPage(PageManager.ITEM_PAGE);

        final Integer itemId = Integer.valueOf(request.getParameter(ITEM_ID));

        HttpSession session = request.getSession(false);

        User currentUserInSystem = (User) session.getAttribute(CURRENT_USER_IN_SYSTEM);
        List<Item> currentUserCart = (List<Item>) session.getAttribute("currentUserCart");
        Item currentItemForShow = (Item) session.getAttribute(CURRENT_ITEM_FOR_SHOW);

        BigDecimal summaryCartPrice = currentItemForShow.getItemPrice();

        if (currentUserCart.size() != 0) {
//            BigDecimal summaryCartPrice = currentUserCart.stream().map(Item::getItemPrice).reduce(BigDecimal::add).get();
            for (Item item : currentUserCart) {
                summaryCartPrice = summaryCartPrice.add(item.getItemPrice());
            }

            session.setAttribute(SUMMARY_CART_PRICE, summaryCartPrice);
        } else if (currentUserCart.size() == 0) {

            session.setAttribute(SUMMARY_CART_PRICE, currentItemForShow.getItemPrice());
        }

        currentUserCart.add(currentItemForShow);
        iClientService.addItemToCart(itemId, currentUserInSystem.getId());
        session.setAttribute(CURRENT_USER_CART, currentUserCart);
        LOGGER.info("Item id: " + itemId + " added to the cart.");

        return itemPage;
    }
}
