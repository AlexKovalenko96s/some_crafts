package ua.kas.XO;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Knopki k = new Knopki("Tic-tac-toe");
		
		
		k.setVisible(true);
		k.setSize(180,180);
		k.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		k.setResizable(false);
		k.setLocationRelativeTo(null);
		k.setLayout(new FlowLayout());
		
		k.knopki();
	}
}
