package com.gmail.jackkobec.internetshop.persistence.model;

/**
 * Created by Jack on 27.12.2016.
 */
public enum ItemCategory {

    CATEGORY1("For Protection"), CATEGORY2("For Respect");
    private final String categoryName;


    ItemCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
