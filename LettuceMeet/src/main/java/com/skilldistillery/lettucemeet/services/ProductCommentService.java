package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Product;
import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.User;

public interface ProductCommentService {

	List<ProductComment> index();

	ProductComment show(Integer pId);

	ProductComment create(ProductComment productComment, User user, Integer productId);

	ProductComment update(User user, Product product, Integer pId, ProductComment productComment);

	boolean destroy(User user, Integer pId);

	ProductComment createReply(Integer pcId, User user, ProductComment newProductComment);

	List<ProductComment> getByProductId(Integer pcId);

}
