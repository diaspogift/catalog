package com.dddtraining.catalog.application.product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.dddtraining.catalog.domain.model.product.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:8082/catalog")
@RestController
@RequestMapping(path="/catalog")
public class ProductServiceApplication {
	
	@Autowired
	private   ProductServices productServices;
    @CrossOrigin
	//@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/category", method = RequestMethod.PUT)
    @ResponseBody
   public Product changeCategory(@RequestBody ProductRepresentation product) {
       return productServices.changeCategory(product.getId(), product.getCategory());
   }

    //@CrossOrigin
	//@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/residualQuantity", method = RequestMethod.PUT)
    @ResponseBody
   public Product changeResidualQuantity(@RequestBody ProductRepresentation product) {
       return productServices.changeResidualQuantity(product.getId(), product.getResidualQuantity());
   }

    //@CrossOrigin
    //@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
    @ResponseBody
   public boolean removeProduct(@PathVariable("productId") String productId) {
		return productServices.delete(productId);
   }

    //@CrossOrigin
    //@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ResponseBody
	public  Set<Product> getAllProduct(){
		
		Collection<Product> products = productServices.getAllProduct();
		Set<Product> result = new HashSet<Product>();
		
		Iterator<Product> iterator = products.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Product) iterator.next());
        }
		
		return result;
	}

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/products/name", method = RequestMethod.GET)
	@ResponseBody
	public  Set<Product> findProductByKeyOnName(@RequestParam("query") String query){
		
		Collection<Product> products = productServices.findProductByKeyOnName(query);
		Set<Product> result = new HashSet<Product>();
		
		Iterator<Product> iterator = products.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Product) iterator.next());
        }
		
		return result;
	}

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/products/brand", method = RequestMethod.GET)
	@ResponseBody
	public  Set<Product> findProductByKeyOnBrandName(@RequestParam("query") String query){
		
		Collection<Product> products = productServices.findProductByKeyOnBrandName(query);
		Set<Product> result = new HashSet<Product>();
		
		Iterator<Product> iterator = products.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Product) iterator.next());
        }
		
		return result;
	}

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/products/category", method = RequestMethod.GET)
	@ResponseBody
	public  Set<Product> findProductByKeyOnCategoryName(@RequestParam("query") String query){
		
		Collection<Product> products = productServices.findProductByKeyOnCategoryName(query);
		Set<Product> result = new HashSet<Product>();
		
		Iterator<Product> iterator = products.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Product) iterator.next());
        }
		
		return result;
	}

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/products/promotion", method = RequestMethod.GET)
	@ResponseBody
	public  Set<Product> findProductInPromotion(){

		//System.out.println("Enter: 00000000000000000");
		Collection<Product> products = productServices.findProductInPromotion();
		Set<Product> result = new HashSet<Product>();
		
		Iterator<Product> iterator = products.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Product) iterator.next());
        }
		//System.out.println("finish: 00000000000000000");
		return result;
	}

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/price", method = RequestMethod.PUT)
    @ResponseBody
   public boolean changePrice(@RequestBody ProductRepresentation product) {
		return productServices.changePrice(product.getId(), product.getPrice());
   }

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/description", method = RequestMethod.PUT)
    @ResponseBody
   public boolean changeDescription(@RequestBody ProductRepresentation product) {
		return productServices.changeDescription(product.getId(), product.getDescription());
   }

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/title", method = RequestMethod.PUT)
    @ResponseBody
   public boolean updateTitle(@RequestBody ProductRepresentation product) {
		return productServices.updateTitle(product.getId());
   }

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/images/{index}", method = RequestMethod.PUT)
    @ResponseBody
   public boolean updateImage(@RequestBody ProductRepresentation product, @PathVariable("index") int index) {
		JSONArray jsonArray = null;
		String newImage = null;
		try {
			jsonArray = new JSONArray(product.getImages());
			newImage = jsonArray.getString(index);
		} catch (JSONException e) {
			return false;
		}
		
		return productServices.updateImage(product.getId(),newImage, index);
   }

    //@CrossOrigin
	//@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/images", method = RequestMethod.PUT)
	@ResponseBody
	public boolean changeImage(@RequestBody ProductRepresentation product) {
		/*JSONArray jsonArray = null;
		String newImage = null;
		try {
			jsonArray = new JSONArray(product.getImages());
			newImage = product.getImages();
		} catch (JSONException e) {
			return false;
		}

		System.out.println("neImage: " + newImage);*/
		return productServices.changeImage(product.getId(),product.getImages());
	}

    //@CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/promotion", method = RequestMethod.PUT)
    @ResponseBody
   public boolean putInPromotion(@RequestBody ProductRepresentation productRepresentation) {
		Discount discount = null;
		int stragegy = productRepresentation.getPromotion().getStrategy();
		if(stragegy == 0){
			discount = new DefaultDiscount();
		}else if(stragegy == 1){
			discount = new DiscountByValue(new BigDecimal(productRepresentation.getPrice().doubleValue()-productRepresentation.getPromotionPrice().doubleValue()));

		}else if(stragegy == 2){
			discount = new DiscountByPercentage(((productRepresentation.getPrice().doubleValue()-productRepresentation.getPromotionPrice().doubleValue())*100)/productRepresentation.getPrice().doubleValue());
		}


		Promotion promotion = new Promotion(discount, productRepresentation.getPromotion().getLifeSpan());

		//System.out.println("\n\n\nStrategy: " + productRepresentation.getStrategy() + "\n\n\n");

		Product product = productServices.getProductById(productRepresentation.getId());
		return productServices.putInPromotion(product.getId(), promotion);
   }

   // @CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/brand", method = RequestMethod.PUT)
    @ResponseBody
   public boolean updateBrand(@RequestBody ProductRepresentation product) {
		return productServices.updateBrand(product.getId(), product.getBrand());
   }

   // @CrossOrigin//
    // @CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public  Product getProductById(@PathVariable("productId") String productId){
		//return categoryId;
		return productServices.getProductById(productId);
	}



	@RequestMapping(value = "/products/search", method = RequestMethod.GET)
	@ResponseBody
	public  Set<Product> searchProduct(@RequestParam("categoryName") String categoryName, @RequestParam("name") String name){

		Collection<Product> products = productServices.searchProduct(categoryName, name);

		Set<Product> result = new HashSet<Product>();

		Iterator<Product> iterator = products.iterator();

		// while loop
		while (iterator.hasNext()) {
			result.add((Product) iterator.next());
		}
		return result;
	}

}
