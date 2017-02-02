package com.gmail.jackkobec.internetshop.persistence.dao;

import com.gmail.jackkobec.internetshop.persistence.model.Order;

import java.util.List;

/**
 * <p>OrderDao interface for declare Order - actions methods.
 */
public interface OrderDao extends CommonDao<Order, Integer> {

    Integer addNewOrder(Order entity);

    boolean addItemToOrder(final Integer orderId, final Integer itemId);

    boolean removeItemFromOrder(final Integer orderId, final Integer itemId);

    boolean cancelOrder (final Integer orderId);

    List<Order> getAllUserOrders(final Integer userId);

    boolean removeAllNotPaidOrders(final Integer userId);
}
