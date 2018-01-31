package ua.kas.main.state;

public class Human implements Activity {

	private Activity act;

	public void setActivity(Activity act) {
		this.act = act;
	}

	@Override
	public void doSomething(Human human) {
		act.doSomething(this);
	}
}
