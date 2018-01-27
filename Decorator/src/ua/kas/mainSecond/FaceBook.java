package ua.kas.mainSecond;

public class FaceBook extends Decorator {

	public FaceBook(SocialNetwork source) {
		super(source);
		System.out.println(source.getMessage());
	}

	@Override
	public String getMessage() {
		return str + "Hello from FaceBook!";
	}

}
