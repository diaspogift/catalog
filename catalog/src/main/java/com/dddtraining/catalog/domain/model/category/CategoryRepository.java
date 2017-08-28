package com.dddtraining.catalog.domain.model.category;

import java.util.Collection;

//This will be AUTO IMPLEMENTED by Spring into a Bean called CategoryRepository
//CRUD refers Create, Read, Update, Delete

public interface CategoryRepository  {

	public Category getCategoryById(String id);

	public Category save(Category category);

	public boolean delete(Category category);

	public Collection<Category> findAll();
	
	public String nextCategoryId();

	

}

