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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	
	private String email;
	
	private String password;
	
	private boolean enabled;
	
	private String role;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "business_name")
	private String businessName;

	@Column(name = "image_url")
	private String imageUrl;

	@CreationTimestamp
	@Column(name = "create_time")
	private LocalDateTime created;
	 
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address; 
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<ProductRating> productRatings; 
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<SellerRating> sellerRatings;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<SellerRating> userRatings; 
	
//	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Product> products; 
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<MarketRating> marketRatings; 
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Market> markets; 
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<ProductComment> productComments;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<MarketComment> marketComments;
	
// no arg constructor 
	public User() {
		super();
	}

	public User(int id, String username, String email, String password, boolean enabled, String role, String firstName,
			String lastName, String businessName, String imageUrl, LocalDateTime created, Address address,
			List<ProductRating> productRatings, List<SellerRating> sellerRatings, List<SellerRating> userRatings,
			List<Product> products, List<MarketRating> marketRatings, List<Market> markets,
			List<ProductComment> productComments, List<MarketComment> marketComments) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.businessName = businessName;
		this.imageUrl = imageUrl;
		this.created = created;
		this.address = address;
		this.productRatings = productRatings;
		this.sellerRatings = sellerRatings;
		this.userRatings = userRatings;
		this.products = products;
		this.marketRatings = marketRatings;
		this.markets = markets;
		this.productComments = productComments;
		this.marketComments = marketComments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<ProductRating> getProductRatings() {
		List<ProductRating> productRatings = this.productRatings;
		return productRatings;
	}

	public void setProductRatings(List<ProductRating> productRatings) {
		this.productRatings = productRatings;
	}
	
	public void addProductRating(ProductRating productRating) {
		if (productRatings == null) productRatings = new ArrayList<>();
		
		if (!productRatings.contains(productRating)) {
			productRatings.add(productRating);
			if (productRating.getUser() != null) {
				productRating.getUser().getProductRatings().remove(productRating);
			} 
			productRating.setUser(this);
		}
	}
	
	public void removeProductRating(ProductRating productRating) {
		productRating.setUser(null);
		if (productRatings != null) {
			productRatings.remove(productRating);
		}
	}
	

	public List<SellerRating> getSellerRatings() {
		List<SellerRating> sellerRatings = this.sellerRatings;
		return sellerRatings;
	}

	public void setSellerRatings(List<SellerRating> sellerRatings) {
		this.sellerRatings = sellerRatings;
	}
	
	public void addSellerRating(SellerRating sellRating) {
		if (sellerRatings == null) productRatings = new ArrayList<>();
		
		if (!sellerRatings.contains(sellRating)) {
			sellerRatings.add(sellRating);
			if (sellRating.getUser() != null) {
				sellRating.getUser().getSellerRatings().remove(sellRating);
			} 
			sellRating.setUser(this);
		}
	}
	
	public void removeSellerRating(SellerRating sellRating) {
		sellRating.setUser(null);
		if (sellerRatings != null) {
			sellerRatings.remove(sellRating);
		}
	}

	public List<SellerRating> getUserRatings() {
		List<SellerRating> userRatings = this.sellerRatings;
		return userRatings;
	}

	public void setUserRatings(List<SellerRating> userRatings) {
		this.userRatings = userRatings;
	}

	public void addUserRating(SellerRating userRating) {
		if (userRatings == null) productRatings = new ArrayList<>();
		
		if (!userRatings.contains(userRating)) {
			userRatings.add(userRating);
			if (userRating.getUser() != null) {
				userRating.getUser().getSellerRatings().remove(userRating);
			} 
			userRating.setUser(this);
		}
	}
	
	public void removeUserRating(SellerRating sellRating) {
		sellRating.setUser(null);
		if (userRatings != null) {
			userRatings.remove(sellRating);
		}
	}
	
	public List<Product> getProducts() {
		List<Product> products = this.products;
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		if (products == null) products = new ArrayList<>();
		
		if (!products.contains(product)) {
			products.add(product);
			if (product.getUser() != null) {
				product.getUser().getProducts().remove(product);
			} 
			product.setUser(this);
		}
	}
	
	public void removeProduct(Product product) {
		product.setUser(null);
		if (products != null) {
			products.remove(product);
		}
	}
	
	public List<MarketRating> getMarketRatings() {
		List<MarketRating> marketRatings = this.marketRatings;
		return marketRatings;
	}

	public void setMarketRatings(List<MarketRating> marketRatings) {
		this.marketRatings = marketRatings;
	}

	public void addMarketRating(MarketRating marketRating) {
		if (marketRatings == null) productRatings = new ArrayList<>();
		
		if (!marketRatings.contains(marketRating)) {
			marketRatings.add(marketRating);
			if (marketRating.getUser() != null) {
				marketRating.getUser().getMarketRatings().remove(marketRating);
			} 
			marketRating.setUser(this);
		}
	}
	
	public void removeMarketRating(MarketRating marketRating) {
		marketRating.setUser(null);
		if (marketRatings != null) {
			marketRatings.remove(marketRating);
		}
	}
	
	
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
			if (market.getUser() != null) {
				market.getUser().getMarkets().remove(market);
			} 
			market.setUser(this);
		}
	}
	
	public void removeMarket(Market market) {
		market.setUser(null);
		if (markets != null) {
			markets.remove(market);
		}
	}
	
	
	public List<ProductComment> getProductComments() {
		List<ProductComment> productComments = this.productComments;
		return productComments;
	}

	public void setProductComments(List<ProductComment> productComments) {
		this.productComments = productComments;
	}

	public void addProductComment(ProductComment productComment) {
		if (productComments == null) productComments = new ArrayList<>();
		
		if (!productComments.contains(productComment)) {
			productComments.add(productComment);
			if (productComment.getUser() != null) {
				productComment.getUser().getProductComments().remove(productComment);
			} 
			productComment.setUser(this);
		}
	}
	
	public void removeProductComment(ProductComment productComment) {
		productComment.setUser(null);
		if (productComments != null) {
			productComments.remove(productComment);
		}
	}
	
	public List<MarketComment> getMarketComments() {
		List<MarketComment> marketComments = this.marketComments;
		return marketComments;
	}

	public void setMarketComments(List<MarketComment> marketComments) {
		this.marketComments = marketComments;
	}
	
	public void addMarketComment(MarketComment marketComment) {
		if (marketComments == null) marketComments = new ArrayList<>();
		
		if (!marketComments.contains(marketComment)) {
			marketComments.add(marketComment);
			if (marketComment.getUser() != null) {
				marketComment.getUser().getMarketComments().remove(marketComment);
			} 
			marketComment.setUser(this);
		}
	}
	
	public void removeMarketComment(MarketComment marketComment) {
		marketComment.setUser(null);
		if (marketComments != null) {
			marketComments.remove(marketComment);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", enabled=" + enabled + ", role="
				+ role + ", firstName=" + firstName + ", lastName=" + lastName + ", businessName=" + businessName
				+ ", created=" + created + "]";
	}

}
