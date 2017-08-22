package com.dddtraining.catalog.application.product;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;


@Component
public class ProductCreatedListener {

	@Autowired
	private ProductServices productServices;
	
	@JmsListener(destination="PRODUCT_CREATED_QUEUE")
	public void proccessMessage(String message){
		
	}
	
}
