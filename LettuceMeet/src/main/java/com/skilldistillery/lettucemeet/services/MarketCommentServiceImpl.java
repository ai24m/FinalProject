package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.MarketCommentRepository;
import com.skilldistillery.lettucemeet.repositories.MarketRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service 
public class MarketCommentServiceImpl implements MarketCommentService{

	@Autowired 
	private MarketCommentRepository mcRepo;
	
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired 
	private MarketRepository marketRepo;

	@Override
	public List<MarketComment> index() {
		return mcRepo.findAll();
	}

	@Override
	public MarketComment show(Integer mcId) {
		Optional <MarketComment> mcOpt = mcRepo.findById(mcId);
		if (mcOpt.isPresent()) {
			return mcOpt.get();
		} return null;
	}

	@Override
	public MarketComment create(MarketComment marketComment, User user) {
		if (user != null) {
			marketComment.setMarketComment(marketComment);
			marketComment.setMarket(marketComment.getMarket());
			marketComment.setUser(user);
			return mcRepo.saveAndFlush(marketComment);
		}	return marketComment; 	
	} 
	
	@Override 
	public MarketComment update(User user, Market market, Integer mcId, MarketComment marketComment) {
//		if (marketComment.getUser().getId() == user.getId()) {
			Optional <MarketComment> mcOpt = mcRepo.findById(mcId);
			MarketComment updatedMarketComment = null;
			if (mcOpt.isPresent()) {
				updatedMarketComment = mcOpt.get();
				updatedMarketComment.setMarketComment(marketComment);
				updatedMarketComment.setUser(user);
				updatedMarketComment.setMarket(market);
				mcRepo.saveAndFlush(updatedMarketComment);
			} return updatedMarketComment;
//		}
	}

	@Override
	public boolean destroy(User user, Integer mcId) {
		boolean deleted = false; 
		MarketComment marketComment = mcRepo.findByIdAndUser(mcId, user); 
		marketComment.setMarketComment(marketComment);
		if (marketComment != null) {
			mcRepo.delete(marketComment);
			deleted = true;
			return deleted; 
		} return deleted; 
	}
}
