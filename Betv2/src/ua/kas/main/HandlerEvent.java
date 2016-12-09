package ua.kas.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class HandlerEvent {

	private LinkedList<EventConstructor> list = new LinkedList<EventConstructor>();

	public HandlerEvent() {
		addEvent();
	}

	private void addEvent() {
		Connection myConn;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bet", "root", "root");
			ResultSet myRs = null;
			java.sql.PreparedStatement myStmt;
			myStmt = myConn.prepareStatement("select * from event");
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				list.add(new Event(myRs.getString("home"), myRs.getString("away"), myRs.getInt("idEvent")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public LinkedList<EventConstructor> getList() {
		return list;
	}
}
