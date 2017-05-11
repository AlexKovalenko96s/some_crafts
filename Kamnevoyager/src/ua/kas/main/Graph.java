package ua.kas.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Graph {
	private int count;
	private static int[][] matrix;
	private boolean[] marks;

	public Graph(int count) {
		System.out.println(count);
		this.count = count;
		matrix = new int[count][count];
		marks = new boolean[count];
	}

	public void setEdge(int a, int b, int weight) {
		matrix[a][b] = weight;
		matrix[b][a] = weight;
	}

	public int getEdge(int a, int b) {
		return matrix[a][b];
	}

	public boolean hasEdge(int a, int b) {
		return matrix[a][b] != 0;
	}

	public static Graph load(File file) throws IOException {
		Scanner scn = new Scanner(file);

		Graph graph = new Graph(scn.nextInt());

		while (scn.hasNext()) {
			int a = scn.nextInt();
			int b = scn.nextInt();
			int weight = scn.nextInt();

			System.out.println(a + " " + b + " " + weight);
			graph.setEdge(a, b, weight);
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

		scn.close();
		return graph;
	}

	public static Graph load(String filename) throws IOException {
		return load(new File(filename));
	}

	public boolean enter(int pos) {
		if (marks[pos]) {
			return false;
		} else {
			marks[pos] = true;
			return true;
		}
	}

	public void leave(int pos) {
		marks[pos] = false;
	}

	public int getCount() {
		return count;
	}

	public boolean allVisited() {
		for (int i = 0; i < marks.length; i++) {
			if (!marks[i])
				return false;
		}
		return true;
	}
}
