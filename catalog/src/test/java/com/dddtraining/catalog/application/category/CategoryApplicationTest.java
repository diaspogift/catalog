package com.dddtraining.catalog.application.category;

import com.dddtraining.catalog.domain.model.category.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryApplicationTest {

    @Autowired
    private CategoryService categoryService;
    private Category category;

    @Before
    public void init(){
        category = new Category(
                      UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                "Peinture",
                "Tout pour les embellissements",
                "/home/diapogift/img/catalog/category/painture.png"
        );
    }

    @Test
    public void addCategory(){
        Category categoryAdded = categoryService.addCategory(category);
        assertNotNull(categoryAdded);
        assertNotEquals(0, (long)categoryAdded.getNativeId());
        assertEquals(category.getId(), categoryAdded.getId());
        assertEquals(category.getName(), categoryAdded.getName());
        assertEquals(category.getDescription(), categoryAdded.getDescription());
        assertEquals(category.getImage(), categoryAdded.getImage());
    }

    @Test
    public void getAllCategory(){
        Category categoryAdded = categoryService.addCategory(category);
        Category categoryAdded2 = categoryService.addCategory(new Category(

                UUID.fromString(UUID.randomUUID().toString()).toString().toUpperCase(),
                "Sanitaire",
                "Tout pour les Sanitaire",
                "/home/diapogift/img/catalog/category/painture.png"
        ));
        Collection<Category> categories = categoryService.getAllCategory();
        assertEquals(2, categories.size());
    }

    @Test
    public void changeNameInRepository(){
        categoryService.addCategory(category);
        categoryService.changeName(category.getId(),"Peinture Qualite Superieure");
        Category categoryChanged = categoryService.getCategoryById(category.getId());
        assertEquals("Peinture Qualite Superieure", categoryChanged.getName());

    }


    @Test
    public void changeDescriptionInRepository(){
        categoryService.addCategory(category);
        categoryService.changeDescription(category.getId(),category.getDescription() + " et decors");
        Category categoryChanged = categoryService.getCategoryById(category.getId());
        assertEquals(category.getDescription() + " et decors", categoryChanged.getDescription());

    }

    @Test
    public void changeImageInRepository(){
        categoryService.addCategory(category);
        categoryService.changeImage(category.getId(),"catalog/category/painture.png");
        Category categoryChanged = categoryService.getCategoryById(category.getId());
        assertEquals("catalog/category/painture.png", categoryChanged.getImage());
    }

    @Test
    public void getCategoryById(){
        categoryService.addCategory(category);
        Category categoryAdded = categoryService.getCategoryById(category.getId());
        assertEquals(category, categoryAdded);
    }

    @After
    public void reset(){
        categoryService.removeAll();
    }
}
