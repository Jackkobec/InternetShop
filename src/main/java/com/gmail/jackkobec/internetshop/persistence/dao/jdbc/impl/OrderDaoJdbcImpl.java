package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.OrderDao;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>OrderDaoJdbcImpl class for implements Order - actions methods with JDBC.
 */
public class OrderDaoJdbcImpl implements OrderDao {
    public static final Logger LOGGER = LogManager.getLogger(OrderDaoJdbcImpl.class);

    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders (userId, orderDateAndTime, summaryPrice, orderStatus) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_ORDER_BY_ID_QUERY = "UPDATE orders SET userId = ?, orderDateAndTime = ?, summaryPrice = ?, orderStatus = ? WHERE orders.id = ";
    private static final String GET_ORDER_BY_ID_QUERY = "SELECT * FROM orders WHERE orders.id = ";
    private static final String ADD_NEW_ORDER_QUERY = "INSERT INTO orders (userId, orderDateAndTime, summaryPrice, orderStatus) VALUES(?, ?, ?, ?)";
    private static final String ADD_ITEM_TO_THE_ORDER_QUERY_BY_ORDER_AND_USER_ID = "INSERT INTO order_item (order_id, item_id) VALUES(?, ?)";
    private static final String DELETE_ITEM_FROM_ORDER_QUERY_BY_ITEM_AND_ORDER_ID = "DELETE FROM order_item WHERE order_item.order_id = ? AND order_item.item_id = ? LIMIT 1";

    private static final String DELETE_ORDER_BY_ID_QUERY = "DELETE FROM orders WHERE orders.id = ";
    private static final String DELETE_ITEMS_IN_ORDER_BY_ORDER_ID_QUERY = "DELETE FROM order_item WHERE order_item.order_id = ";

    private static final String GET_ALL_USER_ORDERS_QUERY_BY_USER_ID = "SELECT * FROM orders WHERE orders.userId = ";
    private static final String DELETE_ALL_NOT_PAID_ORDERS_BY_USER_ID_QUERY = "DELETE FROM orders WHERE orders.orderStatus = 1 AND orders.userId = ";

    private static OrderDaoJdbcImpl orderDaoJdbcImpl;
    private ConnectionManager connectionManager;
    private Connection connection;

