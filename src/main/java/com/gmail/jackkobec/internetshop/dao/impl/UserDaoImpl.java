package com.gmail.jackkobec.internetshop.dao.impl;

import com.gmail.jackkobec.internetshop.dao.UserDao;
import com.gmail.jackkobec.internetshop.model.User;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class UserDaoImpl implements UserDao {

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
