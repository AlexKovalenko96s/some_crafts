package ua.kas.laba9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class First implements Bla_bla_bla{

	private static String not_correct;
	private static File file = new File("correct");
	static String line = "";
	private static String correct;
	
	@Override
	public void first() {
		System.out.println("Enter string(text)");
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		not_correct = scn.nextLine();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			int i = 0;
			while((line = br.readLine()) != null){
				if(i==0){
					correct = not_correct.replaceAll(line, "***");
					i++;
				}
				else{
					correct = correct.replaceAll(line, "***");
				}
			}
			System.out.println("-------------------------------------");
			System.out.println(correct);
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {} catch (IOException e) {}
	}
}
