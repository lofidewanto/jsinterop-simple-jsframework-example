package com.github.lofi.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ProductServiceTest {

	private static Logger logger = Logger.getLogger(ProductServiceTest.class.getName());

	private ProductService productService;

	@BeforeEach
	void init(@Mock(answer = Answers.RETURNS_DEEP_STUBS) ProductIdbRepository productIndexedDbRepository,
			@Mock(answer = Answers.RETURNS_DEEP_STUBS) ProductRandomCreator productRandomCreator) {
		
		when(productRandomCreator.getRandomId()).thenReturn("1");
		when(productRandomCreator.geRandomType()).thenReturn("Machine");
		when(productRandomCreator.getRandomPrice()).thenReturn(25);
		
		productService = new ProductService(productIndexedDbRepository, productRandomCreator);
	}

	@Test
	void create_Product_simple() {
		logger.info("Test...");

		Product product = productService.createProduct();

		assertEquals("Lofi 1", product.getName());
		assertEquals("Machine", product.getType());
		assertEquals(25, product.getPrice());
		assertEquals(250, product.getCalculatedPriceWithAmount());
	}

}
