package ua.kas.threads_test2;

public class Main {

	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadConstr());
		Thread t2 = new Thread(new ThreadConstr());
		t1.start();
		t2.start();
	}
}