package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.AddressRepository;
import com.skilldistillery.lettucemeet.repositories.MarketRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service
public class MarketServiceImpl implements MarketService {

	@Autowired 
	private MarketRepository marketRepo;
	
	@Override
	public List<Market> index() {
		return marketRepo.findAll();
	} 
	
	@Override
	public Market findById(Integer mId) {
		Optional<Market> mOpt = marketRepo.findById(mId);
		if (mOpt.isPresent()) {
			Market market = mOpt.get();
			return market; 
		} return null; 
	}
	
	@Override
	public Market show(int mId) {
		Optional<Market> mOpt = marketRepo.findById(mId); 
		if (mOpt.isPresent()) {
			return mOpt.get(); 
		} return null;
	}

	@Override
	public Market create(Market market, User user) {
		if (user != null) {
			market.setUser(user);
		    return marketRepo.saveAndFlush(market); 
		}
//		market.setAddress(market.getAddress());
//		market.setProducts(market.getProducts());
//		market.setUser(user);
//		marketRepo.saveAndFlush(market); 
		return market;
	}

	@Override
	public Market update(User user, Integer mId, Market market) {
		Market existing = marketRepo.findByIdAndUser(mId, user);
		if (existing != null) {
			existing.setName(market.getName());
			existing.setDescription(market.getDescription());
			existing.setImageUrl(market.getImageUrl());
			existing.setMarketDate(market.getMarketDate());
			existing.setAddress(market.getAddress());;
			marketRepo.saveAndFlush(existing);
		} return market;
	}

	@Override
	public boolean destroy(User user, Integer mId) {
		boolean deleted = false; 
		Market market = marketRepo.findByIdAndUser(mId, user); 
		if (market != null) {
			market.setMarketComments(null);
			market.setMarketRatings(null);
			marketRepo.delete(market);
			deleted = true;
		} return deleted; 
	}

}
