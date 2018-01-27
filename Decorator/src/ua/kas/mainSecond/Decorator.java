package ua.kas.mainSecond;

public class Decorator implements SocialNetwork {

	private SocialNetwork socialNetwork;

	public Decorator(SocialNetwork source) {
		this.socialNetwork = source;
	}

	@Override
	public String getMessage() {
		return socialNetwork.getMessage();
	}
}
