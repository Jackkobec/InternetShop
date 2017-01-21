package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.model.User;
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
public class UserDaoJdbcImpl implements UserDao {
    public static final Logger LOGGER = LogManager.getLogger(UserDaoJdbcImpl.class);

    private static UserDaoJdbcImpl userDaoJdbc;
    private ConnectionManager connectionManager;
    private Connection connection;


    private UserDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @return UserDaoJdbcImpl instance.
     */
    public static synchronized UserDaoJdbcImpl getUserDaoJdbc(ConnectionManager connectionManager) {

        return (userDaoJdbc == null)
                ? userDaoJdbc = new UserDaoJdbcImpl(connectionManager)
                : userDaoJdbc;
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
    public boolean addNewEntity(User entity) {
//        String sqlQuery;
//
//        if (entity != null) {
//            sqlQuery = "INSERT INTO user(email, password, name, language) VALUES (?, ?, ?, ?)";
//        } else return false;

//        return executeQueryInPreparedStatement(entity, sqlQuery);
        return false;
    }


    @Override
    public boolean updateEntityInfo(User entity) {
        return false;
    }

    @Override
    public boolean deleteEntityById(Integer id) {
        return false;
    }

    @Override
    public List<User> getAll() {

        String sqlQuery = "SELECT * FROM user";

        return getListOfUsersBySqlQuery(sqlQuery);
    }

    @Override
    public User getOneByID(Integer id) {
        return null;
    }


    @Override
    public User findByEmail(String email) {

        String sqlQuery;

        if (!email.isEmpty() || email != null) {
            sqlQuery = "SELECT * FROM user WHERE user.email = " + "'" + email + "'";
        } else throw new NullPointerException("Передано значение null");

        return getUserBySqlQuery(sqlQuery);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {

        String sqlQuery;

        if (!email.isEmpty() && !password.isEmpty() || password != null) {
            sqlQuery = "SELECT * FROM user WHERE user.email = " + "'" + email + "'" + " AND " + "user.password = " + "'" + password + "'";
        } else throw new NullPointerException("Передано значение null");

        return getUserBySqlQuery(sqlQuery);
    }

    @Override
    public List<User> getAllNotBannedUsers() {

        String sqlQuery = "SELECT * FROM user WHERE user.userType != 3";

        return getListOfUsersBySqlQuery(sqlQuery);
    }

    @Override
    public List<User> getAllBannedUsers() {

        String sqlQuery = "SELECT * FROM user WHERE user.userType = 3";

        return getListOfUsersBySqlQuery(sqlQuery);
    }

    @Override
    public boolean addUserToBlockListById(Integer id) {

        String sqlQuery = "UPDATE user SET user.userType = 3 WHERE user.id = " + id;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    @Override
    public boolean removeUserFromBlockListById(Integer id) {

        String sqlQuery = "UPDATE user SET user.userType = 1 WHERE user.id = " + id;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    @Override
    public Integer addUser(User user) {
        String sqlQuery;

        if (user != null) {
            sqlQuery = "INSERT INTO user(email, password, name, language) VALUES (?, ?, ?, ?)";
        } else return null;

        return executeQueryInPreparedStatement(user, sqlQuery);
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

    private User getUserBySqlQuery(String sqlQuery) {

        if (null == sqlQuery) {
            throw new NullPointerException("Передан пустой sqlQuery");
        }

        connection = getConnection();
        try (ResultSet resultSet = connection.prepareStatement(sqlQuery).executeQuery(sqlQuery)) {

            User user = new User();
            while (resultSet.next()) {

                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setLanguage(resultSet.getString("language"));
                user.setUserType(resultSet.getInt("userType"));
            }

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }

    private List<User> getListOfUsersBySqlQuery(String sqlQuery) {

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setLanguage(resultSet.getString("language"));
                user.setUserType(resultSet.getInt("userType"));

                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }

    private Integer executeQueryInPreparedStatement(User entity, String sqlQuery) {

        if (sqlQuery == null || entity == null) {
            throw new NullPointerException("Передан пустой sqlQuery / entity");
        }

        connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getLanguage());

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
}
