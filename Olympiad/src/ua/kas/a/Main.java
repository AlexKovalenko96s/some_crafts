package ua.kas.a;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static ArrayList<String> list = new ArrayList<>();
	private static ArrayList<String> lecture = new ArrayList<>();
	private static ArrayList<ArrayList<String>> day = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		while (scn.hasNext()) {
			lecture.add(scn.nextLine());
			System.out.println(lecture);
			if (scn.nextLine().equals("\n")) {
				System.out.println("ff");
				scn.close();
				break;
			}

		}

		System.out.println(lecture);
	}

}
