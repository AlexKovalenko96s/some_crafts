package ua.kas.main;

import java.io.IOException;

public class Main {
	public static void main(String[] Args) throws IOException {
		Runtime r = Runtime.getRuntime();
		Process proc = null;

		ProcessBuilder pb = new ProcessBuilder("notepad.exe", "test");
		pb.start();

		try {
			proc = r.exec("Notepad");
			proc.waitFor();
			System.out.println(new StringBuffer(proc.getInputStream().toString()));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(proc.exitValue());

	}
}
