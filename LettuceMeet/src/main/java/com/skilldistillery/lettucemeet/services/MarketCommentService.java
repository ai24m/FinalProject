package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketCommentService {

	List<MarketComment> index();

	MarketComment show(Integer mcId);

	MarketComment create(MarketComment marketComment, User user);

	MarketComment update(User user, Market market, Integer mcId, MarketComment marketComment);

	boolean destroy(User user, Integer mcId);

}
