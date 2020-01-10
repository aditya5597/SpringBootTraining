package com.aditya.demof.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product 
{
	public Product()
	{
		super();
	}
	
	public Product(String name, Integer id, String description, Double price) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
		this.price = price;
	}
	private String name;
	private Integer id;
	private String description;
	private Double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
