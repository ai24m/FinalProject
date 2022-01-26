package com.skilldistillery.lettucemeet.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.lettucemeet.entities.SellerRating;
import com.skilldistillery.lettucemeet.services.SellerRatingService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class SellerRatingController {
	

	@Autowired
	private SellerRatingService sellerRatingSev;
	
	@GetMapping("sellerRatings")
	public List<SellerRating> index(){
		return sellerRatingSev.showAll();
	}
	
	//Show ratings made by one user
	@GetMapping("sellerRatings/user")
	public List<SellerRating> showAllRatings(Principal principal){
		return sellerRatingSev.findByUserName(principal.getName());
	}
	
	//find one seller's rating made by one user
	@GetMapping("sellerRatings/{sellerId}")
	public SellerRating findBySellerRatingId(HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int sellerId,
			Principal principal) {
		SellerRating sellerRating = sellerRatingSev.findBySellerRatingId(sellerId,principal.getName());
		if(sellerRating==null) {
			res.setStatus(404);
		}
		return sellerRating;
	}
	
	// Create
	@PostMapping("sellerRatings/{sellerId}")
	public SellerRating createSellerRating(@PathVariable int sellerId,
			@RequestBody SellerRating sellerRating,
			HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal) {
		try {
			sellerRating = sellerRatingSev.createSellerRating(sellerId, principal.getName(), sellerRating);
			if(sellerRating == null) {
				res.setStatus(404);
				return sellerRating;
			}else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(sellerRating.getId());
				res.setHeader("Location", url.toString());
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			sellerRating = null;
		}
		return sellerRating;
	}
	
	//Update
	@PutMapping("sellerRatings/{sellerId}")
	public SellerRating updateSellerRating(
			@PathVariable int sellerId,
			@RequestBody SellerRating sellerRating,
			HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal
			) {
		try {
			sellerRating = sellerRatingSev.updateSellerRating(sellerId, principal.getName(), sellerRating);
			if(sellerRating == null) {
				res.setStatus(404);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			sellerRating = null;
		}
		return sellerRating;
	}
	
	//Delete
	@DeleteMapping("sellerRating/{sellerId}")
	public void deleteSellerRating(
			HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int tid,
			Principal principal
			) {
		try {
			if(sellerRatingSev.deleteSellerRating(tid, principal.getName())) {
				res.setStatus(HttpStatus.NO_CONTENT.value());
			}else {
				res.setStatus(404);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
}
