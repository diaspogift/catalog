package com.dddtraining.catalog.application.category;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.category.CategoryRepository;

@Controller
@RequestMapping(path="/catalog")
public class CategoryServiceApplication {
	@Autowired
	private   CategoryService categoryService; 
	
	@Autowired
	private   CategoryRepository categoryRepository; 
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@ResponseBody
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
	
	

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
	@ResponseBody
	public  Category getCategoryById(@PathVariable("categoryId") String categoryId){
		//return categoryId;
		return categoryService.getCategoryById(categoryId);
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
   public Category createCategory(@RequestBody Category category) {
       String id = categoryRepository.nextCategoryId();
       category.setId(id);
       return categoryService.addCategory(category);
   }
	
	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
    @ResponseBody
   public boolean removeCategory(@PathVariable("categoryId") String categoryId) {
		return categoryService.removeCategory(categoryId);
   }
	
	
	@RequestMapping(value = "/category/name", method = RequestMethod.PUT)
    @ResponseBody
   public Category changeName(@RequestBody Category category) {
		return categoryService.changeName(category.getId(), category.getName());
   }
	
	@RequestMapping(value = "/category/description", method = RequestMethod.PUT)
    @ResponseBody
   public Category changeDescription(@RequestBody Category category) {
		return categoryService.changeDescription(category.getId(), category.getDescription());
   }
	
	@RequestMapping(value = "/category/image", method = RequestMethod.PUT)
    @ResponseBody
   public Category changeImage(@RequestBody Category category) {
		return categoryService.changeImage(category.getId(), category.getImage());
   }
	
	
	
}


/*
 
 DC161275-2181-474F-A371-61ECDE921A27
 curl  -X DELETE localhost:8082/catalog/category/DC161275-2181-474F-A371-61ECDE921A27
 
 {"id":"", "name":"Rest Test Name1", "description":"Rest Test Description1", "image":"/home/nkalla/Téléchargements/training.png"}
 
 
 curl -H "Content-Type: application/json" -X POST -d '{"id":"", "name":"Rest Test Name1", "description":"Rest Test Description1", "image":"/home/nkalla/Téléchargements/training.png"}' localhost:8082/catalog/category   
 
 
 
 curl -H "Content-Type: application/json" -X PUT -d '{"id":"90277E0C-1C6A-4398-A23F-697406B76CEF","name":"Rest Test New Name1", "description":"Rest Test Description1", "image":"/home/nkalla/Téléchargements/training.png"}' localhost:8082/catalog/category/name
 
 */
