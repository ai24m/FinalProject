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

class SellerRatingTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private SellerRating sr;

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
		SellerRatingId sri = new SellerRatingId(1, 87);
		sr = em.find(SellerRating.class, sri);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		sr = null;
	}
	
	@Test
	void test_SellerRating_Mapping() {
		assertNotNull(sr);
		assertNotNull(sr.getComment());
		assertEquals(4, sr.getSellerRating());
//		assertEquals(4, sr.getComment());
	}
	
//	|       1 |        87 |             4 | Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.       
//	@Test
//	void test_ProductRating_Seller_Mapping() {
//		assertNotNull(sr.getSeller());
//		assertEquals(2, sr.getSeller());
//	}
	
	@Test
	void test_SellerRating_User_Mapping() {
		assertNotNull(sr.getUser());
		assertEquals(1, sr.getUser().getId());
	}
}
