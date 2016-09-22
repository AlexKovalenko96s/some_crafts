package ua.kas.threads_test;

public class Main {

	public static void main(String[] args) {
		ThreadConstr threadConstr1 = new ThreadConstr();
		threadConstr1.start();
		ThreadConstr threadConstr2 = new ThreadConstr();
		threadConstr2.start();
	}
}