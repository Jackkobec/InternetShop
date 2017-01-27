package com.gmail.jackkobec.internetshop.commands;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.service.ClientServiceImpl;
import com.gmail.jackkobec.internetshop.service.IClientService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jack on 21.01.2017.
 */
public class AppDataInitializer {

    private static AppDataInitializer appDataInitializer;

    private static final String SIX_ITEM_CAROUSEL = "sixItemCarousel";
    private static final String CURRENT_USER_CART = "currentUserCart";
    private static final String SUMMARY_CART_PRICE = "summaryCartPrice";
    private static final String CURRENT_USER_ORDERS = "currentUserOrders";

    private IClientService iClientService = ClientServiceImpl.getClientServiceImpl();

    private AppDataInitializer() {
    }

    /**
     * @return AppDataInitializer instance.
     */
    public static synchronized AppDataInitializer getAppDataInitializer() {

        return (appDataInitializer == null)
                ? appDataInitializer = new AppDataInitializer()
                : appDataInitializer;
    }

    public void initItemCarousel(HttpSession session) {

        List<Item> carouselItems = iClientService.initSixItemCarousel();
        session.setAttribute(SIX_ITEM_CAROUSEL, carouselItems);
    }

    public void initUserCart(HttpSession session, final Integer userId) {

        List<Item> currentUserCart = iClientService.initUserCart(userId);
        BigDecimal summaryCartPrice = new BigDecimal(0.00);

        for (Item item : currentUserCart) {
            summaryCartPrice = summaryCartPrice.add(item.getItemPrice());
        }

        session.setAttribute(CURRENT_USER_CART, currentUserCart);
        session.setAttribute(SUMMARY_CART_PRICE, summaryCartPrice);
    }

    public void initUserOrders(HttpSession session, final Integer userId){

        List<Order> userOrders = iClientService.getAllUserOrders(userId);

        session.setAttribute(CURRENT_USER_ORDERS, userOrders);
    }
}
