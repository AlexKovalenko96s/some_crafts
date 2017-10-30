package ua.kas.dictionary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

	@FXML
	TextField tf;
	@FXML
	TextArea ta;
	@FXML
	ListView<String> lv;

	Server s = new Server();

	public void go(ActionEvent event) throws IOException {
		Threads.sendDate(tf.getText());
		String sub = s.line.substring(s.line.indexOf("-") + 1);
		ta.setText(sub);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new Thread(new Threads()).start();
		new Thread(new Server()).start();
	}
}
