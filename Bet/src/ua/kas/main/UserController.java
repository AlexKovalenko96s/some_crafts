package ua.kas.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController implements Initializable {

	@FXML
	TextField login;
	@FXML
	TextField password;

	@FXML
	ListView<String> list = new ListView<String>();

	@FXML
	Label match = new Label();

	@FXML
	CheckBox cbV;
	@FXML
	CheckBox cbH;
	@FXML
	CheckBox cbD;

	ConfBet confBet = new ConfBet();

	HandlerEvent handler = new HandlerEvent();

	ObservableList<String> event;

	ArrayList<Integer> selected = new ArrayList<Integer>();

	static String message;

	private int id;
	private int idUsers;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		seeAll();
	}

	public void checkIn(ActionEvent e) throws IOException {
		if (login.getText().equals("login") && password.getText().equals("password")) {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Main.fxml")));
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage scene_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene_stage.setScene(scene);
			scene_stage.show();
			idUsers = 666;
		} else {
			JOptionPane.showMessageDialog(null, "Enter correct login and password!");
		}
	}

	private void seeAll() {
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		for (int i = 0; i < handler.getList().size(); i++) {
			list.getItems().add(handler.getList().get(i).getHome() + " vs " + handler.getList().get(i).getVisitors());
		}
	}

	public void work() {
		event = list.getSelectionModel().getSelectedItems();
		for (String m : event) {
			message = m;
			for (int i = 0; i < handler.getList().size(); i++) {
				if (handler.getList().get(i).getHome().equals(message.substring(0, message.indexOf(" vs"))) && handler
						.getList().get(i).getVisitors().equals(message.substring(message.indexOf("vs ") + 3))) {
					match.setText(message);
					id = handler.getList().get(i).getId();
				}
			}
		}
	}

	public void bet(ActionEvent e) throws IOException {
		if (cbH.isSelected()) {
			selected.add(1);
		}
		if (cbV.isSelected()) {
			selected.add(2);
		}
		if (cbD.isSelected()) {
			selected.add(3);
		}

		if (selected.size() == 0) {
			JOptionPane.showMessageDialog(null, "SelectBet!");
		} else {
			System.out.println(message);
			confBet.setIdEvent(id);
			confBet.setIdUser(idUsers);
			confBet.setMatch(message);
			confBet.setSelected(selected);
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Bet.fxml")));
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage scene_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene_stage.setScene(scene);
			scene_stage.show();

		}
	}

}
