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

class ProductRatingTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
//	private ProductRating pr;

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
//		pr = em.find(ProductRating.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
//		pr = null;
	}
	
//	@Test
//	void test_ProductRating_Mapping() {
//		assertNotNull(type);
//		assertEquals(1, type.getId());
//	}
//	
//	@Test
//	void test_ProductRating_Product_Mapping() {
//		assertNotNull(type);
//	}
//	
//	@Test
//	void test_Address_Market_Mapping() {
//		assertNotNull(type);
//	}
}
