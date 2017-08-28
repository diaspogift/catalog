package com.dddtraining.catalog.domain.model.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dddtraining.catalog.domain.model.category.Category;

@Entity 
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer nativeId;
	
	private String id;
	
	@Embedded
	private ProductCategory category;
	
	private String name;
	
	private String description;
	
	@Embedded
	private Title title;
	
	@Column(length = 5000)
	private String images;
	
	private int residualQuantity;
	
	@Embedded
	private Brand brand;
	
	private BigDecimal price;
	
	private BigDecimal promotionPrice;
	

	@SuppressWarnings("unused")
	private boolean inPromotion;
	
	@Embedded
	private Discount discount;
	
	@Embedded
	private Rating rating;
	
	@SuppressWarnings("unused")
	private Product(){}
	
	public Product(String id, Category category, String name, String description, Title title, List<String> images,
			int residualQuantity, Brand brand, BigDecimal price) {
		super();
		discount = new DefaultDiscount();
		this.setId(id);
		this.setCategory(new ProductCategory(category.getId(), category.getName(), category.getDescription(), category.getImage()));
		this.setName(name);
		this.setDescription(description);
		this.setImages(images);
		this.setResidualQuantity(residualQuantity);
		this.setBrand(brand);
		this.updateTitle();
		this.setPrice(price);
		this.setInPromotion(false);
		this.setPromotionPrice(discount.apply(this));
		this.rating = new Rating(5, 0);
	}
	
	

	/**
	 * 
	 * Setters
	 */
	
	
	private void setPromotionPrice(BigDecimal promotionPrice2) {
		this.promotionPrice = promotionPrice2;
	}

	private void setInPromotion(boolean inPromotion2) {
		this.inPromotion = inPromotion2;
	}
	
	public boolean isInPromotion() {
		return (this.price.compareTo(promotionPrice) == 1);//this.inPromotion;
	}
	
	

	private void setBrand(Brand brand2) {
		this.brand = brand2;
	}

	private void setResidualQuantity(int residualQuantity2) {
		this.residualQuantity = residualQuantity2;
	}

	private void setImages(List<String> images2) {
		JSONArray jsonArray = new JSONArray();
		
		for(int i = 0; i<images2.size(); i++){
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("" + i, images2.get(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		this.images = jsonArray.toString();
	}

	private void setDescription(String description2) {
		this.description = description2;
	}

	private void setName(String name2) {
		this.name = name2;
	}

	public Product changeCategory(ProductCategory productCategory){
		this.setCategory(productCategory);
		return this;
	}
	private void setCategory(ProductCategory categoryId2) {
		this.category = categoryId2;
	}

	private void setId(String id2) {
		this.id = id2;		
	}

	private void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	private void setTitle(Title title) {
		this.title = title;
	}
	
	@SuppressWarnings("unused")
	private void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	/*
	 * Getters
	 */
	
	public BigDecimal getPrice() {
		return price;
	}

	public Title getTitle() {
		return title;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}
	
	public Discount getDiscount() {
		return discount;
	}

	public String getId() {
		return id;
	}



	public ProductCategory getCategory() {
		return category;
	}



	public String getName() {
		return name;
	}



	public String getDescription() {
		return description;
	}



	public String getImages() {
		return images;
	}



	public int getResidualQuantity() {
		return residualQuantity;
	}



	public Brand getBrand() {
		return brand;
	}



	public Rating getRating() {
		return rating;
	}

	
	@SuppressWarnings("unused")
	private void updateResidualQuantity(int aQuantity){
		this.setResidualQuantity(aQuantity);
	}
	
	public void changeResidualQuantity(int newResidualQuantity){
		this.setResidualQuantity(newResidualQuantity);
	}

	public boolean applyDiscount(Discount discount) {
		this.discount = discount;
		setPromotionPrice(this.discount.apply(this));
		this.inPromotion = (this.price.compareTo(promotionPrice) == 1);
		return true;
	}


	public double getRate() {
		return rating.rate();
	}
	
	public void changePrice(BigDecimal newPrice){
		this.setPrice(newPrice);
	}

	public void changeDescription(String newDescription){
		this.setDescription(newDescription);
		updateTitle();
	}
	
	public void updateTitle(){
		Title titre = new Title(this.name, this.description, this.brand.getName());
		this.setTitle(titre);
	}

	public boolean updateImage(String image, int index){
		
		JSONArray jsonArray = null;
		try {
			jsonArray = new  JSONArray(this.images);
		} catch (JSONException e1) {
			return false;
		}
		
		int imagesSize = jsonArray.length();
		if(image.trim().equals("")){
			//throw new IllegalArgumentException("");
			return false;
		}
		if(index < 0 || index >= imagesSize){
			index = Math.round(Math.abs(index))%imagesSize;
		}
		
		JSONObject jsonObject;
		try {
			jsonObject = jsonArray.getJSONObject(index);
			jsonObject.put("" + index, image);
			
		} catch (JSONException e) {
			return false;
		}
		return true;
		
		//this.images.get(index).replace(this.images.get(index), image.trim());
		
	}
	
	public void updateBrand(Brand newBrand) {
		this.setBrand(newBrand);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "\n\nProduct [id=" + id + ", categoryId=" + category + ", name=" + name + ", description=" + description
				+ ", title=" + title + ", residualQuantity=" + residualQuantity + ", brand=" + brand + "]\n\n";
	}

	public long getNativeId() {
		return nativeId;
	}

	@SuppressWarnings("unused")
	private void setNativeId(Integer nativeId) {
		this.nativeId = nativeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	

}
