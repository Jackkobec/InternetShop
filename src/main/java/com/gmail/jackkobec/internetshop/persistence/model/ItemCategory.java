package com.gmail.jackkobec.internetshop.persistence.model;

/**
 * Created by Jack on 27.12.2016.
 */
public enum ItemCategory {

    FOR_SELF_DEFENSE("FOR SELF-DEFENSE"), FOR_RESPECT("FOR RESPECT"), ULTIMATE_SOLUTIONS("ULTIMATE SOLUTIONS");
    private final String categoryName;


    ItemCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
