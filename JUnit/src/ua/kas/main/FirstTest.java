package ua.kas.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FirstTest {

	@Test
	public void test() {
		FirstClass firstClass = new FirstClass();
		int result = firstClass.add(100, 200);
		assertEquals(300, result);
	}

}
