package com.skilldistillery.lettucemeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.ProductRating;
import com.skilldistillery.lettucemeet.entities.ProductRatingId;

public interface ProductRatingRepository extends JpaRepository<ProductRating, ProductRatingId> {

	List<ProductRating> findByUser_Username(String userName);
}
