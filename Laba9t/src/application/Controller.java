package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
	
	@FXML TextArea ta;
	First first = new First();
	Second second = new Second();
	Third third = new Third();
	
	public void first_btn(ActionEvent e){
		System.out.println("first");
		first.interf();
	}

	public void second_btn(ActionEvent e){
		System.out.println("second");
		second.interf();

	}
	
	public void third_btn(ActionEvent e){
		System.out.println("third");
		third.interf();
	}
}
