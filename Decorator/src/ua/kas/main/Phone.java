package ua.kas.main;

public class Phone extends Decorator {

	public Phone(Notification source) {
		super(source);
		source.getMessage();
	}

	@Override
	public void getMessage() {
		System.out.println("Hello from Phone!");
	}
}