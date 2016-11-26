package ua.kas.main;

public class Event extends EventConstructor {

	public Event(String home, String visitors, int id) {
		this.home = home;
		this.visitors = visitors;
		this.id = id;
	}

	@Override
	public String getHome() {
		return home;
	}

	@Override
	public String getVisitors() {
		return visitors;
	}

	@Override
	public int getId() {
		return id;
	}
}
