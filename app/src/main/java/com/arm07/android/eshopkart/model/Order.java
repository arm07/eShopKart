package com.arm07.android.eshopkart.model;

/**
 * Created by rashmi on 11/29/2017.
 */

public class Order {

    public final String OrderID;
    public final String ItemName;
    public final String ItemQuantity;
    public final String FinalPrice;
    public final String OrderStatus;

    public Order(String orderID, String itemName, String itemQuantity, String finalPrice, String orderStatus) {
        this.OrderID = orderID;
        this.ItemName = itemName;
        this.ItemQuantity = "Quantity: " + itemQuantity;
        this.FinalPrice = "Final Price: " + finalPrice;
        this.OrderStatus = "Order Status: " + orderStatus;
    }
}
