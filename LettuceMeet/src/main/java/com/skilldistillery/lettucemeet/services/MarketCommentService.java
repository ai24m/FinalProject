package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketCommentService {

	List<MarketComment> index();
	
	List<MarketComment> getByMarketId(int marketId);

	MarketComment show(Integer mcId);
	
	MarketComment createReply(int marketCommentId, User user, MarketComment marketComment);

	MarketComment create(MarketComment marketComment, User user, int mId);

	MarketComment update(User user, Market market, Integer mcId, MarketComment marketComment);

	boolean destroy(User user, Integer mcId);

}
