package ua.kas.main.context;

public class Main {

	public static void main(String[] args) {
		Radio radio = new Radio();
		radio.setStation(new Station1());

		for (int i = 0; i < 10; i++) {
			radio.play();
			radio.nextStation();
		}
	}
}