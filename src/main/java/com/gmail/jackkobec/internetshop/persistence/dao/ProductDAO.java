package com.gmail.jackkobec.internetshop.persistence.dao;

import com.gmail.jackkobec.internetshop.persistence.model.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Created by Jack on 18.12.2016.
 */
public class ProductDAO {
    public static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);

    public boolean addProduct(Product product){
        LOGGER.info("Product added.");
        return true;
    }
}
