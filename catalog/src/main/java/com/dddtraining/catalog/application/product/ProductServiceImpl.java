package com.dddtraining.catalog.application.product;

import java.math.BigDecimal;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dddtraining.catalog.domain.model.product.Brand;
import com.dddtraining.catalog.domain.model.product.Discount;
import com.dddtraining.catalog.domain.model.product.Product;
import com.dddtraining.catalog.domain.model.product.ProductCategory;
import com.dddtraining.catalog.domain.model.product.ProductRepository;

@Service
@Transactional

public class ProductServiceImpl implements ProductServices{

	@Autowired
	private ProductRepository repo;
	
	@Override
	public Product add(Product product) {
		return repo.addProduct(product);
	}

	@Override
	public boolean delete(String productId) {
		Product foundProduct = repo.getPtoductById(productId);
		if(foundProduct == null){
			//throw new IllegalArgumentException("Product not found");
			return false;
		}
		return repo.delete(foundProduct);

	}

	@Override
	public Collection<Product> findProductByKeyOnName(String keyOnName) {
		return repo.findProductByKeyOnName(keyOnName);
	}

	@Override
	public Collection<Product> findProductByKeyOnBrandName(String keyOnBrandName) {
		return repo.findProductByKeyOnBrandName(keyOnBrandName);
	}

	@Override
	public Collection<Product> findProductByKeyOnCategoryName(String keyOnCategoryName) {
		return repo.findProductByKeyOnCategoryName(keyOnCategoryName);
	}

	@Override
	public Collection<Product> findProductInPromotion() {
		return repo.findProductInPromotion();
	}

	@Override
	public boolean changePrice(String productId, BigDecimal newPrice) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			//throw new IllegalArgumentException("Produit non trouve");
			return false;
		}
		
		foundProduct.changePrice(newPrice);
		return true;

	}

	@Override
	public boolean changeDescription(String productId, String newDescription) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			//throw new IllegalArgumentException("Produit non trouve");
			return false;
		}
		
		foundProduct.changeDescription(newDescription);
		return true;
	}

	@Override
	public boolean updateTitle(String productId) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			//throw new IllegalArgumentException("Produit non trouve");
			return false;
		}
		
		foundProduct.updateTitle();
		return true;
	}

	@Override
	public boolean updateImage(String productId, String newImage, int index) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			//throw new IllegalArgumentException("Produit non trouve");
			return false;
		}
		
		return foundProduct.updateImage(newImage, index);
	}

	@Override
	public boolean putInPromotion(String productId, Discount discount) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			//throw new IllegalArgumentException("Produit non trouve");
			return false;
		}
		
		return foundProduct.applyDiscount(discount);
	}

	@Override
	public boolean updateBrand(String productId, Brand newBrand) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			//throw new IllegalArgumentException("Produit non trouve");
			return false;
		}
		
		 foundProduct.updateBrand(newBrand);
		 return true;

	}

	@Override
	public Product getProductById(String id) {
		return repo.getPtoductById(id);
	}

	@Override
	public Collection<Product> getAllProduct() {
		
		return repo.findAll();
	}

	@Override
	public Product changeCategory(String productId, ProductCategory productCategory) {
		Product product = repo.getPtoductById(productId);
		product = product.changeCategory(productCategory);
		return product;
	}

	@Override
	public Product changeResidualQuantity(String id, int newResidualQuantity) {
		Product product = repo.getPtoductById(id);
		if(product == null) return null;
		
		product.changeResidualQuantity(newResidualQuantity);
		return product;
	}

}
