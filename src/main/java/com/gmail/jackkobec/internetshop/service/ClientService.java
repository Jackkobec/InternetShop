package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.dao.ItemDao;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.factory.ConnectionManagerMode;
import com.gmail.jackkobec.internetshop.persistence.factory.DaoType;
import com.gmail.jackkobec.internetshop.persistence.factory.JdbcDaoFactory;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class ClientService implements IClientService{
    public static final Logger LOGGER = LogManager.getLogger(ClientService.class);

    UserDao userDao = (UserDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.USER_DAO);
    ItemDao itemDao = (ItemDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.ITEM_DAO);

    public User findByEmail(String email){

        return userDao.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(final String email, final String password) {

        return userDao.findByEmailAndPassword(email, password);
    }

    public boolean userRegistration(User user) {

        return userDao.addNewEntity(user);
    }

    @Override
    public List<Item> getItemsByCategoryId(Integer categoryId) {

        return null;
    }
}
