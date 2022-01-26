package com.skilldistillery.lettucemeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketRepository extends JpaRepository<Market, Integer> {

	Market findByIdAndUser(Integer mId, User user);

}
