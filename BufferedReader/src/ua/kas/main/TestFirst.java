package ua.kas.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestFirst {

	public static void main(String[] args) throws IOException {
		char c;

		BufferedReader bfReader = new BufferedReader(new InputStreamReader(System.in));

		do {
			c = (char) bfReader.read();
			System.out.println(c);
		} while (c != 'q');

	}

}
