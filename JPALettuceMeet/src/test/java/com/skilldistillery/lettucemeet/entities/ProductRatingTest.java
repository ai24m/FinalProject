package com.skilldistillery.lettucemeet.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	private ProductRating pr;

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
		ProductRatingId pri = new ProductRatingId(1, 306);
		pr = em.find(ProductRating.class, pri);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pr = null;
	}
	
	@Test
	void test_ProductRating_Mapping() {
		assertNotNull(pr);
		assertEquals("Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.", pr.getComment());
	}
	
	@Test
	void test_ProductRating_Product_Mapping() {
		assertNotNull(pr.getProduct());
		assertEquals(306, pr.getProduct().getId());
		assertEquals(2, pr.getProductRating());
	}
	
	@Test
	void test_ProductRating_User_Mapping() {
		assertNotNull(pr.getProduct());
		assertEquals(1, pr.getUser().getId());
	}
}
