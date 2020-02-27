package com.craftsoftware.soap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Component;
import com.dataaccesslayer.entity.LineItem;
import com.dataaccesslayer.entity.Product;
import com.dataaccesslayer.repository.LineItemRepository;
import com.dataaccesslayer.repository.ProductRepository; 
import com.craftsoftware.models.ProductInfo;

@Component
public class ProductService {
 

	 @Autowired
	private ProductRepository productRepo;
	
 
	public enum Status {
		SUCCESS, FAILURE;
	}
	
	public Product createProduct(ProductInfo productInfo) {
		//logger.info("Input recieved to create product = " + productInfo);
		verifyIfProductExists(productInfo.getBarCodeId());
		Product product = new Product();
		product.setBarCodeId(productInfo.getBarCodeId());
		product.setName(productInfo.getName());
		product.setProductCategory(productInfo.getProductCategory());
		product.setRate(productInfo.getRate());

		product = productRepo.save(product);
		//logger.info("Created product with id = " + product.get().getId());
		return product;

	}

	public void deleteProduct(Long id) {
		verifyProductExists(id);
		verifyLineItemExists(id);
		Optional<Product> product = productRepo.findById(id);
		productRepo.delete(product.get());
	}

	private void verifyLineItemExists(Long id) {
		//logger.info("Verifying if the a line item exists with an id = " + id);
	 
	}
		
		


	public Iterable<Product> getAllProducts() {
		Iterable<Product> products = productRepo.findAll();
		//logger.info("returning all products");
		return products;
	}

	public Optional<Product> getProductById(Long id) {
		verifyProductExists(id);
		return productRepo.findById(id);
	}

	public Product updateProduct(Product productInfo, Long id) {
		verifyProductExists(id);
		Optional<Product> product = productRepo.findById(id);
		product.get().setBarCodeId(productInfo.getBarCodeId());
		product.get().setName(productInfo.getName());
		product.get().setProductCategory(productInfo.getProductCategory());
		product.get().setRate(productInfo.getRate());
		Product p = productRepo.save(product.get());
		//logger.info("updated product id = " + product.get().getId());
		return p;
	}

	private void verifyIfProductExists(String barCodeId) {
		List<Product> productsByBarCodeID = productRepo.findByBarCodeId(barCodeId);
		if (null != productsByBarCodeID && !productsByBarCodeID.isEmpty()) {
			//logger.info("Problem with input data: BarCode ID  " + barCodeId + " already exists in Product Master");
		 	}
	}

	private void verifyProductExists(Long id) {
		//logger.info("Verifying if the product exists with an id = " + id);
		Optional<Product> product = productRepo.findById(id);
		if (product == null) {
	 	}
	}

}
