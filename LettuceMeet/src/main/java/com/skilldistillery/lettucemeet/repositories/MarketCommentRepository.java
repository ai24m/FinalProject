package com.skilldistillery.lettucemeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.MarketComment;

public interface MarketCommentRepository extends JpaRepository<MarketComment, Integer> {

}
