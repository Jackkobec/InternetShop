package com.gmail.jackkobec.internetshop.dao.impl;

import com.gmail.jackkobec.internetshop.dao.ItemDao;
import com.gmail.jackkobec.internetshop.model.Item;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean addNewEntity(Item entity) {
        return false;
    }

    @Override
    public boolean updateEntityInfo(Item entity) {
        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) {
        return false;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item getOneByID(Integer id) {
        return null;
    }
}
