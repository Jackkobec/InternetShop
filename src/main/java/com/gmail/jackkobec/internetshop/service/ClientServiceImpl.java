package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.dao.ItemDao;
import com.gmail.jackkobec.internetshop.persistence.dao.OrderDao;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.factory.ConnectionManagerMode;
import com.gmail.jackkobec.internetshop.persistence.factory.DaoType;
import com.gmail.jackkobec.internetshop.persistence.factory.JdbcDaoFactory;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class ClientServiceImpl implements IClientService {
    public static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private static ClientServiceImpl clientServiceImpl;
    private UserDao userDao = (UserDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.USER_DAO);
    private ItemDao itemDao = (ItemDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.ITEM_DAO);
    private OrderDao orderDao = (OrderDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.ORDER_DAO);

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

    public User findByEmail(String email) {

        return userDao.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(final String email, final String password) {

        return userDao.findByEmailAndPassword(email, password);
    }

    public Integer userRegistration(User user) {

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

    @Override
    public Integer createNewOrder(Order order) {

        return orderDao.addNewEntity(order);
    }

    @Override
    public boolean addItemToOrder(final Integer orderId, final Integer itemId) {

        return orderDao.addItemToOrder(orderId, itemId);
    }

    @Override
    public boolean removeItemFromOrder(final Integer orderId, final Integer itemId) {

        return orderDao.removeItemFromOrder(orderId, itemId);
    }

    @Override
    public Order getOrderById(final Integer orderId) {

        return orderDao.getOneByID(orderId);
    }

    @Override
    public List<Item> getItemsFromOrderByOrderId(final Integer orderId) {

        return itemDao.getItemsFromOrderByOrderId(orderId);
    }

    @Override
    public boolean cancelOrder(final Integer orderId) {

        return orderDao.cancelOrder(orderId);
    }

    @Override
    public Integer updateOrderInfo(Order order) {

        return orderDao.updateEntityInfo(order);
    }

    @Override
    public boolean removeAllItemsFromUserCart(final Integer userId) {

        return itemDao.removeAllItemsFromUserCart(userId);
    }

    @Override
    public List<Order> getAllUserOrders(final Integer userId) {

        return orderDao.getAllUserOrders(userId);
    }

    @Override
    public boolean removeAllNotPaidOrders(final Integer userId) {

        return orderDao.removeAllNotPaidOrders(userId);
    }

    @Override
    public Integer updateUser(User user) {

        return userDao.updateEntityInfo(user);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException("Singleton cloning not supported.");
    }
}
