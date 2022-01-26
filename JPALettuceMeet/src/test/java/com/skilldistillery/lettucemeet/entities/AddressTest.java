package com.skilldistillery.lettucemeet.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address address;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPALettuceMeet");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}
	
	@Test
	void test_Address_Mapping() {
		assertNotNull(address);
		assertEquals(1, address.getId());
		assertEquals("1234 Real Street", address.getStreet1());
		assertEquals(null, address.getStreet2());
		assertEquals("Hong Kong", address.getCity());
		assertEquals("80121", address.getZip());
	}
	
	@Test
	void test_Address_User_Mapping() {
		assertNotNull(address.getUsers());
		assertTrue(address.getUsers().size() > 0);
	}
	
	@Test
	void test_Address_Market_Mapping() {
		assertNotNull(address.getMarkets());
		assertTrue(address.getMarkets().size() == 0);
	}
}
