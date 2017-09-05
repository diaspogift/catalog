package com.dddtraining.catalog;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.dddtraining.catalog.application.product.ProductPromotedListener;
import com.dddtraining.catalog.domain.model.product.*;
import com.dddtraining.catalog.domain.model.product.event.ProductPromoted;
import com.dddtraining.catalog.domain.model.product.event.ProductPromotedRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dddtraining.catalog.application.category.CategoryService;
import com.dddtraining.catalog.application.product.ProductServices;
import com.dddtraining.catalog.domain.model.category.Category;

@SpringBootApplication
public class DdtrainingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DdtrainingApplication.class, args);

		CategoryService categoryService = context.getBean(CategoryService.class);
		Collection<Category> categories = categoryService.getAllCategory();
		Set<Category> result = new HashSet<Category>();
		
		Iterator<Category> iterator = categories.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Category) iterator.next());
        }
		System.out.println("\n\n______________________________________\n\n" + result + "\n\n______________________________________\n\n");

		Category aCategory = categoryService.getCategoryById("cbd6ee6d-a59a-4656-9335-6dc991c47b7c");
		System.out.println("\n\n::::::::::::::::\n\n" + aCategory + "\n\n::::::::::::::::::::::::::::::::::::\n\n");

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

		System.out.println();
		
		productServices.add(produit);



		//ProductServices productServices = context.getBean(ProductServices.class);

		String prodIds1 = UUID.randomUUID().toString();
		UUID.fromString(prodIds1);

		List<String> imgs1 = new ArrayList<String>();

		imgs1.add("/home/nkalla/img/dddtraining/product/img1.png");
		imgs1.add("/home/nkalla/img/dddtraining/product/img2.png");
		imgs1.add("/home/nkalla/img/dddtraining/product/img3.png");
		imgs1.add("/home/nkalla/img/dddtraining/product/img4.png");
		imgs1.add("/home/nkalla/img/dddtraining/product/img5.png");

		Product produit1 = new Product(prodIds1, cat1, "Ciment CPJ 35",
				"Ciment de quality medium. Classe CPJ 42", null,
				imgs1, 5000, new Brand("Dangote Cement",
				"/home/nkalla/img/dddtraining/product/brand/dangote.png"), new BigDecimal(3000));


		productServices.add(produit1);*/

		ProductServices productServices = context.getBean(ProductServices.class);

		Collection<Product> products = productServices.getAllProduct();
		System.out.println(products);
		
		//ff56e432-f1a7-45db-b639-7aae1b1e5428

		ProductPromotedListener productPromotedListener = context.getBean(ProductPromotedListener.class);
		System.out.println("ProductPromotedListener: " + productPromotedListener);
		Product product = productServices.getProductById("c5b84738-cad3-4a78-8261-0000ebf755f4");

		Discount discount =  new DiscountByPercentage(20d);
		//System.out.println("Product: " + product);
		Promotion promotion = new Promotion(discount, new LifeSpan(ZonedDateTime.now().minusDays(5), ZonedDateTime.now().plusDays(5)));
		//product.putInPromotion(promotion);
		//System.out.println("Product: " + product);
		//product.putInPromotion(null);
		//System.out.println("Product: " + product);

		//System.out.println(product.putInPromotion(promotion));
		//produit.putInPromotion(promotion);
		//productServices.add(produit);
		boolean xxx = productServices.putInPromotion("c5b84738-cad3-4a78-8261-0000ebf755f4", promotion);
		System.out.println("Puting in Promotion: " + xxx);
		//System.out.println("Puting in Promotion: " + productServices.putInPromotion("65caabf9-e9b6-438d-857e-9301c320fa0b", promotion));


		ProductPromotedRepository productPromotedRepository = context.getBean(ProductPromotedRepository.class);

		/*productPromotedRepository.add(new ProductPromoted(product.getId(),
				product.getPrice(), product.getPromotionPrice(), promotion));*/
		//(String productId, BigDecimal price, BigDecimal promotionPrice, Promotion promotion)
	}
}
