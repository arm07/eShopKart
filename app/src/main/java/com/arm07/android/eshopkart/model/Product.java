package com.arm07.android.eshopkart.model;

/**
 * Created by rashmi on 11/25/2017.
 */

public class Product {

    public final String ID;
    public final String ProductName;
    public final String Quantity;
    public final String Price;
    public final String Description;
    public final String Image;

    public Product(String ID, String ProductName, String Quantity, String Price, String Description,String Image){
        this.ID = ID;
        this.ProductName = ProductName;
        this.Quantity =  Quantity;
        this.Price = Price;
        this.Description = "Description: " + Description;
        this.Image = Image;
    }

    /*public Product(String ID, String productName, String description, String image) {
        this.ID = ID;
        ProductName = productName;
        Description = description;
        Image = image;
    }*/

    public String getID() {
        return ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }



}
