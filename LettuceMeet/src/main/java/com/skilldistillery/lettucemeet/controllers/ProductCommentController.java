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

import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.services.ProductCommentService;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300"})
public class ProductCommentController {

	@Autowired 
	private ProductCommentService pcSvc; 
	
	@Autowired 
	private UserService userSvc; 
	
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
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(productComment.getId());
			res.setHeader("location", url.toString());
		} return productComment; 
	}
	
	@PostMapping("productcomments") //must create address with market 
	public ProductComment create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody ProductComment productComment) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			productComment = pcSvc.create(productComment); 
			if (productComment == null) {
				res.setStatus(404);
				return productComment; 
			}
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(productComment.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		} return productComment; 
	}
	
	@PutMapping("productcomments/{pcId}")
	public ProductComment update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable Integer pcId, @RequestBody ProductComment productComment) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			productComment = pcSvc.update(user, pcId, productComment);
			if (productComment == null) {
				res.setStatus(404); // 404 request body does not exisy
				return null;
			}
		} catch (Exception e) {
			res.setStatus(400); // 400 request body is bad data
			e.printStackTrace();
			productComment = null;
		}
		return productComment;
	}
	
	@DeleteMapping("productcomments/{pcId}")
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
