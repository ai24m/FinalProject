package com.skilldistillery.lettucemeet.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String street1;
	
	private String street2;
	
	private String city;
	
	private String state;
	
	private String zip;
	
//	@JsonIgnore
	@OneToMany(mappedBy="address")
	private List<User> users;
	
//	@JsonIgnore
	@OneToMany(mappedBy="address")
	private List <Market> markets; 

	public Address() {
		super();
	}

	public Address(int id, String street1, String street2, String city, String state, String zip, List<User> users,
			List<Market> markets) {
		super();
		this.id = id;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.users = users;
		this.markets = markets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<User> getUsers() {
		List<User> users = this.users;
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Market> getMarkets() {
		List<Market> markets = this.markets;
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
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
		Address other = (Address) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state="
				+ state + ", zip=" + zip + "]";
	}
	
	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		if (!users.contains(user)) {
			users.add(user);
			if (user.getAddress() != null) {
				user.getAddress().getUsers().remove(user);
			}
			user.setAddress(this);
		}
	}
	
	public void deleteUser(User user) {
		user.setAddress(null);
		if (users != null) {
			users.remove(user);
		}
	}
	
	public void addMarket(Market market) {
		if (markets == null) {
			markets = new ArrayList<>();
		}
		if (!markets.contains(market)) {
			markets.add(market);
			if (market.getAddress() != null) {
				market.getAddress().getMarkets().remove(market);
			}
			market.setAddress(this);
		}
	}
	
	public void deleteMarket(Market market) {
		market.setAddress(null);
		if (markets != null) {
			markets.remove(market);
		}
	}

}
