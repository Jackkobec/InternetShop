package com.gmail.jackkobec.internetshop.model;

/**
 * Created by Jack on 18.12.2016.
 */
public enum OrderStatus {

    NEW, DONE, CANCELED;

    OrderStatus() {
    }

    @Override
    public String toString() {
        return "OrderStatus{}";
    }
}
