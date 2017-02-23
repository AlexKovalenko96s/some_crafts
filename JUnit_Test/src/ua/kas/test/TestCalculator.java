package ua.kas.test;

import org.junit.Assert;
import org.junit.Test;

import ua.kas.main.Calculator;

public class TestCalculator {

	private Calculator calculator = new Calculator();

	@Test
	public void testAdd() {
		double result = calculator.add(3, 7);
		Assert.assertTrue(result == 10);
	}

	@Test
	public void testMinus() {
		double result = calculator.minus(3, 7);
		Assert.assertTrue(result == -4);
	}

	@Test
	public void testMult() {
		double result = calculator.mult(3, 7);
		Assert.assertTrue(result == 21);
	}

	@Test
	public void testDiv() {
		double result = calculator.div(10, 5);
		Assert.assertTrue(result == 2);
	}
}
