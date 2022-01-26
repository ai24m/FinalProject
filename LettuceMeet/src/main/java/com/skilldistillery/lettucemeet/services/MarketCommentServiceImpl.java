package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.MarketCommentRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service 
public class MarketCommentServiceImpl implements MarketCommentService{

	@Autowired 
	private MarketCommentRepository mcRepo;
	
	@Autowired 
	private UserRepository userRepo;

	@Override
	public List<MarketComment> index() {
		return mcRepo.findAll();
	}

	@Override
	public MarketComment show(Integer mcId) {
		Optional <MarketComment> mcOpt = mcRepo.findById(mcId);
		if (mcOpt.isPresent()) {
			MarketComment mc = mcOpt.get();
			return mc; 
		} return null;
	}

	@Override
	public MarketComment create(MarketComment marketComment) {
		if (marketComment.getUser() != null && marketComment.getMarket() != null) {
			marketComment.setMarket(marketComment.getMarket());
			marketComment.setUser(marketComment.getUser());
			marketComment.setMarketComment(marketComment.getMarketComment());
		}
		marketComment.setUser(user);
		
		mcRepo.saveAndFlush(marketComment);
		return marketComment;
	} 
	
	@Override 
	public MarketComment update(User user, Integer mcId, MarketComment marketComment) {
		if (marketComment.getUser().getId() == user.getId()) {
			Optional <MarketComment> mcOpt = mcRepo.findById(mcId);
			if (mcOpt.isPresent()) {
				MarketComment marketComment1 = new MarketComment();
//				MarketComment marketComment = mcOpt.get(); 
				return marketComment1; 
			}
		}
		return marketComment;
	}
	
}
