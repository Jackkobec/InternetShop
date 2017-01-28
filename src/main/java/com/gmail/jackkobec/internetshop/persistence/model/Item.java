package com.gmail.jackkobec.internetshop.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jack on 27.12.2016.
 */
public class Item implements Serializable {

    private Integer id;
    private String itemName;
    private String itemSmallDescription;
    private String itemFullDescription;
    private String itemProductInfo;

    private BigDecimal itemPrice;

    private String itemBigPicturePath800x600;
    private String itemSmallPicturePath350x260;
    private Integer itemRating;

    private ItemCategory itemCategory;
    private ItemStatus itemStatus;

    public Item() {
    }

    public Item(Integer id, String itemName, String itemSmallDescription, String itemFullDescription, String itemProductInfo, BigDecimal itemPrice, String itemBigPicturePath800x600, String itemSmallPicturePath350x260, Integer itemRating, ItemCategory itemCategory, ItemStatus itemStatus) {
        this.id = id;
        this.itemName = itemName;
        this.itemSmallDescription = itemSmallDescription;
        this.itemFullDescription = itemFullDescription;
        this.itemProductInfo = itemProductInfo;
        this.itemPrice = itemPrice;
        this.itemBigPicturePath800x600 = itemBigPicturePath800x600;
        this.itemSmallPicturePath350x260 = itemSmallPicturePath350x260;
        this.itemRating = itemRating;
        this.itemCategory = itemCategory;
        this.itemStatus = itemStatus;
    }

    public Item(String itemName, String itemSmallDescription, String itemFullDescription, String itemProductInfo, BigDecimal itemPrice, String itemBigPicturePath800x600, String itemSmallPicturePath350x260, Integer itemRating, ItemCategory itemCategory, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.itemSmallDescription = itemSmallDescription;
        this.itemFullDescription = itemFullDescription;
        this.itemProductInfo = itemProductInfo;
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

    public String getItemSmallDescription() {
        return itemSmallDescription;
    }

    public void setItemSmallDescription(String itemSmallDescription) {
        this.itemSmallDescription = itemSmallDescription;
    }

    public String getItemFullDescription() {
        return itemFullDescription;
    }

    public void setItemFullDescription(String itemFullDescription) {
        this.itemFullDescription = itemFullDescription;
    }

    public String getItemProductInfo() {
        return itemProductInfo;
    }

    public void setItemProductInfo(String itemProductInfo) {
        this.itemProductInfo = itemProductInfo;
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

    public void setItemCategory(final Integer itemCategoryId) {

        switch (itemCategoryId) {
            case 1:
                this.itemCategory = ItemCategory.FOR_SELF_DEFENSE;
                break;
            case 2:
                this.itemCategory = ItemCategory.FOR_RESPECT;
                break;
            case 3:
                this.itemCategory = ItemCategory.ULTIMATE_SOLUTIONS;
                break;
        }
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(final Integer itemStatusId) {

        switch (itemStatusId) {
            case 1:
                this.itemStatus = ItemStatus.PRESENT;
                break;
            case 2:
                this.itemStatus = ItemStatus.NOT_PRESENT;
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null) return false;
        if (itemSmallDescription != null ? !itemSmallDescription.equals(item.itemSmallDescription) : item.itemSmallDescription != null)
            return false;
        if (itemFullDescription != null ? !itemFullDescription.equals(item.itemFullDescription) : item.itemFullDescription != null)
            return false;
        if (itemProductInfo != null ? !itemProductInfo.equals(item.itemProductInfo) : item.itemProductInfo != null)
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
        result = 31 * result + (itemSmallDescription != null ? itemSmallDescription.hashCode() : 0);
        result = 31 * result + (itemFullDescription != null ? itemFullDescription.hashCode() : 0);
        result = 31 * result + (itemProductInfo != null ? itemProductInfo.hashCode() : 0);
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
                ", itemSmallDescription='" + itemSmallDescription + '\'' +
                ", itemFullDescription='" + itemFullDescription + '\'' +
                ", itemProductInfo='" + itemProductInfo + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemBigPicturePath800x600='" + itemBigPicturePath800x600 + '\'' +
                ", itemSmallPicturePath350x260='" + itemSmallPicturePath350x260 + '\'' +
                ", itemRating=" + itemRating +
                ", itemCategory=" + itemCategory +
                ", itemStatus=" + itemStatus +
                '}';
    }
}
