package com.gmail.jackkobec.internetshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Jack on 18.12.2016.
 */
public class Order {

    private Integer id;
    private LocalDateTime dateAndTumeOfOrderCreation;
    private BigDecimal summaryPruce;
    private List<Product> productList;

    private User user;
    private OrderStatus orderStatus;


    public Order() {
    }

    public Order(Integer id, LocalDateTime dateAndTumeOfOrderCreation, BigDecimal summaryPruce, List<Product> productList, User user, OrderStatus orderStatus) {
        this.id = id;
        this.dateAndTumeOfOrderCreation = dateAndTumeOfOrderCreation;
        this.summaryPruce = summaryPruce;
        this.productList = productList;
        this.user = user;
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTumeOfOrderCreation() {
        return dateAndTumeOfOrderCreation;
    }

    public void setDateAndTumeOfOrderCreation(LocalDateTime dateAndTumeOfOrderCreation) {
        this.dateAndTumeOfOrderCreation = dateAndTumeOfOrderCreation;
    }

    public BigDecimal getSummaryPruce() {
        return summaryPruce;
    }

    public void setSummaryPruce(BigDecimal summaryPruce) {
        this.summaryPruce = summaryPruce;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (dateAndTumeOfOrderCreation != null ? !dateAndTumeOfOrderCreation.equals(order.dateAndTumeOfOrderCreation) : order.dateAndTumeOfOrderCreation != null)
            return false;
        if (summaryPruce != null ? !summaryPruce.equals(order.summaryPruce) : order.summaryPruce != null) return false;
        if (productList != null ? !productList.equals(order.productList) : order.productList != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return orderStatus == order.orderStatus;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateAndTumeOfOrderCreation != null ? dateAndTumeOfOrderCreation.hashCode() : 0);
        result = 31 * result + (summaryPruce != null ? summaryPruce.hashCode() : 0);
        result = 31 * result + (productList != null ? productList.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateAndTumeOfOrderCreation=" + dateAndTumeOfOrderCreation +
                ", summaryPruce=" + summaryPruce +
                ", productList=" + productList +
                ", user=" + user +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
