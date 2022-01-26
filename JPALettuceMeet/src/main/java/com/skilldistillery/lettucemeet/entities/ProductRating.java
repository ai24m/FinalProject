package com.skilldistillery.lettucemeet.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="product_rating")
public class ProductRating {
	
	@EmbeddedId
	private ProductRatingId id;
	
	@Column(name="product_rating")
	private int productRating;
	
	private String comment;

	//--no-arg constructor
	public ProductRating() {
		super();
	}

	//--getter and setter
	public ProductRatingId getId() {
		return id;
	}

	public void setId(ProductRatingId id) {
		this.id = id;
	}

	public int getProductRating() {
		return productRating;
	}

	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	//--toString
	@Override
	public String toString() {
		return "ProductRating [id=" + id + ", productRating=" + productRating + ", comment=" + comment + "]";
	}
	
	
	

}