package ua.kas.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	private ArrayList<String> array = new ArrayList<String>();

	public EnterBet(HandlerEvent handler) {
		this.handler = handler;
	}

	public void menu() {
		System.out.println("-----ENTER_BET-----");
		System.out.println("Match: " + handler.getList().get(eventId).getHome() + " vs "
				+ handler.getList().get(eventId).getVisitors());
		System.out.println("Bet: ");

		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bet", "root", "root");
			ResultSet myRs = null;
			java.sql.PreparedStatement myStmt;
			myStmt = myConn.prepareStatement("select * from eventBet where idEvent =?");
			myStmt.setInt(1, eventId);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				array.add(myRs.getString("bet"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int end = array.size();
		for (int i = 0; i < array.size(); i++) {
			System.out.println(i + ". " + array.get(i));
		}
		System.out.println(end + ". Back");

		select = scn.next();

		if (Integer.parseInt(select) < end) {
			betId = Integer.parseInt(select) + 1;
			money();
		} else if (Integer.parseInt(select) == end) {
			menu();
		} else {
			System.out.println("Not correct comand!");
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
