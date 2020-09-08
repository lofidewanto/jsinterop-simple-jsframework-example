package com.github.lofi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import com.github.lofi.client.CalculatorService;

public class CalculatorServiceTest {

	private static Logger logger = Logger.getLogger(CalculatorServiceTest.class.getName());

	private CalculatorService calculatorService;

	public CalculatorServiceTest() {
		this.calculatorService = new CalculatorService();
	}

	@Test
	public void calculatePrice() {
		assertNotNull(calculatorService);

		// We cannot test because Calculator uses native JavaScript implementation.
		// Only possible if we would mock it,
		// This would be my next article of using Dependency Injection in this
		// calculator.

		logger.info("Test...");
	}

	@Test
	public void calculatePrice_With_Null() {
		assertNotNull(calculatorService);
	}
}
