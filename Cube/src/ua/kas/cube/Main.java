package ua.kas.cube;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Main {

	static int x_cube, y_cube;
	static JFrame f = new JFrame();
	static StartGraphicsPanel sgp = new StartGraphicsPanel(100, 100);

	public static void main(String[] args) {
		f.setTitle("Cube");
		f.setSize(500, 300);
		f.setLayout(new GridBagLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);

		f.add(sgp, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));

		f.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					try {
						for (int i = 0; i < 5; i++, f.repaint(), Thread.sleep(100)) {
							sgp.y_cube -= 10;
							System.out.println("ddd");
						}
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					sgp.x_cube -= 10;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					sgp.x_cube += 10;
				}
				f.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					try {
						for (int i = 0; i < 5; i++, f.repaint(), Thread.sleep(100)) {
							sgp.y_cube -= 10;
							System.out.println("ddd");
						}
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					sgp.x_cube -= 10;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					sgp.x_cube += 10;
				}
				f.repaint();
			}
		});
		f.setVisible(true);
	}

}