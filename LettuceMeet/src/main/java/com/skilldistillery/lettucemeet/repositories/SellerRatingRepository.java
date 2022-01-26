package com.skilldistillery.lettucemeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.SellerRating;

public interface SellerRatingRepository extends JpaRepository<SellerRating, Integer> {

	List<SellerRating> findByUser_Username(String userName);
}
