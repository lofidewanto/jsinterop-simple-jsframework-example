package com.github.lofi.client;

import java.util.logging.Logger;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(namespace = JsPackage.GLOBAL, name = "CalculatorService")
public class CalculatorService {

	private static Logger logger = Logger.getLogger(CalculatorService.class.getName());

	public String calculatePrice(int x, int y) {
		logger.info("CalculatorService: calculatePrice...");

		Calculator calculator = new Calculator();
		calculator.x = x;
		calculator.y = y;
		final int sum = calculator.sum();

		return "Price: " + sum;
	}

}
