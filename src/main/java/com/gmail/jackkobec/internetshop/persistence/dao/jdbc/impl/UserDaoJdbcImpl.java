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
 * <p>UserDaoJdbcImpl class for implements User - actions methods with JDBC.
 */
public class UserDaoJdbcImpl implements UserDao {
    public static final Logger LOGGER = LogManager.getLogger(UserDaoJdbcImpl.class);

    private static final String ADD_NEW_USER_QUERY = "INSERT INTO user(email, password, name, language) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE user SET user.email = ?, user.password = ?, user.name = ?, user.language = ? WHERE user.id = ";
    private static final String GET_ALL_USER_QUERY = "SELECT * FROM user";
    private static final String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE user.email = ";
    private static final String GET_ALL_NOT_BANNED_USERS_QUERY = "SELECT * FROM user WHERE user.userType != 3";
    private static final String GET_ALL_BANNED_USERS_QUERY = "SELECT * FROM user WHERE user.userType = 3";
    private static final String ADD_USER_TO_THE_BLOCK_LIST_BY_ID_QUERY = "UPDATE user SET user.userType = 3 WHERE user.id = ";
    private static final String REMOVE_USER_FROM_THE_BLOCK_LIST_BY_ID_QUERY = "UPDATE user SET user.userType = 1 WHERE user.id = ";

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
     * Method for add new User.
     *
     * @param entity
     * @return
     */
    @Override
    public Integer addNewEntity(User entity) {

        String sqlQuery;

        if (entity != null) {
            sqlQuery = ADD_NEW_USER_QUERY;
        } else return null;

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    /**
     * Method for update User.
     *
     * @param entity
     * @return
     */
    @Override
    public Integer updateEntityInfo(User entity) {

        String sqlQuery;

        if (entity != null) {
            sqlQuery = UPDATE_USER_BY_ID_QUERY + entity.getId();
        } else return null;

        return executeQueryInPreparedStatement(entity, sqlQuery);
    }

    @Override
    public boolean deleteEntityById(final Integer id) {
        return false;
    }

    /**
     * Method for get all Users.
     *
     * @return
     */
    @Override
    public List<User> getAll() {

        return getListOfUsersBySqlQuery(GET_ALL_USER_QUERY);
    }

    @Override
    public User getOneByID(final Integer id) {
        return null;
    }

    /**
     * Method for get User by email.
     *
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {

        String sqlQuery = GET_USER_BY_EMAIL_QUERY + "'" + email + "'";

        return getUserBySqlQuery(sqlQuery);
    }

    /**
     * Method for get User by email and Password.
     *
     * @param email
     * @return
     */
    @Override
    public User findByEmailAndPassword(String email, String password) {

        String sqlQuery;

        if (!email.isEmpty() && !password.isEmpty() || password != null) {
            sqlQuery = "SELECT * FROM user WHERE user.email = " + "'" + email + "'" + " AND " + "user.password = " + "'" + password + "'";
        } else throw new NullPointerException("Передано значение null");

        return getUserBySqlQuery(sqlQuery);
    }

    /**
     * Method for get all not banned users.
     *
     * @return
     */
    @Override
    public List<User> getAllNotBannedUsers() {

        return getListOfUsersBySqlQuery(GET_ALL_NOT_BANNED_USERS_QUERY);
    }

    /**
     * Method for get all banned users.
     *
     * @return
     */
    @Override
    public List<User> getAllBannedUsers() {

        return getListOfUsersBySqlQuery(GET_ALL_BANNED_USERS_QUERY);
    }

    /**
     * Method for add user to the block list.
     *
     * @param id
     * @return
     */
    @Override
    public boolean addUserToBlockListById(final Integer id) {

        String sqlQuery = ADD_USER_TO_THE_BLOCK_LIST_BY_ID_QUERY + id;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    /**
     * Method for remove user from the block list.
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeUserFromBlockListById(final Integer id) {

        String sqlQuery = REMOVE_USER_FROM_THE_BLOCK_LIST_BY_ID_QUERY + id;

        return executeSimpleQueryInThePreparedStatement(sqlQuery);
    }

    /**
     * Method for add new user.
     *
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {

        return executeQueryInPreparedStatement(user, ADD_NEW_USER_QUERY);
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

    /**
     * Method for build User from resultSet.
     *
     * @param sqlQuery
     * @return
     */
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

    /**
     * Method for build User list from resultSet.
     *
     * @param sqlQuery
     * @return
     */
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

    /**
     * Method for execute queries.
     *
     * @param entity
     * @param sqlQuery
     * @return
     */
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

            return entity.getId();

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
