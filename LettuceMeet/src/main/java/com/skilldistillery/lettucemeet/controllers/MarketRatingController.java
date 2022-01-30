package com.skilldistillery.lettucemeet.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.MarketRating;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.services.MarketRatingService;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300"})

public class MarketRatingController {

	@Autowired 
	private MarketRatingService mrSvc; 
	
	@Autowired 
	private UserService userSvc; 
	
	@GetMapping("market/{mId}/marketratings")
	public List<MarketRating> indexForMarket(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer mId){
		List<MarketRating> allMarketRatings = mrSvc.index(mId);  //find based off of marketid
		if (allMarketRatings == null) {
			res.setStatus(404);
		} return allMarketRatings; 
	}
	
	@GetMapping("user/marketratings")
	public List<MarketRating> indexForUser(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User user = userSvc.findByUserName(principal.getName());
		List<MarketRating> allMarketRatings = mrSvc.indexForUser(user.getId());
		if (allMarketRatings == null) {
			res.setStatus(404); 
		} return allMarketRatings; 
	}
	
	@GetMapping("market/averagerating/{marketId}")
	public Integer show(HttpServletRequest req, HttpServletResponse res, @PathVariable int marketId) {
		int avegRating = mrSvc.getAvergeRating(marketId);
		if (avegRating < 0 && this.indexForMarket(req, res, marketId)!=null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
		} return avegRating; 
	}
	
//	@GetMapping("marketratings/{mrId}")
//	public Market show(HttpServletRequest req, HttpServletResponse res, @PathVariable int mcId) {
//		MarketRating marketRating = mrSvc.show(mrId);
//		if (marketRating == null) {
//			res.setStatus(404);
//		} else {
//			res.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(marketRating.getId());
//			res.setHeader("location", url.toString());
//		} return marketRating; 
//	}
	
	@PostMapping("market/{mId}/marketratings")
	public MarketRating create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody MarketRating marketRating,
			@PathVariable Integer mId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			marketRating = mrSvc.create(user.getId(), mId, marketRating); 
			if (marketRating == null) {
				res.setStatus(404);
				return marketRating; 
			}
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(marketRating.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		} return marketRating; 
	}
	
	@PutMapping("market/{mId}/marketratings")
	public MarketRating update(HttpServletRequest req, HttpServletResponse res, Principal principal, 
			@PathVariable Integer mId, @RequestBody MarketRating marketRating) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			marketRating = mrSvc.update(user, mId, marketRating);
			if (marketRating == null) {
				res.setStatus(404); // 404 request body does not exisy
				return null;
			}
		} catch (Exception e) {
			res.setStatus(400); // 400 request body is bad data
			e.printStackTrace();
			marketRating = null;
		}
		return marketRating;
	}
	
	@DeleteMapping("market/{mId}/marketratings")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable Integer mId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			if (mrSvc.destroy(user, mId)) {
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
