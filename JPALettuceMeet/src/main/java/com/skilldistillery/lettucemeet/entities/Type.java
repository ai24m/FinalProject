package com.skilldistillery.lettucemeet.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="type")
	private List<Product> products; 
	
//	no arg constructor 
	public Type() {
		super();
	}
	
	public Type(int id, String name, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		if (products == null) products = new ArrayList<>();
		
		if (!products.contains(product)) {
			products.add(product);
			if (product.getType() != null) {
				product.getType().getProducts().remove(product);
			} 
			product.setType(this);
		}
	}
	
	public void removeProduct(Product product) {
		product.setType(null);
		if (products != null) {
			products.remove(product);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "type [id=" + id + ", name=" + name + "]";
	}
	
}
