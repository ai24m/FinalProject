package com.skilldistillery.lettucemeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketCommentRepository extends JpaRepository<MarketComment, Integer> {

	MarketComment findByIdAndUser(Integer mcId, User user);
	
	List<MarketComment> findByMarket_Id(int marketId);

}