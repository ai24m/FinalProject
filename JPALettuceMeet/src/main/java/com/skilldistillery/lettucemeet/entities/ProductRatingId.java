package com.skilldistillery.lettucemeet.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductRatingId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "product_id")
	private int productId;

	//--no-arg constructor
	public ProductRatingId() {
		super();
	}

	//--getter and setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//--hascode
	@Override
	public int hashCode() {
		return Objects.hash(productId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductRatingId other = (ProductRatingId) obj;
		return productId == other.productId && userId == other.userId;
	}

	
	//--toString
	@Override
	public String toString() {
		return "ProductRatingId [userId=" + userId + ", productId=" + productId + "]";
	}

	//--all constructor
	public ProductRatingId(int userId, int productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	
	
	
}
