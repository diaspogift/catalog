package com.dddtraining.catalog.domain.model.product;



import java.util.Collection;

public interface ProductRepository{
	public Product addProduct(Product product);
	public Product getPtoductById(String ProductId);
	public Collection<Product> findProductByKeyOnName(String keyOnName);
	Collection<Product> findProductByKeyOnBrandName(String keyOnBrandName);
	Collection<Product> findProductByKeyOnCategoryName(String keyOnCategoryName);
	Collection<Product> findProductInPromotion();
	public Product findProductById(String id);
	public boolean delete(Product foundProduct);
	public Collection<Product> findAll();
}
