package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.OrderDao;
import com.gmail.jackkobec.internetshop.persistence.model.Order;
import com.gmail.jackkobec.internetshop.persistence.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class OrderDaoImpl implements OrderDao {
    public static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    private static OrderDaoImpl orderDaoImpl;
    private ConnectionManager connectionManager;
    private Connection connection;

    private OrderDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @return ItemDaoJdbcImpl instance.
     */
    public static synchronized OrderDaoImpl getOrderDaoImpl(ConnectionManager connectionManager) {
        //worked with init in the constructor
        return (orderDaoImpl == null)
                ? orderDaoImpl = new OrderDaoImpl(connectionManager)
                : orderDaoImpl;
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
    public boolean addNewEntity(Order entity) {return false;
    }

    @Override
    public boolean updateEntityInfo(Order entity) {
        return false;
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
        return null;
    }

    @Override
    public Integer addNewOrder(Order entity) {

        String sqlQuery = "INSERT INTO orders (user_id, orderDateAndTime, summaryPrice, orderStatus) VALUES(?, ?, ?, ?)";

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    @Override
    public boolean addItemToOrder(Integer orderId, Integer itemId) {

        String sqlQuery = "INSERT INTO order_item (order_id, item_id) VALUES(?, ?)";

        return executeQueryInPreparedStatementForOrder(orderId,  itemId, sqlQuery);
    }

    @Override
    public boolean removeItemFromOrder(Integer orderId, Integer itemId) {

        String sqlQuery = "DELETE FROM orders WHERE orders.order_id = ? AND orders.item_id = ? LIMIT 1";

        return executeQueryInPreparedStatementForOrder(orderId,  itemId, sqlQuery);
    }

    private Integer executeQueryInPreparedStatement(Order entity, String sqlQuery) {

        if (sqlQuery == null || entity == null) {
            throw new NullPointerException("Передан пустой sqlQuery / entity");
        }

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setObject(2, entity.getOrderDateAndTime());
            preparedStatement.setBigDecimal(3, entity.getSummaryPrice());
            preparedStatement.setInt(4, entity.getOrderStatus());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return null;

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
}
