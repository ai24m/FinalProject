package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketService {

	Market show(int mId);
	
	Market getByMarketCommentId(int mCId);

	List<Market> index();

	Market create(Market market, User user, Address address);

	Market update(User user, Integer mId, Market market);

	boolean destroy(User user, Integer mId);

	Market findById(Integer mId);


}
