package ua.kas.works;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class TSPNearestNeighbour {
	private int numberOfNodes;
	private Stack<Integer> stack;

	public TSPNearestNeighbour() {
		stack = new Stack<Integer>();
	}

	public void tsp(int adjacencyMatrix[][]) {
		numberOfNodes = adjacencyMatrix[1].length - 1;
		int[] visited = new int[numberOfNodes + 1];
		visited[1] = 1;
		stack.push(1);
		int element, dst = 0, i;
		int min = Integer.MAX_VALUE;
		boolean minFlag = false;
		System.out.print(1 + "\t");

		while (!stack.isEmpty()) {
			element = stack.peek();
			i = 1;
			min = Integer.MAX_VALUE;
			while (i <= numberOfNodes) {
				if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {
					if (min > adjacencyMatrix[element][i]) {
						min = adjacencyMatrix[element][i];
						dst = i;
						minFlag = true;
					}
				}
				i++;
			}
			if (minFlag) {
				visited[dst] = 1;
				stack.push(dst);
				System.out.print(dst + "\t");
				minFlag = false;
				continue;
			}
			stack.pop();
		}
	}

	public static void main(String... arg) throws IOException {
		int number_of_nodes;
		Scanner scanner = null;
		try {
			System.out.println("Enter the number of nodes in the graph");
			scanner = new Scanner(System.in);
			number_of_nodes = scanner.nextInt();
			int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];

			// for excel enter
			InputStream in = new FileInputStream("test.xls");
			HSSFWorkbook wb = new HSSFWorkbook(in);

			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> it = sheet.iterator();
			int x, y, weight;
			while (it.hasNext()) {
				Row row = it.next();

				x = (int) row.getCell(0).getNumericCellValue() + 1;
				y = (int) row.getCell(1).getNumericCellValue() + 1;
				weight = (int) row.getCell(2).getNumericCellValue();

				adjacency_matrix[x][y] = weight;
				adjacency_matrix[y][x] = weight;
			}
			wb.close();

			// // for console enter
			//
			// System.out.println("Enter the adjacency matrix");
			// for (int i = 1; i <= number_of_nodes; i++) {
			// for (int j = 1; j <= number_of_nodes; j++) {
			// adjacency_matrix[i][j] = scanner.nextInt();
			// }
			// }

			// // system out
			//
			// for (int i = 0; i < adjacency_matrix.length; i++) {
			// for (int j = 0; j < adjacency_matrix[i].length; j++) {
			// System.out.print(adjacency_matrix[i][j] + " ");
			// }
			// System.out.println();
			// }

			for (int i = 1; i <= number_of_nodes; i++) {
				for (int j = 1; j <= number_of_nodes; j++) {
					if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0) {
						adjacency_matrix[j][i] = 1;
					}
				}
			}
			System.out.println("the citys are visited as follows");
			TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
			tspNearestNeighbour.tsp(adjacency_matrix);
		} catch (InputMismatchException inputMismatch) {
			System.out.println("Wrong Input format");
		}
		scanner.close();
	}
}
