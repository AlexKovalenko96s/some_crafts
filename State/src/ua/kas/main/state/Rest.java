package ua.kas.main.state;

public class Rest implements Activity {

	private int count = 0;

	@Override
	public void doSomething(Human human) {

		if (count < 3) {
			System.out.println("...Rest...zzz...");
			count++;
		} else {
			human.setActivity(new Work());
			human.doSomething(human);
		}
	}

}
