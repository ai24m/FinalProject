package com.skilldistillery.lettucemeet.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.skilldistillery.lettucemeet.entities.*;
import com.skilldistillery.lettucemeet.services.*;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300"})

public class MarketController {

	@Autowired 
	private MarketService marketSvc; 
	
	@Autowired 
	private UserService userSvc; 
	
	@GetMapping("markets")
	public List<Market> index(HttpServletRequest req, HttpServletResponse res){
		List<Market> allMarkets = marketSvc.index(); 
		if (allMarkets == null) {
			res.setStatus(404);
		} return allMarkets; 
	}
	
	@GetMapping("markets/{mId}")
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
	
	@PostMapping("markets") //must create address with market 
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
	
	@PutMapping("markets/{mId}")
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
	
	@DeleteMapping("markets/{mId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, 	 	Principal principal, @PathVariable Integer mId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			if (marketSvc.destroy(user, mId)) {
				res.setStatus(204); // no content
			} else {
				res.setStatus(404); // not found, invalid id
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
