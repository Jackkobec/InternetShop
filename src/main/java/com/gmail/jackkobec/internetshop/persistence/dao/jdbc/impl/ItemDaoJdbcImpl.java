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
 * <p>ItemDaoJdbcImpl class for implements Item - actions methods with JDBC.
 */
public class ItemDaoJdbcImpl implements ItemDao {
    public static final Logger LOGGER = LogManager.getLogger(ItemDaoJdbcImpl.class);

    private static final String INSERT_USER_QUERY = "INSERT INTO item (itemName, itemSmallDescription, itemFullDescription, itemProductInfo, itemPrice, " +
            "itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating, itemCategory, itemStatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE item SET item.itemName = ?, item.itemSmallDescription = ?, item.itemFullDescription = ?, item.itemProductInfo = ?, " +
            "item.itemPrice = ?, item.itemBigPicturePath800x600 = ?, item.itemSmallPicturePath350x260 = ?, item.itemRating = ?, item.itemCategory = ?, " +
            "item.itemStatus = ? WHERE item.id = ";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM item WHERE item.id = ";
    private static final String GET_ITEMS_BY_CATEGORY_ID_QUERY = "SELECT * FROM item WHERE item.itemCategory = ";
    private static final String INIT_SIX_ITEM_CAROUSEL_QUERY = "SELECT * FROM six_item_carousel LEFT JOIN item ON six_item_carousel.item_id = item.id";
    private static final String INIT_USER_CART_QUERY = "SELECT * FROM item " +
            "LEFT JOIN cart ON item.id = cart.item_id " +
            "LEFT JOIN user ON cart.user_id = user.id " +
            "WHERE user.id = ";
    private static final String GET_ITEM_BY_ID_QUERY = "SELECT * FROM item WHERE item.id = ";
    private static final String ADD_ITEM_TO_THE_CART_QUERY_BY_ITEM_AND_USER_ID = "INSERT INTO cart (item_id, user_id) VALUES (?, ?)";
    private static final String REMOVE_ONE_ITEM_FROM_CART_QUERY_BY_ITEM_AND_USER_ID = "DELETE FROM cart WHERE cart.item_id = ? AND cart.user_id = ? LIMIT 1";
    private static final String GET_ITEMS_FROM_ORDER_BY_ORDER_ID = "SELECT * FROM item " +
            "LEFT JOIN order_item ON item.id = order_item.item_id " +
            "WHERE order_item.order_id = ";
    private static final String DELETE_ALL_ITEMS_FROM_CART_BY_USER_ID_QUERY = "DELETE FROM cart WHERE cart.user_id = ";

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

    /**
     * Method for get connection.
     *
     * @return Connection
     */
    private Connection getConnection() {

        return connectionManager.getConnection();
    }

    /**
     * Method for close connection.
     *
     * @param connection
     */
    private void closeConnection(Connection connection) {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Cant close connection");
        }
    }

    /**
     * Method for add new Item.
     *
     * @param entity
     * @return
     */
    @Override
    public Integer addNewEntity(Item entity) {

        return executeQueryInPreparedStatement(entity, INSERT_USER_QUERY);
    }

    /**
     * Method for update Item.
     *
     * @param entity
     * @return
     */
    @Override
    public Integer updateEntityInfo(Item entity) {

        String sqlQuery = UPDATE_USER_BY_ID_QUERY + entity.getId();

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    /**
     * Method for delete Item by id.
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteEntityById(final Integer id) {

        String sqlQuery = DELETE_USER_BY_ID_QUERY + id;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item getOneByID(final Integer id) {
        return null;
    }

    /**
     * Method for get Items for category by id.
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<Item> getItemsByCategoryId(final Integer categoryId) {

        String sqlQuery = GET_ITEMS_BY_CATEGORY_ID_QUERY + categoryId;

        return getListOfItemBySqlQuery(sqlQuery);
    }

    /**
     * Method for init top six items carousel.
     *
     * @return
     */
    @Override
    public List<Item> initSixItemCarousel() {

        return getListOfItemBySqlQuery(INIT_SIX_ITEM_CAROUSEL_QUERY);
    }

    /**
     * Method for init user cart.
     *
     * @param userId
     * @return
     */
    @Override
    public List<Item> initUserCart(final Integer userId) {

        String sqlQuery = INIT_USER_CART_QUERY + userId;

        return getListOfItemBySqlQuery(sqlQuery);
    }

    /**
     * Method for get item by id.
     *
     * @param id
     * @return
     */
    @Override
    public Item getItemById(final Integer id) {

        String sqlQuery = GET_ITEM_BY_ID_QUERY + id;

        return getItemBySqlQuery(sqlQuery);
    }

    /**
     * Method for add item to the cart.
     *
     * @param itemId
     * @param userId
     * @return
     */
    @Override
    public boolean addItemToCart(final Integer itemId, final Integer userId) {

        return executeQueryInPreparedStatementForCart(itemId, userId, ADD_ITEM_TO_THE_CART_QUERY_BY_ITEM_AND_USER_ID);
    }

    /**
     * Method for remove item from the cart.
     *
     * @param itemId
     * @param userId
     * @return
     */
    @Override
    public boolean removeItemFromCart(final Integer itemId, final Integer userId) {

        return executeQueryInPreparedStatementForCart(itemId, userId, REMOVE_ONE_ITEM_FROM_CART_QUERY_BY_ITEM_AND_USER_ID);
    }

    /**
     * Get items in order by id.
     *
     * @param orderId
     * @return
     */
    @Override
    public List<Item> getItemsFromOrderByOrderId(final Integer orderId) {

        String sqlQuery = GET_ITEMS_FROM_ORDER_BY_ORDER_ID + orderId;

        return getListOfItemBySqlQuery(sqlQuery);
    }

    /**
     * Remove all items from order by id.
     *
     * @param userId
     * @return
     */
    @Override
    public boolean removeAllItemsFromUserCart(final Integer userId) {

        String sqlQuery = DELETE_ALL_ITEMS_FROM_CART_BY_USER_ID_QUERY + userId;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    /**
     * Query executor for cart.
     *
     * @param itemId
     * @param userId
     * @param sqlQuery
     * @return
     */
    private boolean executeQueryInPreparedStatementForCart(final Integer itemId, final Integer userId, String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, itemId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Query executor for simples queries.
     *
     * @param sqlQuery
     * @return
     */
    private boolean executeSimpleQueryInThePreparedStatement(String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Query executor for Item queries.
     *
     * @param entity
     * @param sqlQuery
     * @return
     */
    private Integer executeQueryInPreparedStatement(Item entity, String sqlQuery) {

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

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return entity.getId();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Method for build Item from resultSet.
     *
     * @param sqlQuery
     * @return
     */
    private Item getItemBySqlQuery(String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            Item item = new Item();

            while (resultSet.next()) {

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

    /**
     * Method for build Item list from resultSet.
     *
     * @param sqlQuery
     * @return
     */
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

    @Override
    protected Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException("Singleton cloning not supported.");
    }
}
