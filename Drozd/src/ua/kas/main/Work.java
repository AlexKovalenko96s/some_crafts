package ua.kas.main;

import java.util.Scanner;

public class Work {

	private Scanner scn = new Scanner(System.in);

	private int n;
	private int c;
	private int k;

	private int d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;

	public void start() {
		System.out.println("Please enter n = ");

		n = scn.nextInt();
		scn.close();

		k = n - log2(n);
		c = 4 * n - k + 5;

		d1 = (int) Math.round(n / 2.0);
		d2 = (int) Math.round(n / 4.0);
		d3 = (int) Math.round(d2 + (d2 / 3.0) + 1);
		d4 = Math.round(d1 - 4);
		d5 = Math.round(d1 - 3);
		d6 = Math.round(d1 - 2);
		d7 = Math.round(d1 - 1);
		d8 = (int) Math.round((d1 - 1) / 3.0);
		d9 = (int) Math.round((n / 3.0) - 1);
		d10 = (int) Math.round((n / 5.0) * 2.0);

		tablA();
		tablB();
	}

	private void tablA() {
		int[][] tablA = new int[7][d1 + 1];

		// 1
		for (int i = 1; i <= d1; i++) {
			if (i == 1) {
				tablA[5][i] = c;
				tablA[6][i] = c + 1;
			} else {
				tablA[5][i] = tablA[5][i - 1] + 2;
				tablA[6][i] = tablA[6][i - 1] + 2;
			}
		}

		// 2
		for (int i = 1; i <= d2; i++) {
			if (i == 1) {
				tablA[1][i] = 1;
				tablA[2][i] = 2;
				tablA[3][i] = 3;
				tablA[4][i] = 4;
			} else {
				tablA[1][i] = tablA[1][i - 1] + 4;
				tablA[2][i] = tablA[2][i - 1] + 4;
				tablA[3][i] = tablA[3][i - 1] + 4;
				tablA[4][i] = tablA[4][i - 1] + 4;
			}
		}

		// 3
		for (int i = d2 + 1; i <= d3; i++) {
			if (i == d2 + 1) {
				tablA[1][i] = tablA[5][4];
				tablA[2][i] = tablA[6][4];
				tablA[3][i] = tablA[5][5];
				tablA[4][i] = tablA[6][5];
			} else {
				tablA[1][i] = tablA[1][i - 1] + 4;
				tablA[2][i] = tablA[2][i - 1] + 4;
				tablA[3][i] = tablA[3][i - 1] + 4;
				tablA[4][i] = tablA[4][i - 1] + 4;
			}
		}

		// 4
		tablA[3][d4] = tablA[5][1];
		tablA[4][d4] = tablA[6][1];

		// 5
		tablA[1][d5] = tablA[5][2];
		tablA[2][d5] = tablA[6][2];
		tablA[3][d5] = tablA[5][3];
		tablA[4][d5] = tablA[6][3];

		tablA[1][d6] = tablA[5][d4];
		tablA[2][d6] = tablA[6][d4];
		tablA[3][d6] = tablA[5][d5];
		tablA[4][d6] = tablA[6][d5];

		// 6
		tablA[1][d7] = tablA[5][d6];
		tablA[2][d7] = tablA[6][d6];

		// 7
		tablA[1][d1] = n + 1;
		tablA[2][d1] = n + 2;

		// 8
		tablA[3][d7] = tablA[5][d3];
		tablA[4][d7] = tablA[6][d3];

		// 9
		tablA[3][d1] = tablA[5][d7];
		tablA[4][d1] = tablA[6][d7];

		if (n == 36) {
			tablA[1][d4] = tablA[5][d3 - 1];
			tablA[2][d4] = tablA[6][d3 - 1];
		} else {
			tablA[1][d3 + 1] = tablA[5][d3 - 2];
			tablA[2][d3 + 1] = tablA[6][d3 - 2];
			tablA[3][d3 + 1] = tablA[5][d3];
			tablA[4][d3 + 1] = tablA[6][d3];

			tablA[1][d4 - 1] = tablA[5][d3 - 1];
			tablA[2][d4 - 1] = tablA[6][d3 - 1];

			tablA[3][d4 - 1] = n;
			tablA[4][d4 - 1] = 0;

			tablA[1][d4] = tablA[5][d4 - 1];
			tablA[2][d4] = tablA[6][d4 - 1];
		}

		print(tablA);
	}

