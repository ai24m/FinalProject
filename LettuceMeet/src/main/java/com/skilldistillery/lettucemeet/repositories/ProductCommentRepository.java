package com.skilldistillery.lettucemeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.ProductComment;
import com.skilldistillery.lettucemeet.entities.User;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {

	ProductComment findByIdAndUser(Integer pcId, User user);

	List<ProductComment> findByProduct_Id(Integer pcId);

}
