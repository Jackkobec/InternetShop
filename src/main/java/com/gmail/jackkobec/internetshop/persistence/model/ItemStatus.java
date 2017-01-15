package com.gmail.jackkobec.internetshop.persistence.model;

/**
 * Created by Jack on 27.12.2016.
 */
public enum ItemStatus {

    PRESENT(1), NOT_PRESENT(2);
    private Integer itemStatusId;

    ItemStatus(Integer itemStatusId) {
        this.itemStatusId = itemStatusId;
    }

    public Integer getItemStatusId() {
        return itemStatusId;
    }
}
