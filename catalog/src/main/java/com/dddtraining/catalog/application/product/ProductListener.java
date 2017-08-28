package com.dddtraining.catalog.application.product;

import org.springframework.stereotype.Component;

import com.dddtraining.catalog.application.category.CategoryService;
import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.product.Brand;
import com.dddtraining.catalog.domain.model.product.Product;
import com.dddtraining.catalog.domain.model.product.Title;



import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;


@Component
public class ProductListener {

	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private CategoryService categoryService;
	
	@JmsListener(destination="_CATALOG_PRODUCT_CREATED_QUEUE")
	public void proccessProductCreatedMessage(String incomingMessage) throws JSONException{
		/*Category category = getDefaultCategory();		
		String productId = incomingMessage.get("productId");
		String name = incomingMessage.get("name");
		String description = incomingMessage.get("description");*/
		JSONObject jsonObject = new JSONObject(incomingMessage);
		Category category = getDefaultCategory();		
		String productId = jsonObject.getString("productId");
		String name = jsonObject.getString("name");
		String description = jsonObject.getString("description");
		
		Product product = new Product(productId, category, name, description, new Title(""),
				new ArrayList<String>(), 0, new Brand("", ""), new BigDecimal(0));
		productServices.add(product);	
	}
	
	@JmsListener(destination="_CATALOG_PRODUCT_RESIDUAL_QUANTITY_CHANGED_QUEUE")
	public void proccessResidualQuantityChangeMessage(String incomingMessage) throws JSONException{
		/*String productId = incomingMessage.get("productId");
		int quantity = Integer.parseInt(incomingMessage.get("quantity"));*/
		JSONObject jsonObject = new JSONObject(incomingMessage);
		String productId = jsonObject.getString("productId");
		int quantity = Integer.parseInt(jsonObject.getString("quantity"));
		productServices.changeResidualQuantity(productId, quantity);	
	}
	
	public Category getDefaultCategory(){
		return categoryService.getCategoryById("default");
	}
	
}
