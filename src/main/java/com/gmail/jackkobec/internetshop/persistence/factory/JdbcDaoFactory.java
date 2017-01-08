package com.gmail.jackkobec.internetshop.persistence.factory;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl.UserDaoJdbcImpl;

/**
 * Created by Jack on 07.01.2017.
 */
public class JdbcDaoFactory implements IDaoFactory {

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
    public UserDao getDao(DaoType daoType) {

        switch (daoType) {
            case USER_DAO:
                return UserDaoJdbcImpl.getUserDaoJdbc(connectionManager);
        }

        return null;
    }
}