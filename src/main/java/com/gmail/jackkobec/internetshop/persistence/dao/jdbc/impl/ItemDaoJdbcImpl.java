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

        String sqlQuery = "UPDATE item SET item.itemName = ?, item.itemSmallDescription = ?, item.itemFullDescription = ?, item.itemProductInfo = ?, " +
                "item.itemPrice = ?, item.itemBigPicturePath800x600 = ?, item.itemSmallPicturePath350x260 = ?, item.itemRating = ?, item.itemCategory = ?, " +
                "item.itemStatus = ? WHERE item.id = " + entity.getId();

        return executeQueryInPreparedStatement(entity, sqlQuery);
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

        String sqlQuery = "SELECT * FROM item WHERE item.itemCategory = " + categoryId;

        return getListOfItemBySqlQuery(sqlQuery);
    }

    @Override
    public List<Item> initSixItemCarousel() {

        String sqlQuery = "SELECT * FROM six_item_carousel LEFT JOIN item ON six_item_carousel.item_id = item.id";

        return getListOfItemBySqlQuery(sqlQuery);
    }

    @Override
    public List<Item> initUserCart(final Integer userId) {

        String sqlQuery = "SELECT * FROM item " +
                "LEFT JOIN cart ON item.id = cart.item_id " +
                "LEFT JOIN user ON cart.user_id = user.id " +
                "WHERE user.id = " + userId;

        return getListOfItemBySqlQuery(sqlQuery);
    }

    @Override
    public Item getItemById(final Integer id) {

        String sqlQuery = "SELECT * FROM item WHERE item.id = " + id;

        return getItemBySqlQuery(sqlQuery);
    }

    @Override
    public boolean addItemToCart(final Integer itemId, final Integer userId) {

        String sqlQuery = "INSERT INTO cart (item_id, user_id) VALUES (?, ?)";

        return executeQueryInPreparedStatementForCart(itemId, userId, sqlQuery);
    }

    @Override
    public boolean removeItemFromCart(Integer itemId, Integer userId) {

        String sqlQuery = "DELETE FROM cart WHERE cart.item_id = ? AND cart.user_id = ? LIMIT 1";

        return executeQueryInPreparedStatementForCart(itemId, userId, sqlQuery);
    }

    private boolean executeQueryInPreparedStatementForCart(Integer itemId, Integer userId, String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, itemId);
            preparedStatement.setInt(2, userId);
            preparedStatement.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    private boolean executeQueryInPreparedStatement(Item entity, String sqlQuery) {

        if (null == sqlQuery || entity == null) {
            throw new NullPointerException("Передан пустой sqlQuery / entity");
        }

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, entity.getItemName());
            preparedStatement.setString(2, entity.getItemSmallDescription());
            preparedStatement.setString(3, entity.getItemFullDescription());
            preparedStatement.setString(4, entity.getItemProductInfo());
            preparedStatement.setBigDecimal(5, entity.getItemPrice());
            preparedStatement.setString(6, entity.getItemBigPicturePath800x600());
            preparedStatement.setString(7, entity.getItemSmallPicturePath350x260());
            preparedStatement.setInt(8, entity.getItemRating());
            preparedStatement.setInt(9, entity.getItemCategory().getCategoryId());
            preparedStatement.setInt(10, entity.getItemStatus().getItemStatusId());
            preparedStatement.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    private Item getItemBySqlQuery(String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            Item item = new Item();

            while (resultSet.next()) {

                item = new Item();

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

            }

            return item;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }

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
