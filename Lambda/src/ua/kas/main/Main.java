package ua.kas.main;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);

		list.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.println(t + 1);
			}
		});

		list.forEach(integer -> System.out.println(integer + 1));

		list.forEach(System.out::println);

		// ----------------------

		Test testFirst = (n) -> (n % 2) == 0;

		if (testFirst.test(2))
			System.out.println("even");
		if (!testFirst.test(1))
			System.out.println("not even");

		Test testSecond = (n) -> n >= 0;

		if (testSecond.test(1))
			System.out.println("positive");
		if (!testSecond.test(-1))
			System.out.println("negative");

		// ----------------------

		new Thread(() -> {
			int i = 1;

			while (i < 9) {
				System.out.println(++i);
			}
		}).start();
	}

}
