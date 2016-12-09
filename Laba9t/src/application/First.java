package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class First implements Iterf{

	static String City,Data,city,data,t;
	static String line = "";
	static int T;
	private static File file = new File("pogoga");
	
	@Override
	public void interf() {
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter odessa or kiev");
		City = scn.nextLine();
		System.out.println("Enter 05.06.16 or 06.06.16");
		Data = scn.nextLine();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);		
			while((line = br.readLine()) != null){
				city=line.substring(0, line.indexOf(" "));
				line=line.substring(line.indexOf(" ")+1);
				data=line.substring(0, line.indexOf(" "));
				t=line.substring(line.indexOf(" ")+1);

				if(City.equals(city)){
					if(Data.equals(data)){
						T=Integer.parseInt(t);
						System.out.println(T+" - temp.");
					}
				}
			}
		
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {} catch (IOException e) {}
		
	}
		

}
