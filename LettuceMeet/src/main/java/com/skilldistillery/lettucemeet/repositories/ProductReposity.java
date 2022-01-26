package com.skilldistillery.lettucemeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.Product;

public interface ProductReposity extends JpaRepository<Product, Integer> {

}
