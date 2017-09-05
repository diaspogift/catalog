package com.dddtraining.catalog.application.category;

import java.util.Collection;

import com.dddtraining.catalog.domain.model.category.Category;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public boolean removeCategory(String id);
	
	public Category changeName(String id, String newName);
	
	public Category changeDescription(String id, String newDescription);
	
	public Category changeImage(String id, String newImage);
	
	public Category getCategoryById(String id);
	
	public Collection<Category> getAllCategory();

	public boolean removeAll();
	
}
