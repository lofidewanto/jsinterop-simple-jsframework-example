package com.github.lofi.client;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductRandomCreator {

	private static Logger logger = Logger.getLogger(ProductRandomCreator.class.getName());

	@Inject
	public ProductRandomCreator() {
	}

	public String getRandomId() {
		int random = (int) (Math.random() * 50 + 1);
		String id = Integer.valueOf(random).toString();

		logger.info("Random Id: " + id);

		return id;
	}

	public Integer getRandomPrice() {
		int price = (int) (Math.random() * 100 + 1);

		logger.info("Random Price: " + price);

		return price;
	}

	public String geRandomType() {
		Random rand = new Random();
		List<String> givenList = Arrays.asList("Machine", "Computer", "Smartphone", "Car");

		int numberOfElements = 2;
		String randomElement = "None";

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			randomElement = givenList.get(randomIndex);
		}

		logger.info("Random Type: " + randomElement);

		return randomElement;
	}

}
