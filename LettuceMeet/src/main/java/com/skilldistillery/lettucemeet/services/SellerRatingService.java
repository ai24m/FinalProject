package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.SellerRating;
import com.skilldistillery.lettucemeet.entities.SellerRatingId;
import com.skilldistillery.lettucemeet.entities.User;

public interface SellerRatingService {

	//find all
	List<SellerRating> showAll();
	
	//find By userName
	List<SellerRating> findByUserName(String username);
	
	//CRUD
	SellerRating createSellerRating(User seller, User user, SellerRating sellerRating);
	
	SellerRating updateSellerRating(SellerRatingId sellerRatingId, SellerRating sellerRating);
	
	boolean deleteSellerRating(SellerRatingId sellerRatingId);
}
