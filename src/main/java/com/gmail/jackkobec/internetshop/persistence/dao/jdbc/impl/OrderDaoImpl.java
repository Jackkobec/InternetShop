package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.dao.OrderDao;
import com.gmail.jackkobec.internetshop.persistence.model.Order;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean addNewEntity(Order entity) {
        return false;
    }

    @Override
    public boolean updateEntityInfo(Order entity) {
        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order getOneByID(Integer id) {
        return null;
    }
}
