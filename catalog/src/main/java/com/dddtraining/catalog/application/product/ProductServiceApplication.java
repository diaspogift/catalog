package com.dddtraining.catalog.application.product;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dddtraining.catalog.domain.model.product.DefaultDiscount;
import com.dddtraining.catalog.domain.model.product.Discount;
import com.dddtraining.catalog.domain.model.product.DiscountByPercentage;
import com.dddtraining.catalog.domain.model.product.DiscountByValue;
import com.dddtraining.catalog.domain.model.product.Product;



@Controller
@RequestMapping(path="/catalog")
public class ProductServiceApplication {
	
	@Autowired
	private   ProductServices productServices; 
	
	@RequestMapping(value = "/product/category", method = RequestMethod.PUT)
    @ResponseBody
   public Product changeCategory(@RequestBody ProductRepresentation product) {
       return productServices.changeCategory(product.getId(), product.getCategory());
   }
	
	@RequestMapping(value = "/product/residualQuantity", method = RequestMethod.PUT)
    @ResponseBody
   public Product changeResidualQuantity(@RequestBody ProductRepresentation product) {
       return productServices.changeResidualQuantity(product.getId(), product.getResidualQuantity());
   }
	
	
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
    @ResponseBody
   public boolean removeProduct(@PathVariable("productId") String productId) {
		return productServices.delete(productId);
   }
	
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
	
	
	@RequestMapping(value = "/products/promotion", method = RequestMethod.GET)
	@ResponseBody
	public  Set<Product> findProductInPromotion(){
		
		Collection<Product> products = productServices.findProductInPromotion();
		Set<Product> result = new HashSet<Product>();
		
		Iterator<Product> iterator = products.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Product) iterator.next());
        }
		
		return result;
	}
	
	@RequestMapping(value = "/product/price", method = RequestMethod.PUT)
    @ResponseBody
   public boolean changePrice(@RequestBody ProductRepresentation product) {
		return productServices.changePrice(product.getId(), product.getPrice());
   }
	
	@RequestMapping(value = "/product/description", method = RequestMethod.PUT)
    @ResponseBody
   public boolean changeDescription(@RequestBody ProductRepresentation product) {
		return productServices.changeDescription(product.getId(), product.getDescription());
   }
	
	@RequestMapping(value = "/product/title", method = RequestMethod.PUT)
    @ResponseBody
   public boolean updateTitle(@RequestBody ProductRepresentation product) {
		return productServices.updateTitle(product.getId());
   }
	
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
	
	@RequestMapping(value = "/product/promotion", method = RequestMethod.PUT)
    @ResponseBody
   public boolean putInPromotion(@RequestBody ProductRepresentation productRepresentation) {
		Discount discount = null;
		int stragegy = productRepresentation.getStrategy();
		if(stragegy == 0){
			discount = new DefaultDiscount();
		}else if(stragegy == 1){
			discount = new DiscountByValue();
		}else if(stragegy == 2){
			discount = new DiscountByPercentage();
		}
		
		Product product = productServices.getProductById(productRepresentation.getId());
		return productServices.putInPromotion(product.getId(), discount);
   }
	
	@RequestMapping(value = "/product/brand", method = RequestMethod.PUT)
    @ResponseBody
   public boolean updateBrand(@RequestBody ProductRepresentation product) {
		return productServices.updateBrand(product.getId(), product.getBrand());
   }
	
	
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public  Product getProductById(@PathVariable("productId") String productId){
		//return categoryId;
		return productServices.getProductById(productId);
	}
	

}
