package com.gmail.jackkobec.internetshop.persistence.dao;

import com.gmail.jackkobec.internetshop.persistence.model.Item;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface ItemDao extends CommonDao<Item, Integer> {

    List<Item> getItemsByCategoryId(final Integer categoryId);

    List<Item> initSixItemCarousel();

    List<Item> initUserCart(final Integer userId);

    Item getItemById(final Integer id);

    boolean addItemToCart(final Integer itemId, final Integer userId);

    boolean removeItemFromCart(final Integer itemId, final Integer userId);

    List<Item> getItemsFromOrderByOrderId(final Integer orderId);
}
