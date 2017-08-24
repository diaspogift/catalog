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
	public void delete(String productId) {
		Product foundProduct = repo.getPtoductById(productId);
		if(foundProduct == null){
			throw new IllegalArgumentException("Product not found");
		}
		repo.delete(foundProduct);

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
	public void changePrice(String productId, BigDecimal newPrice) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			throw new IllegalArgumentException("Produit non trouve");
		}
		
		foundProduct.changePrice(newPrice);

	}

	@Override
	public void changeDescription(String productId, String newDescription) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			throw new IllegalArgumentException("Produit non trouve");
		}
		
		foundProduct.changeDescription(newDescription);

	}

	@Override
	public void updateTitle(String productId) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			throw new IllegalArgumentException("Produit non trouve");
		}
		
		foundProduct.updateTitle();
	}

	@Override
	public void updateImage(String productId, String newImage, int index) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			throw new IllegalArgumentException("Produit non trouve");
		}
		
		foundProduct.updateImage(newImage, index);
	}

	@Override
	public void putInPromotion(String productId, Discount discount) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			throw new IllegalArgumentException("Produit non trouve");
		}
		
		foundProduct.applyDiscount(discount);
	}

	@Override
	public void updateBrand(String productId, Brand newBrand) {
		Product foundProduct = repo.findProductById(productId);
		if(foundProduct == null){
			throw new IllegalArgumentException("Produit non trouve");
		}
		
		foundProduct.updateBrand(newBrand);

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
	public void changeResidualQuantity(String id, int newResidualQuantity) {
		Product product = repo.getPtoductById(id);
		product.changeResidualQuantity(newResidualQuantity);
		
	}

}
