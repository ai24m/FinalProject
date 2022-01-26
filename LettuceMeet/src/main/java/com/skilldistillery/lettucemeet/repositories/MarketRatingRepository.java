package com.skilldistillery.lettucemeet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.MarketRating;
import com.skilldistillery.lettucemeet.entities.MarketRatingId;
import com.skilldistillery.lettucemeet.entities.User;

public interface MarketRatingRepository extends JpaRepository<MarketRating, MarketRatingId> {

	List<MarketRating> findByMarket_Id(Integer mId);

	List<MarketRating> findByUser_Id(Integer userId);

	Optional<MarketRating> findByIdAndUser(Integer mrId, User user);

}
