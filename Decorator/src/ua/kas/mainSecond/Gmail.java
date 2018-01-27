package ua.kas.mainSecond;

public class Gmail extends Decorator {

	public Gmail(SocialNetwork source) {
		super(source);
		System.out.println(source.getMessage());
	}

	@Override
	public String getMessage() {
		return str + "Hello from Google!";
	}

}
