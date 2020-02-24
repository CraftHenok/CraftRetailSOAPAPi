package com.dataaccesslayer.entity;

import javax.persistence.Column; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
 

public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
 

	@NotNull
	private String name;
 
 

	public ProductCategory() {
		super();
	}

	public ProductCategory( String name ) {
		super(); 
		this.name = name; 
	}

 
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

 
 
 
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

 
 
	public String toString() {
		return "";
	}

}
