package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Product;

public interface ProductService {
	
	public List<Product> getProducts();
	
	public Product findById(int prodId);
	
	public Product createProduct(Product prod);
	
	public Product updateProduct(int prodId, Product prod);
	
	public boolean deleteProduct(int prodId);

}
