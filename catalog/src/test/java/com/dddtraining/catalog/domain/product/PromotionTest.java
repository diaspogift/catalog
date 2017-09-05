package com.dddtraining.catalog.domain.product;

import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.product.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PromotionTest {

    private Promotion promotion;
    private Category category;
    private Product product;

    private void construct(){
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


        Title producttitle = new Title();

        ZonedDateTime startDate = ZonedDateTime.now().minusDays(5);
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(5);
        LifeSpan period = new LifeSpan(startDate, endDate);
        Discount discount = new DiscountByPercentage(20);
        promotion = new Promotion(discount, period);

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

    @Before
    public void init(){
        construct();

    }

    @Test
    public void createPromotion(){
        assertNotNull(promotion);
        assertTrue(promotion.inCourse());
        assertEquals(0, promotion.getStrategy());
        assertEquals("DiscountByPercentage", promotion.getDiscount().getClass().getSimpleName());
    }

    @Test
    public void applyPromotion(){
        /*product.putInPromotion(promotion);
        BigDecimal newPrice = promotion.getDiscount().apply(product);
        assertEquals(6760f, newPrice.doubleValue(), 5);
        assertEquals(2, promotion.getStrategy());

        Discount discount = new DiscountByValue(new BigDecimal(450));
        promotion.setDiscount(discount);
        product.putInPromotion(promotion);
        newPrice = promotion.getDiscount().apply(product);
        assertEquals(8000f, newPrice.doubleValue(), 5);
        assertEquals(1, promotion.getStrategy());*/
    }

    @After
    public void reset(){
        promotion = null;
        product = null;
        category = null;
    }
}
