package com.gmail.jackkobec.internetshop.dao.utils;

import com.gmail.jackkobec.internetshop.dao.ConnectionManager;
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
 * Created by Jack on 28.12.2016.
 */
public class FillDb {
    public static final Logger LOGGER = LogManager.getLogger(FillDb.class);

    private ConnectionManager connectionManager;
    private Connection connection;

    public FillDb(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        return this.connectionManager.getConnection();
    }

    public boolean executeSqlFile(File file) {
        try {
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                List<String> queries = Arrays.asList(content.split(";"))
                        .stream()
                        .map(s -> s.trim())
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());

                Connection conn = getConnection();

                try {
                    for (String query : queries) {
                        try (Statement stmt = conn.createStatement()) {
                            String tr = query.trim();
                            stmt.execute(query.trim());
                        }
                    }
                } finally {
                    tryClose(conn);
                }

                return true;
            }


        } catch (IOException e) {
            LOGGER.warn("SQL script file not found");
            return false;
        }catch (SQLException e) {
            LOGGER.error("Errors while executing SQL script " + file.getAbsolutePath(), e);
            return false;
        }

        return false;
    }

    /**
     *
     * @param connection
     */
    public void tryClose(Connection connection) {
        try {
            if(connection != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot tryClose jdbc connection", e);
        }
    }

}
