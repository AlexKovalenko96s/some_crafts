package ua.kas.laba9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller{
	
	@FXML TextArea ta;
	@FXML Button correct_btn;
	static String line = "";
	
	First first = new First();
	Second second = new Second();
	Third third = new Third();
	
	public void first_btn(ActionEvent e){
		first.first();
	}

	public void second_btn(ActionEvent e){
		second.first();
		ta.setText(Second.not_correct);
	}
	
	public void third_btn(ActionEvent e){
		third.first();
	}
	
	public void correct_btn(ActionEvent e){
		second.correct = ta.getText();
		System.out.println("Admin check you text: "+ second.correct);
		ta.setText("");
	}
}