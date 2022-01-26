package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private LocalDateTime marketDate;
	@Column(name ="create_time")
	private LocalDateTime createTime;
	@Column(name ="update_time")
	private LocalDateTime updateTime;
	
	
}
