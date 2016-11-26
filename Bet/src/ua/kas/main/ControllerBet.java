package ua.kas.main;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerBet {

	@FXML
	TextField money;

	@FXML
	Label event = new Label();
	@FXML
	Label select = new Label();

	ConfBet confBet = new ConfBet();

	public ControllerBet() {
		event.setText(UserController.message);
		System.out.println(UserController.message);
		select.setText(confBet.getS());
	}

	public void confirm(ActionEvent e) {
		if (!money.getText().equals("")) {
			System.out.println("UserID = " + confBet.getIdUser() + " Event = " + confBet.getIdEvent() + " Bet = "
					+ confBet.getSelected() + " Money = " + money.getText());
		} else {
			JOptionPane.showMessageDialog(null, "Enter Money");
		}
	}

	public void back(ActionEvent e) throws IOException {
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Main.fxml")));
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage scene_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene_stage.setScene(scene);
		scene_stage.show();
	}
}
