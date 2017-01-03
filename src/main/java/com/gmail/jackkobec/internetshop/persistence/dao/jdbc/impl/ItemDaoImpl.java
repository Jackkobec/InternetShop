package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.ItemDao;
import com.gmail.jackkobec.internetshop.persistence.model.Item;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class ItemDaoImpl implements ItemDao {

    private ConnectionManager connectionManager;
    private Connection connection;

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
