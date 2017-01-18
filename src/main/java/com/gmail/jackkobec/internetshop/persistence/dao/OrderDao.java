package com.gmail.jackkobec.internetshop.persistence.dao;

import com.gmail.jackkobec.internetshop.persistence.model.Order;

/**
 * Created by Jack on 28.12.2016.
 */
public interface OrderDao extends CommonDao<Order, Integer> {

    Integer addNewOrder(Order entity);

    boolean addItemToOrder(final Integer orderId, final Integer itemId);

    boolean removeItemFromOrder(final Integer orderId, final Integer itemId);
}
