package ua.kas.basicAnimation;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	private static int x = 0;
	private static int y = 0;

	public static final String NAME = "Basic Animation";

	private boolean running = false;
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;

	public static Sprite hero;

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;

		init();

		while (running) {

			delta += (System.nanoTime() - lastTime) / ns;
			lastTime = System.nanoTime();

			if (delta > 1) {
				update();
			}

			render();
		}
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void init() {
		addKeyListener(new KeyInputHandler());
		hero = getSprite("res/hero.png");
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		hero.draw(g, x, y);
		g.dispose();

		bs.show();
	}

	public void update() {
		if (up && y >= 0) {
			y--;
		}
		if (down && y <= HEIGHT - hero.getHeight()) {
			y++;
		}
		if (left && x >= 0) {
			x--;
		}
		if (right && x <= WIDTH - hero.getWidth()) {
			x++;
		}
	}

	public Sprite getSprite(String path) {

		BufferedImage sourseImage = null;

		try {
			sourseImage = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourseImage.getSource()));

		return sprite;
	}

	public static void main(String[] args) {

		Main main = new Main();

		main.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(main, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		main.start();
	}

	private class KeyInputHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				right = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				right = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// not used
		}
	}
}
