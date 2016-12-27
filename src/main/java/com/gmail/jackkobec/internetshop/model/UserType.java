package com.gmail.jackkobec.internetshop.model;

/**
 * Created by Jack on 18.12.2016.
 */
public enum UserType {

    CLIENT, ADMIN, BANNED;

    UserType() {
    }

    @Override
    public String toString() {
        return "UserType{}";
    }
}
