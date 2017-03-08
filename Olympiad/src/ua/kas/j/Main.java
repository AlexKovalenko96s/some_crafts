package ua.kas.j;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String enterNumber = scn.nextLine();
		if (enterNumber.contains("1"))
			System.out.println("Yes");
		else
			System.out.println("No");
		scn.close();
	}
}