    private OrderDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @return OrderDaoJdbcImpl instance.
     */
    public static synchronized OrderDaoJdbcImpl getOrderDaoImpl(ConnectionManager connectionManager) {
        //worked with init in the constructor
        return (orderDaoJdbcImpl == null)
                ? orderDaoJdbcImpl = new OrderDaoJdbcImpl(connectionManager)
                : orderDaoJdbcImpl;
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
     * Method for add new Order.
     *
     * @param entity
     * @return
     */
    @Override
    public Integer addNewEntity(Order entity) {

        return executeQueryInPreparedStatement(entity, INSERT_ORDER_QUERY);
    }

    /**
     * Method for update Order.
     *
     * @param entity
     * @return
     */
    @Override
    public Integer updateEntityInfo(Order entity) {

        String sqlQuery = UPDATE_ORDER_BY_ID_QUERY + entity.getId();

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    @Override
    public boolean deleteEntityById(final Integer id) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    /**
     * Method for get Order be id.
     *
     * @param id
     * @return
     */
    @Override
    public Order getOneByID(final Integer id) {

        String sqlQuery = GET_ORDER_BY_ID_QUERY + id;

        return getOrderBySqlQuery(sqlQuery);
    }

    /**
     * Method for add new Order.
     *
     * @param entity
     * @return
     */
    @Override
    public Integer addNewOrder(Order entity) {

        return executeQueryInPreparedStatement(entity, ADD_NEW_ORDER_QUERY);
    }

    /**
     * Method for add item to the order.
     *
     * @param orderId
     * @param itemId
     * @return
     */
    @Override
    public boolean addItemToOrder(final Integer orderId, final Integer itemId) {

        return executeQueryInPreparedStatementForOrder(orderId, itemId, ADD_ITEM_TO_THE_ORDER_QUERY_BY_ORDER_AND_USER_ID);
    }

    /**
     * Method for remove item from order.
     *
     * @param orderId
     * @param itemId
     * @return
     */
    @Override
    public boolean removeItemFromOrder(final Integer orderId, final Integer itemId) {

        return executeQueryInPreparedStatementForOrder(orderId, itemId, DELETE_ITEM_FROM_ORDER_QUERY_BY_ITEM_AND_ORDER_ID);
    }

    /**
     * Method for cancel and delete order and remove all his items.
     *
     * @param orderId
     * @return
     */
    @Override
    public boolean cancelOrder(final Integer orderId) {

        String sqlQuery = DELETE_ORDER_BY_ID_QUERY + orderId;
        String sqlQuery2 = DELETE_ITEMS_IN_ORDER_BY_ORDER_ID_QUERY + orderId;

        connection = getConnection();

        startTransaction(connection);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.executeUpdate();

            try (PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery2)) {

                preparedStatement2.executeUpdate();
                commitTransaction(connection);

                return true;
            }

        } catch (SQLException e) {
            rollbackTransaction(connection);
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Method for get all user orders.
     *
     * @param userId
     * @return
     */
    @Override
    public List<Order> getAllUserOrders(final Integer userId) {

        String sqlQuery = GET_ALL_USER_ORDERS_QUERY_BY_USER_ID + userId;

        return getListOfOrdersBySqlQuery(sqlQuery);
    }

    /**
     * Method for remove all not paid orders by user id.
     *
     * @param userId
     * @return
     */
    @Override
    public boolean removeAllNotPaidOrders(final Integer userId) {

        String sqlQuery = DELETE_ALL_NOT_PAID_ORDERS_BY_USER_ID_QUERY + userId;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    /**
     * Method for start transaction.
     *
     * @param connection
     */
    private void startTransaction(Connection connection) {

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            LOGGER.error("Transaction not started", e);
        }
    }

    /**
     * Method for commit transaction.
     *
     * @param connection
     */
    private void commitTransaction(Connection connection) {

        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Transaction not commit", e);
        }
    }

    /**
     * Method for rollback transaction.
     *
     * @param connection
     */
    private void rollbackTransaction(Connection connection) {

        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("Transaction don'n rollback", e);
        }
    }

    /**
     * Query executor for Order queries.
     *
     * @param entity
     * @param sqlQuery
     * @return
     */
    private Integer executeQueryInPreparedStatement(Order entity, String sqlQuery) {

        if (sqlQuery == null || entity == null) {
            throw new NullPointerException("Передан пустой sqlQuery / entity");
        }

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setObject(2, entity.getOrderDateAndTime());
            preparedStatement.setBigDecimal(3, entity.getSummaryPrice());
            preparedStatement.setInt(4, entity.getOrderStatus());

            preparedStatement.executeUpdate();

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
     * Method for build Order from resultSet.
     *
     * @param sqlQuery
     * @return
     */
    private Order getOrderBySqlQuery(String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            Order order = new Order();

            while (resultSet.next()) {

                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setOrderDateAndTime((java.util.Date) resultSet.getObject("orderDateAndTime"));
                order.setSummaryPrice(resultSet.getBigDecimal("summaryPrice"));
                order.setOrderStatus(resultSet.getInt("orderStatus"));

            }
            System.out.println("getOrderBySqlQuery" + order);
            return order;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Method for build Order list from resultSet.
     *
     * @param sqlQuery
     * @return
     */
    private List<Order> getListOfOrdersBySqlQuery(String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {

                Order order = new Order();

                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setOrderDateAndTime((java.util.Date) resultSet.getObject("orderDateAndTime"));
                order.setSummaryPrice(resultSet.getBigDecimal("summaryPrice"));
                order.setOrderStatus(resultSet.getInt("orderStatus"));

                orders.add(order);
            }

            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Method for execute query for order_item table.
     *
     * @param orderId
     * @param itemId
     * @param sqlQuery
     * @return
     */
    private boolean executeQueryInPreparedStatementForOrder(Integer orderId, Integer itemId, String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, itemId);
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
     * Simple query executor.
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

    @Override
    protected Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException("Singleton cloning not supported.");
    }
}
