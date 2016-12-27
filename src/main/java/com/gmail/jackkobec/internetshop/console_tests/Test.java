package com.gmail.jackkobec.internetshop.console_tests;

import com.gmail.jackkobec.internetshop.dao.ProductDAO;
import com.gmail.jackkobec.internetshop.model.Product;

import java.math.BigDecimal;

/**
 * Created by Jack on 18.12.2016.
 */
public class Test {

    public static void main(String[] args) {

        new ProductDAO().addProduct(new Product());
//        System.out.println(0.1+0.2);//out: 0.30000000000000004
//        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));//out: 0.3
    }
}
