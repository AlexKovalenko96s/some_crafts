package ua.kas.c;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int number = scn.nextInt();
		scn.close();

		int countYes = 0;
		int countNo = 0;

		for (int i = 0; i <= number; i++) {
			if (Integer.toString(i).contains("1"))
				countYes++;
			else
				countNo++;
		}

		if (countNo >= countYes)
			System.out.println("NO");
		else
			System.out.println("YES");

	}

}
