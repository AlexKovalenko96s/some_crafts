package ua.kas.main;

public class Anything<Type> {

	private Type type;

	Anything(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void show() {
		System.out.println(type.getClass().getName());
	}
}
