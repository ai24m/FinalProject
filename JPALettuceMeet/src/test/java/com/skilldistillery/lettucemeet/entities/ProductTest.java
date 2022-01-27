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

class ProductTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Product product;

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
		product = em.find(Product.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		product = null;
	}

	@Test
	void test_Product_Mapping() {
		assertNotNull(product);
		assertEquals("", product.getDescription());
		assertEquals(3.02, product.getPrice());
		assertEquals(68, product.getQuantity());
		assertTrue(product.isOrganic());
	}
	
	@Test
	void test_Product_User_Mapping() {
		assertNotNull(product.getUser());
		assertEquals(41, product.getUser().getId());
	}
	
	@Test
	void test_Product_Product_Rating_Mapping() {
		assertNotNull(product.getProductRatings());
		assertTrue(product.getProductRatings().size() == 0);
	}
	
	@Test
	void test_Product_Type_Mapping() {
		assertNotNull(product.getType());
		assertEquals(4, product.getType().getId());
	}
}
