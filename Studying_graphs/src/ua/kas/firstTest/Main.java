package ua.kas.firstTest;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph(6);

		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		graph.insertVertex("F");

		graph.insertEdge(0, 1);
		graph.insertEdge(0, 2);
		graph.insertEdge(1, 3);
		graph.insertEdge(2, 4);
		graph.insertEdge(2, 5);

		graph.dfs(0);
		// graph.bfs(0);
	}
}
