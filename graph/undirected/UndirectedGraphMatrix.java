package map.graph.undirected;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;
import java.util.ArrayList;

import main.Main;
import map.Node;
import map.graph.Graph;
/**
 * 
 * creates a undirected graph witch is represented by a adjacency matrix
 * 
 * @author Josef Weldemariam, Matr. 7316168
 * 
 */
public class UndirectedGraphMatrix extends Graph<Integer> implements IUndirectedGraphMatrix {
	private int[][] a;
	ArrayList<Node> nodes = new ArrayList<Node>();

	/**
	 * calls the constructor of the super class with no_Edge=0 and initialize the
	 * adjacency matrix with no_Edge. then the capacities and the coordinates for
	 * every node are read in. Initialized the Graph always with one node more because 
	 * 0 is always a isolated node
	 * 
	 * @param n number of nodes in the graph
	 */
	public UndirectedGraphMatrix(int n) {
		super(n, 0);
		a = new int[n][n];
		
		InputStream is = Main.class.getResourceAsStream("pipeCostsMatrix.txt");
		
		try {

			// Einlesen der Rohrkapazitaeten und Flussrichtungen 
			InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			String s = reader.readLine();
			
			
			for (int i = 1; i < n; i++) {
				s = reader.readLine();
				String[] y = s.split(";");
				for (int j = 1; j < n; j++) {
					setWeight(i,j,Integer.parseInt(y[j]));
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
/*		setWeight(12,13,33);
		setWeight(13,14,50);
		setWeight(13,18,5);
		setWeight(14,15,10);
		setWeight(15,19,32);
		setWeight(17,18,20);
		setWeight(17,22,6);
		setWeight(18,21,19);
		setWeight(19,21,24);
		setWeight(19,24,21);
		setWeight(21,23,16);
		setWeight(22,23,9);
		setWeight(22,30,12);
		setWeight(23,24,26);
		setWeight(23,31,3);
		setWeight(30,34,13);
		setWeight(31,34,40);*/
		
	}

	@Override
	public Integer getWeight(int i, int j) {
		
		return a[i][j];
	}

	@Override
	public void setWeight(int i, int j, Integer b) {
		a[i][j] = b;
		a[j][i] = b;

	}
	
	public int[][] getGraph(){
		return a;
	}
	/**
	 * writes the costs for the pipes and the 
	 */
	public void writeFile() {

		try {
			//Schreiben der Rohrkosten 
			FileWriter fw = new FileWriter("pipeCostsMatrix.txt");
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
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
