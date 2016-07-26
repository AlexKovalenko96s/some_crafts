package ua.kas.cube;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class StartGraphicsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	int x_cube, y_cube;

	public StartGraphicsPanel(int x_cube, int y_cube) {
		super();
		this.x_cube = x_cube;
		this.y_cube = y_cube;
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.fillRect(x_cube, y_cube, 50, 50);
		g2.setColor(Color.GREEN);
		g2.fillRect(0, 150, 500, 150);
	}
}