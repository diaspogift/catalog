package com.dddtraining.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dddtraining.catalog.application.category.CategoryService;
import com.dddtraining.catalog.application.product.ProductServices;
import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.product.Brand;
import com.dddtraining.catalog.domain.model.product.Product;

@SpringBootApplication
public class DdtrainingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DdtrainingApplication.class, args);
		
		/*CategoryService categoryService = context.getBean(CategoryService.class);
		Collection<Category> categories = categoryService.getAllCategory();
		Set<Category> result = new HashSet<Category>();
		
		Iterator<Category> iterator = categories.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Category) iterator.next());
        }
		System.out.println("\n\n______________________________________\n\n" + result + "\n\n______________________________________\n\n");
		*/
		/*String cat1Id_s = UUID.randomUUID().toString();
		UUID.fromString(cat1Id_s);
		
		Category cat1 = new Category(cat1Id_s, "Genie Civil", "Tout pour les construtions de batiment pont et chaussee", "");
		
		categoryService.addCategory(cat1);
		
		ProductServices productServices = context.getBean(ProductServices.class);
		
		String prodIds = UUID.randomUUID().toString();
		UUID.fromString(prodIds);
		
		List<String> imgs = new ArrayList<String>();
		
		imgs.add("/home/nkalla/img/dddtraining/product/visual1.png");
		imgs.add("/home/nkalla/img/dddtraining/product/visual2.png");
		imgs.add("/home/nkalla/img/dddtraining/product/visual3.png");
		imgs.add("/home/nkalla/img/dddtraining/product/visual4.png");
		imgs.add("/home/nkalla/img/dddtraining/product/visual5.png");
		
		Product produit = new Product(prodIds, cat1, "Ciment CPJ 42", 
				"Ciment de tres bonne qualite. Classe CPJ 42", null, 
				imgs, 2000, new Brand("Dangote Cement", 
						"/home/nkalla/img/dddtraining/product/brand/dangote.png"), new BigDecimal(4500));
		
		productServices.add(produit);*/
		
		
		ProductServices productServices = context.getBean(ProductServices.class);
		
		Collection<Product> products = productServices.findProductByKeyOnName("Ciment");
		System.out.println(products);
		
		
	}
}
