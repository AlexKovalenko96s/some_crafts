package ua.kas.secondTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	private static int INF = Integer.MAX_VALUE / 2;

	private int n; // количество вершин в орграфе
	private int m; // количествое дуг в орграфе
	private ArrayList<Integer> adj[]; // список смежности
	private ArrayList<Integer> weight[]; // вес ребра в орграфе
	private boolean used[]; // массив для хранения информации о пройденных и не
							// пройденных вершинах
	private int dist[]; // массив для хранения расстояния от стартовой вершины
	// массив предков, необходимых для восстановления кратчайшего пути из
	// стартовой вершины
	private int pred[];
	int start; // стартовая вершина, от которой ищется расстояние до всех других

	private BufferedReader cin;
	private PrintWriter cout;
	private StringTokenizer tokenizer;

	// процедура запуска алгоритма Дейкстры из стартовой вершины
	private void dejkstra(int s) {
		dist[s] = 0; // кратчайшее расстояние до стартовой вершины равно 0
		for (int iter = 0; iter < n; ++iter) {
			int v = -1;
			int distV = INF;
			// выбираем вершину, кратчайшее расстояние до которого еще не
			// найдено
			for (int i = 0; i < n; ++i) {
				if (used[i]) {
					continue;
				}
				if (distV < dist[i]) {
					continue;
				}
				v = i;
				distV = dist[i];
			}
			// рассматриваем все дуги, исходящие из найденной вершины
			for (int i = 0; i < adj[v].size(); ++i) {
				int u = adj[v].get(i);
				int weightU = weight[v].get(i);
				// релаксация вершины
				if (dist[v] + weightU < dist[u]) {
					dist[u] = dist[v] + weightU;
					pred[u] = v;
				}
			}
			// помечаем вершину v просмотренной, до нее найдено кратчайшее
			// расстояние
			used[v] = true;
		}
	}

	// процедура считывания входных данных с консоли
	private void readData() throws IOException {
		cin = new BufferedReader(new InputStreamReader(System.in));
		cout = new PrintWriter(System.out);
		tokenizer = new StringTokenizer(cin.readLine());

		n = Integer.parseInt(tokenizer.nextToken()); // считываем количество
														// вершин графа
		m = Integer.parseInt(tokenizer.nextToken()); // считываем количество
														// ребер графа
		start = Integer.parseInt(tokenizer.nextToken()) - 1;

		// инициализируем списка смежности графа размерности n
		adj = new ArrayList[n];
		for (int i = 0; i < n; ++i) {
			adj[i] = new ArrayList<Integer>();
		}
		// инициализация списка, в котором хранятся веса ребер
		weight = new ArrayList[n];
		for (int i = 0; i < n; ++i) {
			weight[i] = new ArrayList<Integer>();
		}

		// считываем граф, заданный списком ребер
		for (int i = 0; i < m; ++i) {
			tokenizer = new StringTokenizer(cin.readLine());
			int u = Integer.parseInt(tokenizer.nextToken());
			int v = Integer.parseInt(tokenizer.nextToken());
			int w = Integer.parseInt(tokenizer.nextToken());
			u--;
			v--;
			adj[u].add(v);
			weight[u].add(w);
		}

		used = new boolean[n];
		Arrays.fill(used, false);

		pred = new int[n];
		Arrays.fill(pred, -1);

		dist = new int[n];
		Arrays.fill(dist, INF);

	}

	// процедура восстановления кратчайшего пути по массиву предком
	void printWay(int v) {
		if (v == -1) {
			return;
		}
		printWay(pred[v]);
		cout.print((v + 1) + " ");
	}

	// процедура вывода данных в консоль
	private void printData() throws IOException {
		for (int v = 0; v < n; ++v) {
			if (dist[v] != INF) {
				cout.print(dist[v] + " ");
			} else {
				cout.print("-1 ");
			}
		}
		cout.println();
		for (int v = 0; v < n; ++v) {
			cout.print((v + 1) + ": ");
			if (dist[v] != INF) {
				printWay(v);
			}
			cout.println();
		}

		cin.close();
		cout.close();
	}

	private void run() throws IOException {
		readData();
		dejkstra(start);
		printData();

		cin.close();
		cout.close();
	}

	public static void main(String[] args) throws IOException {
		Solution solution = new Solution();
		solution.run();
	}
}