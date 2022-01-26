package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	//no-arg-constructor
	public Market() {
		super();
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
