package com.dddtraining.catalog.application.product;

import java.math.BigDecimal;
import java.util.Collection;

import com.dddtraining.catalog.domain.model.product.Brand;
import com.dddtraining.catalog.domain.model.product.Discount;
import com.dddtraining.catalog.domain.model.product.Product;
import com.dddtraining.catalog.domain.model.product.ProductCategory;


public interface ProductServices {
	
	public Product add(Product product);
	public void delete(String productId);
	public Collection<Product> findProductByKeyOnName(String keyOnName);
	public Collection<Product> findProductByKeyOnBrandName(String keyOnBrandName);
	public Collection<Product> findProductByKeyOnCategoryName(String keyOnCategoryName);
	public Collection<Product> findProductInPromotion();
	public void changePrice(String productId, BigDecimal newPrice);
	public void changeDescription(String productId, String newDescription);
	public void updateTitle(String productId);
	public void updateImage(String productId, String newImage, int index);
	public void putInPromotion(String productId, Discount discount);
	public void updateBrand(String productId, Brand newBrand);
	public Product getProductById(String id);
	public Collection<Product> getAllProduct();
	public Product changeCategory(String productId, ProductCategory category);
	public void changeResidualQuantity(String id, int newResidualQuantity);
	
}
