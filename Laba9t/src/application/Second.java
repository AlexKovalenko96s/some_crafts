package application;

import java.util.Random;
import java.util.Scanner;

public class Second implements Iterf{

	static String City,t;
	Random r = new Random();
	static int T;
	
	@Override
	public void interf() {
		
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter city");
		City = scn.nextLine();
		System.out.println("Enter tempr");
		t = scn.nextLine();
		//T=First.T;
		T=Integer.parseInt(t)+r.nextInt(5);
		
		System.out.println("Today in "+City+" "+T+"C");
	}
}
