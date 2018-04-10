package ua.kas.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SecondTest {

	@Test
	public void test() {
		FirstClass firstClass = new FirstClass();
		String result = firstClass.conCat("Hello ", "world!");
		assertEquals("Hello world!", result);
	}

}
