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
		System.out.println("first start");
		first.first();
		System.out.println("first finish");
	}

	public void second_btn(ActionEvent e){
		System.out.println("second");
		second.first();
	}
	
	public void third_btn(ActionEvent e){
		System.out.println("third start");
		third.first();
		System.out.println("third finish");
	}
}