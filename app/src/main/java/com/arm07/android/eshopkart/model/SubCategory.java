package com.arm07.android.eshopkart.model;

/**
 * Created by rashmi on 11/27/2017.
 */

public class SubCategory {
    public final String ID;
    public final String SubCatagoryName;
    public final String SubCatagoryDiscription;
    public final String CatagoryImage;
    public SubCategory(String ID, String SubCatagoryName, String SubCatagoryDiscription,String CategoryImage){
        this.ID = ID;
        this.SubCatagoryName = SubCatagoryName;
        this.SubCatagoryDiscription = "Category Description: " + SubCatagoryDiscription;
        this.CatagoryImage = CategoryImage;
    }

    public String getID() {
        return ID;
    }

    public String getSubCatagoryName() {
        return SubCatagoryName;
    }

    public String getSubCatagoryDiscription() {
        return SubCatagoryDiscription;
    }

    public String getCatagoryImage() {
        return CatagoryImage;
    }
}
