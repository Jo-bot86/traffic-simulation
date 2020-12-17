package map.graph.directed;

import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import main.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import map.Node;
import map.graph.Graph;
/**
 * 
 * creates a graph witch is represented by a adjacency matrix
 * @author Josef Weldemariam, Matr. 7316168
 * source: http://www.inf.fh-flensburg.de/lang/algorithmen/graph/graph-implementierung.htm
 * 
 */
public class DirectedGraph extends Graph<Integer> implements IDirectedGraph {

	private int[][] a;
	ArrayList<Node> nodes = new ArrayList<Node>();
	/**
	 * calls the constructor of the super class with no_Edge=0
	 * and initialize the adjacency matrix with no_Edge.
	 * then the capacities and the coordinates for every node are read in
	 * @param n number of nodes in the graph
	 */
	public DirectedGraph(int n) {
		super(n, 0);
		a = new int[n][n];
		//initialize();
		//Einleseroutine
		InputStream input = Main.class.getResourceAsStream("pipeCapacity.txt");
		InputStream input1 = Main.class.getResourceAsStream("node.txt");
		try {

			// Einlesen der Rohrkapazitaeten und Flussrichtungen 
			InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			String s = reader.readLine();
			
			
			for (int i = 1; i < n; i++) {
				s = reader.readLine();
				String[] y = s.split(";");
				for (int j = 1; j < n; j++) {
					setWeight(i,j,Integer.parseInt(y[j]));
				}
			}
			
			//einlesen der Nodes
			InputStreamReader isr1 = new InputStreamReader(input1, StandardCharsets.UTF_8);
			BufferedReader reader1 = new BufferedReader(isr1);	
			
			// n-1 entspricht der wirklichen Anzahl Knoten bei 36 Knoten wird der Graph mit 37 initialisiert
			for(int i=0; i<n-1; i++) {
				String s1 = reader1.readLine();
				String[] x = s1.split(";");
				Node newNode = new Node(Integer.parseInt(x[0]), Integer.parseInt(x[1]));
				nodes.add(newNode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	/*	
		  addEdge(1, 2, 99); addEdge(2, 8, 99); addEdge(8, 10, 99); addEdge(10, 6, 99);
		  addEdge(6, 14, 99);
		  
		  addEdge(14, 15, 15); addEdge(15, 19, 8); addEdge(19, 24, 5); addEdge(24, 25,
		  8); addEdge(25, 33, 50); addEdge(33, 28, 11); addEdge(28, 36, 10);
		  addEdge(36, 34, 12); addEdge(34, 30, 18); addEdge(30, 29, 20); addEdge(29,
		  35, 20);
		  
		  addEdge(24, 27, 15); addEdge(27, 26, 16); addEdge(26, 32, 9); addEdge(32, 33,
		  9); addEdge(32, 31, 4); addEdge(31, 34, 7);
		  
		  addEdge(14, 13, 13); addEdge(13, 18, 12); addEdge(18, 21, 6); addEdge(21, 23,
		  17); addEdge(23, 31, 11);
		 
		*/
		
	/*	// erstellt die Kreuzungsknoten
				Node x_1 = new Node(13, 3);
				Node x_2 = new Node(7, 3);
				Node x_3 = new Node(23, 3);
				Node x_4 = new Node(27, 3);
				Node x_5 = new Node(23, 5);
				Node x_6 = new Node(13, 9);
				Node x_7 = new Node(19, 5);
				Node x_8 = new Node(7, 6);
				Node x_9 = new Node(5, 6);
				Node x_10 = new Node(7, 9);
				Node x_11 = new Node(3, 9);
				Node x_12 = new Node(27, 13);
				Node x_13 = new Node(19, 13);
				Node x_14 = new Node(13, 13);
				Node x_15 = new Node(7, 13);
				Node x_16 = new Node(3, 15);
				Node x_17 = new Node(25, 19);
				Node x_18 = new Node(19, 19);
				Node x_19 = new Node(7, 19);
				Node x_20 = new Node(3, 19);
				Node x_21 = new Node(15, 19);
				Node x_22 = new Node(25, 25);
				Node x_23 = new Node(15, 25);
				Node x_24 = new Node(7, 25);
				Node x_25 = new Node(3, 25);
				Node x_26 = new Node(11, 29);
				Node x_27 = new Node(7, 29);
				Node x_28 = new Node(3, 37);
				Node x_29 = new Node(28, 33);
				Node x_30 = new Node(25, 33);
				Node x_31 = new Node(15, 33);
				Node x_32 = new Node(11, 33);
				Node x_33 = new Node(3, 33);
				Node x_34 = new Node(21, 33);
				Node x_35 = new Node(28, 37);
				Node x_36 = new Node(21, 37);
				
				nodes.add(x_1);
				nodes.add(x_2);
				nodes.add(x_3);
				nodes.add(x_4);
				nodes.add(x_5);
				nodes.add(x_6);
				nodes.add(x_7);
				nodes.add(x_8);
				nodes.add(x_9);
				nodes.add(x_10);
				nodes.add(x_11);
				nodes.add(x_12);
				nodes.add(x_13);
				nodes.add(x_14);
				nodes.add(x_15);
				nodes.add(x_16);
				nodes.add(x_17);
				nodes.add(x_18);
				nodes.add(x_19);
				nodes.add(x_20);
				nodes.add(x_21);
				nodes.add(x_22);
				nodes.add(x_23);
				nodes.add(x_24);
				nodes.add(x_25);
				nodes.add(x_26);
				nodes.add(x_27);
				nodes.add(x_28);
				nodes.add(x_29);
				nodes.add(x_30);
				nodes.add(x_31);
				nodes.add(x_32);
				nodes.add(x_33);
				nodes.add(x_34);
				nodes.add(x_35);
				nodes.add(x_36);   */


	}
	
	/**
	 * returns all nodes in a array list
	 * @return a list with all nodes
	 */
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	
	@Override
	public Integer getWeight(int i, int j) {
		return a[i][j];
	}

	@Override
	public void setWeight(int i, int j, Integer b) {
		a[i][j] = b;
	}
	/**
	 * representation of a graph by a adjacency matrix
	 * @return adjacency matrix of a graph
	 */
	public int[][] getGraph(){
		return a;
	}


	/**
	 * writes the capacity for the pipes and the coordinates for the nodes
	 */
	public void writeFile() {

		try {
			//Schreiben der Rohrkapazitaeten
			FileWriter fw = new FileWriter("pipeCapacity.txt");
			BufferedWriter bfw = new BufferedWriter(fw);
		//	bfw.write(String.valueOf(this.a.length));

		//	bfw.newLine();
			for (int i = 0; i < this.a.length; i++) {
				for (int j = 0; j < this.a.length; j++) {
					if (i == 0) {
						if (j < 10) {
							bfw.write(0 + "" + 0 + "" + String.valueOf(j) + ";");
						} else
							bfw.write(0 + "" + String.valueOf(j) + ";");
					} else {
						if (this.a[i][j] < 10) {
							bfw.write(0 + "" + "" + 0 + String.valueOf(a[i][j]) + ";");
						} else if (this.a[i][j] < 100) {
							bfw.write(0 + "" + String.valueOf(a[i][j]) + ";");
						} else
							bfw.write(String.valueOf(a[i][j]) + ";");
					}
				}
				bfw.newLine();
			}
			bfw.close();
			
			//Schreiben der Nodekoordinaten
			FileWriter fw1 = new FileWriter("node.txt");
			BufferedWriter bfw1 = new BufferedWriter(fw1);
			for(int i=0; i<nodes.size();i++) {
				bfw1.write(nodes.get(i).toDataString());
				bfw1.newLine();
			}
			bfw1.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
