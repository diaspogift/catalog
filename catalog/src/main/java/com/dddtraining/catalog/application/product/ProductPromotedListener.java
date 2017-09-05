package com.dddtraining.catalog.application.product;

import com.dddtraining.catalog.domain.model.DomainRegistry;
import com.dddtraining.catalog.domain.model.product.event.DomainEventPublisher;
import com.dddtraining.catalog.domain.model.product.event.DomainEventSubscriber;
import com.dddtraining.catalog.domain.model.product.event.ProductPromoted;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ProductPromotedListener {
    @Autowired
    ProductPromotedService productPromotedService;

    @Before("execution(* com.dddtraining.catalog.application.product.ProductServiceImpl.putInPromotion(..))")
    public void listen(){
        System.out.println("\n\nBefore puting in promotion");
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<ProductPromoted>() {
            @Override
            public void handleEvent(ProductPromoted aDomainEvent) {
                System.out.println("\n\nSaving puting  promotion");
                productPromotedService.add(aDomainEvent);
            }

            @Override
            public Class<ProductPromoted> subscribedToEventType() {
                return ProductPromoted.class;
            }
        });
    }

    @After("execution(* com.dddtraining.catalog.application.product.ProductServiceImpl.putInPromotion(..))")
    public void unsubscribProductPromotedListener(){
        //DomainEventPublisher.instance().unSubscribe(ProductPromoted.class);
        //System.out.println("\n\nAfter puting in promotion");
    }
}
