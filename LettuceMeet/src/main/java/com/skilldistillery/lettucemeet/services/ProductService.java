package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Product;

public interface ProductService {
	
	public List<Product> getProducts();
	
	public Product findById(int prodId);
	
	public Product createProduct(String username, Product prod);
	
	public Product updateProduct(String username, int prodId, Product prod);
	
	public boolean deleteProduct(String username, int prodId);

}
