package com.gmail.jackkobec.internetshop.persistence.dao.jdbc.impl;

import com.gmail.jackkobec.internetshop.persistence.connection.pool.ConnectionManager;
import com.gmail.jackkobec.internetshop.persistence.dao.UserDao;
import com.gmail.jackkobec.internetshop.persistence.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public class UserDaoJdbcImpl implements UserDao {

    private static UserDaoJdbcImpl userDaoJdbc;
    private ConnectionManager connectionManager;


    private UserDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @return UserDaoJdbcImpl instance.
     */
    public static UserDaoJdbcImpl getUserDaoJdbc(ConnectionManager connectionManager) {
        //worked with init in the constructor
        return (userDaoJdbc == null)
                ? userDaoJdbc = new UserDaoJdbcImpl(connectionManager)
                : userDaoJdbc;
    }


    @Override
    public boolean addNewEntity(User entity) {
        String sqlQuery;

        if (entity != null) {
            sqlQuery = "INSERT INTO user(email, password, name, language) VALUES (?, ?, ?, ?)";
        } else return false;

        return executeQueryInPreparedStatement(entity, sqlQuery);
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
        return null;
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

        return getGroupBySQLquery(sqlQuery);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {

        String sqlQuery;

        if (!email.isEmpty() && !password.isEmpty() || password != null) {
            sqlQuery = "SELECT * FROM user WHERE user.email = " + "'" + email + "'" + " AND " + "user.password = " + "'" + password + "'";
        } else throw new NullPointerException("Передано значение null");

        return getGroupBySQLquery(sqlQuery);
    }

    private User getGroupBySQLquery(String sqlQuery) {

        if (null == sqlQuery) {
            throw new NullPointerException("Передан пустой sqlQuery");
        }

        try (ResultSet resultSet = connectionManager.getConnection().prepareStatement(sqlQuery).executeQuery(sqlQuery)) {
//             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
//             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            User user = new User();
            while (resultSet.next()) {

                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setLanguage(resultSet.getString("language"));
            }
            System.out.println(user);
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean executeQueryInPreparedStatement(User entity, String sqlQuery) {

        if (sqlQuery == null || entity == null) {
            throw new NullPointerException("Передан пустой sqlQuery / entity");
        }

        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getLanguage());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
