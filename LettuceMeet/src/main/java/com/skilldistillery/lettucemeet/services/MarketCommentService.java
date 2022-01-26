package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketCommentService {

	List<MarketComment> index();

	MarketComment show(Integer mcId);

	MarketComment create(MarketComment marketComment, User user);

	MarketComment update(User user, Integer mcId, MarketComment marketComment);

}
