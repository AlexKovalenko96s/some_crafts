package ua.kas.laba9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller implements Bla_bla_bla{
	
	@FXML TextArea ta;
	@FXML Button correct_btn;
	private String give_me_money;
	private static File file = new File("correct");
	static String line = "";
	private static String correct;
	private static String not_correct;	
	
	public void first_btn(ActionEvent e){
		first();
	}

	public void second_btn(ActionEvent e){
		second();
		ta.setText(not_correct);
	}
	
	public void third_btn(ActionEvent e){
		third("Здесь могла быть ваша реклама.");
		System.out.println(give_me_money);
	}
	
	public void correct_btn(ActionEvent e){
		correct = ta.getText();
		System.out.println("Admin check you text: "+ correct);
		ta.setText("");
	}
	
	@Override
	public void first() {
		System.out.println("Enter string(text)");
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		not_correct = scn.nextLine();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			int i = 0;
			while((line = br.readLine()) != null){
				if(i==0){
					correct = not_correct.replaceAll(line, "***");
					i++;
				}
				else{
					correct = correct.replaceAll(line, "***");
				}
			}
			System.out.println("__________________________________________");
			System.out.println(correct);
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {} catch (IOException e) {}
	}
	
	@Override
	public void second() {
		System.out.println("Enter string(text)");
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		not_correct = scn.nextLine();
		System.out.println(not_correct);
		
		ta.setText(not_correct);
	}
	
	@Override
	public void third(String s) {
		this.give_me_money = s;
	}
}