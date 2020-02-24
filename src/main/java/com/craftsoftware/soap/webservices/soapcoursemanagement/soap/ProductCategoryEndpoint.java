package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.ProductCategorys.CreateProductCategory;
import com.craftsoftware.ProductCategorys.CreateProductCategoryRequest;
import com.craftsoftware.ProductCategorys.CreateProductCategoryResponse;
import com.craftsoftware.ProductCategorys.DeleteProductCategoryRequest;
import com.craftsoftware.ProductCategorys.DeleteProductCategoryResponse;
import com.craftsoftware.ProductCategorys.GetAllProductCategorysRequest;
import com.craftsoftware.ProductCategorys.GetAllProductCategorysResponse;
import com.craftsoftware.ProductCategorys.GetProductCategoryRequest;
import com.craftsoftware.ProductCategorys.GetProductCategoryResponse;
import com.craftsoftware.ProductCategorys.UpdateProductCategoryRequest;
import com.craftsoftware.ProductCategorys.UpdateProductCategoryResponse;
import com.craftsoftware.soap.service.ProductCategoryService;
import com.dataaccesslayer.entity.ProductCategory;

@Endpoint
public class ProductCategoryEndpoint {
	
	ProductCategoryService service = new ProductCategoryService();

	@PayloadRoot(namespace = "http://www.craftsoftware.com/ProductCategorys", localPart = "GetProductCategoryRequest")
	@ResponsePayload
	public GetProductCategoryResponse processProductCategoryDetailsRequest(@RequestPayload GetProductCategoryRequest request) {
		GetProductCategoryResponse response = new GetProductCategoryResponse();	
		ProductCategory ProductCategorys =service.getProductCategoryById(Long.parseLong(String.valueOf(request.getId())));
		response.setProductCategory(ProductCategorys);
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/ProductCategorys", localPart = "DeleteProductCategoryRequest")
	@ResponsePayload
	public DeleteProductCategoryResponse deleteProductCategoryRequest(@RequestPayload DeleteProductCategoryRequest request) {
		DeleteProductCategoryResponse response = new DeleteProductCategoryResponse();
		ProductCategoryService ProductCategory = new ProductCategoryService();
		ProductCategory.deleteProductCategory(Long.parseLong(String.valueOf(request.getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/ProductCategorys", localPart = "GetAllProductCategorysRequest")
	@ResponsePayload
	public GetAllProductCategorysResponse getAllProductCategorysRequest(@RequestPayload GetAllProductCategorysRequest request) {
		Iterable<ProductCategory> ProductCategorys = service.getAllProductCategorys();
		GetAllProductCategorysResponse response = new GetAllProductCategorysResponse();
		for (ProductCategory ProductCategory : ProductCategorys) {
			ProductCategory mapProductCategory = mapProductCategory(ProductCategory);
			response.getProducts().add(mapProductCategory);
		}
		return response;
		
	}
	
	private ProductCategory mapProductCategory(ProductCategory ProductCategory) {
	 
		return ProductCategory;
	}
	private ProductCategory mapProductCategory(CreateProductCategory ProductCategory) {
		ProductCategory ProductCategoryDetails = new ProductCategory();
	 	ProductCategoryDetails.setName(ProductCategory.getname());
		 
		return ProductCategoryDetails;
	}
	@PayloadRoot(namespace = "http://www.craftsoftware.com/ProductCategorys", localPart = "UpdateProductCategoryRequest")
	@ResponsePayload
	public UpdateProductCategoryResponse updateProductCategoryRequest(@RequestPayload UpdateProductCategoryRequest request) {
		UpdateProductCategoryResponse response = new UpdateProductCategoryResponse();
		service.updateProductCategory(request.getProductCategory(),request.getProductCategory().getId());
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/ProductCategorys", localPart = "CreateProductCategoryRequest")
	@ResponsePayload
	public CreateProductCategoryResponse createProductCategoryRequest(@RequestPayload CreateProductCategoryRequest request) {
		CreateProductCategoryResponse response = new CreateProductCategoryResponse();
		ProductCategory ProductCategory = mapProductCategory(request.getCreateProductCategory());
		service.createProductCategory(ProductCategory);
		return response;
		
	}
}
