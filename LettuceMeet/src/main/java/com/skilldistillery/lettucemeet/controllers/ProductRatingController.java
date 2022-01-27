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

import com.skilldistillery.lettucemeet.entities.ProductRating;
import com.skilldistillery.lettucemeet.services.ProductRatingService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class ProductRatingController {

	@Autowired
	private ProductRatingService prodcutRatingSev;

	@GetMapping("productRatings")
	public List<ProductRating> index() {
		return prodcutRatingSev.showAll();
	}

	@GetMapping("productRatings/user")
	public List<ProductRating> showAllRatings(Principal principal) {
		return prodcutRatingSev.findByUserName(principal.getName());
	}

	// find one product's rating made by one user
	@GetMapping("productRatings/{productId}")
	public ProductRating findByProductRatingId(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int sellerId, Principal principal) {
		ProductRating productRating = prodcutRatingSev.findByProductRatingId(sellerId, principal.getName());
		if (productRating == null) {
			res.setStatus(404);
		}
		return productRating;
	}

	@PostMapping("productRatings/{productId}")
	public ProductRating createProductRating(@PathVariable int sellerId, @RequestBody ProductRating productRating,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			productRating = prodcutRatingSev.createProductRating(sellerId, principal.getName(), productRating);
			if (productRating == null) {
				res.setStatus(404);
				return productRating;
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(productRating.getId());
				res.setHeader("Location", url.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			productRating = null;
		}
		return productRating;
	}

	@PutMapping("productRatings/{productId}")
	public ProductRating updateProductRating(@PathVariable int sellerId, @RequestBody ProductRating productRating,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			productRating = prodcutRatingSev.createProductRating(sellerId, principal.getName(), productRating);
			if (productRating == null) {
				res.setStatus(404);
			}
			return productRating;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			productRating = null;
		}
		return productRating;
	}

	@DeleteMapping("productRatings/{productId}")
	public void deleteProductRating(
			HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int productId,
			Principal principal
			) {
		try {
			if(prodcutRatingSev.deleteProductRating(productId, principal.getName())) {
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
