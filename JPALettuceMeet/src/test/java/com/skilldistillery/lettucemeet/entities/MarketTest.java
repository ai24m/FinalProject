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

class MarketTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Market market;

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
		market = em.find(Market.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		market = null;
	}
	
	@Test
	void test_Address_Mapping() {
		assertNotNull(market);
		assertEquals(1, market.getId());
		assertEquals("Jetpulse", market.getName());
		assertEquals(11, market.getMarketDate().getMonthValue());
		assertEquals(10, market.getCreateTime().getMonthValue());
		assertEquals(3, market.getUpdateTime().getMonthValue());
	}
	
	@Test
	void test_Address_User_Mapping() {
		assertNotNull(market.getUser());
		assertEquals(1, market.getUser().getId());
	}
	
	@Test
	void test_Address_Address_Mapping() {
		assertNotNull(market.getUser());
		assertEquals(168, market.getAddress().getId());
	}
}
