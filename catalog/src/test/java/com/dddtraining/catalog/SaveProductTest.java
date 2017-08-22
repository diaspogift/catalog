package com.dddtraining.catalog;



import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.dddtraining.catalog.application.category.CategoryService;
import com.dddtraining.catalog.application.product.ProductServices;


@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SaveProductTest {
	
	@Test
	public void createProductAndSave() {
		//ConfigurableApplicationContext context = SpringApplication.run(SaveProductTest.class);
		
		//CategoryService categoryService = context.getBean(CategoryService.class);
		
		/*String cat1Id_s = UUID.randomUUID().toString();
		UUID.fromString(cat1Id_s);
		
		Category cat1 = new Category(cat1Id_s, "Painture", "Tout pour les embellissement", "/home/diapogift/img/catalog/category/painture.png");
		
		categoryService.addCategory(cat1);
		
		Category extractedCategory = categoryService.getCategoryById(cat1Id_s);
		
		assertEquals(cat1Id_s, extractedCategory.getId());*/
		//Category defaultCat = new Category("default", "default", "default", "/home/diapogift/img/catalog/category/default.png");
		//categoryService.addCategory(defaultCat);
		
		/*Collection<Category> categories = categoryService.getAllCategory();
		int size = categories.size();
		
		assertEquals(2, size);
		Category category = null;
		int i = 0;
		for(Category cat:categories){
			if(cat.getImage().equals("")){
				categoryService.changeImage(cat.getId(), "/home/diapogift/img/catalog/category/geniecivil.png");
			}
			if(i == 1){
				category = cat;
			}
			i++;
		}
		
		
		
		ProductServices productServices = context.getBean(ProductServices.class);
		
		String prodIds = UUID.randomUUID().toString();
		UUID.fromString(prodIds);
		
		List<String> imgs = new ArrayList<String>();
		
		imgs.add("/home/nkalla/img/dddtraining/product/paintahuile1.png");
		imgs.add("/home/nkalla/img/dddtraining/product/paintahuile2.png");
		imgs.add("/home/nkalla/img/dddtraining/product/paintahuile3.png");
		imgs.add("/home/nkalla/img/dddtraining/product/paintahuile4.png");
		imgs.add("/home/nkalla/img/dddtraining/product/paintahuile5.png");
		
		Product produit = new Product(prodIds, category, "Painture a huile email 10l", 
				"Bonne peinture de qualite", null, 
				imgs, 250, new Brand("Italy Paint", 
						"/home/nkalla/img/dddtraining/product/brand/paintahuile.png"), new BigDecimal(8450));
		
		//productServices.add(produit);
		
		
		Collection<Product> products = productServices.getAllCategory();
		int sizep = products.size();
		System.out.println(prodIds);
		assertEquals(3, sizep);*/
		
		
		
	}
	
	@Test
	public void listenToNewProduct() throws InterruptedException, JMSException {
		ConfigurableApplicationContext context = SpringApplication.run(SaveProductTest.class);
		ProductServices productServices = context.getBean(ProductServices.class);
		CategoryService categoryService = context.getBean(CategoryService.class);
		
		Consumer consumer = new Consumer(categoryService, productServices);
		consumer.consume();
		
	}

}
