package com.skilldistillery.lettucemeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.SellerRating;
import com.skilldistillery.lettucemeet.entities.SellerRatingId;

public interface SellerRatingRepository extends JpaRepository<SellerRating, SellerRatingId> {

	List<SellerRating> findByUser_Username(String userName);

//	SellerRating findBySellerRating_Id(SellerRatingId sellerRatingId);

}
