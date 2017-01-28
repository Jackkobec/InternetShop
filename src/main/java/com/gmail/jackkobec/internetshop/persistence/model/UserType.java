package com.gmail.jackkobec.internetshop.persistence.model;

import java.io.Serializable;

/**
 * Created by Jack on 18.12.2016.
 */
public enum UserType implements Serializable {

    CLIENT(1), ADMIN(2), BANNED(3);
    private final Integer userTypeId;

    UserType(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }
}
