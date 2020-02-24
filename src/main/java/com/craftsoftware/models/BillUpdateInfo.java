package com.craftsoftware.models;

import java.util.List;

import javax.validation.constraints.NotNull;
 

import com.craftsoftware.models.BillStatus;

public class BillUpdateInfo {
	private List<ProductInfoForBill> productsToBeAdded;
	private List<ProductInfoForBill> productsToBeRemoved;

	@NotNull
	private BillStatus status;

	public BillUpdateInfo() {
		super();

	}

	public List<ProductInfoForBill> getProductsToBeAdded() {
		return productsToBeAdded;
	}

	public List<ProductInfoForBill> getProductsToBeRemoved() {
		return productsToBeRemoved;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setProductsToBeAdded(List<ProductInfoForBill> productsToBeAdded) {
		this.productsToBeAdded = productsToBeAdded;
	}

	public void setProductsToBeRemoved(List<ProductInfoForBill> productsToBeRemoved) {
		this.productsToBeRemoved = productsToBeRemoved;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public String toString() {
		return "";
	}

	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTotalValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTotalCost() {
		// TODO Auto-generated method stub
		return 0;
	}

}
