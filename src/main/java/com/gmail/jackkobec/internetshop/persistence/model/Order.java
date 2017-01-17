package com.gmail.jackkobec.internetshop.persistence.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Jack on 18.12.2016.
 */
@Data
public class Order {

    private Integer id;
    private Integer user_id;

    private LocalDateTime orderDateAndTime;
    private BigDecimal summaryPrice;
    private Integer orderStatus;

    private List<Item> itemList;

    public Order() {
    }

    public Order(Integer user_id, LocalDateTime orderDateAndTime, BigDecimal summaryPrice, Integer orderStatus, List<Item> itemList) {
        this.user_id = user_id;
        this.orderDateAndTime = orderDateAndTime;
        this.summaryPrice = summaryPrice;
        this.orderStatus = orderStatus;
        this.itemList = itemList;
    }
}
