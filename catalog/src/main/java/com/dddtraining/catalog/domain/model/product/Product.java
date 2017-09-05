package com.dddtraining.catalog.domain.model.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

import javax.persistence.*;

import com.dddtraining.catalog.domain.model.DomainRegistry;
import com.dddtraining.catalog.domain.model.product.event.DomainEventPublisher;
import com.dddtraining.catalog.domain.model.product.event.DomainEventSubscriber;
import com.dddtraining.catalog.domain.model.product.event.ProductPromoted;
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

	@Column(unique=true)
	private String id;
	
	@Embedded
	private ProductCategory category;
	
	private String name;
	
	private String description;
	
	@Embedded
	private Title title;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> images;
	
	private int residualQuantity;
	
	@Embedded
	private Brand brand;
	
	private BigDecimal price;


    private BigDecimal promotionPrice;


    @SuppressWarnings("unused")
    private boolean inPromotion;

    @Embedded
    private  Promotion promotion;
	
	@Embedded
	private Rating rating;
	
	@SuppressWarnings("unused")
	private Product(){}
	
	public Product(String id, Category category, String name, String description, Title title, List<String> images,
			int residualQuantity, Brand brand, BigDecimal price) {
		super();
		this.setId(id);
		this.setCategory(new ProductCategory(category.getId(), category.getName(), category.getDescription(), category.getImage()));
		this.setName(name);
		this.setDescription(description);
		this.setImages(images);
		this.setResidualQuantity(residualQuantity);
		this.setBrand(brand);
		this.updateTitle();
		this.setPrice(price);
		this.setPromotionPrice(null);
		this.setInPromotion(false);
		this.setPromotion(null);
		this.rating = new Rating(5, 0);
	}
	
	

	/**
	 * 
	 * Setters
	 */

	private void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	private void setInPromotion(boolean inPromotion2) {
		this.inPromotion = inPromotion2;
	}


	public boolean isInPromotion() {
		return inPromotion;
	}

	private void setBrand(Brand brand2) {
		this.brand = brand2;
	}

	private void setResidualQuantity(int residualQuantity2) {
		this.residualQuantity = residualQuantity2;
	}

	private void setImages(List<String> images2) {
		this.images = images2;
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

    private void setPromotionPrice(BigDecimal promotionPrice){
        this.promotionPrice = promotionPrice;
    }
	
	@SuppressWarnings("unused")
	/*private void setDiscount(Discount discount) {
		this.discount = discount;
	}*/

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



	public List<String> getImages() {
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

    /*public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }*/
	
	@SuppressWarnings("unused")
	private void updateResidualQuantity(int aQuantity){
		this.setResidualQuantity(aQuantity);
	}
	
	public void changeResidualQuantity(int newResidualQuantity){
		this.setResidualQuantity(newResidualQuantity);
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
		if(index < 0 || index >= images.size()){
			return false;
		}

		this.getImages().set(index, image);

		return true;
	}
	
	public void updateBrand(Brand newBrand) {
		this.setBrand(newBrand);
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




    public boolean putInPromotion(Promotion promotion) {
        if (promotion == null){
            return  false;
        }
        if (promotion.inCourse()){
            this.setPromotion(promotion);
            this.setInPromotion(true);
            this.setPromotionPrice(promotion.getDiscount().apply(this));
            DomainEventPublisher.instance().publish(new ProductPromoted(this.id,this.price, this.promotionPrice, promotion));
            return true;
        }
        return false;
    }

    public boolean removePromotion() {
	    this.promotion = null;
        this.setInPromotion(false);
        this.setPromotionPrice(null);
	    return true;
    }

    public BigDecimal getPromotionPrice(){
        return this.promotionPrice;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public boolean changeImages(List<String> newImages) {
        this.setImages(newImages);
        return true;
    }


    @Override
    public String toString() {
        return "\n\nProduct{" +
                "\nnativeId=" + nativeId +
                ", \nid='" + id + '\'' +
                ", \ncategory=" + category +
                ", \nname='" + name + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \ntitle=" + title +
                ", \nimages='" + images + '\'' +
                ", \nresidualQuantity=" + residualQuantity +
                ", \nbrand=" + brand +
                ", \nprice=" + price +
                ", \npromotionPrice=" + promotionPrice +
                ", \ninPromotion=" + inPromotion +
                ", \npromotion=" + promotion +
                ", \nrating=" + rating +
                '}';
    }


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		return id.equals(product.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
