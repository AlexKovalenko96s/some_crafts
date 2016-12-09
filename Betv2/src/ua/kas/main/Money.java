package ua.kas.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Money {

	private int userId;
	private int eventId;
	private int betId;
	private int money;

	// ArrayList<Integer> bet = new ArrayList<Integer>();
	//
	// LinkedList<ArrayList<Integer>> event = new
	// LinkedList<ArrayList<Integer>>();

	private LinkedList<Integer> usersList = new LinkedList<Integer>();
	private LinkedList<Integer> moneyList = new LinkedList<Integer>();

	public void addBet() {
		Connection myConn;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost/bet", "root", "root");
			java.sql.PreparedStatement myStmt;
			myStmt = myConn.prepareStatement("insert into enteredBet(userId, eventId, betId, money) values (?,?,?,?)");
			myStmt.setInt(1, userId);
			myStmt.setInt(2, eventId);
			myStmt.setInt(3, betId);
			myStmt.setInt(4, money);
			myStmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		checkBet();

		// bet.add(eventId);
		// bet.add(betId);
		// bet.add(userId);
		// bet.add(money);
		//
		// event.add(bet);
		// bet.clear();

	}

	private void checkBet() {
		// connect with server and check bet
		Connection myConn;
		ResultSet myRs = null;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bet", "root", "root");
			java.sql.PreparedStatement myStmt;
			myStmt = myConn.prepareStatement(
					"select enteredBet.userId, enteredBet.money from enteredBet left join sucsesfulBet on enteredBet.eventId = sucsesfulBet.eventId and enteredBet.betId = sucsesfulBet.betId");
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				usersList.add(myRs.getInt("enteredBet.userId"));
				moneyList.add(myRs.getInt("enteredBet.money"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < usersList.size(); i++) {
			try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bet", "root", "root");
				java.sql.PreparedStatement ps = myConn
						.prepareStatement("update usersMoney = (usersMoney + ?) from users where id=?");
				ps.setInt(1, moneyList.get(i));
				ps.setInt(2, usersList.get(i));
				myRs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getBetId() {
		return betId;
	}

	public void setBetId(int betId) {
		this.betId = betId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
