package com.skilldistillery.lettucemeet.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product_rating")
public class ProductRating {
	
	@JsonIgnore
	@EmbeddedId
	private ProductRatingId id;
	
	@Column(name="product_rating")
	private int rating;
	
	private String comment;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	@MapsId(value="productId")
	private Product product; 

	//--no-arg constructor
	public ProductRating() {
		super();
	}

	public ProductRating(ProductRatingId id, int productRating, String comment, User user, Product product) {
		super();
		this.id = id;
		this.rating = productRating;
		this.comment = comment;
		this.user = user;
		this.product = product;
	}

	//--getter and setter
	public ProductRatingId getId() {
		return id;
	}

	public void setId(ProductRatingId id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int productRating) {
		this.rating = productRating;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	//hashcode
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
		ProductRating other = (ProductRating) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ProductRating [id=" + id + ", rating=" + rating + ", comment=" + comment + ", user=" + user
				+ ", product=" + product + "]";
	}
	
	
	

}
