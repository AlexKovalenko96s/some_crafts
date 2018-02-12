package ua.kas.main;

public class RealizationOfAnything {

	public static void main(String[] args) {

		Anything<Integer> anyInt = new Anything<Integer>(69);
		Anything<String> anyStr = new Anything<String>("Hello world!");

		System.out.println("INT:");

		anyInt.show();
		int i = anyInt.getType();
		System.out.println(i);

		System.out.println();
		System.out.println("STRING:");

		anyStr.show();
		String s = anyStr.getType();
		System.out.println(s);
	}

}
