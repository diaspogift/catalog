package com.dddtraining.catalog.domain.model.product;

import javax.persistence.Embeddable;

import com.dddtraining.catalog.domain.model.category.Category;


@Embeddable
public class ProductCategory {

	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	private String categoryImage;
	
	public ProductCategory(){}
	public ProductCategory(String categoryId, String categoryName, String categoryDescription, String categoryImage) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.categoryImage = categoryImage;
	}
	
	public ProductCategory(Category category){
		super();
		this.categoryId = category.getId();
		this.categoryName = category.getName();
		this.categoryDescription = category.getDescription();
		this.categoryImage = category.getImage();
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public String getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}
	@Override
	public String toString() {
		return "ProductCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + ", categoryImage=" + categoryImage + "]";
	}
	
	
	
	
}
