package ua.kas.mainSecond;

public class Client {

	public static void main(String[] args) {
		Decorator decorator = new FaceBook(new Gmail(new SourceDecorator()));
		System.out.println(decorator.getMessage());
	}
}
