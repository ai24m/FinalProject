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
import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.services.MarketCommentService;
import com.skilldistillery.lettucemeet.services.MarketService;
import com.skilldistillery.lettucemeet.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300"})
public class MarketCommentController {
	
	@Autowired 
	private MarketCommentService mcSvc; 
	
	@Autowired 
	private UserService userSvc; 
	
	@Autowired
	private MarketService marketSvc;
	
	
	@GetMapping("marketcomments")
	public List<MarketComment> index(HttpServletRequest req, HttpServletResponse res){
		List<MarketComment> allMarketComments = mcSvc.index(); 
		if (allMarketComments == null) {
			res.setStatus(404);
		} return allMarketComments; 
	}
	
	@GetMapping("marketcomments/market/{marketId}")
	public List<MarketComment> getMakrketCommentByMarketID(
			HttpServletRequest req, 
			HttpServletResponse res,
			@PathVariable Integer marketId){
		List<MarketComment> MarketComments = mcSvc.getByMarketId(marketId); 
		if (MarketComments == null) {
			res.setStatus(404);
		} return MarketComments; 
	}
	
	@GetMapping("marketcomments/{mcId}")
	public MarketComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer mcId) {
		MarketComment marketComment = mcSvc.show(mcId);
		if (marketComment == null) {
			res.setStatus(404);
		} return marketComment; 
	}
	
	@PostMapping("marketcomments/{mcId}/comments") //must create address with market 
	public MarketComment createReply(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody MarketComment marketComment,
			@PathVariable Integer mcId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			mcSvc.createReply(mcId,user,marketComment); 
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(marketComment.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			marketComment = null;
		} return null; 
	}
	@PostMapping("marketcomments/{marketId}") //must create address with market 
	public MarketComment create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody MarketComment marketComment,
			@PathVariable Integer marketId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			mcSvc.create(marketComment,user,marketId); 
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(marketComment.getId());
			res.setHeader("location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			marketComment = null;
		} return null; 
	}
	
	@PutMapping("market/{mId}/marketcomments/{mcId}")
	public MarketComment update(HttpServletRequest req, HttpServletResponse res, Principal principal, 
			@PathVariable Integer mcId, @RequestBody MarketComment marketComment, @PathVariable Integer mId) {
		try {
			Market market = marketSvc.findById(mId);
			User user = userSvc.findByUserName(principal.getName()); 
			marketComment = mcSvc.update(user, market, mcId, marketComment);
			if (marketComment == null) {
				res.setStatus(404); // 404 request body does not exisy
			} 
		} catch (Exception e) {
			res.setStatus(400); // 400 request body is bad data
			e.printStackTrace();
			marketComment = null;
		}
		return marketComment;
	}
	
	@DeleteMapping("marketcomments/{mcId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable Integer mcId) {
		try {
			User user = userSvc.findByUserName(principal.getName()); 
			if (mcSvc.destroy(user, mcId)) {
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
