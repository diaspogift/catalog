package com.dddtraining.catalog.domain.model.product;

import java.math.BigDecimal;

public class DefaultDiscount implements Discount {

	public DefaultDiscount(){}
	@Override
	public BigDecimal apply(Product product) {
		
		return product.getPrice();
	}

}
