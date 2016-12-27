package com.gmail.jackkobec.internetshop.model;

/**
 * Created by Jack on 27.12.2016.
 */
public class OrderItem {

    private Integer orderId;
    private Integer itemId;

    public OrderItem() {
    }

    public OrderItem(Integer orderId, Integer itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (orderId != null ? !orderId.equals(orderItem.orderId) : orderItem.orderId != null) return false;
        return itemId != null ? itemId.equals(orderItem.itemId) : orderItem.itemId == null;

    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                '}';
    }
}
