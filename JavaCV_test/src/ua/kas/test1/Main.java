package ua.kas.test1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedImage img = ImageIO.read(new File("test.jpg"));
		IplImage image = IplImage.createFrom(img);

		// IplImage image = cvLoadImage("test.jpg");

		CanvasFrame canvas = new CanvasFrame("Demo");
		canvas.showImage(image);
		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}
}
