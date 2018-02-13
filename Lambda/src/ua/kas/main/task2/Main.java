package ua.kas.main.task2;

public class Main {

	public static void main(String[] args) {
		Test<String> testFirst = (str) -> {

			String newStr = "";

			for (int i = str.length() - 1; i >= 0; i--) {
				newStr += str.charAt(i);
			}

			return newStr;
		};

		System.out.println(testFirst.test("test"));

		Test<Integer> testSecond = (i) -> {
			return ++i;
		};

		System.out.println(testSecond.test(1));

	}

}
