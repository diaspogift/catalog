package com.dddtraining.catalog.application.product;

import java.math.BigDecimal;



import com.dddtraining.catalog.domain.model.product.Brand;
import com.dddtraining.catalog.domain.model.product.ProductCategory;
import com.dddtraining.catalog.domain.model.product.Rating;
import com.dddtraining.catalog.domain.model.product.Title;

public class ProductRepresentation {
	
	private static final long serialVersionUID = 1L;
	
	private Integer nativeId;
	
	private String id;
	
	private ProductCategory category;
	
	private String name;
	
	private String description;

	private Title title;

	private String images;
	
	private int residualQuantity;

	private Brand brand;
	
	private BigDecimal price;
	
	private BigDecimal promotionPrice;
	
	private boolean inPromotion;
		
	private Rating rating;
	
	private int strategy;
	
	private BigDecimal reduction;
	
	private Float percentage;
	
	
	public ProductRepresentation(){
		
	}


	public ProductRepresentation(Integer nativeId, String id, ProductCategory category, String name, String description,
			Title title, String images, int residualQuantity, Brand brand, BigDecimal price, BigDecimal promotionPrice,
			boolean inPromotion, Rating rating, int strategy, BigDecimal reduction, Float percentage) {
		super();
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
		this.promotionPrice = promotionPrice;
		this.inPromotion = inPromotion;
		this.rating = rating;
		this.strategy = strategy;
		this.reduction = reduction;
		this.percentage = percentage;
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


	public String getImages() {
		return images;
	}


	public void setImages(String images) {
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


	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}


	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
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


	public int getStrategy() {
		return strategy;
	}


	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}


	public BigDecimal getReduction() {
		return reduction;
	}


	public void setReduction(BigDecimal reduction) {
		this.reduction = reduction;
	}


	public Float getPercentage() {
		return percentage;
	}


	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
