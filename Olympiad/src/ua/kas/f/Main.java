package ua.kas.f;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String first = scn.nextLine();
		String second = scn.nextLine();
		scn.close();

		int firstX = Integer.parseInt(first.substring(0, first.indexOf(" ")));
		int firstY = Integer.parseInt(first.substring(first.indexOf(" ") + 1));

		int secondX = Integer.parseInt(second.substring(0, second.indexOf(" ")));
		int secondY = Integer.parseInt(second.substring(second.indexOf(" ") + 1));

		System.out.println((secondX + (secondX - firstX)) + " " + (secondY + (secondY - firstY)));
	}

}
