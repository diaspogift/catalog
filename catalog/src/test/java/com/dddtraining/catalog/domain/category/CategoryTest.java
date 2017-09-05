package com.dddtraining.catalog.domain.category;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dddtraining.catalog.domain.model.category.Category;
import org.junit.After;
import org.junit.Before;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {
    private Category category;

    @Before
    public void init(){
        System.out.println("Seting");
        category = new Category(UUID.fromString(UUID.randomUUID().toString()).toString(), "Painture", "Tout pour les embellissement", "/home/diapogift/img/catalog/category/painture.png");
    }

    @Test
    public void testCreateCategory(){
        assertNotNull(category);
        assertEquals("Painture", category.getName());
        assertEquals("Tout pour les embellissement", category.getDescription());
        assertEquals("/home/diapogift/img/catalog/category/painture.png", category.getImage());
    }

    @Test
    public void testChangeCategoryName(){
        category.changeName("Peinture");
        assertEquals("Peinture", category.getName());
    }

    @Test
    public void testChangeCategoryDescription(){
        category.changeDescription("Tout pour les embellissements");
        assertEquals("Tout pour les embellissements", category.getDescription());
    }

    @Test
    public void testChangeCategoryImage(){
        category.changeImage("/home/diapogift/img/catalog/category/peinture.png");
        assertEquals("/home/diapogift/img/catalog/category/peinture.png", category.getImage());
    }

    @After
    public void reset(){
        category = null;
        System.out.println("Reseting");
    }
}
