package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.MarketRating;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketRatingService {

	List<MarketRating> index(Integer mId);

	List<MarketRating> indexForUser(Integer userId);

	MarketRating create(Integer userId, Integer mId, MarketRating marketRating);

	MarketRating update(User user, Integer mrId, MarketRating marketRating);

	boolean destroy(User user, Integer mrId);
	
	Integer getAvergRating(int marketId);

}
