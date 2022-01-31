package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.Type;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.ProductRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Product> getProducts() {
		return prodRepo.findAll();
	}

	@Override
	public Product findById(int prodId) {
		Optional<Product> optProd = prodRepo.findById(prodId);
		if (optProd.isPresent()) {
			return optProd.get();
		}
		return null;
	}

	@Override
	public Product createProduct(String username, Product product) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			product.setUser(user);
			if (product.getType() == null) {
				Type temp = new Type();
				temp.setId(10);
				product.setType(temp);
			}
			return prodRepo.saveAndFlush(product);
		}
		return null;
	}

	@Override
	public Product updateProduct(String username, int prodId, Product product) {
		Product existing = prodRepo.findByIdAndUserUsername(prodId, username);
		if (existing != null) {
			existing.setDescription(product.getDescription());
			existing.setOrganic(product.isOrganic());
			existing.setPrice(product.getPrice());
			existing.setImageUrl(product.getImageUrl());
			existing.setQuantity(product.getQuantity());
			existing.setAvailableDate(product.getAvailableDate());
			if (product.getType() != null) {
				existing.setType(product.getType());
			}
			prodRepo.saveAndFlush(existing);
		}
		return product;
	}

	@Override
	public boolean deleteProduct(String username, int prodId) {
		boolean deleted = false;
		Product prod = prodRepo.findByIdAndUserUsername(prodId, username);
		if (prod != null) {
			prodRepo.delete(prod);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<Product> getUserProducts(String username) {
		return prodRepo.findByUserUsername(username);
	}

}
