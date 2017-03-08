package ua.kas.d;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String s = scn.nextLine();
		int l = Integer.parseInt(s.substring(0, s.indexOf(" ")));
		s = s.substring(s.indexOf(" ") + 1);
		int r = Integer.parseInt(s.substring(0, s.indexOf(" ")));
		int q = Integer.parseInt(s.substring(s.indexOf(" ") + 1));

		int[] mas;

		mas = new int[r - l + 1];

		for (int i = 0; i < mas.length; i++) {
			mas[i] = 0;
		}

		String comand = "";
		int first;
		int second;

		for (int i = 0; i < q; i++) {
			comand = scn.nextLine();
			if (comand.contains("set")) {
				comand = comand.substring(4);
				first = Integer.parseInt(comand.substring(0, comand.indexOf(" ")));
				second = Integer.parseInt(comand.substring(comand.indexOf(" ") + 1));
				mas[first - l] = second;

			} else if (comand.contains("sum")) {
				comand = comand.substring(4);
				first = Integer.parseInt(comand.substring(0, comand.indexOf(" ")));
				second = Integer.parseInt(comand.substring(comand.indexOf(" ") + 1));

				int count = 0;

				for (int j = first - l; j <= second - l; j++) {
					count += mas[j];
				}
				System.out.println(count);
			}
		}

		scn.close();
	}
}
