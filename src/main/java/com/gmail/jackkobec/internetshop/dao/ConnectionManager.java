package com.gmail.jackkobec.internetshop.dao;

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
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by Jack on 28.12.2016.
 */
public class ConnectionManager {
    public static final Logger LOGGER = LogManager.getLogger(ConnectionManager.class);

    private final static String PATH_TO_PROPERTIES = "src/main/resources/database.properties";
    private static final java.lang.String JDBC_URL = "jdbc.url";
    private static final java.lang.String JDBC_USER = "jdbc.user";
    private static final java.lang.String JDBC_PASSWORD = "jdbc.password";

    private DataSource dataSource;

    public ConnectionManager() {
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Cannot create connection to the database", e);
        }

        return null;
    }


    public static ConnectionManager getConnectionManagerFromJNDI(String name) {
        try {
//            Context initContext = new InitialContext();
//            Context envContext  = (Context)initContext.lookup("java:/comp/env");
//            DataSource ds = (DataSource)envContext.lookup(name);
//            ConnectionManager connManager = new ConnectionManager();
//            connManager.dataSource = ds;




            /*Hashtable env = new Hashtable(5);
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "weblogic.jndi.WLInitialContextFactory");
            env.put(Context.PROVIDER_URL,
                    "t3://weblogicServer:7001");
            Context initContext = new InitialContext(env);*/
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
//            System.setProperty(Context.URL_PKG_PREFIXES,
//                    "org.apache.naming");

//            Context initContext = new InitialContext();
//            //DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/mymysql");
//            DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/mymysql");
//

//            Context initContext = new InitialContext();
//            Context envContext  = (Context)initContext.lookup("java:comp/env");
//            DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");

            DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TestDB");

            ConnectionManager connectionManager = new ConnectionManager();
            connectionManager.dataSource = ds;
            return connectionManager;
        } catch (NamingException e) {
            LOGGER.error("Cannot create InitialContext", e);
            return null;
        }
    }


    public static ConnectionManager getConnectionManagerFromPropertiesFile(String filename) {
        ConnectionManager connectionManager = new ConnectionManager();
        MysqlDataSource mysqlDs = new MysqlDataSource();

        Properties props = loadProperties();

        mysqlDs.setUrl(props.getProperty(JDBC_URL));
        mysqlDs.setUser(props.getProperty(JDBC_USER, "root"));
        mysqlDs.setPassword(props.getProperty(JDBC_PASSWORD, "root"));


        connectionManager.dataSource = mysqlDs;
        return connectionManager;
    }



    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream(new File(PATH_TO_PROPERTIES))) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.warn("Cannot load database.properties", e);
        }

        return properties;
    }


}
