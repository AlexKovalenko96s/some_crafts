package ua.kas.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFifth {

	public static void main(String[] args) throws IOException {
		int i;
		FileInputStream fis = null;
		FileOutputStream fos = null;

		System.out.println("Please enter file name!");

		try {
			fis = new FileInputStream("test.txt");
			fos = new FileOutputStream("testOut.txt");
		} catch (Exception ex) {
			System.out.println("File not found!");
			fis.close();
			fos.close();
			return;
		}

		do {
			i = fis.read();

			if (i != -1) {
				fos.write(i);
			}
		} while (i != -1);

		fis.close();
		fos.close();
	}

}
