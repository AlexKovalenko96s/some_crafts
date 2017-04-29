package ua.kas.firstTest;

public class Vertex {

	private String label;

	private boolean visited = false;

	public Vertex(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
