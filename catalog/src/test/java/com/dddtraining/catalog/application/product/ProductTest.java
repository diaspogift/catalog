package com.dddtraining.catalog.application.product;

import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.product.*;
import com.dddtraining.catalog.domain.model.product.event.DomainEventPublisher;
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
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductServices productServices;

    private Product product;
    private Category category;

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
    public void add(){

        Product productAdded = productServices.add(product);
        assertNotNull(productAdded);
        assertEquals(product.getId(), productAdded.getId());
        assertEquals(product.getName(), productAdded.getName());
        assertEquals(product.getDescription(), productAdded.getDescription());
    }

    @Test
    public void getProductById(){

        Product productAdded = productServices.add(product);

        Product p = productServices.getProductById(product.getId());
        assertNotNull(p);
        assertEquals(product.getName(), p.getName());
        assertEquals(product.getDescription(), p.getDescription());
        assertEquals(product.getImages().get(1), p.getImages().get(1));
    }

    @Test
    public void getAll(){
        List<String> images = new ArrayList<>();
        images.add("catalog/product/paintahuile10.png");
        images.add("catalog/product/paintahuile20.png");
        images.add("catalog/product/paintahuile30.png");
        images.add("catalog/product/paintahuile40.png");
        images.add("catalog/product/paintahuile50.png");
        Product produit = new Product(
            UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
            category,
            "Chaux de couleur blanche",
            "Bonne Chaux de qualite de couleur blanche",
            null,
            images,
            150,
            new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italchaux.png"),
            new BigDecimal(3200)
        );



        List<String> images1 = new ArrayList<>();
        images1.add("catalog/product/paintahuile112.png");
        images1.add("catalog/product/paintahuile212.png");
        images1.add("catalog/product/paintahuile312.png");
        images1.add("catalog/product/paintahuile412.png");
        images1.add("catalog/product/paintahuile512.png");
        Product produit1 = new Product(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                category,
                "Diluan",
                "Pour dilution de peinture a huile",
                null,
                images1,
                320,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italdiluant.png"),
                new BigDecimal(750)
        );

        Product produitAjoute = productServices.add(produit);
        Product produitAjoute0 = productServices.add(product);
        Product produitAjoute1 = productServices.add(produit1);


        Collection<Product> listOfProduct = productServices.getAllProduct();
        assertTrue(listOfProduct.size() == 3);

    }

    @Test
    public void findProductByKeyOnBrandName(){

        List<String> images = new ArrayList<>();
        images.add("catalog/product/paintahuile10.png");
        images.add("catalog/product/paintahuile20.png");
        images.add("catalog/product/paintahuile30.png");
        images.add("catalog/product/paintahuile40.png");
        images.add("catalog/product/paintahuile50.png");
        Product produit = new Product(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                category,
                "Chaux de couleur blanche",
                "Bonne Chaux de qualite de couleur blanche",
                null,
                images,
                150,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italchaux.png"),
                new BigDecimal(3200)
        );



        List<String> images1 = new ArrayList<>();
        images1.add("catalog/product/paintahuile112.png");
        images1.add("catalog/product/paintahuile212.png");
        images1.add("catalog/product/paintahuile312.png");
        images1.add("catalog/product/paintahuile412.png");
        images1.add("catalog/product/paintahuile512.png");
        Product produit1 = new Product(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                category,
                "Diluan",
                "Pour dilution de peinture a huile",
                null,
                images1,
                320,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italdiluant.png"),
                new BigDecimal(750)
        );

        Product produitAjoute = productServices.add(produit);
        Product produitAjoute0 = productServices.add(product);
        Product produitAjoute1 = productServices.add(produit1);

        Collection<Product> listOfProduct = productServices.findProductByKeyOnName("a");
        assertEquals(3, listOfProduct.size());
    }


    @Test
    public void findProductByKeyOnCategoryName(){

        List<String> images = new ArrayList<>();
        images.add("catalog/product/paintahuile10.png");
        images.add("catalog/product/paintahuile20.png");
        images.add("catalog/product/paintahuile30.png");
        images.add("catalog/product/paintahuile40.png");
        images.add("catalog/product/paintahuile50.png");
        Product produit = new Product(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                category,
                "Chaux de couleur blanche",
                "Bonne Chaux de qualite de couleur blanche",
                null,
                images,
                150,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italchaux.png"),
                new BigDecimal(3200)
        );



        List<String> images1 = new ArrayList<>();
        images1.add("catalog/product/paintahuile112.png");
        images1.add("catalog/product/paintahuile212.png");
        images1.add("catalog/product/paintahuile312.png");
        images1.add("catalog/product/paintahuile412.png");
        images1.add("catalog/product/paintahuile512.png");
        Product produit1 = new Product(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                category,
                "Diluan",
                "Pour dilution de peinture a huile",
                null,
                images1,
                320,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italdiluant.png"),
                new BigDecimal(750)
        );

        Product produitAjoute = productServices.add(produit);
        Product produitAjoute0 = productServices.add(product);
        Product produitAjoute1 = productServices.add(produit1);

        Collection<Product> listOfProduct = productServices.findProductByKeyOnCategoryName("a");
        assertEquals(3, listOfProduct.size());
    }

    @Test
    public void findProductInPromotion(){

        List<String> images = new ArrayList<>();
        images.add("catalog/product/paintahuile10.png");
        images.add("catalog/product/paintahuile20.png");
        images.add("catalog/product/paintahuile30.png");
        images.add("catalog/product/paintahuile40.png");
        images.add("catalog/product/paintahuile50.png");
        Product produit = new Product(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                category,
                "Chaux de couleur blanche",
                "Bonne Chaux de qualite de couleur blanche",
                null,
                images,
                150,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italchaux.png"),
                new BigDecimal(3200)
        );

        List<String> images1 = new ArrayList<>();
        images1.add("catalog/product/paintahuile112.png");
        images1.add("catalog/product/paintahuile212.png");
        images1.add("catalog/product/paintahuile312.png");
        images1.add("catalog/product/paintahuile412.png");
        images1.add("catalog/product/paintahuile512.png");
        Product produit1 = new Product(
                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                category,
                "Diluan",
                "Pour dilution de peinture a huile",
                null,
                images1,
                320,
                new Brand("Italy Paint", "/home/nkalla/img/dddtraining/product/brand/italdiluant.png"),
                new BigDecimal(750)
        );

        Product produitAjoute = productServices.add(produit);
        Product produitAjoute0 = productServices.add(product);
        Product produitAjoute1 = productServices.add(produit1);

        Collection<Product> listOfProduct = productServices.findProductInPromotion();
        assertEquals(0, listOfProduct.size());

        ZonedDateTime startDate = ZonedDateTime.now().minusDays(5);
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(5);
        LifeSpan period = new LifeSpan(startDate, endDate);
        Discount discount = new DiscountByPercentage(20);
        Discount discount1 = new DiscountByValue(new BigDecimal(500));
        Promotion promotion = new Promotion(discount, period);
        Promotion promotion1 = new Promotion(discount1, period);

        productServices.putInPromotion(produitAjoute0.getId(), promotion);
        productServices.putInPromotion(produitAjoute.getId(), promotion1);

        assertEquals(2, DomainEventPublisher.instance().getSubscribers().size());

        listOfProduct = productServices.findProductInPromotion();
        assertEquals(2, listOfProduct.size());
    }


    @After
    public void reset(){
        productServices.removeAll();
        product = null;
        category = null;
    }
}
