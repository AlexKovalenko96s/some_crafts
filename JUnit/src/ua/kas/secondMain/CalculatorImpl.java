package ua.kas.secondMain;

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public int sub(int a, int b) {
		return a - b;
	}

	@Override
	public int mult(int a, int b) {
		return a * b;
	}

	@Override
	public int div(int a, int b) {
		int result = 0;

		try {
			result = a / b;
		} catch (ArithmeticException e) {
			System.err.println("Divisor is 0.\nDivision by 0 not possible!");
		}
		return result;
	}

}
