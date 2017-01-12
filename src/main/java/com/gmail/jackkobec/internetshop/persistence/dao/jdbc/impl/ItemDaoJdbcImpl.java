package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.ItemDao;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class ItemDaoJdbcImpl implements ItemDao {
    public static final Logger LOGGER = LogManager.getLogger(ItemDaoJdbcImpl.class);

    private static ItemDaoJdbcImpl itemDaoJdbc;
    private ConnectionManager connectionManager;

    private ItemDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @return ItemDaoJdbcImpl instance.
     */
    public static synchronized ItemDaoJdbcImpl getItemDaoJdbc(ConnectionManager connectionManager) {
        //worked with init in the constructor
        return (itemDaoJdbc == null)
                ? itemDaoJdbc = new ItemDaoJdbcImpl(connectionManager)
                : itemDaoJdbc;
    }

    @Override
    public boolean addNewEntity(Item entity) {
        return false;
    }

    @Override
    public boolean updateEntityInfo(Item entity) {
        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) {
        return false;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item getOneByID(Integer id) {
        return null;
    }

    @Override
    public List<Item> getItemsByCategoryId(final Integer categoryId) {

        String sqlQuery = "SELECT * FROM item_category WHERE item_category.id = " + categoryId;

        return getListOfItemBySqlQuery(sqlQuery);
    }

    private List<Item> getListOfItemBySqlQuery(String sqlQuery) {

        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            List<Item> items = new ArrayList<>();

            while (resultSet.next()) {

                Item group = new Item();
//
//                group.setId(resultSet.getInt("id"));
//                group.setName(resultSet.getString("name"));
//
//                groups.add(group);
            }

            return items;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
