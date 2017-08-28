package com.dddtraining.catalog.infrastructure;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.dddtraining.catalog.domain.model.product.Product;
import com.dddtraining.catalog.domain.model.product.ProductRepository;

@Repository

public class ProductRepositoryImpl implements ProductRepository{

	@PersistenceContext
	private EntityManager entityManager;
	

	@SuppressWarnings("unchecked")
	@Override
	public Product getPtoductById(String ProductId) {
				
		Query query = this.session().createQuery(
                "from com.dddtraining.catalog.domain.model.product.Product as _obj_ "
                + "where _obj_.id = ?");

        query.setParameter(0, ProductId);
        
        Collection<Product> returnedCollection = query.list();
        
        if(returnedCollection.size() != 1) return null;
        
        return returnedCollection.iterator().next();
	}
	
	private Session session() {
		Session session = (Session) entityManager.unwrap(Session.class);;
		return session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> findProductByKeyOnName(String keyOnName) {
		
		Query query = this.session().createQuery(
                "from com.dddtraining.catalog.domain.model.product.Product as _obj_ "
                + "where LCASE(_obj_.name) like ?");

        query.setParameter(0, "%" + keyOnName.trim().toLowerCase() + "%");
        
        return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> findProductByKeyOnBrandName(String keyOnBrandName) {
		Query query = this.session().createQuery(
                "from com.dddtraining.catalog.domain.model.product.Product as _obj_ "
                + "where LCASE(_obj_.brand.name) like ?");

        query.setParameter(0, "%" + keyOnBrandName.trim().toLowerCase() + "%");
        
        return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> findProductByKeyOnCategoryName(String keyOnCategoryName) {
		Query query = this.session().createQuery(
                "from com.dddtraining.catalog.domain.model.product.Product as _obj_ "
                + "where LCASE(_obj_.category.categoryName) like ? or LCASE(_obj_.category.categoryDescription) like ?");

        query.setParameter(0, "%" + keyOnCategoryName.trim().toLowerCase() + "%");
        
        query.setParameter(1, "%" + keyOnCategoryName.trim().toLowerCase() + "%");
        
        return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> findProductInPromotion() {
		Query query = this.session().createQuery(
                "from com.dddtraining.catalog.domain.model.product.Product as _obj_ "
                + "where _obj_.inPromotion = true");
        return query.list();
	}

	@Override
	public Product findProductById(String id) {
		
		Query query = this.session().createQuery(
                "from com.dddtraining.catalog.domain.model.product.Product as _obj_ "
                + "where _obj_.id = ?");

        query.setParameter(0, id);
        @SuppressWarnings("unchecked")
		Collection<Product> foundProducts = query.list();
        if(foundProducts.size() != 1) return null;
        
        return foundProducts.iterator().next();
        
	}

	@Override
	public Product addProduct(Product product) {
		this.session().save(product);
		return product;
	}

	@Override
	public boolean delete(Product foundProduct) {
		this.session().delete(foundProduct);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> findAll() {
		Query query =  this.session().createQuery("from com.dddtraining.catalog.domain.model.product.Product as _obj_ ");

		return query.list();
	}
}
