package ua.kas.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML
	private Label isConnected;

	@FXML
	private TextField login;

	@FXML
	private PasswordField password;

	private LoginModel loginModel = new LoginModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginModel.isDbConnected())
			isConnected.setText("Connected!");
		else
			isConnected.setText("Not Connected!");
	}

	public void login(ActionEvent event) throws SQLException {
		if (login.getText() != null && password.getText() != null)
			if (loginModel.isLogin(login.getText(), password.getText()))
				isConnected.setText("username and password is correct!");
			else
				isConnected.setText("username or password is uncorrect!");
	}

	public void test(ActionEvent event) throws SQLException {
		if (loginModel.testUpdate())
			System.out.println("complete");
		else
			System.out.println("error");
	}
}
