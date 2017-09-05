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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DiscountTest {
    private Category category;
    private Product product;

    @Before
    public void init(){


    }


    @Test
    public void discountPercentage(){
        Discount discount = new DiscountByPercentage(20);

    }


    @After
    public void reset(){

    }
}
