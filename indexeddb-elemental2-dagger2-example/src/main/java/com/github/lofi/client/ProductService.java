package com.github.lofi.client;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductService {

	private static Logger logger = Logger.getLogger(ProductService.class.getName());

	private ProductIdbRepository productRepository;

	@Inject
	public ProductService(ProductIdbRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product createProduct() {
		int random = (int) (Math.random() * 50 + 1);
		String key = Integer.valueOf(random).toString();

		logger.info("Random: " + key);

		Product product = new Product();
		product.setId(key);
		product.setName("Lofi " + key);

		productRepository.persist(product);

		return product;
	}

}
