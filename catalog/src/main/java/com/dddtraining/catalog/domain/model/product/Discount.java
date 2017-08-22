package com.dddtraining.catalog.domain.model.product;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public interface Discount {

	public BigDecimal apply(Product product);
}
