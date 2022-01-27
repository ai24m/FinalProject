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
	
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired 
	private AddressRepository addressRepo;
	
	@Override
	public List<Market> index() {
		return marketRepo.findAll();
	} 
	
	@Override
	public Market show(int mId) {
		Optional<Market> mOpt = marketRepo.findById(mId); 
		if (mOpt.isPresent()) {
			Market market = mOpt.get(); 
			return market; 
		} return null;
	}

	@Override
	public Market create(Market market, Address address, User user) {
		addressRepo.saveAndFlush(address); 
		userRepo.saveAndFlush(user); 
		market.setAddress(address);
		market.setUser(user);
		marketRepo.saveAndFlush(market); 
		return market;
	}

	@Override
	public Market update(User user, Integer mId, Market market) {
		if (market.getUser().getId() == user.getId()) {
			Optional<Market> mOpt = marketRepo.findById(market.getId());
			if (mOpt.isPresent()) {
				Market updatedMarket = mOpt.get();
				updatedMarket.setAddress(market.getAddress());
				return updatedMarket; 
			}
		} return null;
	}

	@Override
	public boolean destroy(User user, Integer mId) {
		boolean deleted = false; 
		Market market = marketRepo.findByIdAndUser(mId, user); 
		if (market != null) {
			marketRepo.delete(market);
			deleted = true;
			return deleted; 
		} return deleted; 
	}
	
}
