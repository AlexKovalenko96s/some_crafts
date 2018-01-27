package ua.kas.mainSecond;

public class SourceDecorator implements SocialNetwork {

	@Override
	public String getMessage() {
		return str + "--Start--\n";
	}

}
