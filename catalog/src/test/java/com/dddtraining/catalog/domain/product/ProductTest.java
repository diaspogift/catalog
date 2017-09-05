package com.dddtraining.catalog.domain.product;

import static org.junit.Assert.*;

import com.dddtraining.catalog.application.product.ProductPromotedService;
import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.product.*;
import com.dddtraining.catalog.domain.model.product.event.DomainEventPublisher;
import com.dddtraining.catalog.domain.model.product.event.DomainEventSubscriber;
import com.dddtraining.catalog.domain.model.product.event.ProductPromoted;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    private Category category;
    private Product product;

    @Autowired
    private ProductPromotedService productPromotedService;

    @Before
    public void init(){
        category = new Category(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                "Painture", "Tout pour les embellissement",
                "/home/diapogift/img/catalog/category/painture.png"
        );

        List<String> images = new ArrayList<>();
        images.add("catalog/product/paintahuile1.png");
        images.add("catalog/product/paintahuile2.png");
        images.add("catalog/product/paintahuile3.png");
        images.add("catalog/product/paintahuile4.png");
        images.add("catalog/product/paintahuile5.png");

        product = new Product(
           UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
           category,
           "Peinture a huile email 10l",
                "Bonne peinture de qualite",
                null,
                images,
                250,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/paintahuile.png"),
                new BigDecimal(8450)
        );

    }

    @Test
    public void createProduct(){
        assertNotNull(product);
        assertEquals((new ProductCategory(category)).getCategoryId(), product.getCategory().getCategoryId());
        assertEquals("Peinture a huile email 10l", product.getName());
        assertEquals("Bonne peinture de qualite", product.getDescription());
        assertEquals(5, product.getImages().size());
        assertEquals("Peinture a huile email 10l Bonne peinture de qualite Italy Paint", product.getTitle().getTitle());
        assertEquals("Peinture a huile email 10l", product.getName());
        assertEquals(250, product.getResidualQuantity());
        assertEquals("Italy Paint", product.getBrand().getName());
        assertEquals("/home/nkalla/img/dddtraining/product/brand/paintahuile.png", product.getBrand().getLogo());
        assertEquals(new BigDecimal(8450), product.getPrice());
        assertFalse(product.isInPromotion());
        assertNull(product.getPromotion());


    }


    @Test
    public void setInPercentagePromotion(){
        ZonedDateTime startDate = ZonedDateTime.now().minusDays(5);
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(5);
        LifeSpan period = new LifeSpan(startDate, endDate);
        Discount discount = new DiscountByPercentage(20);
        Promotion promotion = new Promotion(discount, period);
       /* product.putInPromotion(promotion);

        BigDecimal promtionPrice = product.getPromotionPrice();//promotion.getDiscount().apply(product);
        assertEquals(6760f, promtionPrice.doubleValue(), 5);
        assertEquals(2, product.getPromotion().getStrategy());
        assertTrue(product.isInPromotion());*/
    }


    @Test
    public void setInValuePromotion(){
        ZonedDateTime startDate = ZonedDateTime.now().minusDays(5);
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(5);
        LifeSpan period = new LifeSpan(startDate, endDate);
        Discount discount = new DiscountByValue(new BigDecimal(450));
        Promotion promotion = new Promotion(discount, period);
/*        product.putInPromotion(promotion);
        BigDecimal promotionPrice  = product.getPromotionPrice();
        assertEquals(8000f, promotionPrice.doubleValue(), 5);
        assertEquals(1, product.getPromotion().getStrategy());
        assertTrue(product.isInPromotion());*/
    }

    @Test
    public void updateImage(){
        product.updateImage("catalog/product/paintahuile3_new.png", 2);
        assertEquals("catalog/product/paintahuile3_new.png", product.getImages().get(2));
    }

    @Test
    public void removePromotion(){
       /* ZonedDateTime startDate = ZonedDateTime.now().minusDays(5);
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(5);
        LifeSpan period = new LifeSpan(startDate, endDate);
        Discount discount = new DiscountByPercentage(20);
        Promotion promotion = new Promotion(discount, period);
        product.putInPromotion(promotion);

        BigDecimal promotionPrice = product.getPromotionPrice();//promotion.getDiscount().apply(product);
        assertEquals(6760f, promotionPrice.doubleValue(), 5);
        assertEquals(2, product.getPromotion().getStrategy());
        assertTrue(product.isInPromotion());

        discount = new DiscountByValue(new BigDecimal(450));
        promotion = new Promotion(discount, period);
        product.putInPromotion(promotion);
        promotionPrice  = product.getPromotionPrice();
        assertEquals(8000f, promotionPrice.doubleValue(), 5);
        assertEquals(1, product.getPromotion().getStrategy());
        assertTrue(product.isInPromotion());

        boolean bool = product.removePromotion();
        assertNull(product.getPromotion());
        assertFalse(product.isInPromotion());*/
    }


    @Test
    public void domainEventTest(){
        /*ZonedDateTime startDate = ZonedDateTime.now().minusDays(5);
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(5);
        LifeSpan period = new LifeSpan(startDate, endDate);
        Discount discount = new DiscountByPercentage(20);
        Promotion promotion = new Promotion(discount, period);
        product.putInPromotion(promotion);

        DomainEventSubscriber<ProductPromoted> domainEventSubscriberOfProductPromoted = new DomainEventSubscriber<ProductPromoted>() {
            @Override
            public void handleEvent(ProductPromoted aDomainEvent) {
                System.out.println("\n\nSaving puting  promotion");
                //productPromotedService.add(aDomainEvent);
                DomainEventPublisher.instance().unSubscribe(ProductPromoted.class);
            }

            @Override
            public Class<ProductPromoted> subscribedToEventType() {
                return ProductPromoted.class;
            }
        };

        DomainEventPublisher.instance().subscribe(domainEventSubscriberOfProductPromoted);

        assertEquals(1, DomainEventPublisher.instance().getSubscribers().size());
        product.putInPromotion(promotion);

        assertEquals(0, DomainEventPublisher.instance().getSubscribers().size());*/
        return;
    }

    @After
    public void reset(){
        product = null;
        category = null;
    }
}
