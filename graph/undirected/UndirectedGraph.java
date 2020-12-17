package map.graph.undirected;

import java.util.*;

import main.Main;
import map.Grid;
import map.graph.undirected.Edge;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 
 * creates a graph witch is represented by a adjacency list
 * 
 * @author Josef Weldemariam, Matr. 7316168
 * 
 */
public class UndirectedGraph {

	int n; // number of vertices
	ArrayList<Vertex> adjazenzList = new ArrayList<Vertex>();
	int[][] a; // adjazenz matrix fuer die addPipeCosts methode in Grid
				// da besserer Zugriff auf die Gewichte als aus Liste
	
	

	/**
	 * Constructor for construct the adjazenList out of the pipe Costs Matrix
	 * 
	 * @param x placeholder
	 */
	public UndirectedGraph(double x) {
		ArrayList<Vertex> allVertex = new ArrayList<Vertex>();
		UndirectedGraphMatrix udgm = Grid.udgm;
		InputStream input = Main.class.getResourceAsStream("allVertex.txt");

		try {
			// Einlesen aller Vertexes mit Koordinaten
			InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			//liest die erste zeile aus und legt den Vertex an
			String s = reader.readLine();
			String[] z = s.split(";");	
			Vertex first = new Vertex(z[0], Integer.parseInt(z[1]), Integer.parseInt(z[2]));
			allVertex.add(first);
			
			for (int i = 1; i < udgm.getSize() - 1; i++) {
				s = reader.readLine();
				String[] z1 = s.split(";");
				Vertex other = new Vertex(z1[0], Integer.parseInt(z1[1]), Integer.parseInt(z1[2]));
				allVertex.add(other);
				
			}
			//speichert alle vorkommenden Knoten in adjazenzList
			for (int i = 1; i < udgm.getSize(); i++) {
				for (int j = 1; j < udgm.getSize(); j++) {
					if (udgm.isEdge(i, j) && !adjazenzList.contains(allVertex.get(i-1))) {
						adjazenzList.add(allVertex.get(i-1));	
					}
				}
			}
			
			//speichert fuer alle Knoten in adjazenzList die Nachbarn 
			for (int i = 1; i < udgm.getSize(); i++) {
				for (int j = 1; j < udgm.getSize(); j++) {
					if (udgm.isEdge(i, j)) {
						//lokalisiere den nachbarn eines Knotens in adjazenzList
						String name1 = allVertex.get(j-1).getName();
						int index1 = 0;
						for(int k=0; k<adjazenzList.size(); k++) {
							if(adjazenzList.get(k).getName().equals(name1)){
								index1 = k;
							}
						}
						Edge e = new Edge(adjazenzList.get(index1),udgm.getWeight(i, j));
						//lokalisiere den nachbarn eines Knotens in adjazenzList
						String name2 = allVertex.get(i-1).getName();
						int index2 = 0;
						for(int k=0; k<adjazenzList.size(); k++) {
							if(adjazenzList.get(k).getName().equals(name2)) {
								index2 = k;
							}
						}
						
						adjazenzList.get(index2).addNeighbour(e);	
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor for MST in Prim.java
	 */
	public UndirectedGraph() {

	}

	/**
	 * the pipe, in case of a street field also the speed limit and the directions
	 * for every for every field are read in.
	 * 
	 * @param n
	 * @param x
	 */
	public UndirectedGraph(int n) {
		this.n = n;
		InputStream input = Main.class.getResourceAsStream("pipeCosts.txt");
		InputStream inputN = Main.class.getResourceAsStream("pipeCosts.txt");
		InputStream input1 = Main.class.getResourceAsStream("pipeCostsVertex.txt");

		try {

			// Einlesen der Rohrkosten und der Koordinaten der beteiligten knoten
			InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
			InputStreamReader isr1 = new InputStreamReader(input1, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			BufferedReader reader1 = new BufferedReader(isr1);
			// liest die erste Zeile aus
			String s = reader.readLine();

			String[] z = s.split(";");
			// fuegt den ersten Knoten in pipeCosts.txt hinzu
			Vertex n1 = new Vertex(z[0]);
			adjazenzList.add(n1);
			// fuegt alle weiteren Knoten hinzu
			for (int i = 1; i < n; i++) {
				s = reader.readLine();
				String[] y = s.split(";");

				// damit ein Knoten nicht zweimal zur adjazenzlist hinzugefuegt wird
				if (y[0].equals(adjazenzList.get(i - 1).getName())) {
					i = i - 1;
				} else {
					Vertex neu = new Vertex(y[0]);
					adjazenzList.add(neu);
				}
			}
			reader.close();

			// lies die pipeCosts.txt erneut ein um die Nachbarn zu setzen
			InputStreamReader isrN = new InputStreamReader(inputN, StandardCharsets.UTF_8);
			BufferedReader readerN = new BufferedReader(isrN);
			s = readerN.readLine();
			String[] zN = s.split(";");
			// Sucht in adjazenzList nach dem ersten Nachbarn des ersten Knoten
			for (int j = 0; j < adjazenzList.size(); j++) {
				if (adjazenzList.get(j).getName().equals(zN[1])) {
					Edge e = new Edge(adjazenzList.get(j), Integer.parseInt(zN[2]));
					adjazenzList.get(0).addNeighbour(e);
				}
			}
			// 2*n+6 enspricht der Anzahl gerichteter Kanten im ungerichteten Graphen
			for (int i = 1; i < 2 * n + 6; i++) {
				s = readerN.readLine();
				String[] zN1 = s.split(";");
				// prueft ob der naechste ausgelesene Knoten mit einem Knoten in der
				// AdjazenzListe uebereinstimmt
				for (int k = 1; k < adjazenzList.size(); k++) {
					if (zN1[0].equals(adjazenzList.get(k).getName())) {
						for (int j = 0; j < adjazenzList.size(); j++) {
							if (adjazenzList.get(j).getName().equals(zN1[1])) {
								Edge e = new Edge(adjazenzList.get(j), Integer.parseInt(zN1[2]));
								adjazenzList.get(k).addNeighbour(e);

							}
						}
					}
				}

			}

			/*
			 * String[] z = s.split(";"); String[] z1 = s1.split(";"); Vertex anf = new
			 * Vertex(z[0], Integer.parseInt(z1[1]), Integer.parseInt(z1[2]));
			 * adjazenzList.add(anf); Vertex end = new Vertex(z[1]); Edge e = new Edge(end,
			 * Integer.parseInt(z[2])); adjazenzList.get(0).addNeighbour(e);
			 * 
			 * for (int i = 1; i < n; i++) { s = reader.readLine(); String[] y =
			 * s.split(";");
			 * 
			 * // damit ein Knoten nicht zweimal zur adjazenzlist hinzugefuegt wird if
			 * (y[0].equals(adjazenzList.get(i - 1).getName())) { Vertex end1 = new
			 * Vertex(y[1]); Edge e1 = new Edge(end1, Integer.parseInt(y[2]));
			 * adjazenzList.get(i - 1).addNeighbour(e1); i = i - 1;
			 * 
			 * } else { Vertex anf1 = new Vertex(y[0]); adjazenzList.add(anf1); Vertex end1
			 * = new Vertex(y[1]); Edge e1 = new Edge(end1, Integer.parseInt(y[2]));
			 * adjazenzList.get(i).addNeighbour(e1); } }
			 */
			// Liest die Koordinaten fuer die Elemente in adjazenzList ein
			String s1 = reader1.readLine();
			String[] z1 = s1.split(";");
			adjazenzList.get(0).setRowIndex(Integer.parseInt(z1[1]));
			adjazenzList.get(0).setColIndex(Integer.parseInt(z1[2]));

			for (int i = 1; i < n; i++) {
				s1 = reader1.readLine();
				String[] y1 = s1.split(";");
				adjazenzList.get(i).setRowIndex(Integer.parseInt(y1[1]));
				adjazenzList.get(i).setColIndex(Integer.parseInt(y1[2]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * first constructor: implements the graph
	 * 
	 * @param n number of nodes
	 * @param s place holder
	 */
	public UndirectedGraph(int n, String s) {

		Vertex n12 = new Vertex("x12", 27, 13);
		Vertex n13 = new Vertex("x13", 19, 13);
		Vertex n14 = new Vertex("x14", 13, 13);
		Vertex n15 = new Vertex("x15", 7, 13);
		Vertex n17 = new Vertex("x17", 25, 19);
		Vertex n18 = new Vertex("x18", 19, 19);
		Vertex n19 = new Vertex("x19", 7, 19);
		Vertex n21 = new Vertex("x21", 15, 19);
		Vertex n22 = new Vertex("x22", 25, 25);
		Vertex n23 = new Vertex("x23", 15, 25);
		Vertex n24 = new Vertex("x24", 7, 25);
		Vertex n30 = new Vertex("x30", 25, 33);
		Vertex n31 = new Vertex("x31", 15, 33);
		Vertex n34 = new Vertex("x34", 21, 33);

		// Ausgehende kanten aus knoten 12
		Edge e1 = new Edge(n13, 33); // nachbar von knoten 12 , vorwaerts kankte
		n12.addNeighbour(e1);

		// Ausgehende kanten aus knoten 13
		Edge e2 = new Edge(n12, 33); // nachbar von knoten 13, rueckwaerts kante
		Edge e3 = new Edge(n14, 50);
		Edge e4 = new Edge(n18, 5);
		n13.addNeighbour(e2);
		n13.addNeighbour(e3);
		n13.addNeighbour(e4);

		// Ausgehende kanten aus knoten 14
		Edge e5 = new Edge(n13, 50);
		Edge e6 = new Edge(n15, 10);
		n14.addNeighbour(e5);
		n14.addNeighbour(e6);

		// Ausgehende Kandten aus Knoten 15
		Edge e7 = new Edge(n14, 10);
		Edge e8 = new Edge(n19, 32);
		n15.addNeighbour(e7);
		n15.addNeighbour(e8);

		// Ausgehende Kanten aus Knoten 17
		Edge e9 = new Edge(n22, 6);
		Edge e10 = new Edge(n18, 20);
		n17.addNeighbour(e9);
		n17.addNeighbour(e10);

		// Ausgehende Kanten aus Knoten 18
		Edge e11 = new Edge(n13, 5);
		Edge e12 = new Edge(n17, 20);
		Edge e13 = new Edge(n21, 19);
		n18.addNeighbour(e11);
		n18.addNeighbour(e12);
		n18.addNeighbour(e13);

		// Ausgehende Kanten aus Knoten 19
		Edge e14 = new Edge(n15, 32);
		Edge e15 = new Edge(n21, 24);
		Edge e16 = new Edge(n24, 21);
		n19.addNeighbour(e14);
		n19.addNeighbour(e15);
		n19.addNeighbour(e16);

		// Ausgehende Kanten aus Knoten 21
		Edge e17 = new Edge(n19, 24);
		Edge e18 = new Edge(n18, 19);
		Edge e19 = new Edge(n23, 16);
		n21.addNeighbour(e17);
		n21.addNeighbour(e18);
		n21.addNeighbour(e19);

		// Ausgehende Kanten aus Knoten 22
		Edge e20 = new Edge(n17, 6);
		Edge e21 = new Edge(n23, 9);
		Edge e22 = new Edge(n30, 12);
		n22.addNeighbour(e20);
		n22.addNeighbour(e21);
		n22.addNeighbour(e22);

		// Ausgehende Kanten aus Knoten 23
		Edge e23 = new Edge(n21, 16);
		Edge e24 = new Edge(n22, 9);
		Edge e25 = new Edge(n24, 26);
		Edge e26 = new Edge(n31, 3);
		n23.addNeighbour(e23);
		n23.addNeighbour(e24);
		n23.addNeighbour(e25);
		n23.addNeighbour(e26);

		// Ausgehende Kanten aus Knoten 24
		Edge e27 = new Edge(n19, 21);
		Edge e28 = new Edge(n23, 26);
		n24.addNeighbour(e27);
		n24.addNeighbour(e28);

		// Ausgehende Kanten aus Knoten 30
		Edge e29 = new Edge(n22, 12);
		Edge e30 = new Edge(n34, 13);
		n30.addNeighbour(e29);
		n24.addNeighbour(e30);

		// Ausghende Kanten aus Knoten 31
		Edge e31 = new Edge(n23, 3);
		Edge e32 = new Edge(n34, 40);
		n31.addNeighbour(e31);
		n31.addNeighbour(e32);

		// Ausgehende Kanten aus Knoten 34
		Edge e33 = new Edge(n30, 13);
		Edge e34 = new Edge(n31, 40);
		n34.addNeighbour(e33);
		n34.addNeighbour(e34);

		adjazenzList.add(n12);
		adjazenzList.add(n13);
		adjazenzList.add(n14);
		adjazenzList.add(n15);
		adjazenzList.add(n17);
		adjazenzList.add(n18);
		adjazenzList.add(n19);
		adjazenzList.add(n21);
		adjazenzList.add(n22);
		adjazenzList.add(n23);
		adjazenzList.add(n24);
		adjazenzList.add(n30);
		adjazenzList.add(n31);
		adjazenzList.add(n34);

		// a = new int[adjazenzList.g][]

	}

	/**
	 * returns the number of vertexes in the grah
	 * 
	 * @return number of vertexes
	 */
	public int getSize() {
		return adjazenzList.size();
	}

	/**
	 * returns the adjacency list representation of the graph
	 * 
	 * @return adjacency list of graph
	 */
	public ArrayList<Vertex> getGraph() {
		return this.adjazenzList;
	}

	/**
	 * returns the vertex in adjazenzList with the same name e.g x1, x2 or x14
	 * 
	 * @param i index for a specific vertex
	 * @return vertex at position i
	 */
	public Vertex getVertex(Vertex n) {
		for (int i = 0; i < adjazenzList.size(); i++) {
			if (adjazenzList.get(i).getName().equals(n.getName())) {
				return adjazenzList.get(i);
			}
		}
		return null;
	}

	/**
	 * adds a node of type Vertex to adjazenzList
	 * 
	 * @param n node of type Vertex
	 */
	public void setVertex(Vertex n) {
		PriorityQueueElement pqe = new PriorityQueueElement(n, Integer.MAX_VALUE);
		n.setPriorityQueueElement(pqe);
		adjazenzList.add(n);
		this.n++;
	}

	/**
	 * writes the text file for pipe costs and for the coordinates of the nodes
	 */
	public void write() {

		try {
			// Schreiben der Rohrkosten
			FileWriter writer = new FileWriter("pipeCosts.txt");
			BufferedWriter bwriter = new BufferedWriter(writer);
			for (int i = 0; i < adjazenzList.size(); i++) {
				for (int j = 0; j < adjazenzList.get(i).getNeighbours().size(); j++) {
					// Gibt den Startknoten, dann den Endknoten und dann das Gewicht aus
					bwriter.write(adjazenzList.get(i).getName() + ";"
							+ adjazenzList.get(i).getNeighbours().get(j).getEndNode().getName() + ";"
							+ adjazenzList.get(i).getNeighbours().get(j).getWeight());
					bwriter.newLine();
				}
			}
			bwriter.close();

			FileWriter writer1 = new FileWriter("pipeCostsVertex.txt");
			BufferedWriter bwriter1 = new BufferedWriter(writer1);
			for (int i = 0; i < adjazenzList.size(); i++) {

				// Gibt den Startknoten, dann den Endknoten und dann das Gewicht aus
				bwriter1.write(adjazenzList.get(i).getName() + ";" + adjazenzList.get(i).getRowIndex() + ";"
						+ adjazenzList.get(i).getColIndex());
				bwriter1.newLine();

			}
			bwriter1.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
