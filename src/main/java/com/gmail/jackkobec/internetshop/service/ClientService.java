package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.factory.ConnectionManagerMode;
import com.gmail.jackkobec.internetshop.persistence.factory.DaoType;
import com.gmail.jackkobec.internetshop.persistence.factory.JdbcDaoFactory;
import com.gmail.jackkobec.internetshop.persistence.model.User;

/**
 * Created by Jack on 28.12.2016.
 */
public class ClientService implements IClientService{

    UserDao userDao = JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.USER_DAO);


    public User findByEmail(String email){

        return userDao.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {

        return userDao.findByEmailAndPassword(email, password);
    }

    public boolean userRegistrer(User user) {

        return userDao.addNewEntity(user);
    }
}
