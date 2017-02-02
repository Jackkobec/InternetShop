package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.controller.PageManager;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
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

import static com.gmail.jackkobec.internetshop.controller.PageManager.*;

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

    private static final String CURRENT_USER_ORDER_ID = "currentUserOrderId";
    private static final String DATE_AND_TIME_FORMAT_PATTERN = "Date %1$td.%1$tm.%1$ty Time %1$tH:%1$tM:%1$tS";
    private static final String CURRENT_ORDER_FORMATTED_DATE = "currentOrderFormattedDate";
    private static final String CURRENT_USER_ORDER = "currentUserOrder";

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

            if (fromPage.equals(ORDER_PAGE)) {

                final Integer currentUserOrderId = Integer.valueOf(request.getParameter(CURRENT_USER_ORDER_ID));

                Order order = iClientService.getOrderById(currentUserOrderId);
                List<Item> itemsInOrder = iClientService.getItemsFromOrderByOrderId(order.getId());
                order.setItemList(itemsInOrder);

                String currentOrderFormattedDate = String.format(
                        DATE_AND_TIME_FORMAT_PATTERN, order.getOrderDateAndTime());

                request.setAttribute(CURRENT_ORDER_FORMATTED_DATE, currentOrderFormattedDate);
                request.setAttribute(CURRENT_USER_ORDER, order);
            }

            if (fromPage.equals(EDIT_ITEM_PAGE)) {

                new EditItemCommand().executeCommand(request, response);
            }

            if (fromPage.equals(CHOSEN_CATEGORY_PAGE)) {

                new GoCategoryCommand().executeCommand(request, response);
            }

            if (fromPage.equals(PAYMENT_PAGE)) {

                new PayOrderCommand().executeCommand(request, response);
            }

            return PageManager.getPageManager().getPage(fromPage);
        } else {
            request.setAttribute(ERROR_INFO, "Item with id: " + itemId + " not removed from cart. ClientService response false.");
            LOGGER.error("Item with id: " + itemId + " not removed from cart. ClientService response false.");

            return errorPage;
        }
    }
}
