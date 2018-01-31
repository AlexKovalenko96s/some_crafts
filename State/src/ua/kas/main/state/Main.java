package ua.kas.main.state;

public class Main {

	public static void main(String[] args) {
		Human human = new Human();
		human.setActivity(new Work());

		for (int i = 0; i < 10; i++) {
			human.doSomething(human);
		}
	}

}
