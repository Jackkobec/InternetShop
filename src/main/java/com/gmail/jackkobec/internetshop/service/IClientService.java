package com.gmail.jackkobec.internetshop.service;

import com.gmail.jackkobec.internetshop.persistence.model.Item;
import com.gmail.jackkobec.internetshop.persistence.model.User;

import java.util.List;

/**
 * Created by Jack on 28.12.2016.
 */
public interface IClientService {

    User findByEmail(String email);

    User findByEmailAndPassword(final String email, final String password);

    boolean userRegistration(User user);

    List<Item> getItemsByCategoryId(final Integer categoryId);

    List<Item> initSixItemCarousel();

    List<Item> initUserCart(final Integer userId);

    Item getItemById(final Integer id);
}
