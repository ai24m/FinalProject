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

class UserTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("user entity mapping")
	void test() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("admin@email.com", user.getEmail());
		assertEquals("admin", user.getPassword());
		assertEquals("admin", user.getFirstName());
		assertEquals("admin", user.getLastName());
	}
	
	@Test
	@DisplayName("user address mapping")
	void test1() {
		assertNotNull(user.getAddress());
		assertEquals(1, user.getAddress().getId());
	}

}
