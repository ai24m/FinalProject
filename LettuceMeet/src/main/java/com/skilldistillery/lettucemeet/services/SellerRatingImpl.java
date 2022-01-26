package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.SellerRating;
import com.skilldistillery.lettucemeet.entities.SellerRatingId;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.SellerRatingRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service
public class SellerRatingImpl implements SellerRatingService {

	@Autowired
	private SellerRatingRepository sellerRatingRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<SellerRating> showAll() {
		// TODO Auto-generated method stub
		return sellerRatingRepo.findAll();
	}

	@Override
	public List<SellerRating> findByUserName(String username) {
		// TODO Auto-generated method stub
		return sellerRatingRepo.findByUser_Username(username);
	}

	@Override
	public SellerRating findBySellerRatingId(int sellerId, String username) {
		// TODO Auto-generated method stub
		SellerRatingId sId = new SellerRatingId(sellerId, userRepo.findByUsername(username).getId());
		Optional <SellerRating> op =sellerRatingRepo.findById(sId);
		SellerRating sellerRating = null;
		if(op.isPresent()) {
			 sellerRating = op.get();
		}
		return sellerRating;
	}

	@Override
	public SellerRating createSellerRating(int sellerId, String username, SellerRating sellerRating) {
		// TODO Auto-generated method stub

		SellerRatingId sId = new SellerRatingId(sellerId, userRepo.findByUsername(username).getId());
		if (sId != null) {
			Optional<User> op = userRepo.findById(sellerId);
			User user = userRepo.findByUsername(username);
			if (op.isPresent()) {
				User seller = op.get();
				userRepo.saveAndFlush(seller);
				userRepo.saveAndFlush(user);
				sellerRating.setSeller(seller);
				sellerRating.setUser(user);
				sellerRatingRepo.saveAndFlush(sellerRating);
				return sellerRating;
			}
		}
		return null;
	}

	@Override
	public SellerRating updateSellerRating(int sellerId, String username, SellerRating sellerRating) {
		// TODO Auto-generated method stub

		SellerRatingId sId = new SellerRatingId(sellerId, userRepo.findByUsername(username).getId());
		Optional<SellerRating> op = sellerRatingRepo.findById(sId);
		SellerRating existing = null;
		if (op.isPresent()) {
			existing = op.get();
			existing.setComment(sellerRating.getComment());
			existing.setSellerRating(sellerRating.getSellerRating());
			sellerRatingRepo.saveAndFlush(existing);
		}
		return existing;
	}

	@Override
	public boolean deleteSellerRating(int sellerId, String username) {

		// TODO Auto-generated method stub
		boolean deleted = false;
		SellerRatingId sId = new SellerRatingId(sellerId, userRepo.findByUsername(username).getId());
		Optional<SellerRating> op = sellerRatingRepo.findById(sId);
		SellerRating existing = null;
		
		if (op.isPresent()) {
			existing = op.get();
			sellerRatingRepo.delete(existing);
			deleted = true;
		}
		return deleted;
	}

}
