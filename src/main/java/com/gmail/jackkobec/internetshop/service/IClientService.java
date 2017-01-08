package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.model.User;

/**
 * Created by Jack on 28.12.2016.
 */
public interface IClientService {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    boolean userRegistrer(User user);
}
