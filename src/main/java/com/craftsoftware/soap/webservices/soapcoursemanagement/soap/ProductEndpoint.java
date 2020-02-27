package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.soap.service.ProductService;
import com.craftsoftware.retail.CreateProduct;
import com.craftsoftware.retail.CreateProductRequest;
import com.craftsoftware.retail.CreateProductResponse;
import com.craftsoftware.retail.DeleteProductRequest;
import com.craftsoftware.retail.DeleteProductResponse;
import com.craftsoftware.retail.GetAllProductsRequest;
import com.craftsoftware.retail.GetAllProductsResponse;
import com.craftsoftware.retail.GetProductRequest;
import com.craftsoftware.retail.GetProductResponse;
import com.craftsoftware.retail.UpdateProductRequest;
import com.craftsoftware.retail.UpdateProductResponse;
import com.craftsoftware.models.ProductInfo;
import com.dataaccesslayer.entity.Product;

@Endpoint
public class ProductEndpoint {
	 @Autowired
	 public ProductService service;

	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "GetProductRequest")
	@ResponsePayload
	public GetProductResponse processProductDetailsRequest(@RequestPayload GetProductRequest request) {
		GetProductResponse response = new GetProductResponse();	
		Optional<Product> product = service.getProductById(Long.parseLong(String.valueOf(request.getId())));
		response.setProduct(product.get());
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "DeleteProductRequest")
	@ResponsePayload
	public DeleteProductResponse deleteProductRequest(@RequestPayload DeleteProductRequest request) {
		DeleteProductResponse response = new DeleteProductResponse();
		ProductService Product = new ProductService();
		Product.deleteProduct(Long.parseLong(String.valueOf(request.getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "GetAllProductsRequest")
	@ResponsePayload
	public GetAllProductsResponse getAllProductsRequest(@RequestPayload GetAllProductsRequest request) {
		Iterable<Product> Products = service.getAllProducts();
		GetAllProductsResponse response = new GetAllProductsResponse();
		for (Product product : Products) {
		 	response.getProducts().add(product);
		}
		return response;
		
	}
	
	private ProductInfo mapProduct(Product Product) {
		ProductInfo ProductDetails = new ProductInfo();
		ProductDetails.setBarCodeId(Product.getBarCodeId());
		ProductDetails.setName(Product.getName());
		ProductDetails.setProductCategory(Product.getProductCategory());
		ProductDetails.setRate(Product.getRate());
	 	return ProductDetails;
	}
	private ProductInfo mapProduct(CreateProduct Product) {
		ProductInfo ProductDetails = new ProductInfo();
		ProductDetails.setBarCodeId(Product.getBarcode());
		ProductDetails.setName(Product.getName());
		ProductDetails.setProductCategory(Product.getProductCategory());
		ProductDetails.setRate(Product.getRate());
	 return ProductDetails;
	}
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "UpdateProductRequest")
	@ResponsePayload
	public UpdateProductResponse updateProductRequest(@RequestPayload UpdateProductRequest request) {
		UpdateProductResponse response = new UpdateProductResponse();
	 	service.updateProduct(request.getProduct(),Long.parseLong(String.valueOf(request.getProduct().getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "CreateProductRequest")
	@ResponsePayload
	public CreateProductResponse createProductRequest(@RequestPayload CreateProductRequest request) {
		CreateProductResponse response = new CreateProductResponse();
		ProductInfo product = mapProduct(request.getCreateProduct());
		service.createProduct(product);
		return response;
		
	}
}
