package ua.kas.main;

import java.util.Scanner;

public class EnterBet {

	private Scanner scn = new Scanner(System.in);

	private HandlerEvent handler;

	private Money moneyClass = new Money();

	private String select = "";

	private int userId;
	private int eventId;
	private int betId;
	private int money;

	public EnterBet(HandlerEvent handler) {
		this.handler = handler;
	}

	public void menu() {
		System.out.println("-----ENTER_BET-----");
		System.out.println("Match: " + handler.getList().get(eventId).getHome() + " vs "
				+ handler.getList().get(eventId).getVisitors());
		System.out.println("Bet: ");
		System.out.println("1. winHome");
		System.out.println("2. winAway");
		System.out.println("3. draw");
		System.out.println("4. Exit");

		select = scn.next();

		if (select.equals("1")) {
			betId = 1;
			money();
		} else if (select.equals("2")) {
			betId = 2;
			money();
		} else if (select.equals("3")) {
			betId = 3;
			money();
		} else if (select.equals("4")) {
			System.out.println("Bye...");
			System.exit(0);
		} else {
			System.err.println("Not correct command!");
			menu();
		}
	}

	private void money() {
		System.out.println("Enter money: ");
		money = scn.nextInt();
		moneyClass.setBetId(betId);
		moneyClass.setEventId(eventId);
		moneyClass.setUserId(userId);
		moneyClass.setMoney(money);
		System.out.println("You`re confirm your bet!");
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
