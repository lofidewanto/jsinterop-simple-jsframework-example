package com.github.lofi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import com.github.lofi.client.Calculator;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

	private static Logger logger = Logger.getLogger(CalculatorTest.class.getName());

	private Calculator calculator;

	public CalculatorTest() {
		this.calculator = new Calculator();
	}

	@Test
	public void calculateSum() {
		assertNotNull(calculator);

		Double[] values = { 12.0, 14.0 };

		double result = calculator.calculateSum(values);
		assertEquals(26.0, result);

		logger.info("Result: " + result);
	}

	@Test
	public void calculateSum_With_Null() {
		assertNotNull(calculator);

		Double[] values = {};

		double result = calculator.calculateSum(values);
		assertEquals(0.0, result);

		logger.info("Result: " + result);
	}
}
