package com.gmail.jackkobec.internetshop.persistence.model;

import lombok.Data;

/**
 * Created by Jack on 17.12.2016.
 */
@Data
public class User {

    private Integer id;
    private String email;
    private String password;
    private String name;
    private String language;

    private UserType userType;

    public User() {
    }



}
