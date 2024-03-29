package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.ProductRating;

public interface ProductRatingService {

	//show all product ratings
	List<ProductRating> showAll();
	
	// find By username
	List<ProductRating> findByUserName(String username);
	
	//find By Username and id
	ProductRating findByProductRatingId(int productId, String userName);
	
	//CRUD
	ProductRating createProductRating(int productId, String userName, ProductRating productRating);
	
	ProductRating updateProductRating(int productId, String userName, ProductRating productRating);
	
	boolean deleteProductRating(int productId, String userName);

	List<ProductRating> getByProductId(Integer pcId);
}
