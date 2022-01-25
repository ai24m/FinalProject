package com.skilldistillery.lettucemeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.User;

public interface UserRepository extends JpaRepository <User, Integer>{

}
