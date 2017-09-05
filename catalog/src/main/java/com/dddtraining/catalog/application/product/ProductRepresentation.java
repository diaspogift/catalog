package com.dddtraining.catalog.application.product;

import java.math.BigDecimal;
import java.util.List;


import com.dddtraining.catalog.domain.model.product.*;

public class ProductRepresentation {
	
	private static final long serialVersionUID = 1L;
	
	private Integer nativeId;
	
	private String id;
	
	private ProductCategory category;
	
	private String name;
	
	private String description;

	private Title title;

	private List<String> images;
	
	private int residualQuantity;

	private Brand brand;
	
	private BigDecimal price;
	
	private Promotion promotion;
	
	private boolean inPromotion;
	private BigDecimal promotionPrice;
		
	private Rating rating;
	
	
	public ProductRepresentation(){
		
	}


    public ProductRepresentation(Integer nativeId, String id, ProductCategory category, String name, String description, Title title, List<String> images, int residualQuantity, Brand brand, BigDecimal price, Promotion promotion, boolean inPromotion, BigDecimal promotionPrice, Rating rating) {
        this.nativeId = nativeId;
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.title = title;
        this.images = images;
        this.residualQuantity = residualQuantity;
        this.brand = brand;
        this.price = price;
        this.promotion = promotion;
        this.inPromotion = inPromotion;
        this.promotionPrice = promotionPrice;
        this.rating = rating;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getNativeId() {
		return nativeId;
	}


	public void setNativeId(Integer nativeId) {
		this.nativeId = nativeId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ProductCategory getCategory() {
		return category;
	}


	public void setCategory(ProductCategory category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Title getTitle() {
		return title;
	}


	public void setTitle(Title title) {
		this.title = title;
	}


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public int getResidualQuantity() {
		return residualQuantity;
	}


	public void setResidualQuantity(int residualQuantity) {
		this.residualQuantity = residualQuantity;
	}


	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Promotion getPromotion() {
		return promotion;
	}


	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}


	public boolean isInPromotion() {
		return inPromotion;
	}


	public void setInPromotion(boolean inPromotion) {
		this.inPromotion = inPromotion;
	}


	public Rating getRating() {
		return rating;
	}


	public void setRating(Rating rating) {
		this.rating = rating;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


}
