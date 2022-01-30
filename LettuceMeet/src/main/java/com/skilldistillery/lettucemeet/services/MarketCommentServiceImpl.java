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
public class MarketCommentServiceImpl implements MarketCommentService {

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
	public List<MarketComment> getByMarketId(int marketId) {
		// TODO Auto-generated method stub
		return mcRepo.findByMarket_Id(marketId);
	}

	@Override
	public MarketComment show(Integer mcId) {
		Optional<MarketComment> mcOpt = mcRepo.findById(mcId);
		if (mcOpt.isPresent()) {
			return mcOpt.get();
		}
		return null;
	}

	@Override
	public MarketComment create(MarketComment marketComment, User user, int MarketId) {
		if (user != null && MarketId > 0) {
			Market market = marketRepo.queryById(MarketId);
			marketComment.setReplyTo(marketComment);
			marketComment.setMarket(market);
			marketComment.setUser(user);
			return mcRepo.saveAndFlush(marketComment);
		}
		return null;
	}

	@Override
	public MarketComment createReply(int marketCommentId, User user, MarketComment newMarketComment) {
		if (user != null && marketCommentId > 0) {
			Market market = marketRepo.findByMarketComments_Id(marketCommentId);
			MarketComment marketComment = this.show(marketCommentId);
			newMarketComment.setReplyTo(marketComment);

			newMarketComment.setMarket(market);
			newMarketComment.setUser(user);
			return mcRepo.saveAndFlush(newMarketComment);
		}
		return null;
	}

	@Override
	public MarketComment update(User user, Market market, Integer mcId, MarketComment marketComment) {
		MarketComment existing = mcRepo.findByIdAndUser(mcId, user);
		if (existing != null) {
			existing.setComment(marketComment.getComment());
			existing.setReplyTo(marketComment);
			existing.setMarket(market);
			existing.setUser(user);
			mcRepo.saveAndFlush(existing);
		}
		return existing;
	}

	@Override
	public boolean destroy(User user, Integer mcId) {
		boolean deleted = false;
		MarketComment marketComment = this.show(mcId);
		int marketId = marketComment.getMarket().getId();
		int userId = marketComment.getUser().getId();
		if (marketRepo.existsById(marketId) && userRepo.existsById(userId)) {
			mcRepo.delete(marketComment);
			deleted = true;
		}
		return deleted;
	}

}
