package com.dddtraining.catalog.application.category;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.category.CategoryRepository;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	

	/*public CategoryRepository getRepo() {
		return repo;
	}*/

	public void setRepo(CategoryRepository repo) {
		this.repo = repo;
	}

	@Override
	public Category addCategory(Category category) {
		return repo.save(category);
	}

	@Override
	public boolean removeCategory(String id) {
		Category category = repo.getCategoryById(id);
		
		if(category == null){
			//throw new IllegalArgumentException("Category not found");
			return false;
		}
		return repo.delete(category);
	}

	@Override
	public Category changeName(String id, String newName) {
		Category category = repo.getCategoryById(id);
		
		if(category == null){
			throw new IllegalArgumentException("Category not found");
		}
		category.changeName(newName);
		return category;
	}

	@Override
	public Category changeDescription(String id, String newDescription) {
		Category category = repo.getCategoryById(id);
		
		if(category == null){
			throw new IllegalArgumentException("Category not found");
		}
		category.changeDescription(newDescription);
		return category;
	}

	@Override
	public Category changeImage(String id, String newImage) {
		Category category = repo.getCategoryById(id);
		
		if(category == null){
			throw new IllegalArgumentException("Category not found");
		}
		category.changeImage(newImage);
		return category;
	}

	@Override
	public Category getCategoryById(String id) {
		return repo.getCategoryById(id);
	}

	@Override
	public Collection<Category> getAllCategory() {
		return repo.findAll();
	}

	@Override
	public boolean removeAll() {
		return repo.removeAll();
	}

}
