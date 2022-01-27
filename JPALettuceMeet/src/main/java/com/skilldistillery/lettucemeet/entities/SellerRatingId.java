package com.skilldistillery.lettucemeet.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SellerRatingId implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "seller_id")
	private int sellerId;
	
	//--no arg constructor
	public SellerRatingId() {
		super();
	}

	//--getter and setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	//--hascode
	@Override
	public int hashCode() {
		return Objects.hash(sellerId, userId);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerRatingId other = (SellerRatingId) obj;
		return sellerId == other.sellerId && userId == other.userId;
	}

	//---tostring
	@Override
	public String toString() {
		return "SellerRatingId [userId=" + userId + ", sellerId=" + sellerId + "]";
	}

	//---all constructor
	public SellerRatingId(int userId, int sellerId) {
		super();
		this.userId = userId;
		this.sellerId = sellerId;
	}

	
}
