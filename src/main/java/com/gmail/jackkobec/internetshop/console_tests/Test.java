package com.gmail.jackkobec.internetshop.console_tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Jack on 18.12.2016.
 */
public class Test {
    public static final Logger LOGGER = LogManager.getLogger(Test.class);

    public static void main(String[] args) throws IOException, SQLException {

        //new ProductDAO().addProduct(new Product());
//        System.out.println(0.1+0.2);//out: 0.30000000000000004
//        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));//out: 0.3
//        String PATH_TO_PROPERTIES = "src/main/resources/DataBaseScriptForAutoFilling.sql";
////        ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromJNDI("jdbc/mySqlResource");
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(new File(PATH_TO_PROPERTIES)));
//
//        Connection connection = DriverManager.getConnection(
//                properties.getProperty("jdbc.url"),
//                properties.getProperty("jdbc.user"),
//                properties.getProperty("jdbc.password"));
        //File file = ResourcesUtil.getResourceFile("DataBaseScriptForAutoFilling.sql");
        File file = new File("src/main/resources/DataBaseScriptForAutoFilling.sql");
        //new DataBaseUtils(connectionManager).executeSqlFile(file);

        executeSqlFile(file);
    }

    public static boolean executeSqlFile(File file) {
        try {
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                List<String> queries = Arrays.asList(content.split(";"))
                        .stream()
                        .map(s -> s.trim())
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());

                String PATH_TO_PROPERTIES = "src/main/resources/database.properties";
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File(PATH_TO_PROPERTIES)));

                Connection connection = DriverManager.getConnection(
                        properties.getProperty("jdbc.url"),
                        properties.getProperty("jdbc.user"),
                        properties.getProperty("jdbc.password"));

                try {
                    for (String query : queries) {
                        try (Statement stmt = connection.createStatement()) {
                            //String tr = query.trim();
                            stmt.execute(query.trim());
                        }
                    }
                } finally {

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
}
