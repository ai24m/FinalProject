package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Market;
import com.skilldistillery.lettucemeet.entities.MarketComment;
import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.ProductCommentRepository;

@Service 
public class ProductCommentServiceImpl implements ProductCommentService {

	@Autowired 
	private ProductCommentRepository pcRepo;

	@Override
	public List<ProductComment> index() {
		return pcRepo.findAll();
	}

	@Override
	public ProductComment show(Integer pcId) {
		Optional<ProductComment> mOpt = pcRepo.findById(pcId); 
		if (mOpt.isPresent()) {
			ProductComment market = mOpt.get(); 
			return market; 
		} return null;
	}

	@Override
	public ProductComment create(ProductComment productComment) {
		if (productComment.getUser() != null && productComment.getProduct() != null) {
			productComment.setUser(productComment.getUser());
			productComment.setProduct(productComment.getProduct());
			productComment.setProductComment(productComment.getProductComment());
			pcRepo.saveAndFlush(productComment);
			return productComment; 
		} return productComment;
	}

	@Override
	public ProductComment update(User user, Integer pcId, ProductComment productComment) {
		if (productComment.getUser().getId() == user.getId()) {
			Optional <ProductComment> pcOpt = pcRepo.findById(pcId);
			if (pcOpt.isPresent()) {
				ProductComment updatedProductComment = pcOpt.get();
				updatedProductComment.setProductComment(productComment);
				updatedProductComment.setUser(user);
				updatedProductComment.setProduct(productComment.getProduct());
				return updatedProductComment; 
			}
		} return productComment;
	}

	@Override
	public boolean destroy(User user, Integer pcId) {
		boolean deleted = false; 
		ProductComment productComment = pcRepo.findByIdAndUser(pcId, user); 
		if (productComment != null) {
			pcRepo.delete(productComment);
			deleted = true;
			return deleted; 
		} return deleted; 
	}
}
