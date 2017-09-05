package com.dddtraining.catalog.application.product;

import com.dddtraining.catalog.domain.model.product.event.ProductPromoted;
import com.dddtraining.catalog.domain.model.product.event.ProductPromotedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPromotedServiceImpl implements ProductPromotedService {
    @Autowired
    private ProductPromotedRepository repo;

    @Override
    public void add(ProductPromoted productPromoted) {
        repo.add(productPromoted);
    }
}
