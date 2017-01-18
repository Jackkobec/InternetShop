package com.gmail.jackkobec.internetshop.persistence.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 18.12.2016.
 */
@Data
public class Order {

    private Integer id;
    private Integer user_id;

    private Date orderDateAndTime;
    private BigDecimal summaryPrice;
    private Integer orderStatus;

    private List<Item> itemList;

    public Order() {
    }

    public Order(Integer user_id, Date orderDateAndTime, BigDecimal summaryPrice, Integer orderStatus, List<Item> itemList) {
        this.user_id = user_id;
        this.orderDateAndTime = orderDateAndTime;
        this.summaryPrice = summaryPrice;
        this.orderStatus = orderStatus;
        this.itemList = itemList;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getOrderDateAndTime() {
        return orderDateAndTime;
    }

    public void setOrderDateAndTime(Date orderDateAndTime) {
        this.orderDateAndTime = orderDateAndTime;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}