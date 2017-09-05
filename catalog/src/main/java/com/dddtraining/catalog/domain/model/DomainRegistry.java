package com.dddtraining.catalog.domain.model;

import com.dddtraining.catalog.domain.model.product.event.ProductPromotedRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DomainRegistry implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext applicationContext;

    @Override
    public synchronized void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = anApplicationContext;
        }
    }

    public static ProductPromotedRepository productPromotedRepository() {
        return (ProductPromotedRepository) applicationContext.getBean(ProductPromotedRepository.class);
    }
}




