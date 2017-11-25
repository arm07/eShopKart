package com.arm07.android.eshopkart.model;

public class CategoryItem{


	private String catagoryImage;

	private String id;

	private String catagoryName;

	private String catagoryDiscription;

	public void setCatagoryImage(String catagoryImage){
		this.catagoryImage = catagoryImage;
	}

	public String getCatagoryImage(){
		return catagoryImage;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCatagoryName(String catagoryName){
		this.catagoryName = catagoryName;
	}

	public String getCatagoryName(){
		return catagoryName;
	}

	public void setCatagoryDiscription(String catagoryDiscription){
		this.catagoryDiscription = catagoryDiscription;
	}

	public String getCatagoryDiscription(){
		return catagoryDiscription;
	}

	public CategoryItem( String id, String catagoryName, String catagoryDiscription, String catagoryImage) {
		this.id = id;
		this.catagoryName = catagoryName;
		this.catagoryDiscription = catagoryDiscription;
		this.catagoryImage = catagoryImage;
	}

	@Override
 	public String toString(){
		return
				"CategoryItem{"+
						"id='"+id+ '\'' +
						",catagoryName = '" + catagoryName + '\'' +
						",catagoryDiscription = '" + catagoryDiscription + '\'' +
						",catagoryImage = '" + catagoryImage + '\'' +
						"}";
			/*"CategoryItem{" +
			"catagoryImage = '" + catagoryImage + '\'' +
			",id = '" + id + '\'' +
			",catagoryName = '" + catagoryName + '\'' + 
			",catagoryDiscription = '" + catagoryDiscription + '\'' +
			"}"*/
		}
}