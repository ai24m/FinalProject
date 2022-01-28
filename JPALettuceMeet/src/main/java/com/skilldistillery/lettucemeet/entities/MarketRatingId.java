package com.skilldistillery.lettucemeet.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MarketRatingId  implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "market_id")
	private int marketId;

	public MarketRatingId() {
		super();
	}

	public MarketRatingId(int userId, int marketId) {
		super();
		this.userId = userId;
		this.marketId = marketId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMarketId() {
		return marketId;
	}

	public void setMarketId(int marketId) {
		this.marketId = marketId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(marketId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketRatingId other = (MarketRatingId) obj;
		return marketId == other.marketId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "MarketRatingId [userId=" + userId + ", marketId=" + marketId + "]";
	}

}
