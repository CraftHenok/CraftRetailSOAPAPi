package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.retail.CreateProductCategory;
import com.craftsoftware.retail.CreateProductCategoryRequest;
import com.craftsoftware.retail.CreateProductCategoryResponse;
import com.craftsoftware.retail.DeleteProductCategoryRequest;
import com.craftsoftware.retail.DeleteProductCategoryResponse;
import com.craftsoftware.retail.GetAllProductCategorysRequest;
import com.craftsoftware.retail.GetAllProductCategorysResponse;
import com.craftsoftware.retail.GetProductCategoryRequest;
import com.craftsoftware.retail.GetProductCategoryResponse;
import com.craftsoftware.retail.UpdateProductCategoryRequest;
import com.craftsoftware.retail.UpdateProductCategoryResponse;
import com.craftsoftware.soap.service.ProductCategoryService;
import com.dataaccesslayer.entity.ProductCategory;

@Endpoint
public class ProductCategoryEndpoint {
	 @Autowired
	ProductCategoryService service; 

	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "GetProductCategoryRequest")
	@ResponsePayload
	public GetProductCategoryResponse processProductCategoryDetailsRequest(@RequestPayload GetProductCategoryRequest request) {
		GetProductCategoryResponse response = new GetProductCategoryResponse();	
		Optional<ProductCategory> ProductCategorys =service.getProductCategoryById(Long.parseLong(String.valueOf(request.getId())));
		response.setProductCategory(ProductCategorys.get());
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "DeleteProductCategoryRequest")
	@ResponsePayload
	public DeleteProductCategoryResponse deleteProductCategoryRequest(@RequestPayload DeleteProductCategoryRequest request) {
		DeleteProductCategoryResponse response = new DeleteProductCategoryResponse();
		ProductCategoryService ProductCategory = new ProductCategoryService();
		ProductCategory.deleteProductCategory(Long.parseLong(String.valueOf(request.getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "GetAllProductCategorysRequest")
	@ResponsePayload
	public GetAllProductCategorysResponse getAllProductCategorysRequest(@RequestPayload GetAllProductCategorysRequest request) {
		Iterable<ProductCategory> ProductCategorys = service.getAllProductCategorys();
		GetAllProductCategorysResponse response = new GetAllProductCategorysResponse();
		for (ProductCategory ProductCategory : ProductCategorys) {
			ProductCategory mapProductCategory = mapProductCategory(ProductCategory);
			response.getProductCategorys().add(mapProductCategory);
		}
		return response;
		
	}
	
	private ProductCategory mapProductCategory(ProductCategory ProductCategory) {
	 
		return ProductCategory;
	}
	private ProductCategory mapProductCategory(CreateProductCategory ProductCategory) {
		ProductCategory ProductCategoryDetails = new ProductCategory();
	 	ProductCategoryDetails.setName(ProductCategory.getName());
		 
		return ProductCategoryDetails;
	}
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "UpdateProductCategoryRequest")
	@ResponsePayload
	public UpdateProductCategoryResponse updateProductCategoryRequest(@RequestPayload UpdateProductCategoryRequest request) {
		UpdateProductCategoryResponse response = new UpdateProductCategoryResponse();
		service.updateProductCategory(request.getProductCategory(),Long.parseLong(String.valueOf(request.getProductCategory().getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/retail", localPart = "CreateProductCategoryRequest")
	@ResponsePayload
	public CreateProductCategoryResponse createProductCategoryRequest(@RequestPayload CreateProductCategoryRequest request) {
		CreateProductCategoryResponse response = new CreateProductCategoryResponse();
		ProductCategory ProductCategory = mapProductCategory(request.getCreateProductCategory());
		service.createProductCategory(ProductCategory);
		return response;
		
	}
}
