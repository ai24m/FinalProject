package com.skilldistillery.lettucemeet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.Type;


public interface TypeRepository extends JpaRepository <Type, Integer>{

	List<Type> findByName(String name);
}


