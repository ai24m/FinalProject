package com.skilldistillery.lettucemeet.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
	}
	
	@Test
	void test_mcComment_mc_Mapping() {
		assertNotNull(mc);
		assertEquals(1, mc.getId());
	}
	
	@Test
	void test_mcComment_User_Mapping() {
		assertNotNull(mc);
		assertEquals(1, mc.getId());
	}

}
