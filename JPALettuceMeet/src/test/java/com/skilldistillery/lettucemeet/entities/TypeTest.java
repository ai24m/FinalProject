package com.skilldistillery.lettucemeet.entities;

//import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

class TypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
//	private Type type;

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
//		type = em.find(Type.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
//		type = null;
	}
	
//	@Test
//	void test_Type_Mapping() {
//		assertNotNull(type);
//		assertEquals(1, type.getId());
//	}
//	
//	@Test
//	void test_Type_Product_Mapping() {
//		assertNotNull(type);
//	}
//	
//	@Test
//	void test_Address_Market_Mapping() {
//		assertNotNull(type);
//	}
}
