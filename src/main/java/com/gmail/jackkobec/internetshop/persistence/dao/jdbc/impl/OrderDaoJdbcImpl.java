package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.OrderDao;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class OrderDaoJdbcImpl implements OrderDao {
    public static final Logger LOGGER = LogManager.getLogger(OrderDaoJdbcImpl.class);

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
    public Integer addNewEntity(Order entity) {

        String sqlQuery = "INSERT INTO orders (userId, orderDateAndTime, summaryPrice, orderStatus) VALUES(?, ?, ?, ?)";

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    @Override
    public Integer updateEntityInfo(Order entity) {

        String sqlQuery = "UPDATE orders SET userId = ?, orderDateAndTime = ?, summaryPrice = ?, orderStatus = ? WHERE orders.id = " + entity.getId();

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    @Override
    public boolean deleteEntityById(Integer id) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order getOneByID(Integer id) {

        String sqlQuery = "SELECT * FROM orders WHERE orders.id = " + id;

        return getOrderBySqlQuery(sqlQuery);
    }

    @Override
    public Integer addNewOrder(Order entity) {

        String sqlQuery = "INSERT INTO orders (userId, orderDateAndTime, summaryPrice, orderStatus) VALUES(?, ?, ?, ?)";

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    @Override
    public boolean addItemToOrder(Integer orderId, Integer itemId) {

        String sqlQuery = "INSERT INTO order_item (order_id, item_id) VALUES(?, ?)";

        return executeQueryInPreparedStatementForOrder(orderId, itemId, sqlQuery);
    }

    @Override
    public boolean removeItemFromOrder(Integer orderId, Integer itemId) {

        String sqlQuery = "DELETE FROM order_item WHERE order_item.order_id = ? AND order_item.item_id = ? LIMIT 1";

        return executeQueryInPreparedStatementForOrder(orderId, itemId, sqlQuery);
    }

    @Override
    public boolean cancelOrder(Integer orderId) {

        String sqlQuery = "DELETE FROM orders WHERE orders.id = " + orderId;
        String sqlQuery2 = "DELETE FROM order_item WHERE order_item.order_id = " + orderId;


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

    @Override
    public List<Order> getAllUserOrders(final Integer userId) {

        String sqlQuery = "SELECT * FROM orders WHERE orders.userId = " + userId;

        return getListOfOrdersBySqlQuery(sqlQuery);
    }

    @Override
    public boolean removeAllNotPaidOrders(final Integer userId) {

        String sqlQuery = "DELETE FROM orders WHERE orders.orderStatus = 1 AND orders.userId = " + userId;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    private void startTransaction(Connection connection) {

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            LOGGER.error("Transaction not started", e);
        }
    }

    private void commitTransaction(Connection connection) {

        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Transaction not commit", e);
        }
    }

    private void rollbackTransaction(Connection connection) {

        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("Transaction don'n rollback", e);
        }
    }

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
