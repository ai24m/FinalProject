package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.SellerRating;

public interface SellerRatingService {

	//find all
	List<SellerRating> showAll();
	
	//find By userName
	List<SellerRating> findByUserName(String username);
	//find By userName and Id
	SellerRating findBySellerRatingId(int sellerId, String username);
	
	//CRUD
	SellerRating createSellerRating(int sellerId, String username, SellerRating sellerRating);
	
	SellerRating updateSellerRating(int sellerId, String username, SellerRating sellerRating);
	
	boolean deleteSellerRating(int sellerId, String username);

}
