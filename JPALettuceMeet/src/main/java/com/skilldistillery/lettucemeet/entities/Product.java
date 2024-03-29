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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
//	@JsonFormat(pattern = "MM-dd-yyyy")
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
	
	@JsonIgnoreProperties({"products"})
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	
	@JsonIgnoreProperties({"products"})
	@ManyToMany()
	@JoinTable(name="market_product",
		joinColumns=@JoinColumn(name="product_id"),
		inverseJoinColumns=@JoinColumn(name="market_id")
	)
	private List<Market> markets;
	
	@JsonIgnoreProperties({"product"})
//	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@OneToMany(mappedBy="product")
	private List<ProductRating> productRatings; 
	
	@JsonIgnoreProperties({"product", "replyTo", "myReplies"})
//	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@OneToMany(mappedBy="product")
	private List<ProductComment> productComments; 

	
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
		this.productRatings = productRating;
		this.productComments = productComment;
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
	
	public void addMarket(Market market) {
		if (markets == null) markets = new ArrayList<>();
		
		if (!markets.contains(market)) {
			markets.add(market);
			market.addProduct(this);
		}
	}
	
	public void removeMarket(Market market) {
		if (markets != null && markets.contains(market)) {
			markets.remove(market);
			market.removeProduct(this);
		} 
	}

	public List<ProductRating> getProductRatings() {
		List<ProductRating> productRatings = this.productRatings;
		return productRatings;
	}

	public void setProductRatings(List<ProductRating> productRating) {
		this.productRatings = productRating;
	}
	
	public void addProductRating(ProductRating productRating) {
		if (productRatings == null) productRatings = new ArrayList<>();
		
		if (!productRatings.contains(productRating)) {
			productRatings.add(productRating);
			if (productRating.getProduct() != null) {
				productRating.getProduct().getProductRatings().remove(productRating);
			}
			productRating.setProduct(this);
		}
	}
	public void removeProductRating(ProductRating productRating) {
		productRating.setProduct(null);
		if (productRatings != null) {
			productRatings.remove(productRating); 
		}
	}
	
	
	public List<ProductComment> getProductComments() {
		List<ProductComment> productComments = this.productComments;
		return productComments;
	}


	public void setProductComments(List<ProductComment> productComment) {
		this.productComments = productComment;
	}
	
	public void addProductComment(ProductComment productComment) {
		if (productComments == null) productComments = new ArrayList<>();
		
		if (!productComments.contains(productComment)) {
			productComments.add(productComment);
			if (productComment.getProduct() != null) {
				productComment.getProduct().getProductComments().remove(productComment);
			}
			productComment.setProduct(this);
		}
	}
	public void removeProductComment(ProductComment productComment) {
		productComment.setProduct(null);
		if (productComments != null) {
			productComments.remove(productComment); 
		}
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