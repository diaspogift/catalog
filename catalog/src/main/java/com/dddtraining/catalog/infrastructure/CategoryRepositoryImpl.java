package com.dddtraining.catalog.infrastructure;


import java.util.Collection;

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
	
	public CategoryRepositoryImpl(){}
	
	
	
	/*public void startSession(){
		this.setSession(this.session());
	}*/
	
	/*public EntityManager getEntityManager() {
		return entityManager;
	}*/



	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	private Session session() {
		return (Session) entityManager.unwrap(Session.class);
	}

	/*@Override
	public long count() {
		Query query =  this.session().createQuery("from com.dddtraining.catalog.domain.model.category.Category as _obj_ ");
        
        return query.list().size();
	}

	@Override
	public void delete(Long arg0) {
		this.delete(this.findOne(arg0));
	}

	@Override
	public void delete(Category arg0) {
		this.session().delete(arg0);	
	}

	@Override
	public void delete(Iterable<? extends Category> arg0) {
		for (Iterator<? extends Category> it = arg0.iterator(); it.hasNext(); ){
			this.delete((Category)it.next());
		}
	}

	@Override
	public void deleteAll() {
		this.delete(this.findAll());	
	}

	@Override
	public boolean exists(Long arg0) {
		
		return (this.findOne(arg0) != null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Category> findAll() {
		Query query =  this.session().createQuery("from com.dddtraining.catalog.domain.model.category.Category as _obj_ ");
        
        return query.list();
	}

	@Override
	public Iterable<Category> findAll(Iterable<Long> arg0) {
		Set<Category> result = new HashSet<Category>();
		for (Iterator<Long> it = arg0.iterator(); it.hasNext(); ){
			Category cat = this.findOne(it.next());
			if(cat != null){
				result.add(cat);
			}
		}
		return result;
	}

	@Override
	public Category findOne(Long arg0) {
		return (Category)this.session().get(Category.class, arg0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <S extends Category> S save(S arg0) {
		Category category = arg0;
		try {
             this.session().save(category);
        } catch (ConstraintViolationException e) {
            throw new IllegalStateException("category is not unique.", e);
        }
		return (S) category;
	}

	@Override
	public <S extends Category> Iterable<S> save(Iterable<S> arg0) {
		//Set<Category> result = new HashSet<Category>();
		for (Iterator<S> it = arg0.iterator(); it.hasNext(); ){
			S aS = (S)it.next();
		    this.save(aS);
		}
		
		return arg0;
	}*/
	
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
	public void delete(Category category) {
		this.session().delete(category);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Category> findAll() {
		Query query =  this.session().createQuery("from com.dddtraining.catalog.domain.model.category.Category as _obj_ ");

		return query.list();
	}
}
