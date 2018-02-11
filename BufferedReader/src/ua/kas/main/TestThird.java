package ua.kas.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestThird {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = new String[100];

		System.out.println("Please enter your text:");

		for (int i = 0; i < str.length; i++) {
			str[i] = br.readLine();

			if (str[i].equals("stop")) {
				break;
			}
		}

		System.out.println("\nYout text:\n");

		for (String s : str) {
			System.out.println(s);
			if (s.equals("stop")) {
				break;
			}
		}
	}
}
