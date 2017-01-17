package com.gmail.jackkobec.internetshop.persistence.model;

/**
 * Created by Jack on 18.12.2016.
 */
public enum OrderStatus {

    NEW(1), DONE(2), CANCELED(3);
    private Integer orderStatusId;

    OrderStatus(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }
}
