package ua.kas.XO;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Knopki extends JFrame {

	int hod = 1;

	JLabel l0 = new JLabel("Now, FIRST player !");

	public JButton b1 = new JButton("     ");
	public JButton b2 = new JButton("     ");
	public JButton b3 = new JButton("     ");
	public JButton b4 = new JButton("     ");
	public JButton b5 = new JButton("     ");
	public JButton b6 = new JButton("     ");
	public JButton b7 = new JButton("     ");
	public JButton b8 = new JButton("     ");
	public JButton b9 = new JButton("     ");
	JButton reset = new JButton("Reset");

	public Knopki(String s) {
		super(s);
		setLayout(new FlowLayout());

		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		add(b8);
		add(b9);
		add(l0);
		add(reset);

	}

	public void knopki() {

		// JOptionPane.showMessageDialog(null, "Hi, come on play 'Tic-tac-toe'.
		// ");

		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == reset) {
					b1.setText("     ");
					b2.setText("     ");
					b3.setText("     ");
					b4.setText("     ");
					b5.setText("     ");
					b6.setText("     ");
					b7.setText("     ");
					b8.setText("     ");
					b9.setText("     ");
					b1.setForeground(Color.BLACK);
					b2.setForeground(Color.BLACK);
					b3.setForeground(Color.BLACK);
					b4.setForeground(Color.BLACK);
					b5.setForeground(Color.BLACK);
					b6.setForeground(Color.BLACK);
					b7.setForeground(Color.BLACK);
					b8.setForeground(Color.BLACK);
					b9.setForeground(Color.BLACK);
					b1.setBackground(null);
					b2.setBackground(null);
					b3.setBackground(null);
					b4.setBackground(null);
					b5.setBackground(null);
					b6.setBackground(null);
					b7.setBackground(null);
					b8.setBackground(null);
					b9.setBackground(null);
					hod = 1;
				}
			}
		});

		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b1 && hod == 1 || hod == 3 || hod == 5 || hod == 7) {
					b1.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b1 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b1.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b1 && hod == 9) {
					b1.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b2 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b2.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b2 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b2.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b2 && hod == 9) {
					b2.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b3 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b3.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b3 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b3.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b3 && hod == 9) {
					b3.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b4 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b4.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b4 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b4.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b4 && hod == 9) {
					b4.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b5 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b5.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b5 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b5.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b5 && hod == 9) {
					b5.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b6 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b6.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b6 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b6.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b6 && hod == 9) {
					b6.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b7.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b7 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b7.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b7 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b7.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b7 && hod == 9) {
					b7.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b8.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b8 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b8.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b8 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b8.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b8 && hod == 9) {
					b8.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});

		b9.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == b9 && hod == 1 || hod == 3 || hod == 5 || hod == 7 || hod == 9) {
					b9.setText(" X ");
					l0.setText("Now, SECOND player !");
					proverkaNeChet();
				}
				if (e.getSource() == b9 && hod == 2 || hod == 4 || hod == 6 || hod == 8) {
					b9.setText(" O ");
					l0.setText("Now, FIRST player !");
					proverkaChet();
				}
				if (e.getSource() == b9 && hod == 9) {
					b9.setText(" X ");
					l0.setText("END !");
					proverkaNeChet();
				}
				hod++;
			}
		});
	}

	public void proverkaChet() {
		if (b1.getText() == " O " && b2.getText() == " O " && b3.getText() == " O ") {
			b1.setBackground(Color.BLUE);
			b2.setBackground(Color.BLUE);
			b3.setBackground(Color.BLUE);
			b1.setForeground(Color.WHITE);
			b2.setForeground(Color.WHITE);
			b3.setForeground(Color.WHITE);

		}
		if (b4.getText() == " O " && b5.getText() == " O " && b6.getText() == " O ") {
			b4.setBackground(Color.BLUE);
			b5.setBackground(Color.BLUE);
			b6.setBackground(Color.BLUE);
			b4.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b6.setForeground(Color.WHITE);
		}
		if (b7.getText() == " O " && b8.getText() == " O " && b9.getText() == " O ") {
			b7.setBackground(Color.BLUE);
			b8.setBackground(Color.BLUE);
			b9.setBackground(Color.BLUE);
			b7.setForeground(Color.WHITE);
			b8.setForeground(Color.WHITE);
			b9.setForeground(Color.WHITE);
		}
		if (b1.getText() == " O " && b3.getText() == " O " && b7.getText() == " O ") {
			b1.setBackground(Color.BLUE);
			b3.setBackground(Color.BLUE);
			b7.setBackground(Color.BLUE);
			b1.setForeground(Color.WHITE);
			b3.setForeground(Color.WHITE);
			b7.setForeground(Color.WHITE);
		}
		if (b2.getText() == " O " && b5.getText() == " O " && b8.getText() == " O ") {
			b2.setBackground(Color.BLUE);
			b5.setBackground(Color.BLUE);
			b8.setBackground(Color.BLUE);
			b2.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b8.setForeground(Color.WHITE);
		}
		if (b3.getText() == " O " && b6.getText() == " O " && b9.getText() == " O ") {
			b3.setBackground(Color.BLUE);
			b6.setBackground(Color.BLUE);
			b9.setBackground(Color.BLUE);
			b3.setForeground(Color.WHITE);
			b6.setForeground(Color.WHITE);
			b9.setForeground(Color.WHITE);
		}
		if (b1.getText() == " O " && b5.getText() == " O " && b9.getText() == " O ") {
			b1.setBackground(Color.BLUE);
			b5.setBackground(Color.BLUE);
			b9.setBackground(Color.BLUE);
			b1.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b9.setForeground(Color.WHITE);
		}
		if (b3.getText() == " O " && b5.getText() == " O " && b7.getText() == " O ") {
			b3.setBackground(Color.BLUE);
			b5.setBackground(Color.BLUE);
			b7.setBackground(Color.BLUE);
			b3.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b7.setForeground(Color.WHITE);
		}
	}

	public void proverkaNeChet() {
		if (b1.getText() == " X " && b2.getText() == " X " && b3.getText() == " X ") {
			b1.setBackground(Color.RED);
			b2.setBackground(Color.RED);
			b3.setBackground(Color.RED);
			b1.setForeground(Color.WHITE);
			b2.setForeground(Color.WHITE);
			b3.setForeground(Color.WHITE);

		}
		if (b4.getText() == " X " && b5.getText() == " X " && b6.getText() == " X ") {
			b4.setBackground(Color.RED);
			b5.setBackground(Color.RED);
			b6.setBackground(Color.RED);
			b4.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b6.setForeground(Color.WHITE);
		}
		if (b7.getText() == " X " && b8.getText() == " X " && b9.getText() == " X ") {
			b7.setBackground(Color.RED);
			b8.setBackground(Color.RED);
			b9.setBackground(Color.RED);
			b7.setForeground(Color.WHITE);
			b8.setForeground(Color.WHITE);
			b9.setForeground(Color.WHITE);
		}
		if (b1.getText() == " X " && b3.getText() == " X " && b7.getText() == " X ") {
			b1.setBackground(Color.RED);
			b3.setBackground(Color.RED);
			b7.setBackground(Color.RED);
			b1.setForeground(Color.WHITE);
			b3.setForeground(Color.WHITE);
			b7.setForeground(Color.WHITE);
		}
		if (b2.getText() == " X " && b5.getText() == " X " && b8.getText() == " X ") {
			b2.setBackground(Color.RED);
			b5.setBackground(Color.RED);
			b8.setBackground(Color.RED);
			b2.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b8.setForeground(Color.WHITE);
		}
		if (b3.getText() == " X " && b6.getText() == " X " && b9.getText() == " X ") {
			b3.setBackground(Color.RED);
			b6.setBackground(Color.RED);
			b9.setBackground(Color.RED);
			b3.setForeground(Color.WHITE);
			b6.setForeground(Color.WHITE);
			b9.setForeground(Color.WHITE);
		}
		if (b1.getText() == " X " && b5.getText() == " X " && b9.getText() == " X ") {
			b1.setBackground(Color.RED);
			b5.setBackground(Color.RED);
			b9.setBackground(Color.RED);
			b1.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b9.setForeground(Color.WHITE);
		}
		if (b3.getText() == " X " && b5.getText() == " X " && b7.getText() == " X ") {
			b3.setBackground(Color.RED);
			b5.setBackground(Color.RED);
			b7.setBackground(Color.RED);
			b3.setForeground(Color.WHITE);
			b5.setForeground(Color.WHITE);
			b7.setForeground(Color.WHITE);
		}

	}
}
