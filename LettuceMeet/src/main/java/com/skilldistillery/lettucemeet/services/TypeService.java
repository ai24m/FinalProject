package com.skilldistillery.lettucemeet.services;

import java.util.List;

import com.skilldistillery.lettucemeet.entities.Type;

public interface TypeService {
	
	//get Types;
	public List<Type> getAllTypes();
	
	//get by Type name
	public List<Type> getTypeByName(String name);
	
	
	

}
