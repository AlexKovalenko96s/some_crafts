package ua.kas.main;

public class Test {

	public static void main(String[] args) {
		for (int i = 10; i >= 0; i--) {
			assert i > 5 : "ERROR!!!";
			System.out.println(i);
		}

	}

}
