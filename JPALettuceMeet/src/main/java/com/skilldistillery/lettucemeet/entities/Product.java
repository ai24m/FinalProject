package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	private boolean organic;
	
	@Column(name = "unit_price")
	private double price;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private int quantity;
	
	@Column(name = "available_date")
	private LocalDate availableDate;
	
	@CreationTimestamp
	@Column(name = "create_time")
	private LocalDateTime created;
	
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updated;
	
	@ManyToOne
	@JoinColumn(name="type_id")
	private Type type; 
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	
	@ManyToMany 
	@JoinTable(name="market",
		joinColumns=@JoinColumn(name="product_id"),
		inverseJoinColumns=@JoinColumn(name="market_id")
	)
	private List<Market> markets; 
	
	@OneToMany(mappedBy="product")
	private List<ProductRating> productRating; 

	
//	no arg contructor 
	public Product() {
		super();
	}

	public Product(int id, String description, boolean organic, double price, String imageUrl, int quantity,
		LocalDate availableDate, LocalDateTime created, LocalDateTime updated, Type type, User user,
		List<Market> markets, List<ProductRating> productRating) {
		super();
		this.id = id;
		this.description = description;
		this.organic = organic;
		this.price = price;
		this.imageUrl = imageUrl;
		this.quantity = quantity;
		this.availableDate = availableDate;
		this.created = created;
		this.updated = updated;
		this.type = type;
		this.user = user;
		this.markets = markets;
		this.productRating = productRating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOrganic() {
		return organic;
	}

	public void setOrganic(boolean organic) {
		this.organic = organic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
	}

	public List<ProductRating> getProductRating() {
		return productRating;
	}

	public void setProductRating(List<ProductRating> productRating) {
		this.productRating = productRating;
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
		Product other = (Product) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", organic=" + organic + ", price=" + price + ", quantity=" + quantity
				+ ", availableDate=" + availableDate + ", created=" + created + "]";
	}
	
}
