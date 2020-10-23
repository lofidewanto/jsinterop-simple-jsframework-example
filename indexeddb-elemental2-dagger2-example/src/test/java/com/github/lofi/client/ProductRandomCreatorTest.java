package com.github.lofi.client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductRandomCreatorTest {

	private static Logger logger = Logger.getLogger(ProductServiceTest.class.getName());

	private ProductRandomCreator productRandomCreator;

	@BeforeEach
	void init() {
		this.productRandomCreator = new ProductRandomCreator();
	}

	@Test
	void get_RandomId_ok() {
		String randomId = productRandomCreator.getRandomId();

		logger.info("Random Id: " + randomId);

		// Result from 1 to 50
		Integer randomIdAsInteger = new Integer(randomId);
		assertTrue(randomIdAsInteger <= 50 && randomIdAsInteger >= 1);
	}

	@Test
	void get_RandomType_ok() {
		String randomType = productRandomCreator.geRandomType();

		logger.info("Random Type: " + randomType);

		// Result like this array
		List<String> givenList = Arrays.asList("Machine", "Computer", "Smartphone", "Car");
		assertTrue(givenList.contains(randomType));
	}
	
	@Test
	void get_RandomPrice_ok() {
		Integer randomPrice = productRandomCreator.getRandomPrice();

		logger.info("Random Price: " + randomPrice);

		// Result from 1 to 100
		assertTrue(randomPrice <= 100 && randomPrice >= 1);
	}

}
