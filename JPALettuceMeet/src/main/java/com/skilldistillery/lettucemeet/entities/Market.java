package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Market {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name ="image_url")
	private String imageUrl;
	
	@Column(name ="market_date")
	@CreationTimestamp
	private LocalDateTime marketDate;
	
	@Column(name ="create_time")
	@CreationTimestamp
	private LocalDateTime createTime;
	
	@Column(name ="update_time")
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	
	@ManyToMany(mappedBy = "markets")
	private List<Product> products;
	
	@JsonIgnore
	@OneToMany(mappedBy="market")
	private List<MarketRating> marketRatings;
	
	@JsonIgnore
	@OneToMany(mappedBy="market", cascade = CascadeType.ALL)
	private List<MarketComment> marketComments;
	
	//no-arg-constructor
	public Market() {
		super();
	}
	
	public Market(int id, String name, String description, String imageUrl, LocalDateTime marketDate,
			LocalDateTime createTime, LocalDateTime updateTime, User user, Address address,
			List<MarketRating> marketRatings) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.marketDate = marketDate;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.user = user;
		this.address = address;
		this.marketRatings = marketRatings;
	}

	//--getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getMarketDate() {
		return marketDate;
	}

	public void setMarketDate(LocalDateTime marketDate) {
		this.marketDate = marketDate;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<MarketRating> getMarketRatings() {
		return marketRatings;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setMarketRatings(List<MarketRating> marketRatings) {
		this.marketRatings = marketRatings;
	}
	
	public void addMarketRating(MarketRating marketRating) {
		if (marketRatings == null) marketRatings = new ArrayList<>();
		
		if (!marketRatings.contains(marketRating)) {
			marketRatings.add(marketRating);
			if (marketRating.getMarket() != null) {
				marketRating.getMarket().getMarketRatings().remove(marketRating);
			} 
			marketRating.setMarket(this);
		}
	}
	
	public void removeMarketRating(MarketRating marketRating) {
		marketRating.setMarket(null);
		if (marketRatings != null) {
			marketRatings.remove(marketRating);
		}
	}
	
	public List<MarketComment> getMarketComments() {
		return marketComments;
	}

	public void setMarketComments(List<MarketComment> marketComments) {
		this.marketComments = marketComments;
	}

	public void addMarketComment(MarketComment marketComment) {
		if (marketComments == null) marketComments = new ArrayList<>();
		
		if (!marketComments.contains(marketComment)) {
			marketComments.add(marketComment);
			if (marketComment.getMarket() != null) {
				marketComment.getMarket().getMarketComments().remove(marketComment);
			} 
			marketComment.setMarket(this);
		}
	}
	
	public void removeMarketComment(MarketComment marketComment) {
		marketComment.setMarket(null);
		if (marketComments != null) {
			marketComments.remove(marketComment);
		}
	}
	
	//--hashCode
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
		Market other = (Market) obj;
		return id == other.id;
	}	
	
	//--toString
	@Override
	public String toString() {
		return "Market [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", marketDate=" + marketDate + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}
