package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.model.User;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class UserDaoImpl implements UserDao {

    private ConnectionManager connectionManager;
    private Connection connection;

    private UserDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean addNewEntity(User entity) {
        return false;
    }


    @Override
    public boolean updateEntityInfo(User entity) {
        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getOneByID(Integer id) {
        return null;
    }
}
