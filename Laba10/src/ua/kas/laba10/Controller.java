package ua.kas.laba10;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

	@FXML Label out;
	@FXML TextField enter_engl;
	@FXML TextField enter_rus;
	@FXML TextField translate;
	String engl;
	String rus;
	Map<String, String> map = new HashMap<String, String>();
	
	public void add(ActionEvent e){
		
		engl = enter_engl.getText();
		rus = enter_rus.getText();
		map.put(rus, engl);
		enter_engl.setText("");
		enter_rus.setText("");	
	}
	
	public void translate(ActionEvent e){
		Map<String, String> map_sorted = new HashMap<String, String>(map);
		out.setText(map_sorted.get(translate.getText()));	
	}
	
	public void output(ActionEvent e){
		Map<String, String> map_sorted = new HashMap<String, String>(map);
		System.out.println(map_sorted);
	}
	
	public void del(ActionEvent e){
		map.remove(enter_rus.getText());
//		map.remove(enter_rus.getText(), enter_engl.getText());
	}
}
