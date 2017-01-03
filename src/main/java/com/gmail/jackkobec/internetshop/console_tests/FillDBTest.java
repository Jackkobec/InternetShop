package com.gmail.jackkobec.internetshop.console_tests;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.db.utils.DataBaseUtils;

import java.io.File;

/**
 * Created by Jack on 28.12.2016.
 */
public class FillDBTest {
    public static void main(String[] args) {

        //ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromJNDI("jdbc/mymysql");
        ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromPropertiesFile();
        File file = new File("src/main/resources/DataBaseScriptForAutoFilling.sql");

        DataBaseUtils dataBaseUtils = new DataBaseUtils(connectionManager);
        dataBaseUtils.executeSqlFile(file);
    }
}
