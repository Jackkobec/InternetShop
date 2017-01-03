package com.gmail.jackkobec.internetshop.persistence.model;

/**
 * Created by Jack on 27.12.2016.
 */
public enum ItemStatus {

    PRESENT, NOT_PRESENT;

    ItemStatus() {
    }

    @Override
    public String toString() {
        return "ItemStatus{}";
    }
}
