package com.craftsoftware.soap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Component;
import com.dataaccesslayer.entity.LineItem;
import com.dataaccesslayer.entity.Product;
import com.dataaccesslayer.entity.ProductCategory;
import com.dataaccesslayer.repository.LineItemRepository;
import com.dataaccesslayer.repository.ProductCategoryRepository;
import com.dataaccesslayer.repository.ProductRepository; 
import com.craftsoftware.models.ProductInfo;

@Component
public class ProductCategoryService {
 

	 @Autowired
	private ProductCategoryRepository productRepo;
	
 
	public enum Status {
		SUCCESS, FAILURE;
	}
	
	public ProductCategory createProductCategory(ProductCategory productInfo) {
		 
		ProductCategory product = productRepo.save(productInfo); 
		return product;

	}

	public void deleteProductCategory(Long id) {
	 
		Optional<ProductCategory> product = productRepo.findById(id);
		productRepo.delete(product.get());
	}

 
		
		


	public Iterable<ProductCategory> getAllProductCategorys() {
		Iterable<ProductCategory> products = productRepo.findAll();
		//logger.info("returning all products");
		return products;
	}

	public Optional<ProductCategory> getProductCategoryById(Long id) {
	 	return productRepo.findById(id);
	}

	public ProductCategory updateProductCategory(com.craftsoftware.retail.ProductCategory productCategory, long l) {
		 	Optional<ProductCategory> product = productRepo.findById(l);
		 	product.get().setName(productCategory.getName());
		 
		ProductCategory p = productRepo.save(product.get());
		//logger.info("updated product id = " + product.getId());
		return p;
	}

	 

 

}