	private void tablB() {
		int[][] tablB = new int[7][d1 + 1];

		// 1
		for (int i = 1; i <= d1; i++) {
			if (i == 1) {
				tablB[5][i] = c + (d1 * 2);
				tablB[6][i] = c + (d1 * 2) + 1;
			} else {
				tablB[5][i] = tablB[5][i - 1] + 2;
				tablB[6][i] = tablB[6][i - 1] + 2;
			}
		}

		// 2
		for (int i = 1; i <= d8; i++) {
			if (i == 1) {
				tablB[1][i] = n + 2 + 1;
				tablB[2][i] = n + 2 + 2;
				tablB[3][i] = n + 2 + 3;
				tablB[4][i] = n + 2 + 4;
			} else {
				tablB[1][i] = tablB[1][i - 1] + 4;
				tablB[2][i] = tablB[2][i - 1] + 4;
				tablB[3][i] = tablB[3][i - 1] + 4;
				tablB[4][i] = tablB[4][i - 1] + 4;
			}
		}

		// 3
		for (int i = d8 + 1; i <= d9; i++) {
			if (i == d8 + 1) {
				tablB[1][i] = n + 2 + (d8 * 4) + 1;
				tablB[2][i] = n + 2 + (d8 * 4) + 2;
			} else {
				tablB[1][i] = tablB[1][i - 1] + 2;
				tablB[2][i] = tablB[2][i - 1] + 2;
			}
		}

		// 4
		tablB[3][d8 + 1] = tablB[5][d8 - 1];
		tablB[4][d8 + 1] = tablB[6][d8 - 1];

		// 5
		for (int i = d8 + 2; i <= d9; i++) {
			if (i == d8 + 2) {
				tablB[3][i] = tablB[5][d8 + 1];
				tablB[4][i] = tablB[6][d8 + 1];
			} else {
				tablB[3][i] = tablB[3][i - 1] + 2;
				tablB[4][i] = tablB[4][i - 1] + 2;
			}
		}

		// 6
		int j = 0;
		for (int i = d9 + 1; i <= d10; i++) {
			j++;
			if (i == d9 + 1) {
				tablB[1][i] = tablB[5][2];
				tablB[2][i] = tablB[6][2];
				tablB[3][i] = tablB[5][1];
				tablB[4][i] = tablB[6][1];
			} else {
				tablB[1][i] = tablB[5][j + 1];
				tablB[2][i] = tablB[6][j + 1];
				tablB[3][i] = tablB[5][d9 + j - 1];
				tablB[4][i] = tablB[6][d9 + j - 1];
			}
		}

		// 7
		tablB[3][d10 + 1] = n * 2;
		tablB[4][d10 + 1] = n * 2 + 1;

		// 8
		tablB[1][d10 + 1] = tablB[5][d8];
		tablB[2][d10 + 1] = tablB[6][d8];

		// 9
		tablB[1][d6] = tablB[5][d6 - 1];
		tablB[2][d6] = tablB[6][d6 - 1];
		tablB[1][d7] = tablB[5][d6];
		tablB[2][d7] = tablB[6][d6];

		// 10
		tablB[1][d1] = n * 2 + 2 + 1;
		tablB[2][d1] = n * 2 + 2 + 2;

		// 11
		for (int i = d6; i <= d1; i++) {
			if (i == d6) {
				tablB[3][i] = tablB[5][d9];
				tablB[4][i] = tablB[6][d9];
			} else {
				tablB[3][i] = tablB[3][i - 1] + (d10 - (d9 + 1)) * 2 + 2;
				tablB[4][i] = tablB[4][i - 1] + (d10 - (d9 + 1)) * 2 + 2;
			}
		}

		if (n == 45) {
			tablB[1][d5] = n * 2 + 2;
			tablB[2][d5] = 0;
			tablB[3][d5] = tablB[5][d4];
			tablB[4][d5] = tablB[6][d4];
		}

		print(tablB);
	}

	private void print(int[][] mass) {
		for (int i = 0; i < mass.length; i++) {
			for (int j = 0; j < mass[i].length; j++) {
				if (i == 0 && j != 0) {
					System.out.print("|" + j + " ");
				} else if (i != 0 && j == 0) {
					System.out.print(i + "| ");
				} else {
					System.out.print(mass[i][j] + " ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	// public static double log2(double num)
	// {
	// return (Math.log(num)/Math.log(2));
	// }

	public static int log2(int x) {
		int y, v;
		if (x <= 0) {
			throw new IllegalArgumentException("" + x + " <= 0");
		}
		v = x;
		y = -1;
		while (v > 0) {
			v >>= 1;
			y++;
		}
		return y;
	}
}
