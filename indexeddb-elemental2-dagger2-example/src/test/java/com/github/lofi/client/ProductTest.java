package com.github.lofi.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

	private static Logger logger = Logger.getLogger(ProductTest.class.getName());

	private Product.Builder productBuilder;

	@BeforeEach
	void init() {
		this.productBuilder = new Product.Builder("1", "Name");
	}

	@Test
	void create_Product() {
		Product product = productBuilder.build();
		assertNotNull(product);

		logger.info("Test...");
	}

	@Test
	void create_Product_with_null_price_calculated_price_zero() {
		Product product = productBuilder.setAmount(10).setPrice(null).setType(null).build();

		assertEquals(0, product.getCalculatedPriceWithAmount());
	}

	@Test
	void create_Product_with_null_amount_calculated_price_zero() {
		Product product = productBuilder.setAmount(null).setPrice(null).setType(null).build();

		assertEquals(0, product.getCalculatedPriceWithAmount());
	}

	@Test
	void create_Product_with_not_null_price_calculated_price_not_zero() {
		Product product = productBuilder.setAmount(10).setPrice(25).setType(null).build();

		assertEquals(250, product.getCalculatedPriceWithAmount());
	}
}
