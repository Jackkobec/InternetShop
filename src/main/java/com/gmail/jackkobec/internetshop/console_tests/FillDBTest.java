package com.gmail.jackkobec.internetshop.console_tests;

import com.gmail.jackkobec.internetshop.dao.ConnectionManager;
import com.gmail.jackkobec.internetshop.dao.utils.FillDb;

import java.io.File;

/**
 * Created by Jack on 28.12.2016.
 */
public class FillDBTest {
    public static void main(String[] args) {

        //ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromJNDI("jdbc/mymysql");
        ConnectionManager connectionManager = ConnectionManager.getConnectionManagerFromPropertiesFile("src/main/resources/database.properties");
        File file = new File("src/main/resources/DataBaseScriptForAutoFilling.sql");

        FillDb fillDb = new FillDb(connectionManager);
        fillDb.executeSqlFile(file);
    }
}
