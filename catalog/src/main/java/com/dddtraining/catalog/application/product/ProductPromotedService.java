package com.dddtraining.catalog.application.product;

import com.dddtraining.catalog.domain.model.product.event.ProductPromoted;
import com.dddtraining.catalog.domain.model.product.event.ProductPromotedRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public interface ProductPromotedService {

    public void add(ProductPromoted productPromoted);
}
