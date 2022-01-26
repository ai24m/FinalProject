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
@Table(name="seller_rating")
public class SellerRating {
	
	@EmbeddedId
	private SellerRatingId id;
	
	@Column(name="seller_rating")
	private int sellerRating;
	
	private String comment;
	
	@ManyToOne 
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;
	
	@ManyToOne 
	@JoinColumn(name="seller_id")
	@MapsId(value="sellerId")
	private User seller;

	//--no-arg constructor
	public SellerRating() {
		super();
	}
	
	public SellerRating(SellerRatingId id, int sellerRating, String comment, User user, User seller) {
		super();
		this.id = id;
		this.sellerRating = sellerRating;
		this.comment = comment;
		this.user = user;
		this.seller = seller;
	}

	//--getter and setter
	public SellerRatingId getId() {
		return id;
	}

	public void setId(SellerRatingId id) {
		this.id = id;
	}

	public int getSellerRating() {
		return sellerRating;
	}

	public void setSellerRating(int sellerRating) {
		this.sellerRating = sellerRating;
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

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
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
		SellerRating other = (SellerRating) obj;
		return Objects.equals(id, other.id);
	}

	//--toString
	@Override
	public String toString() {
		return "SellerRating [id=" + id + ", sellerRating=" + sellerRating + ", comment=" + comment + "]";
	}
	
	

}
