package ua.kas.main;

import java.util.Scanner;

public class UserController {

	private HandlerEvent handler = new HandlerEvent();
	private EnterBet enterBet = new EnterBet(handler);

	private Scanner scn = new Scanner(System.in);

	private String select = "";

	private int userId;

	public void menu() {
		System.out.println("-----MENU-----");
		System.out.println("1. Money");
		System.out.println("2. Select event");
		System.out.println("3. Exit");

		select = scn.next();

		if (select.equals("1")) {
			money();
		} else if (select.equals("2")) {
			seeAll();
		} else if (select.equals("3")) {
			exit();
		} else {
			System.err.println("Not correct command!");
			menu();
		}
	}

	private void money() {
		System.out.println("-----MONEY-----");
		System.out.println("Youre id is: " + userId);
		System.out.println("You have " + "(some)" + " $");
		System.out.println("1. Add money");
		System.out.println("2. Withdraw money");
		System.out.println("3. Back");

		select = scn.next();

		if (select.equals("1")) {
			System.out.println("Please enter card number: ");
			select = scn.next();
			System.out.println("Thanks to the money added!");
			menu();
		} else if (select.equals("2")) {
			System.err.println("Hah, you`re dump!");
			money();
		} else if (select.equals("3")) {
			menu();
		} else {
			System.err.println("Not correct command!");
			menu();
		}
	}

	private void seeAll() {
		System.out.println("-----EVENT-----");
		int indexOfBack = handler.getList().size() + 1;
		for (int i = 0; i < handler.getList().size(); i++) {
			System.out.println(i + 1 + ". " + handler.getList().get(i).getHome() + " vs "
					+ handler.getList().get(i).getVisitors());
		}
		System.out.println(indexOfBack + ". Back");

		select = scn.next();

		if (Integer.parseInt(select) < indexOfBack) {
			enterBet.setUserId(userId);
			enterBet.setEventId(Integer.parseInt(select) - 1);
			enterBet.menu();

		} else if (Integer.parseInt(select) == indexOfBack) {
			menu();
		} else {
			System.err.println("Not correct command!");
			seeAll();
		}
	}

	private void exit() {
		System.out.println("Bye...");
		System.exit(0);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
