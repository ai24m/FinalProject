package com.skilldistillery.lettucemeet.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.lettucemeet.entities.Address;
import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.services.MarketService;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })

public class MarketController {

	@Autowired 
	private MarketService marketSvc; 
	
	@Autowired 
	private UserService userSvc; 
	
	@GetMapping("market")
	public List<Market> index(HttpServletRequest req, HttpServletResponse res) {
		List<Market> allMarkets = marketSvc.index(); 
		if (allMarkets == null) {
			res.setStatus(404);
		} return allMarkets; 
	}
	
	@GetMapping("market/{mId}")
	public Market show(HttpServletRequest req, HttpServletResponse res, @PathVariable int mId) {
		Market market = marketSvc.show(mId);
		if (market == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(market.getId());
			res.setHeader("location", url.toString());
		} return market; 
	}
	
	@PostMapping("market") //must create address with market 
	public Market create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Market market,
			@RequestBody Address address) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			market = marketSvc.create(market, address, user); 
			if (market == null) {
				res.setStatus(404);
				return market; 
			}
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(market.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		} return market; 
	}
	
	@PutMapping("market/{mId}")
	public Market update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable Integer mId, @RequestBody Market market) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			market = marketSvc.update(user, mId, market);
			if (market == null) {
				res.setStatus(404); // 404 request body does not exisy
				return null;
			}
		} catch (Exception e) {
			res.setStatus(400); // 400 request body is bad data
			e.printStackTrace();
			market = null;
		}
		return market;
	}
}
