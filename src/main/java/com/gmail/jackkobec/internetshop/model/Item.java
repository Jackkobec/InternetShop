package com.gmail.jackkobec.internetshop.model;

import java.math.BigDecimal;

/**
 * Created by Jack on 27.12.2016.
 */
public class Item {

    private Integer id;
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;

    private ItemCategory itemCategory;
    private ItemStatus itemStatus;

    public Item() {
    }

    public Item(Integer id, String itemName, String itemDescription, BigDecimal itemPrice, ItemCategory itemCategory, ItemStatus itemStatus) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemStatus = itemStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null) return false;
        if (itemDescription != null ? !itemDescription.equals(item.itemDescription) : item.itemDescription != null)
            return false;
        if (itemPrice != null ? !itemPrice.equals(item.itemPrice) : item.itemPrice != null) return false;
        if (itemCategory != item.itemCategory) return false;
        return itemStatus == item.itemStatus;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemDescription != null ? itemDescription.hashCode() : 0);
        result = 31 * result + (itemPrice != null ? itemPrice.hashCode() : 0);
        result = 31 * result + (itemCategory != null ? itemCategory.hashCode() : 0);
        result = 31 * result + (itemStatus != null ? itemStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemCategory=" + itemCategory +
                ", itemStatus=" + itemStatus +
                '}';
    }
}
