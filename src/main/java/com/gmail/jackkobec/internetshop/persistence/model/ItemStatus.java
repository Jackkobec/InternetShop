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

    public static ItemStatus getItemStatus(Integer itemStatusId){

        switch (itemStatusId){

            case 1: return PRESENT;
            case 2: return NOT_PRESENT;
        }
        return null;
    }
}
