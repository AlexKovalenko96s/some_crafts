package ua.kas.firstTest;

import java.util.Queue;
import java.util.Stack;

public class Graph {

	private Vertex[] vertexMass;

	private int[][] matrix;

	private int count;

	private Stack<Integer> stack = new Stack<>();

	public Graph(int n) {
		vertexMass = new Vertex[n];
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	public void insertVertex(String key) {
		Vertex v = new Vertex(key);
		vertexMass[count++] = v;
	}

	public void insertEdge(int begin, int end) {
		matrix[begin][end] = 1;
		matrix[end][begin] = 1;
	}

	private int getUnvisitedVertex(int vertex) {
		for (int i = 0; i < count; i++)
			if (matrix[vertex][i] == 1 && vertexMass[i].isVisited() == false)
				return i;
		return -1;
	}

	public void dfs(int v) {
		System.out.print("Выполняем обход в глубину: " + vertexMass[v].getLabel() + " -> ");
		vertexMass[v].setVisited(true);
		stack.push(v);
		while (!stack.isEmpty()) {
			int adjVertex = getUnvisitedVertex((int) stack.peek());
			if (adjVertex == -1)
				stack.pop();
			else {
				vertexMass[adjVertex].setVisited(true);
				System.out.print(vertexMass[adjVertex].getLabel() + " -> ");
				stack.push(adjVertex);
			}
		}

		for (Vertex vertex : vertexMass)
			vertex.setVisited(false);
	}

	public void bfs(int v) {
		System.out.print("Выполняем обход в ширину: " + vertexMass[v].getLabel() + " -> ");
		vertexMass[v].setVisited(true);
		int vertex;
		Queue<Integer> queue = null;
		queue.add(v);
		while (!queue.isEmpty()) {
			int currentVertex = queue.poll();

			while ((vertex = getUnvisitedVertex(currentVertex)) != -1) {
				vertexMass[vertex].setVisited(true);
				System.out.print(vertexMass[vertex].getLabel() + " -> ");
				queue.add(vertex);
			}
		}
	}
}
