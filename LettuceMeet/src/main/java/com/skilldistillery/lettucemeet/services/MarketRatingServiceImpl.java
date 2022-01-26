package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.MarketRating;
import com.skilldistillery.lettucemeet.entities.MarketRatingId;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.MarketRatingRepository;
import com.skilldistillery.lettucemeet.repositories.MarketRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service 
public class MarketRatingServiceImpl implements MarketRatingService {

	@Autowired 
	private MarketRatingRepository mrRepo;
	
	@Autowired 
	private UserRepository userRepo; 
	
	@Autowired 
	private MarketRepository marketRepo; 

	@Override
	public List<MarketRating> index(Integer mId) {
		return mrRepo.findByMarket_Id(mId);
	}

	@Override
	public List<MarketRating> indexForUser(Integer userId) {
		return mrRepo.findByUser_Id(userId);
	}

	@Override
	public MarketRating create(Integer userId, Integer mId, MarketRating marketRating) {
		MarketRatingId mrId = new MarketRatingId(userId, mId);
		Optional <User> uOpt = userRepo.findById(mId);
		if (uOpt.isPresent()) {
			User user = uOpt.get();
			marketRating.setUser(user);
		}
		Optional <Market> mOpt = marketRepo.findById(mId);
		if (mOpt.isPresent()) {
			Market market = mOpt.get();
			marketRating.setMarket(market);
		}
		if (mrId != null) {
			if (userId != null && mId != null) {
				mrRepo.saveAndFlush(marketRating);
				return marketRating; 
			}
		} return null;
	}

	@Override
	public MarketRating update(User user, Integer mId, MarketRating marketRating) {
		MarketRatingId mrId = new MarketRatingId(user.getId(), mId);
		if (marketRating.getUser().getId() == user.getId()) {
			Optional<MarketRating> mOpt = mrRepo.findById(mrId);
			if (mOpt.isPresent()) {
				MarketRating updatedMarketRating = mOpt.get();
				return updatedMarketRating; 
			}
		} return null;
	}
	
	@Override 
	public boolean destroy(User user, Integer mId) {
		boolean deleted = false; 
		MarketRatingId mrId = new MarketRatingId(user.getId(), mId);
		Optional<MarketRating> mrOpt = mrRepo.findById(mrId);
		if (mrOpt.isPresent()) {
			MarketRating marketRating = mrOpt.get();
			mrRepo.delete(marketRating);
			deleted = true;
			return deleted; 
		} return deleted; 
	}
	
}
