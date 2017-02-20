package ua.kas.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			resultSet.close();
			preparedStatement.close();
		}
	}

	public boolean testAdd() throws SQLException {
		Statement statement = null;

		try {
			String query = "insert into employee (name, surname, age, username, password) values ('1qw2', '1qw21qw2', 19, '1qw2', '1qw21qw2')";
			statement = connection.createStatement();
			statement.execute(query);

			return true;
		} catch (Exception e) {
			System.out.println(e);

			return false;
		} finally {
			statement.close();
		}
	}

	public boolean testDel() throws SQLException {
		Statement statement = null;

		try {
			String query = "delete from employee where age = 19";
			statement = connection.createStatement();
			statement.execute(query);

			return true;
		} catch (Exception e) {
			System.out.println(e);

			return false;
		} finally {
			statement.close();
		}
	}

	public boolean testUpdate() throws SQLException {
		Statement statement = null;

		try {
			String query = "update employee set name = 'noAlex' where age = 19";
			statement = connection.createStatement();
			statement.execute(query);

			return true;
		} catch (Exception e) {
			System.out.println(e);

			return false;
		} finally {
			statement.close();
		}
	}
}
