package com.github.lofi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.lofi.client.Product;
import com.github.lofi.client.ProductIdbRepository;
import com.github.lofi.client.ProductService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ProductServiceTest {

	private static Logger logger = Logger.getLogger(ProductServiceTest.class.getName());

	private ProductService productService;

	@BeforeEach
	void init(@Mock(answer = Answers.RETURNS_DEEP_STUBS) ProductIdbRepository productIndexedDbRepository) {
		productService = new ProductService(productIndexedDbRepository);
	}

	@Test
	void create_Product_simple() {
		logger.info("Test...");

		Product product = productService.createProduct();

		assertEquals("Lofi " + product.getId(), product.getName());
	}

}
