package com.gmail.jackkobec.internetshop.persistence.db.utils;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>DataBaseUtils class for quick fill data base
 */
public class DataBaseUtils {
    public static final Logger LOGGER = LogManager.getLogger(DataBaseUtils.class);

    private ConnectionManager connectionManager;
    private Connection connection;

    public DataBaseUtils(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    public Connection getConnection() {

        if (connection != null) {
            return connection;
        }

        return this.connectionManager.getConnection();
    }

    /**
     * Method for auto fill data base from script file
     *
     * @param file
     * @return
     */
    public boolean executeSqlFile(File file) {

        try {
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                List<String> queries = Arrays.asList(content.split(";"))
                        .stream()
                        .map(s -> s.trim())
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());

                connection = getConnection();

                try {
                    for (String query : queries) {
                        try (Statement stmt = connection.createStatement()) {
                            stmt.execute(query.trim());
                        }
                    }
                } finally {
                    closeConnection(connection);
                }

                return true;
            }
        } catch (IOException e) {
            LOGGER.warn("SQL script file " + file.getAbsolutePath() + " not found");

            return false;
        } catch (SQLException e) {
            LOGGER.error("Errors while executing SQL script from file " + file.getAbsolutePath(), e);

            return false;
        }

        return false;
    }

    /**
     * Method for close connection
     *
     * @param connection
     */
    private void closeConnection(Connection connection) {

        try {
            connection.close();

        } catch (SQLException e) {
            LOGGER.error("Cannot close jdbc connection", e);
        }
    }

}
