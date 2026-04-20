package com.stationery.model;

import java.math.BigDecimal;
import java.util.Date;

public class StationeryItem {

    private int itemId;
    private String name;
    private int categoryId;
    private String categoryName;
    private String brand;
    private BigDecimal price;
    private int quantityInStock;
    private String description;
    private Date dateAdded;

    public StationeryItem() {}

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDateAdded() { return dateAdded; }
    public void setDateAdded(Date dateAdded) { this.dateAdded = dateAdded; }
}
   
