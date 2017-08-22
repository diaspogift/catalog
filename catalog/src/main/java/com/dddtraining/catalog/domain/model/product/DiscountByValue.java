package com.dddtraining.catalog.domain.model.product;

import java.math.BigDecimal;

public class DiscountByValue implements Discount {

	private BigDecimal delta;
	
	
	public DiscountByValue(){}
	
	public DiscountByValue(BigDecimal delta) {
		super();
		this.delta = delta;
	}


	@Override
	public BigDecimal apply(Product product) {
		if(delta.compareTo(new BigDecimal(0)) == -1 || delta.compareTo(product.getPrice()) == 1)
			throw new IllegalArgumentException("Invalid Discount");
		double rest = product.getPrice().doubleValue() - delta.doubleValue();
		return new BigDecimal(rest);
	}

}
