package com.dddtraining.catalog.application.product;

import java.math.BigDecimal;
import java.util.Collection;

import com.dddtraining.catalog.domain.model.product.Brand;
import com.dddtraining.catalog.domain.model.product.Discount;
import com.dddtraining.catalog.domain.model.product.Product;
import com.dddtraining.catalog.domain.model.product.ProductCategory;


public interface ProductServices {
	
	public Product add(Product product);
	public boolean delete(String productId);
	public Collection<Product> findProductByKeyOnName(String keyOnName);
	public Collection<Product> findProductByKeyOnBrandName(String keyOnBrandName);
	public Collection<Product> findProductByKeyOnCategoryName(String keyOnCategoryName);
	public Collection<Product> findProductInPromotion();
	public boolean changePrice(String productId, BigDecimal newPrice);
	public boolean changeDescription(String productId, String newDescription);
	public boolean updateTitle(String productId);
	public boolean updateImage(String productId, String newImage, int index);
	public boolean putInPromotion(String productId, Discount discount);
	public boolean updateBrand(String productId, Brand newBrand);
	public Product getProductById(String id);
	public Collection<Product> getAllProduct();
	public Product changeCategory(String productId, ProductCategory category);
	public Product changeResidualQuantity(String id, int newResidualQuantity);
	
}
