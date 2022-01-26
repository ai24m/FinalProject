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

class mcCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private MarketComment mc;

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
		mc = em.find(MarketComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		mc = null;
	}

	@Test
	void test_mcComment_Mapping() {
		assertNotNull(mc);
		assertEquals(1, mc.getId());
		assertEquals("Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.", mc.getComment());
		assertEquals(1, mc.getCreated().getMonth().getValue());
		assertEquals(10, mc.getMarket().getId());
	}
	
	@Test
	void test_mcComment_mc_Mapping() {
		assertNotNull(mc);
//		assertEquals(1, mc.getMarketComment());
	}
	
	@Test
	void test_mcComment_User_Mapping() {
		assertNotNull(mc.getUser());
		assertEquals(50, mc.getUser().getId());	}

}
