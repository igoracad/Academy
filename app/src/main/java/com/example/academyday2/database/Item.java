package com.example.academyday2.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String itemName;

    @ColumnInfo(name = "price")
    private double itemPrice;

    @ColumnInfo(name = "quantity")
    private int quantityInStock;

    // in JAVA need to have constructor
    // and also getters and setters

    public Item(int id, String itemName, double itemPrice, int quantityInStock) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantityInStock = quantityInStock;
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    // in JAVA to show data
    @Override
    public String toString() {
        return "Item Name: " + itemName + ", Price: " + itemPrice + ", Quantity: " + quantityInStock;
    }
}
