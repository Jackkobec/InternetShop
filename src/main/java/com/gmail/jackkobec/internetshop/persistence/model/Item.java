package com.gmail.jackkobec.internetshop.persistence.model;

import java.math.BigDecimal;

/**
 * Created by Jack on 27.12.2016.
 */
public class Item {

    private Integer id;
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;

    private String itemBigPicturePath800x600;
    private String itemSmallPicturePath350x260;
    private Integer itemRating;

    private ItemCategory itemCategory;
    private ItemStatus itemStatus;

    public Item() {
    }

    public Item(Integer id, String itemName, String itemDescription, BigDecimal itemPrice, String itemBigPicturePath800x600, String itemSmallPicturePath350x260, Integer itemRating, ItemCategory itemCategory, ItemStatus itemStatus) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemBigPicturePath800x600 = itemBigPicturePath800x600;
        this.itemSmallPicturePath350x260 = itemSmallPicturePath350x260;
        this.itemRating = itemRating;
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

    public String getItemBigPicturePath800x600() {
        return itemBigPicturePath800x600;
    }

    public void setItemBigPicturePath800x600(String itemBigPicturePath800x600) {
        this.itemBigPicturePath800x600 = itemBigPicturePath800x600;
    }

    public String getItemSmallPicturePath350x260() {
        return itemSmallPicturePath350x260;
    }

    public void setItemSmallPicturePath350x260(String itemSmallPicturePath350x260) {
        this.itemSmallPicturePath350x260 = itemSmallPicturePath350x260;
    }

    public Integer getItemRating() {
        return itemRating;
    }

    public void setItemRating(Integer itemRating) {
        this.itemRating = itemRating;
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
        if (itemBigPicturePath800x600 != null ? !itemBigPicturePath800x600.equals(item.itemBigPicturePath800x600) : item.itemBigPicturePath800x600 != null)
            return false;
        if (itemSmallPicturePath350x260 != null ? !itemSmallPicturePath350x260.equals(item.itemSmallPicturePath350x260) : item.itemSmallPicturePath350x260 != null)
            return false;
        if (itemRating != null ? !itemRating.equals(item.itemRating) : item.itemRating != null) return false;
        if (itemCategory != item.itemCategory) return false;
        return itemStatus == item.itemStatus;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemDescription != null ? itemDescription.hashCode() : 0);
        result = 31 * result + (itemPrice != null ? itemPrice.hashCode() : 0);
        result = 31 * result + (itemBigPicturePath800x600 != null ? itemBigPicturePath800x600.hashCode() : 0);
        result = 31 * result + (itemSmallPicturePath350x260 != null ? itemSmallPicturePath350x260.hashCode() : 0);
        result = 31 * result + (itemRating != null ? itemRating.hashCode() : 0);
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
                ", itemBigPicturePath800x600='" + itemBigPicturePath800x600 + '\'' +
                ", itemSmallPicturePath350x260='" + itemSmallPicturePath350x260 + '\'' +
                ", itemRating=" + itemRating +
                ", itemCategory=" + itemCategory +
                ", itemStatus=" + itemStatus +
                '}';
    }
}
