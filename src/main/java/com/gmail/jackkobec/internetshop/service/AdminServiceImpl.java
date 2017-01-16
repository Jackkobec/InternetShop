package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.dao.ItemDao;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.factory.ConnectionManagerMode;
import com.gmail.jackkobec.internetshop.persistence.factory.DaoType;
import com.gmail.jackkobec.internetshop.persistence.factory.JdbcDaoFactory;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class AdminServiceImpl implements IAdminService {
    public static final Logger LOGGER = LogManager.getLogger(AdminServiceImpl.class);

    private static AdminServiceImpl adminServiceImpl;
    private UserDao userDao = (UserDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.USER_DAO);
    private ItemDao itemDao = (ItemDao) JdbcDaoFactory.getJdbcDaoFactory(ConnectionManagerMode.FROM_JNDI).getDao(DaoType.ITEM_DAO);

    private AdminServiceImpl() {
    }

    /**
     * @return ClientServiceImpl instance.
     */
    public static AdminServiceImpl getAdminServiceImpl() {

        LOGGER.info("AdminServiceImpl");
        return (adminServiceImpl == null)
                ? adminServiceImpl = new AdminServiceImpl()
                : adminServiceImpl;
    }

    @Override
    public List<User> getAllUsers() {

        return userDao.getAll();
    }

    @Override
    public List<User> getAllNotBannedUsers() {

        return userDao.getAllNotBannedUsers();
    }

    @Override
    public List<User> getAllBannedUsers() {

        return userDao.getAllBannedUsers();
    }

    @Override
    public boolean addUserToBlockListById(final Integer id) {

        return userDao.addUserToBlockListById(id);
    }
}
