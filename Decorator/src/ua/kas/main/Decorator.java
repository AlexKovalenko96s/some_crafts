package ua.kas.main;

public class Decorator implements Notification {

	private Notification notification;

	public Decorator(Notification source) {
		this.notification = source;
	}

	@Override
	public void getMessage() {
		notification.getMessage();
	}

}
