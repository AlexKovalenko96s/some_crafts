package ua.kas.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		Connection myConn;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost/bet", "root", "root");
			java.sql.PreparedStatement myStmt;
			myStmt = myConn.prepareStatement("select * from users where user_name = ? and password = ? ");
			myStmt.setString(1, login);
			myStmt.setString(2, password);
			ResultSet result = myStmt.executeQuery();

			if (result.next()) {
				System.out.println("Hello, I didn`t see you for a long time.");
				UserController userController = new UserController();
				userController.setUserId(result.getInt("id"));
				userController.menu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
