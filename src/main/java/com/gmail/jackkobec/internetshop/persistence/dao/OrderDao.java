package com.gmail.jackkobec.internetshop.persistence.dao;

import com.gmail.jackkobec.internetshop.persistence.model.Order;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface OrderDao extends CommonDao<Order, Integer> {

    Integer addNewOrder(Order entity);

    boolean addItemToOrder(final Integer orderId, final Integer itemId);

    boolean removeItemFromOrder(final Integer orderId, final Integer itemId);

    boolean cancelOrder (final Integer orderId);

    List<Order> getAllUserOrders(final Integer userId);

    boolean removeAllNotPaidOrders(final Integer userId);
}
