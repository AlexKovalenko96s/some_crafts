package ua.kas.main;

import java.util.Scanner;

public class Main {

	private static Scanner scn = new Scanner(System.in);

	private static String login = "";
	private static String password = "";

	public static void main(String[] args) {
		enter();
	}

	public static void enter() {
		System.out.println("Welcom!");
		System.out.println("Enter login : ");
		login = scn.next();
		System.out.println("Enter password : ");
		password = scn.next();

		if (login.equals("1") && password.equals("1")) {
			System.out.println("Hello, I didn`t see you for a long time.");

			UserController userController = new UserController();
			userController.setUserId(666);
			userController.menu();
			return;
		} else {
			System.err.println("Not correct password or login!");
			enter();
		}

	}

}
