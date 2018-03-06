package ua.kas.main;

import jade.core.Agent;

public class FirstAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();

		System.out.println("Hello Jade!");
		System.out.println("I`m the first agent");
		doDelete();
	}

}
