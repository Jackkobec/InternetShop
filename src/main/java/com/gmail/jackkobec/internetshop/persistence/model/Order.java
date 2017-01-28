package com.gmail.jackkobec.internetshop.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 18.12.2016.
 */
@Data
public class Order implements Serializable {

    private Integer id;
    private Integer userId;

    private Date orderDateAndTime;
    private BigDecimal summaryPrice;
    private Integer orderStatus;

    private List<Item> itemList;

    public Order() {
    }

    public Order(Integer userId, Date orderDateAndTime, BigDecimal summaryPrice, Integer orderStatus, List<Item> itemList) {
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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