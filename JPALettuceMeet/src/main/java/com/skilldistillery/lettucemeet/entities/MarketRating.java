package com.skilldistillery.lettucemeet.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="market_rating")
public class MarketRating {
	
	@EmbeddedId
	private MarketRatingId id;
	
	@Column(name="market_rating")
	private int marketRating;
	
	private String comment;
	
	@ManyToOne 
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;
	
	@ManyToOne 
	@JoinColumn(name="market_id")
	@MapsId(value="marketId")
	private Market market;
	
	//--no-arg constructor
	public MarketRating() {
		super();
	}

	//--getter and setter
	public MarketRatingId getId() {
		return id;
	}

	public void setId(MarketRatingId id) {
		this.id = id;
	}

	public int getMarketRating() {
		return marketRating;
	}

	public void setMarketRating(int marketRating) {
		this.marketRating = marketRating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	//--hashcode
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketRating other = (MarketRating) obj;
		return Objects.equals(id, other.id);
	}

	//--toString
	@Override
	public String toString() {
		return "MarketRating [id=" + id + ", marketRating=" + marketRating + ", comment=" + comment + "]";
	}
	
	
	
	
}
