package com.gmail.jackkobec.internetshop.persistence.dao;

import com.gmail.jackkobec.internetshop.persistence.model.Item;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface ItemDao extends CommonDao<Item, Integer>{

    List<Item> getItemsByCategoryId(final Integer categoryId);
}
