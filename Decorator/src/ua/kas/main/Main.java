package ua.kas.main;

public class Main {

	public static void main(String[] args) {
		Decorator decorator = new FaceBook(new Phone(new StandartNotification()));
		decorator.getMessage();
	}

}
