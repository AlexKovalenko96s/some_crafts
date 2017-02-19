package ua.kas.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

	private Connection connection;

	public LoginModel() {
		connection = SqliteConnection.Connector();

		if (connection == null)
			System.exit(0);
	}

	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isLogin(String login, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from employee where username = ? and password = ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pass);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
}
