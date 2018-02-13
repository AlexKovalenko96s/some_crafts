package ua.kas.main.task3;

public class Main {

	public static void main(String[] args) {

		Test test = str -> str.toUpperCase();

		System.out.println(upper(test, "All to upper case"));

		System.out.println(upper((str) -> {
			str = str.toLowerCase();
			str += "TEST";
			return str;
		}, "ALL to LOWER CASE"));
	}

	private static String upper(Test test, String str) {
		return test.test(str);
	}
}
