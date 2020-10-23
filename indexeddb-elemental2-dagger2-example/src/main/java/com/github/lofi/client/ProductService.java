package com.github.lofi.client;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductService {

	private static Logger logger = Logger.getLogger(ProductService.class.getName());

	private ProductIdbRepository productRepository;

	private ProductRandomCreator productRandomCreator;

	@Inject
	public ProductService(ProductIdbRepository productRepository, ProductRandomCreator productRandomCreator) {
		this.productRepository = productRepository;
		this.productRandomCreator = productRandomCreator;
	}

	public Product createProduct() {
		String key = productRandomCreator.getRandomId();
		String type = productRandomCreator.geRandomType();
		Integer price = productRandomCreator.getRandomPrice();

		Product product = new Product.Builder(key, "Lofi " + key).setType(type).setAmount(10).setPrice(price).build();
		logger.info(product.toString());
		
		productRepository.persist(product);

		return product;
	}

}
