package com.github.lofi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import com.github.lofi.client.Product;

public class ProductTest {

	private static Logger logger = Logger.getLogger(ProductTest.class.getName());

	private Product product;

	public ProductTest() {
		this.product = new Product();
	}

	@Test
	public void create_Product() {
		assertNotNull(product);

		logger.info("Test...");
	}
	
}
