package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDate;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name; 
	
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
	
	@JsonIgnoreProperties({"products","hibernateLazyInitializer"})
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	
	@JsonIgnoreProperties({"products","hibernateLazyInitializer"})
//	@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany()
	@JoinTable(name="market_product",
		joinColumns=@JoinColumn(name="product_id"),
		inverseJoinColumns=@JoinColumn(name="market_id")
	)
	private List<Market> markets;
	
	@JsonIgnoreProperties({"product","hibernateLazyInitializer"})
//	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@OneToMany(mappedBy="product")
	private List<ProductRating> productRating; 
	
	@JsonIgnoreProperties({"product","hibernateLazyInitializer"})
//	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@OneToMany(mappedBy="product")
	private List<ProductComment> productComment; 

	
//	no arg contructor 
	public Product() {
		super();
	}

	public Product(int id, String name, String description, boolean organic, double price, String imageUrl,
			int quantity, LocalDate availableDate, LocalDateTime created, LocalDateTime updated, Type type, User user,
			List<Market> markets, List<ProductRating> productRating, List<ProductComment> productComment) {
		super();
		this.id = id;
		this.name = name;
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
		this.productComment = productComment;
	}

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

	//get set , create and remove markets
	public List<Market> getMarkets() {
		List<Market> markets = this.markets;
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
	}
	
	public boolean addMarket(Market market) {
		if(markets ==null) {
			markets = new ArrayList<>();
		}
		boolean addedToList = false;
		if(market !=null) {
			if(!markets.contains(market)) {
				addedToList = markets.add(market);
			}
			if(!market.getProducts().contains(this)) {
				market.getProducts().add(this);
			}
		}
		return addedToList;
		
	}
	
	
	
	
	

	public List<ProductRating> getProductRating() {
		List<ProductRating>productRating= this.productRating;
		return productRating;
	}

	public void setProductRating(List<ProductRating> productRating) {
		this.productRating = productRating;
	}
	
	public List<ProductComment> getProductComment() {
		List<ProductComment> productComment=this.productComment;
		return productComment;
	}


	public void setProductComment(List<ProductComment> productComment) {
		this.productComment = productComment;
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
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", organic=" + organic
				+ ", price=" + price + ", imageUrl=" + imageUrl + ", quantity=" + quantity + ", availableDate="
				+ availableDate + ", created=" + created + ", updated=" + updated + ", type=" + type + ", user=" + user
				+ "]";
	}
	
}
