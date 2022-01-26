package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "market_comment")
public class MarketComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	@CreationTimestamp
	@Column(name = "created_time")
	private LocalDateTime created;
	
	@UpdateTimestamp
	@Column(name = "updated_time")
	private LocalDateTime updated;
	
	@ManyToOne
	@JoinColumn(name="comment_id")
	private MarketComment marketComment; 
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	
	@ManyToOne
	@JoinColumn(name="market_id")
	private Market market; 

//	no arg constructor 
	public MarketComment() {
		super();
	}

	public MarketComment(int id, String comment, LocalDateTime created, LocalDateTime updated, MarketComment marketComment,
		User user, Market market) {
		super();
		this.id = id;
		this.comment = comment;
		this.created = created;
		this.updated = updated;
		this.marketComment = marketComment;
		this.user = user;
		this.market = market;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public MarketComment getMarketComment() {
		return marketComment;
	}

	public void setMarketComment(MarketComment marketComment) {
		this.marketComment = marketComment;
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
		MarketComment other = (MarketComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "MarketComment [id=" + id + ", created=" + created + ", updated=" + updated + "]";
	}
	
}
