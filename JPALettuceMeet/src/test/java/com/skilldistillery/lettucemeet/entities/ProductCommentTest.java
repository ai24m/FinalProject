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

class ProductCommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private ProductComment pc;

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
		pc = em.find(ProductComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pc = null;
	}

	@Test
	void test_ProductComment_Mapping() {
		assertNotNull(pc);
//		assertEquals(1, pc.getId());
	}
	
	@Test
	void test_ProductComment_User_Mapping() {
		assertNotNull(pc);
//		assertEquals(1, mc.getId());
	}
	
	@Test
	void test_ProductComment_Product_Mapping() {
		assertNotNull(pc);
//		assertEquals(1, mc.getId());
	}
}
