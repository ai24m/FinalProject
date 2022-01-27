package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "market_comment")
public class MarketComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	@CreationTimestamp
	@Column(name = "create_time")
	private LocalDateTime created;
	
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updated;
	
//	@JsonIgnoreProperties({"marketComment"})
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="comment_id")
	private MarketComment replyTo; 
	
	@OneToMany(mappedBy="replyTo")
	private List<MarketComment> myReplies; 
	
//	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="market_id")
	private Market market; 

//	no arg constructor 
	public MarketComment() {
		super();
	}

	public MarketComment(int id, String comment, LocalDateTime created, LocalDateTime updated, MarketComment replyTo,
			List<MarketComment> myReplies, User user, Market market) {
		super();
		this.id = id;
		this.comment = comment;
		this.created = created;
		this.updated = updated;
		this.replyTo = replyTo;
		this.myReplies = myReplies;
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

	public MarketComment getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(MarketComment replyTo) {
		this.replyTo = replyTo;
	}

	public List<MarketComment> getMyReplies() {
		return myReplies;
	}

	public void setMyReplies(List<MarketComment> myReplies) {
		this.myReplies = myReplies;
	}
	
	public void addMarketComment(MarketComment marketComment) {
		if (myReplies == null) myReplies = new ArrayList<>();
		
		if (!myReplies.contains(marketComment)) {
			myReplies.add(marketComment);
			if (marketComment.getMyReplies() != null) {
				marketComment.getReplyTo().getMyReplies().remove(marketComment);
			} 
			marketComment.setReplyTo(this);
		}
	}
	
	public void removeMarketComment(MarketComment marketComment) {
		marketComment.setReplyTo(null);
		if (myReplies != null) {
			myReplies.remove(marketComment);
		}
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
