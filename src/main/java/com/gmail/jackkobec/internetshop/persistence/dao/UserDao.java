package com.gmail.jackkobec.internetshop.persistence.dao;

import com.gmail.jackkobec.internetshop.persistence.model.User;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface UserDao extends CommonDao<User, Integer> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> getAllNotBannedUsers();

    List<User> getAllBannedUsers();

    boolean addUserToBlockListById(final Integer id);
}
