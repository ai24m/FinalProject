package com.skilldistillery.lettucemeet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.ProductRating;
import com.skilldistillery.lettucemeet.entities.ProductRatingId;
import com.skilldistillery.lettucemeet.entities.User;
import com.skilldistillery.lettucemeet.repositories.ProductRatingRepository;
import com.skilldistillery.lettucemeet.repositories.ProductRepository;
import com.skilldistillery.lettucemeet.repositories.UserRepository;

@Service
public class ProductRatingImpl implements ProductRatingService {

	@Autowired
	private ProductRatingRepository productRatingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepo;
	@Override
	public List<ProductRating> showAll() {
		// TODO Auto-generated method stub
		return productRatingRepo.findAll();
	}

	@Override
	public List<ProductRating> findByUserName(String username) {
		// TODO Auto-generated method stub
		return productRatingRepo.findByUser_Username(username);
	}

	@Override
	public ProductRating findByProductRatingId(int productId, String userName) {
		// TODO Auto-generated method stub
		ProductRatingId pId = new ProductRatingId(userRepo.findByUsername(userName).getId(),productId);
		Optional<ProductRating> op = productRatingRepo.findById(pId);
		ProductRating productRating = null;
		if(op.isPresent()) {
			productRating = op.get();
		}
		return productRating;
	}

	@Override
	public ProductRating createProductRating(int productId, String userName, ProductRating productRating) {
		// TODO Auto-generated method stub
		ProductRatingId pId = new ProductRatingId(userRepo.findByUsername(userName).getId(),productId);
		Optional<ProductRating> op = productRatingRepo.findById(pId);
		if(!op.isPresent()) {
			
			Product product = productRepo.getById(productId);
			User user = userRepo.findByUsername(userName);
			if(product!=null && user!=null) {
				productRating.setId(pId);
				productRating.setUser(user);
				productRating.setProduct(product);
				productRatingRepo.saveAndFlush(productRating);
				return productRating;
			}
			}
		System.out.println("pid is null");
//		System.out.println(product.getId());
//		System.out.println(user.getId());
		return null;
	}

	@Override
	public ProductRating updateProductRating(int productId, String userName, ProductRating productRating) {
		// TODO Auto-generated method stub
		ProductRatingId pId = new ProductRatingId(userRepo.findByUsername(userName).getId(),productId);
		Optional<ProductRating> op = productRatingRepo.findById(pId);
		ProductRating existing = null;
		if(op.isPresent()) {
			existing = op.get();
			existing.setComment(productRating.getComment());
			existing.setRating(productRating.getRating());
			productRatingRepo.saveAndFlush(existing);

		}
		return existing;
	}

	@Override
	public boolean deleteProductRating(int productId, String userName) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		ProductRatingId pId = new ProductRatingId(userRepo.findByUsername(userName).getId(),productId);
		Optional<ProductRating> op = productRatingRepo.findById(pId);
		ProductRating existing = null;
		if(op.isPresent()) {
			existing = op.get();
			productRatingRepo.delete(existing);
			deleted = true;
		}
		return deleted;
	}

}
