package com.craftsoftware.soap.service;

import java.util.List;
 
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
	 
		ProductCategory product = productRepo.findOne(id);
		productRepo.delete(product);
	}

 
		
		


	public Iterable<ProductCategory> getAllProductCategorys() {
		Iterable<ProductCategory> products = productRepo.findAll();
		//logger.info("returning all products");
		return products;
	}

	public ProductCategory getProductCategoryById(Long id) {
	 	return productRepo.findOne(id);
	}

	public ProductCategory updateProductCategory(ProductCategory productInfo, Long id) {
		 	ProductCategory product = productRepo.findOne(id);
		 	product.setName(productInfo.getName());
		 
		ProductCategory p = productRepo.save(product);
		//logger.info("updated product id = " + product.getId());
		return p;
	}

	 

 

}
