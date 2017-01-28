package com.gmail.jackkobec.internetshop.persistence.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jack on 17.12.2016.
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String email;
    private String password;
    private String name;
    private String language;

    private UserType userType;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password, String name, String language) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.language = language;
    }

    public User(Integer id, String email, String password, String name, String language) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.language = language;
    }

    public User(Integer id, String email, String password, String name, String language, UserType userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.language = language;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {

        switch (userType) {
            case 1:
                this.userType = UserType.CLIENT;
                break;
            case 2:
                this.userType = UserType.ADMIN;
                break;
            case 3:
                this.userType = UserType.BANNED;
                break;
        }
    }


}
