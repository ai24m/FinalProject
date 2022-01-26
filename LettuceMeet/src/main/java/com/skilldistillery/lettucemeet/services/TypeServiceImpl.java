package com.skilldistillery.lettucemeet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.lettucemeet.entities.Type;
import com.skilldistillery.lettucemeet.repositories.TypeRepository;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeRepository typeRepo;
	@Override
	public List<Type> getAllTypes() {
		// TODO Auto-generated method stub
		
		return typeRepo.findAll();
	}

	@Override
	public List <Type> getTypeByName(String name) {
		// TODO Auto-generated method stub
		name = "%"+name+"%";
		return typeRepo.findByName(name);
	}

}
