package com.dddtraining.catalog.domain.model.product;



import java.util.Collection;

public interface ProductRepository{
	public Product addProduct(Product product);
	public Product getPtoductById(String ProductId);
	public Collection<Product> findProductByKeyOnName(String keyOnName);
	public Collection<Product> findProductByKeyOnBrandName(String keyOnBrandName);
	public Collection<Product> findProductByKeyOnCategoryName(String keyOnCategoryName);
	public Collection<Product> searchProduct(String categoryName, String name);
	public Collection<Product> findProductInPromotion();
	public Product findProductById(String id);
	public boolean delete(Product foundProduct);
	public Collection<Product> findAll();
	public void removeAll();
}
