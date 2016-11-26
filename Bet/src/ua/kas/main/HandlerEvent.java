package ua.kas.main;

import java.util.LinkedList;

public class HandlerEvent {

	private LinkedList<EventConstructor> list = new LinkedList<EventConstructor>();

	public HandlerEvent() {
		addEvent();
	}

	private void addEvent() {
		// add all events
		list.add(new Event("LIV", "CHE", 1));
		list.add(new Event("MUN", "MCI", 2));
		list.add(new Event("TOT", "WHE", 3));
		list.add(new Event("BOR", "LES", 4));
		list.add(new Event("ARS", "STO", 5));

	}

	public LinkedList<EventConstructor> getList() {
		return list;
	}
}
