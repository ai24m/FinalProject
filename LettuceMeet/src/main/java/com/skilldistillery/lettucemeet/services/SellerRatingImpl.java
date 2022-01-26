package com.skilldistillery.lettucemeet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.SellerRating;
import com.skilldistillery.lettucemeet.entities.SellerRatingId;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.SellerRatingRepository;

@Service
public class SellerRatingImpl implements SellerRatingService {

	@Autowired
	private SellerRatingRepository sellerRatingRepo;

	@Override
	public List<SellerRating> showAll() {
		// TODO Auto-generated method stub
		return sellerRatingRepo.findAll();
	}

	@Override
	public List<SellerRating> findByUserName(String username) {
		// TODO Auto-generated method stub
		username = "%" + username + "%";
		return sellerRatingRepo.findByUser_Username(username);
	}

	@Override
	public SellerRating createSellerRating(User seller, User user, SellerRating sellerRating) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public SellerRating updateSellerRating(SellerRatingId sellerRatingId, SellerRating sellerRating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteSellerRating(SellerRatingId sellerRatingId) {
		// TODO Auto-generated method stub
		return false;
	}

}
