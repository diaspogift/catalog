package com.dddtraining.catalog.domain.model.product;

import java.math.BigDecimal;

public class DiscountByPercentage implements Discount {

	private double percentage;
	
	
	public DiscountByPercentage(){
		
	}
	
	public DiscountByPercentage(double percentage) {
		super();
		this.percentage = percentage;
	}



	@Override
	public BigDecimal apply(Product product) {
		if(percentage < 0 || percentage > 100 ){
			throw new IllegalArgumentException("Invalid Percentage");
		}
		product.getPromotion().setStrategy(STRATEGY.TWO);
		double newPrice = product.getPrice().doubleValue()*(1d-percentage/100);
		return new BigDecimal(newPrice);
	}



}
