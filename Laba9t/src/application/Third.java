package application;

import java.util.Random;

public class Third implements Iterf{
	
	Random r = new Random();
	
	@Override
	public void interf() {
		System.out.println("t= "+r.nextInt(20));
	}
}
