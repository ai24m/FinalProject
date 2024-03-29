package com.skilldistillery.lettucemeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByUserUsername(String username);
	
	Product findByIdAndUserUsername(int id, String username);

	Product queryById(int productId);

	Product findByProductComments_Id(Integer pcId);
	
}
