package ua.kas.test2;

import javax.swing.JFrame;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

public class Main {

	public static void main(String[] args) {

		CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);
		opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 720);
		opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 1280);

		IplImage grabbedImage = opencv_highgui.cvQueryFrame(capture);
		CanvasFrame frame = new CanvasFrame("WebCam");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (frame.isVisible() && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) != null) {
			frame.showImage(grabbedImage);
		}

	}

}
