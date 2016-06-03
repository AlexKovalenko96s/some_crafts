package ua.kas.laba9;

import java.util.Scanner;

public class Second implements Bla_bla_bla{

	static String not_correct;
	static String correct;
	
	@Override
	public void first() {
		System.out.println("second start");
		System.out.println("Enter string(text)");
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		not_correct = scn.nextLine();
		System.out.println(not_correct);
	}
}
