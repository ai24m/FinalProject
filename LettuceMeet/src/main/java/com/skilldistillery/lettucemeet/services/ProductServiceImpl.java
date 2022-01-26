package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.Type;
import com.skilldistillery.lettucemeet.repositories.ProductReposity;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductReposity prodRepo;

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
	public Product createProduct(Product product) {
		if (product.getType() == null) {
			Type type = new Type();
			type.setId(1);
			product.setType(type);
			prodRepo.saveAndFlush(product);
		}
		return product;
	}

	@Override
	public Product updateProduct(int prodId, Product product) {
		Optional<Product> optProd = prodRepo.findById(prodId);
		Product managed = null;
		if (optProd.isPresent()) {
			managed = optProd.get();
			managed.setDescription(product.getDescription());
			managed.setOrganic(product.isOrganic());
			managed.setPrice(product.getPrice());
			managed.setImageUrl(product.getImageUrl());
			managed.setQuantity(product.getQuantity());
			managed.setAvailableDate(product.getAvailableDate());
			if (product.getType() != null) {
				managed.setType(product.getType());
			}
			prodRepo.saveAndFlush(managed);
		}
		return product;
	}

	@Override
	public boolean deleteProduct(int prodId) {
		boolean deleted = false;
		if (prodRepo.existsById(prodId)) {
			prodRepo.deleteById(prodId);
			deleted = true;
		}
		return deleted;
	}

}
