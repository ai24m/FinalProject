package com.skilldistillery.lettucemeet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.lettucemeet.entities.Address;

public interface AddressRepository extends JpaRepository <Address, Integer> {

}
