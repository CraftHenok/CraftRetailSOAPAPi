package com.craftsoftware.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
  

public class ProductInfo {

	@NotNull
	private String barCodeId;

	@NotNull
	private String name;

	@NotNull 
	private String productCategory;

	@NotNull
	@DecimalMin(value = "0.1")
	private double rate;

	public ProductInfo() {
		super();
	}

	public ProductInfo(String barCodeId, double rate, String name, String productCategory) {
		super();
		this.barCodeId = barCodeId;
		this.name = name;
		this.productCategory = productCategory;
		this.rate = rate;
	}

	public String getBarCodeId() {
		return barCodeId;
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return name;
	}
	public String getProductCategory() {
		return productCategory;
	}

	public double getRate() {
		return rate;
	}

	public void setBarCodeId(String barCodeId) {
		this.barCodeId = barCodeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String toString() {
		return "";
	}

}
