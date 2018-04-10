package ua.kas.secondMain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CalculatorImplTest {

	private Calculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new CalculatorImpl();
	}

	@Test
	public void testAdd() {
		// case1
		int expectedResult1 = 10;
		int actualResult1 = calculator.add(3, 7);
		assertEquals(expectedResult1, actualResult1);

		// case2
		int expectedResult2 = -4;
		int actualResult2 = calculator.add(3, -7);
		assertEquals(expectedResult2, actualResult2);

		// case3
		int expectedResult3 = -10;
		int actualResult3 = calculator.add(-3, -7);
		assertEquals(expectedResult3, actualResult3);
	}

	@Test
	public void testSub() {
		// case1
		int expectedResult1 = 7;
		int actualResult1 = calculator.sub(10, 3);
		assertEquals(expectedResult1, actualResult1);

		// case2
		int expectedResult2 = -4;
		int actualResult2 = calculator.add(3, -7);
		assertEquals(expectedResult2, actualResult2);

	}

	@Test
	public void testMult() {
		// case1
		int expectedResult1 = 21;
		int actualResult1 = calculator.mult(3, 7);
		assertEquals(expectedResult1, actualResult1);

		// case2
		int expectedResult2 = -21;
		int actualResult2 = calculator.mult(-3, 7);
		assertEquals(expectedResult2, actualResult2);

		// case3
		int expectedResult3 = 21;
		int actualResult3 = calculator.mult(-3, -7);
		assertEquals(expectedResult3, actualResult3);
	}

	@Test
	public void testDiv() {
		// case1
		int expectedResult1 = 5;
		int actualResult1 = calculator.div(10, 2);
		assertEquals(expectedResult1, actualResult1);

		// case2
		int expectedResult2 = -5;
		int actualResult2 = calculator.div(-10, 2);
		assertEquals(expectedResult2, actualResult2);

		// case3
		int expectedResult3 = -5;
		int actualResult3 = calculator.div(10, -2);
		assertEquals(expectedResult3, actualResult3);

		// case4
		int expectedResult4 = 0;
		int actualResult4 = calculator.div(10, 0);
		assertEquals(expectedResult4, actualResult4);

		// case5
		int expectedResult5 = 0;
		int actualResult5 = calculator.div(0, 0);
		assertEquals(expectedResult5, actualResult5);

		// case6
		int expectedResult6 = 0;
		int actualResult6 = calculator.div(0, 2);
		assertEquals(expectedResult6, actualResult6);
	}

}
