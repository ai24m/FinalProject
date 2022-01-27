package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.User;

public interface ProductCommentService {

	List<ProductComment> index();

	ProductComment show(Integer pId);

	ProductComment create(ProductComment productComment);

	ProductComment update(User user, Integer pId, ProductComment productComment);

	boolean destroy(User user, Integer pId);

}
