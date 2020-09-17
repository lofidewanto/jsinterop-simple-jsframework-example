package com.github.lofi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.lofi.client.Product;

class ProductTest {

	private static Logger logger = Logger.getLogger(ProductTest.class.getName());

	private Product product;

	@BeforeEach
	void init() {
		this.product = new Product();
	}

	@Test
	void create_Product() {
		assertNotNull(product);

		logger.info("Test...");
	}

}
