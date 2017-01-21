package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface IClientService {

    User findByEmail(String email);

    User findByEmailAndPassword(final String email, final String password);

    Integer userRegistration(User user);

    List<Item> getItemsByCategoryId(final Integer categoryId);

    List<Item> initSixItemCarousel();

    List<Item> initUserCart(final Integer userId);

    boolean addItemToCart(final Integer itemId, final Integer userId);

    boolean removeItemFromCart(final Integer itemId, final Integer userId);

    Item getItemById(final Integer id);

    Integer createNewOrder(Order order);

    boolean addItemToOrder(final Integer orderId, final Integer itemId);

    boolean removeItemFromOrder(final Integer orderId, final Integer itemId);

    Order getOrderById(final Integer orderId);

    List<Item> getItemsFromOrderByOrderId(final Integer orderId);

    boolean cancelOrder (final Integer orderId);
}
