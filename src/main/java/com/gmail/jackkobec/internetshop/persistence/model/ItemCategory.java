package com.gmail.jackkobec.internetshop.persistence.model;

import java.io.Serializable;

/**
 * Created by Jack on 27.12.2016.
 */
public enum ItemCategory implements Serializable {

    FOR_SELF_DEFENSE("FOR SELF-DEFENSE" , 1), FOR_RESPECT("FOR RESPECT", 2), ULTIMATE_SOLUTIONS("ULTIMATE SOLUTIONS", 3);

    private static final String CATEGORY_FOR_SELF_DEFENSE = "category.for_self_defense";
    private static final String CATEGORY_FOR_RESPECT = "category.for_respect";
    private static final String CATEGORY_ULTIMATE_SOLUTIONS = "category.ultimate_solutions";

    private final String categoryName;
    private final Integer categoryId;

    ItemCategory(String categoryName, Integer categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public static ItemCategory getItemCategoryByID(final Integer categoryId){

        switch (categoryId){

            case 1: return FOR_SELF_DEFENSE;
            case 2: return FOR_RESPECT;
            case 3: return ULTIMATE_SOLUTIONS;
        }

        return null;
    }

    public static String getItemCategoryResourceBundleByID(final Integer categoryId){

        switch (categoryId){

            case 1: return CATEGORY_FOR_SELF_DEFENSE;
            case 2: return CATEGORY_FOR_RESPECT;
            case 3: return CATEGORY_ULTIMATE_SOLUTIONS;
        }

        return null;
    }
}
