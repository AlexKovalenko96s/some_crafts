package ua.kas.threads_test3;

public class Main {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName() + " " + i);
				}
			}
		});
		t1.start();
	}
}