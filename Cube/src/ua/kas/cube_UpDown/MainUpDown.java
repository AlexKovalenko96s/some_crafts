package ua.kas.cube_UpDown;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainUpDown extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	Timer timer = new Timer(5, this);
	int x = 0, velX = 2;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(30, x, 50, 50);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (x < 0 || x > 230) {
			velX = -velX;
		}

		x += velX;
		repaint();
	}

	public static void main(String[] args) {
		MainUpDown mud = new MainUpDown();
		JFrame f = new JFrame();
		f.setTitle("Try");
		f.setSize(500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.add(mud);
		f.setVisible(true);
	}
}
