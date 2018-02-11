package ua.kas.main;

import java.applet.Applet;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class AppletFirst extends Applet {

	@Override
	public void paint(Graphics g) {
		g.drawString("Hello world!", 20, 20);
		super.paint(g);
	}
}
