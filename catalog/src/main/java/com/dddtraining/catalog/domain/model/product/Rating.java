package com.dddtraining.catalog.domain.model.product;

import javax.persistence.Embeddable;

@Embeddable
public class Rating {

	private int ratingTotal;
	private int ratingCount;
	
	public Rating(){}
	
	public Rating(int ratingTotal, int ratingCount) {
		super();
		
		this.ratingTotal = ratingTotal;
		this.ratingCount = ratingCount;
	}
	
	public double rate(){
		return 5d*(ratingCount/ratingTotal);
	}
	
	public int getRatingTotal() {
		return ratingTotal;
	}
	public void setRatingTotal(int ratingsTotal) {
		this.ratingTotal = ratingsTotal;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingsCount) {
		this.ratingCount = ratingsCount;
	}
	
	
	
}
