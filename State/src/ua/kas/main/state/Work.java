package ua.kas.main.state;

public class Work implements Activity {

	@Override
	public void doSomething(Human human) {
		System.out.println("Works!");
		human.setActivity(new Rest());
	}

}
