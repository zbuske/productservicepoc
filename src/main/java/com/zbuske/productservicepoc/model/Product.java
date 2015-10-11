package com.zbuske.productservicepoc.model;
import java.sql.Date;


public class Product {
	
	private Integer id;
	private String sku;
	private String name;
	private String category;
	private Double price;
	private Date lastUpdated;
	

	public Product(Integer id, String sku, String name, String category, Double price, Date lastUpdated) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.category = category;
		this.price = price;
		this.lastUpdated = lastUpdated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer productId) {
		this.id = productId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", sku=" + sku + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", lastUpdated=" + lastUpdated + "]";
	}


}
