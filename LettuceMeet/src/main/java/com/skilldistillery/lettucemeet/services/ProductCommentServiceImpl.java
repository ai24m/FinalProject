package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.ProductCommentRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service 
public class ProductCommentServiceImpl implements ProductCommentService {

	@Autowired 
	private ProductCommentRepository pcRepo;
	
	@Autowired 
	private UserRepository userRepo;

	@Override
	public List<ProductComment> index() {
		return pcRepo.findAll();
	}

	@Override
	public ProductComment show(Integer pcId) {
		Optional<ProductComment> mOpt = pcRepo.findById(pcId); 
		if (mOpt.isPresent()) {
			ProductComment productComment = mOpt.get(); 
			return productComment; 
		} return null;
	}

	@Override
	public ProductComment create(ProductComment productComment, User user) {
		if (user != null) {
			productComment.setUser(user);
			productComment.setProduct(productComment.getProduct());
			productComment.setReplyTo(productComment);
			return pcRepo.saveAndFlush(productComment);
		} return null;
	}

	@Override
	public ProductComment update(User user, Product product, Integer pcId, ProductComment productComment) {
		ProductComment existing = pcRepo.findByIdAndUser(pcId, user);
		if (existing != null) {
			existing.setComment(productComment.getComment());
			existing.setReplyTo(productComment);
			existing.setProduct(product);
			existing.setUser(user);
			pcRepo.saveAndFlush(existing); 
		} return existing;
	}

	@Override
	public boolean destroy(User user, Integer pcId) {
		boolean deleted = false;
		ProductComment productComment = pcRepo.findByIdAndUser(pcId, user); 
		if (productComment != null) {
			deleted = true;
		} return deleted; 
	}
}
