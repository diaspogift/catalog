package com.dddtraining.catalog.application.category;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dddtraining.catalog.domain.model.category.Category;

@Service
public class CategoryServiceApplication {
	@Autowired
	private   CategoryService categoryService; 
	
	public  Set<Category> getAllCategory(){
		Collection<Category> categories = categoryService.getAllCategory();
		Set<Category> result = new HashSet<Category>();
		
		Iterator<Category> iterator = categories.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	result.add((Category) iterator.next());
        }
		
		return result;
	}
	
	public static void main(String[] args){
		CategoryServiceApplication categoryServiceApplication = new CategoryServiceApplication();
		System.out.println(categoryServiceApplication.getAllCategory());
	}

}
