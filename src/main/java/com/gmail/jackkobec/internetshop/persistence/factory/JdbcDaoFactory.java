package com.gmail.jackkobec.internetshop.persistence.factory;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.CommonDao;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl.ItemDaoJdbcImpl;
import com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl.OrderDaoImpl;
import com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl.UserDaoJdbcImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Jack on 07.01.2017.
 */
public class JdbcDaoFactory implements IDaoFactory {
    public static final Logger LOGGER = LogManager.getLogger(JdbcDaoFactory.class);

    private static JdbcDaoFactory jdbcDaoFactory;
    private static ConnectionManager connectionManager;


    /**
     * @return JdbcDaoFactory instance.
     */
    public static JdbcDaoFactory getJdbcDaoFactory(ConnectionManagerMode connectionManagerMode) {

        setConnectionManager(connectionManagerMode);

        return (jdbcDaoFactory == null)
                ? jdbcDaoFactory = new JdbcDaoFactory()
                : jdbcDaoFactory;
    }

    private static ConnectionManager setConnectionManager(ConnectionManagerMode connectionManagerMode) {

        switch (connectionManagerMode) {
            case FROM_JNDI:
                return connectionManager = ConnectionManager.getConnectionManagerFromJNDI();
            case FROM_PROPERTIES_FILE:
                return connectionManager = ConnectionManager.getConnectionManagerFromPropertiesFile();
        }

        return null;
    }

    @Override
    public CommonDao getDao(DaoType daoType) {

        switch (daoType) {
            case USER_DAO:
                return UserDaoJdbcImpl.getUserDaoJdbc(connectionManager);
            case ITEM_DAO:
                return ItemDaoJdbcImpl.getItemDaoJdbc(connectionManager);
            case ORDER_DAO:
                return OrderDaoImpl.getOrderDaoImpl(connectionManager);
        }

        return null;
    }
}