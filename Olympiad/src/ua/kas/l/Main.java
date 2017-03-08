package ua.kas.l;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static ArrayList<Integer> wList = new ArrayList<>();
	private static ArrayList<Integer> mList = new ArrayList<>();

	private static int count = 0;

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String dn = scn.nextLine();
		String w = scn.nextLine();
		String m = scn.nextLine();
		scn.close();

		int d = Integer.parseInt(dn.substring(0, dn.indexOf(" ")));
		int n = Integer.parseInt(dn.substring(dn.indexOf(" ") + 1));

		for (int i = 0; i < n; i++) {

			try {
				wList.add(Integer.parseInt(w.substring(0, w.indexOf(" "))));
			} catch (Exception e) {
				wList.add(Integer.parseInt(w));
			}

			try {
				mList.add(Integer.parseInt(m.substring(0, m.indexOf(" "))));
			} catch (Exception e) {
				mList.add(Integer.parseInt(m));
			}

			w = w.substring(w.indexOf(" ") + 1);
			m = m.substring(m.indexOf(" ") + 1);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				for (int k = 0; k < n; k++) {
					if (wList.get(i) + j == mList.get(k)) {
						count++;
						wList.remove(i);
						mList.remove(k);

						break;
					}
				}
			}
		}

		System.out.println(count);
	}

}
