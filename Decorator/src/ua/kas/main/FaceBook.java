package ua.kas.main;

public class FaceBook extends Decorator {

	public FaceBook(Notification source) {
		super(source);
		source.getMessage();
	}

	@Override
	public void getMessage() {
		System.out.println("Hello from FaceBook!");
	}
}
