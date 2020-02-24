package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.soap.service.ProductService;
import com.craftsoftware.Products.CreateProduct;
import com.craftsoftware.Products.CreateProductRequest;
import com.craftsoftware.Products.CreateProductResponse;
import com.craftsoftware.Products.DeleteProductRequest;
import com.craftsoftware.Products.DeleteProductResponse;
import com.craftsoftware.Products.GetAllProductsRequest;
import com.craftsoftware.Products.GetAllProductsResponse;
import com.craftsoftware.Products.GetProductRequest;
import com.craftsoftware.Products.GetProductResponse;
import com.craftsoftware.Products.UpdateProductRequest;
import com.craftsoftware.Products.UpdateProductResponse;
import com.craftsoftware.models.ProductInfo;
import com.dataaccesslayer.entity.Product;

@Endpoint
public class ProductEndpoint {
	
	ProductService service = new ProductService();

	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "GetProductRequest")
	@ResponsePayload
	public GetProductResponse processProductDetailsRequest(@RequestPayload GetProductRequest request) {
		GetProductResponse response = new GetProductResponse();	
		Product product = service.getProductById(Long.parseLong(String.valueOf(request.getId())));
		response.setProduct(product);
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "DeleteProductRequest")
	@ResponsePayload
	public DeleteProductResponse deleteProductRequest(@RequestPayload DeleteProductRequest request) {
		DeleteProductResponse response = new DeleteProductResponse();
		ProductService Product = new ProductService();
		Product.deleteProduct(Long.parseLong(String.valueOf(request.getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "GetAllProductsRequest")
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
		 
		return ProductDetails;
	}
	private ProductInfo mapProduct(CreateProduct Product) {
		ProductInfo ProductDetails = new ProductInfo();
		ProductDetails.setBarCodeId(Product.getBarcode());
		ProductDetails.setName(Product.getName());
	 return ProductDetails;
	}
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "UpdateProductRequest")
	@ResponsePayload
	public UpdateProductResponse updateProductRequest(@RequestPayload UpdateProductRequest request) {
		UpdateProductResponse response = new UpdateProductResponse();
		service.updateProduct(request.getProduct(),Long.parseLong(String.valueOf(request.getProduct().getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "CreateProductRequest")
	@ResponsePayload
	public CreateProductResponse createProductRequest(@RequestPayload CreateProductRequest request) {
		CreateProductResponse response = new CreateProductResponse();
		ProductInfo product = mapProduct(request.getCreateProduct());
		service.createProduct(product);
		return response;
		
	}
}
