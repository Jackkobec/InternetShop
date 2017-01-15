package com.gmail.jackkobec.internetshop.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;

/**
 * <p>PageManager class for return needed page from properties file.
 * Created by Jack on 30.12.2016.
 */
public class PageManager {
    public static final Logger LOGGER = LogManager.getLogger(PageManager.class);

    private static PageManager pageManager;
    private ResourceBundle resourceBundle;

    private static final String PAGES_RESOURCE_BUNDLE = "pages";

    public static final String INDEX_PAGE = "INDEX_PAGE";
    public static final String REGISTRATION_PAGE = "REGISTRATION_PAGE";
    public static final String MAIN_PAGE = "MAIN_PAGE";
    public static final String ITEM_PAGE = "ITEM_PAGE";
    public static final String ORDER_PAGE = "ORDER_PAGE";
    public static final String ERROR_PAGE = "ERROR_PAGE";


    private PageManager() {
        resourceBundle = ResourceBundle.getBundle(PAGES_RESOURCE_BUNDLE);
    }

    //worked like a simple singleton
//    public static PageManager getPageManager() {
//        if (pageManager == null) {
//            pageManager = new PageManager();
//            pageManager.resourceBundle = ResourceBundle.getBundle(PAGES_RESOURCE_BUNDLE);
//        }
//        return pageManager;
//    }

    /**
     * @return PageManager instance.
     */
    public static PageManager getPageManager() {
        //worked with init in the constructor
        LOGGER.info("getPageManager");
        return (pageManager == null)
                ? pageManager = new PageManager()
                : pageManager;
    }

    /**
     * @param pageName
     * @return page from properties file(ResourceBundle) by page name.
     */
    public String getPage(String pageName) {
        return resourceBundle.getString(pageName);
    }

}
