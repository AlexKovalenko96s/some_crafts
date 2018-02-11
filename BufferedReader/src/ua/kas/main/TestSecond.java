package ua.kas.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSecond {

	public static void main(String[] args) throws IOException {
		String s;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Hello! Please enter text:");

		do {
			s = br.readLine();
			System.out.println(s);
		} while (!s.equals("stop"));

		System.out.println("EXIT");
	}

}
