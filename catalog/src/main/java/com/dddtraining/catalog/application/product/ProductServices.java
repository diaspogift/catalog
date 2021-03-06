package com.dddtraining.catalog.application.product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.dddtraining.catalog.domain.model.product.*;


public interface  ProductServices {
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
	public boolean changeImage(String productId, List<String> newImage);
	public boolean putInPromotion(String productId, Promotion promotion);
	public boolean removePromotion(String productId);
	public boolean updateBrand(String productId, Brand newBrand);
	public Product getProductById(String id);
	public Collection<Product> getAllProduct();
	public Product changeCategory(String productId, ProductCategory category);
	public Product changeResidualQuantity(String id, int newResidualQuantity);
	public Collection<Product> searchProduct(String categoryName, String name);
	public void removeAll();
}
