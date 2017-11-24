package com.arm07.android.eshopkart.model;

import java.util.List;



public class FeaturedResponse{

	private List<CategoryItem> category;

	public void setCategory(List<CategoryItem> category){
		this.category = category;
	}

	public List<CategoryItem> getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"FeaturedResponse{" + 
			"category = '" + category + '\'' + 
			"}";
		}
}