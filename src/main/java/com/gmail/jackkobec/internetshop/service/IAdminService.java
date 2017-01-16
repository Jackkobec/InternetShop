package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.model.User;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface IAdminService {

    List<User> getAllUsers();

    List<User> getAllNotBannedUsers();

    List<User> getAllBannedUsers();

    boolean addUserToBlockListById(final Integer id);
}
