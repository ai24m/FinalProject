package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketService {

	Market show(int mId);

	List<Market> index();

	Market create(Market market, Address address, User user);

	Market update(User user, Integer mId, Market market);

	boolean destroy(User user, Integer mId);


}
