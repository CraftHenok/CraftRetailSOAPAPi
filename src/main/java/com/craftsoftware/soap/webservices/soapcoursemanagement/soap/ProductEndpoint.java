package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.soap.service.ProductService;
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
import com.dataaccesslayer.entity.Product

@Endpoint
public class ProductEndpoint {
	
	ProductService service = new ProductService();

	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "GetProductRequest")
	@ResponsePayload
	public GetProductResponse processProductDetailsRequest(@RequestPayload GetProductRequest request) {
		GetProductResponse response = new GetProductResponse();	
		response.setProduct(service.getProductById(request.getId()));
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "DeleteProductRequest")
	@ResponsePayload
	public DeleteProductResponse deleteProductRequest(@RequestPayload DeleteProductRequest request) {
		DeleteProductResponse response = new DeleteProductResponse();
		ProductService Product = new ProductService();
		Product.deleteProduct(request.getId());
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "GetAllProductsRequest")
	@ResponsePayload
	public GetAllProductsResponse getAllProductsRequest(@RequestPayload GetAllProductsRequest request) {
		List<Product> Products = service.getAllProducts();
		GetAllProductsResponse response = new GetAllProductsResponse();
		for (Product Product : Products) {
			Product mapProduct = mapProduct(Product);
			response.getProducts().add(mapProduct);
		}
		return response;
		
	}
	
	private Product mapProduct(Product Product) {
		Product ProductDetails = new Product();
		ProductDetails.setId(Product.getId());
		ProductDetails.setFist(Product.getFist());
		ProductDetails.setDepartment(Product.getDepartment());
		return ProductDetails;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "UpdateProductRequest")
	@ResponsePayload
	public UpdateProductResponse updateProductRequest(@RequestPayload UpdateProductRequest request) {
		UpdateProductResponse response = new UpdateProductResponse();
		service.udpateProduct(request.getProduct());
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Products", localPart = "CreateProductRequest")
	@ResponsePayload
	public CreateProductResponse updateProductRequest(@RequestPayload CreateProductRequest request) {
		CreateProductResponse response = new CreateProductResponse();
		service.createProduct(request.getCreateProduct());
		return response;
		
	}
}
