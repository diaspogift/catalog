package com.dddtraining.catalog.infrastructure;


import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.category.CategoryRepository;
@Repository
//@Qualifier("myCategoryRepository")
//@Primary
public class CategoryRepositoryImpl implements CategoryRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	private Session session() {
		return (Session) entityManager.unwrap(Session.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Category getCategoryById(String id){
		Query query =  this.session().createQuery("from com.dddtraining.catalog.domain.model.category.Category as _obj_ " +
	" where id = ?");
		query.setParameter(0,  id.trim());
		Collection<Category> listeCollection = query.list();
		if(listeCollection.size() != 1) return null;
		
        return listeCollection.iterator().next();
	}

	@Override
	public Category save(Category category) {
		this.session().save(category);
		return category;
	}

	@Override
	public boolean  delete(Category category) {
		 this.session().delete(category);
		 return true;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Category> findAll() {
		Query query =  this.session().createQuery("from com.dddtraining.catalog.domain.model.category.Category as _obj_ ");

		return query.list();
	}



	@Override
	public String nextCategoryId() {
		String id = UUID.randomUUID().toString();
		UUID uuid = UUID.fromString(id);
		return uuid.toString().toUpperCase();
	}

	@Override
	public boolean removeAll() {
		Query query =  this.session().createQuery("delete from com.dddtraining.catalog.domain.model.category.Category");
		  query.executeUpdate();
		return true;
	}
}
