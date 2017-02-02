package com.gmail.jackkobec.internetshop.persistence.connection.pool;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>ConnectionManager class for create connection to the data base from DataSource
 * We can receive DataSource from JNDI or properties file and get connection
 * from the DataSource after that.
 * <p>
 * Created by Jack on 28.12.2016.
 */
public class ConnectionManager {
    public static final Logger LOGGER = LogManager.getLogger(ConnectionManager.class);

    private static final String PATH_TO_DATA_BASE_PROPERTIES = "src/main/resources/database.properties";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USER = "jdbc.user";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String MY_JNDI_NAME = "jdbc/TestDB";

    private DataSource dataSource;

    private ConnectionManager() {
    }

    /**
     * @return a connection to the data base from DataSource.
     */
    public Connection getConnection() {

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Cannot create connection to the database", e);
        }

        return null;
    }

    /**
     * Creates ConnectionManager with DataSource from JNDI.
     *
     * @return ConnectionManager instance.
     */
    public static synchronized ConnectionManager getConnectionManagerFromJNDI() {

        try {
            //for correct work with JNDI
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");

            DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/" + MY_JNDI_NAME);

            ConnectionManager connectionManager = new ConnectionManager();
            connectionManager.dataSource = ds;

            return connectionManager;

        } catch (NamingException e) {
            LOGGER.error("Can't create InitialContext", e);

            return null;
        }
    }

    /**
     * Creates ConnectionManager with DataSource from properties file.
     *
     * @return ConnectionManager instance.
     */
    public static synchronized ConnectionManager getConnectionManagerFromPropertiesFile() {

        ConnectionManager connectionManager = new ConnectionManager();
        MysqlDataSource mySqlDataSource = new MysqlDataSource();

        Properties props = loadProperties();

        mySqlDataSource.setUrl(props.getProperty(JDBC_URL));
        mySqlDataSource.setUser(props.getProperty(JDBC_USER));
        mySqlDataSource.setPassword(props.getProperty(JDBC_PASSWORD));

        connectionManager.dataSource = mySqlDataSource;

        return connectionManager;
    }

    /**
     * Load data base properties from the file.
     *
     * @return Properties.
     */
    private static Properties loadProperties() {

        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream(new File(PATH_TO_DATA_BASE_PROPERTIES))) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Cannot load database.properties from path: " + PATH_TO_DATA_BASE_PROPERTIES, e);
        }

        return properties;
    }
}
