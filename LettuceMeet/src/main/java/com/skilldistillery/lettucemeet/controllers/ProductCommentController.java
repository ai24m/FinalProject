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

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.services.ProductCommentService;
import com.skilldistillery.lettucemeet.services.ProductService;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300"})
public class ProductCommentController {

	@Autowired 
	private ProductCommentService pcSvc; 
	
	@Autowired 
	private UserService userSvc; 
	
	@Autowired 
	private ProductService productSvc; 
	
	@GetMapping("productcomments")
	public List<ProductComment> index(HttpServletRequest req, HttpServletResponse res){
		List<ProductComment> allProductComments = pcSvc.index(); 
		if (allProductComments == null) {
			res.setStatus(404);
		} return allProductComments; 
	}
	
	@GetMapping("productcomments/{pcId}")
	public ProductComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer pcId) {
		ProductComment productComment = pcSvc.show(pcId);
		if (productComment == null) {
			res.setStatus(404);
		} return productComment; 
	}
	
	@PostMapping("productcomments/{pcId}/comments") //must create address with market 
	public ProductComment create(HttpServletRequest req, HttpServletResponse res, Principal principal, 
			@RequestBody ProductComment productComment, @PathVariable Integer pcId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			pcSvc.create(productComment, user); 
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(productComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid entry for new product");
			res.setStatus(400);
			productComment = null;
		} return null; 
	}
	
	@PutMapping("products/{pId}/productcomments/{pcId}")
	public ProductComment update(HttpServletRequest req, HttpServletResponse res, Principal principal, 
			@PathVariable Integer pcId, @RequestBody ProductComment productComment, @PathVariable Integer pId) {
		try {
			Product product = productSvc.findById(pId); 
			User user = userSvc.findByUserName(principal.getName()); 
			productComment = pcSvc.update(user, product, pcId, productComment);
			if (productComment == null) {
				res.setStatus(404); // 404 request body does not exisy
			} 
		} catch (Exception e) {
			res.setStatus(400); // 400 request body is bad data
			e.printStackTrace();
			productComment = null;
		}
		return productComment;
	}
	
	@DeleteMapping("productcomments/{pcId}/comments")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable Integer pcId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			if (pcSvc.destroy(user, pcId)) {
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
