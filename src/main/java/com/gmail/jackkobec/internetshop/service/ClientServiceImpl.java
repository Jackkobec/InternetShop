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
public class ClientServiceImpl implements IClientService{
    public static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private static ClientServiceImpl clientServiceImpl;
    private UserDao userDao = (UserDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.USER_DAO);
    private ItemDao itemDao = (ItemDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.ITEM_DAO);

    private ClientServiceImpl() {
    }

    /**
     * @return ClientServiceImpl instance.
     */
    public static synchronized ClientServiceImpl getClientServiceImpl() {

        LOGGER.info("getClientServiceImpl");
        return (clientServiceImpl == null)
                ? clientServiceImpl = new ClientServiceImpl()
                : clientServiceImpl;
    }

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
    public List<Item> getItemsByCategoryId(final Integer categoryId) {

        return itemDao.getItemsByCategoryId(categoryId);
    }

    @Override
    public List<Item> initSixItemCarousel() {

        return itemDao.initSixItemCarousel();
    }

    @Override
    public List<Item> initUserCart(final Integer userId) {

        return itemDao.initUserCart(userId);
    }

    @Override
    public boolean addItemToCart(final Integer itemId, final Integer userId) {

        return itemDao.addItemToCart(itemId, userId);
    }

    @Override
    public boolean removeItemFromCart(final Integer itemId, final Integer userId) {

        return itemDao.removeItemFromCart(itemId, userId);
    }

    @Override
    public Item getItemById(final Integer id) {

        return itemDao.getItemById(id);
    }
}
