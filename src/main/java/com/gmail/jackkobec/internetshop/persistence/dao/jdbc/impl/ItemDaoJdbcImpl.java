package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.ItemDao;
import com.gmail.jackkobec.internetshop.persistence.model.Item;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
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
    private Connection connection;

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

    private Connection getConnection() {

        return connectionManager.getConnection();
    }

    private void closeConnection(Connection connection) {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Cant close connection");
        }
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

    @Override
    public List<Item> initSixItemCarousel() {

        String sqlQuery = "SELECT * FROM six_item_carousel LEFT JOIN item ON six_item_carousel.item_id = item.id";

        return getListOfItemBySqlQuery(sqlQuery);
    }

    private List<Item> getListOfItemBySqlQuery(String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            List<Item> items = new ArrayList<>();

            while (resultSet.next()) {

                Item item = new Item();

                item.setId(resultSet.getInt("id"));
                item.setItemName(resultSet.getString("itemName"));
                item.setItemSmallDescription(resultSet.getString("itemSmallDescription"));
                item.setItemFullDescription(resultSet.getString("itemFullDescription"));
                item.setItemProductInfo(resultSet.getString("itemProductInfo"));
                item.setItemPrice(resultSet.getBigDecimal("itemPrice"));
                item.setItemBigPicturePath800x600(resultSet.getString("itemBigPicturePath800x600"));
                item.setItemSmallPicturePath350x260(resultSet.getString("itemSmallPicturePath350x260"));
                item.setItemRating(resultSet.getInt("itemRating"));
                item.setItemCategory(resultSet.getInt("itemCategory"));
                item.setItemStatus(resultSet.getInt("itemStatus"));

                items.add(item);
            }

            return items;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }
}
