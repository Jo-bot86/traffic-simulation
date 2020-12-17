package map;

import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import static map.Direction.*;
import java.util.*;

import main.Main;
import map.field.Field;
import map.field.fieldtype.*;
import map.graph.undirected.*;
//import map.graph.undirected.Vertex;
import map.graph.directed.DirectedGraph;

/**
 * representing the map witch is a 2 dimensional Array of type Field. implements
 * IGrid witch include methods for manipulating and transform the map e.g
 * setSpeedLimit() witch allow to set the speed limit for a certain part of a
 * street
 * 
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class Grid implements IGrid {

	private Field[][] grid;
	private int rowDimension;
	private int colDimension;
	public static UndirectedGraphMatrix udgm = new UndirectedGraphMatrix(37);
	public static DirectedGraph dg = new DirectedGraph(37);
	int[][] speedLimits ;

	// Konstruktor fuer Testumgebung
	/**
	 * test constructor: initialize grid as a 2-dimensional array of type Field with
	 * the value 2 witch represents the class tree
	 * 
	 * @param dim1 row dimension of grid
	 * @param dim2 column dimension of grid
	 * @param x    placeholder
	 */
	public Grid(int dim1, int dim2, double x) {
		grid = new Field[dim1][dim2];
		for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
			for (int colIndex = 0; colIndex < grid[0].length; colIndex++) {
				// initialisiert alle Felder als Baum Felder
				grid[rowIndex][colIndex] = new Field(rowIndex + 1, colIndex + 1, 2);
			}
		}
		this.rowDimension = dim1;
		this.colDimension = dim2;
	}

	/**
	 * constructor for the big map. initialize the small grid four times. 
	 * In left button part without pipes and two on the right sight without buildings and pipes
	 * @param dim1 row dimension of the small grid
	 * @param dim2 column dimension of the small grid
	 * @param x    placeholder
	 */
	public Grid(int dim1, int dim2, String x) {
		grid = new Field[2 * dim1][2 * dim2];
		// initialisiert die grosse map
		for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
			for (int colIndex = 0; colIndex < grid[0].length; colIndex++) {
				// initialisiert alle Felder als Baum Felder
				grid[rowIndex][colIndex] = new Field(rowIndex + 1, colIndex + 1, 2);
			}
		}
		// initialisiert die alte map im linken oberen viertel der map
		Grid smallGrid = new Grid(dim1, dim2);
		for (int i = 1; i <= 30; i++) {
			for (int j = 1; j <= 40; j++) {
				if (smallGrid.getField(i, j).getValue() == 1) {
					// Abfrage fuer Rahim, sollen keine Richtungen aus der map fuehren
					if (i == 7 && j == 1) {
						Street newStreetField = new Street(i, j);
						newStreetField.setSpeedLimit(((Street) smallGrid.getField(i, j)).getSpeedLimit());
						newStreetField.setDirection(South);
						newStreetField.setPipeCapacity(((Street) smallGrid.getField(i, j)).getPipeCapacity());
						newStreetField.setFlowDirection(((Street) smallGrid.getField(i, j)).getFlowDirection());
						newStreetField.setPipeCosts(((Street) smallGrid.getField(i, j)).getPipeCosts());
						grid[i - 1][j - 1] = newStreetField;
					} else {
						if (i == 1 & j == 16) {
							Street newStreetField = new Street(i, j);
							newStreetField.setSpeedLimit(((Street) smallGrid.getField(i, j)).getSpeedLimit());
							newStreetField.setDirection(West);
							newStreetField.setPipeCapacity(((Street) smallGrid.getField(i, j)).getPipeCapacity());
							newStreetField.setFlowDirection(((Street) smallGrid.getField(i, j)).getFlowDirection());
							newStreetField.setPipeCosts(((Street) smallGrid.getField(i, j)).getPipeCosts());
							grid[i - 1][j - 1] = newStreetField;
						} else {
							if (i == 1 && j == 34) {
								Street newStreetField = new Street(i, j);
								newStreetField.setSpeedLimit(((Street) smallGrid.getField(i, j)).getSpeedLimit());
								newStreetField.setDirection(West);
								newStreetField.setPipeCapacity(((Street) smallGrid.getField(i, j)).getPipeCapacity());
								newStreetField.setFlowDirection(((Street) smallGrid.getField(i, j)).getFlowDirection());
								newStreetField.setPipeCosts(((Street) smallGrid.getField(i, j)).getPipeCosts());
								grid[i - 1][j - 1] = newStreetField;

							} else {
								Street newStreetField = new Street(i, j);
								newStreetField.setSpeedLimit(((Street) smallGrid.getField(i, j)).getSpeedLimit());
								newStreetField.setDirectionAll(((Street) smallGrid.getField(i, j)).getDirection());
								newStreetField.setPipeCapacity(((Street) smallGrid.getField(i, j)).getPipeCapacity());
								newStreetField.setFlowDirection(((Street) smallGrid.getField(i, j)).getFlowDirection());
								newStreetField.setPipeCosts(((Street) smallGrid.getField(i, j)).getPipeCosts());
								grid[i - 1][j - 1] = newStreetField;
							}
						}
					}
				} else {
					grid[i - 1][j - 1] = smallGrid.getField(i, j);
				}
			}
		}
		/*
		 * // Berichtigung der Richtungen am Rand der grossen map ((Street)
		 * bigGrid[6][0]).deleteDirection(); ((Street)
		 * bigGrid[6][0]).setDirection(Direction.South); ((Street)
		 * bigGrid[0][33]).deleteDirection(); ((Street)
		 * bigGrid[0][33]).setDirection(Direction.West);
		 */

		// initialisiert das linke untere viertel der map
		for (int i = 31; i <= 60; i++) {
			for (int j = 1; j <= 40; j++) {
				if (smallGrid.getField(i - 30, j).getValue() == 1) {
					if (i == 37 && j == 1) {
						Street s = new Street(i, j);
						s.setDirection(South);
						s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j)).getSpeedLimit());
						grid[i - 1][j - 1] = s;
					} else {
						if (i == 60 && j == 15) {
							Street s = new Street(i, j);
							s.setDirection(East);
							s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j)).getSpeedLimit());
							grid[i - 1][j - 1] = s;
						} else {
							if (i == 60 && j == 33) {
								Street s = new Street(i, j);
								s.setDirection(East);
								s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j)).getSpeedLimit());
								grid[i - 1][j - 1] = s;
							} else {
								Street s = new Street(i, j);
								s.setDirectionAll(((Street) smallGrid.getField(i - 30, j)).getDirection());
								s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j)).getSpeedLimit());
								grid[i - 1][j - 1] = s;
							}
						}
					}
				}
				grid[i - 1][j - 1].setValue(smallGrid.getField(i - 30, j).getValue());
			}
		}
		// Berichtigung der Richtungen am Rand der grossen map
		// ((Street) bigGrid[36][0]).deleteDirection();
		// ((Street) bigGrid[36][0]).setDirection(Direction.South);

		// initialisiert das rechte obere viertel der map
		for (int i = 1; i <= 30; i++) {
			for (int j = 41; j <= 80; j++) {
				if (smallGrid.getField(i, j - 40).getValue() == 1) {
					if (i == 1 && j == 56) {
						Street s = new Street(i, j);
						s.setDirection(West);
						s.setSpeedLimit(((Street) smallGrid.getField(i, j - 40)).getSpeedLimit());
						grid[i - 1][j - 1] = s;
					} else {
						if (i == 1 && j == 74) {
							Street s = new Street(i, j);
							s.setDirection(West);
							s.setSpeedLimit(((Street) smallGrid.getField(i, j - 40)).getSpeedLimit());
							grid[i - 1][j - 1] = s;
						} else {
							if (i == 8 && j == 80) {
								Street s = new Street(i, j);
								s.setDirection(North);
								s.setSpeedLimit(((Street) smallGrid.getField(i, j - 40)).getSpeedLimit());
								grid[i - 1][j - 1] = s;
							} else {
								Street s = new Street(i, j);
								s.setDirectionAll(((Street) smallGrid.getField(i, j - 40)).getDirection());
								s.setSpeedLimit(((Street) smallGrid.getField(i, j - 40)).getSpeedLimit());
								grid[i - 1][j - 1] = s;
							}
						}
					}
				}
			}
		}
		// Berichtigung der Richtungen am Rand der grossen map
		// ((Street) bigGrid[7][79]).deleteDirection();
		// ((Street) bigGrid[7][79]).setDirection(Direction.North);

		// initialisiert das rechte untere viertel der map
		for (int i = 31; i <= 60; i++) {
			for (int j = 41; j <= 80; j++) {
				if (smallGrid.getField(i - 30, j - 40).getValue() == 1) {
					if (i == 60 && j == 55) {
						Street s = new Street(i, j);
						s.setDirection(East);
						s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j - 40)).getSpeedLimit());
						grid[i - 1][j - 1] = s;
					} else {
						if (i == 60 && j == 33) {
							Street s = new Street(i, j);
							s.setDirection(East);
							s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j - 40)).getSpeedLimit());
							grid[i - 1][j - 1] = s;
						} else {
							if (i == 38 && j == 80) {
								Street s = new Street(i, j);
								s.setDirection(North);
								s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j - 40)).getSpeedLimit());
								grid[i - 1][j - 1] = s;
							} else {
								if (i == 60 && j == 73) {
									Street s = new Street(i, j);
									s.setDirection(East);
									s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j - 40)).getSpeedLimit());
									grid[i - 1][j - 1] = s;
								} else {
									Street s = new Street(i, j);
									s.setDirectionAll(((Street) smallGrid.getField(i - 30, j - 40)).getDirection());
									s.setSpeedLimit(((Street) smallGrid.getField(i - 30, j - 40)).getSpeedLimit());
									grid[i - 1][j - 1] = s;
								}
							}
						}
					}
				}
			}
		}
		// Hospital h = new Hospital(21,29);
		// addFieldMS2(h);
		// System.out.println(((Street)grid[6][14]).getPipeCosts());
		// Grid.udgm.setWeight(15, 19, 12);
		//readSpeedLimits();
		
		speedLimits = new int[grid.length][grid[0].length];
		readSpeedLimits();
		
	}

	/**
	 * main constructor: initialize grid as a 2-dimensional array of type Field with
	 * the value 2 witch represents the class tree then the field id, in case of a
	 * street field also the speed limit and the directions for every for every
	 * field are read in. At the end we called addPipeCapacity
	 * 
	 * @param dim1 row dimension of grid
	 * @param dim2 column dimension of grid
	 */
	public Grid(int dim1, int dim2) {
		grid = new Field[dim1][dim2];
		for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
			for (int colIndex = 0; colIndex < grid[0].length; colIndex++) {
				// initialisiert alle Felder als Baum Felder
				grid[rowIndex][colIndex] = new Field(rowIndex + 1, colIndex + 1, 2);
			}
		}
		this.rowDimension = dim1;
		this.colDimension = dim2;
		InputStream input = Main.class.getResourceAsStream("map.txt");
		InputStream input1 = Main.class.getResourceAsStream("mapSpeed.txt");
		InputStream input2 = Main.class.getResourceAsStream("mapDirection.txt");
		// Read the InputStream in UTF-8 and use a BufferedReader to read lines
		try {
			// Einlesen der FeldIDs
			InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			String s = reader.readLine();
			// String[] x = s.split(";");

			// Read new lines until readLine() returns null
			for (int i = 0; i < grid.length; i++) {
				s = reader.readLine();
				String[] y = s.split(";");
				for (int j = 0; j < grid[0].length; j++) {
					grid[i][j].setValue(Integer.parseInt(y[j]));
					// Field muss zu Streetfield gecastestet werden
					if (grid[i][j].getValue() == 1) {
						Street newStreetField = new Street(i + 1, j + 1);
						grid[i][j] = newStreetField;
					}
				}

			}

			// Einlesen der Geschwindigkeitsbegrenzungen
			InputStreamReader isr1 = new InputStreamReader(input1, StandardCharsets.UTF_8);
			BufferedReader reader1 = new BufferedReader(isr1);

			// Read new lines until readLine() returns null
			for (int i = 0; i < grid.length; i++) {
				String s1 = reader1.readLine();
				String[] y = s1.split(";");
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j].getValue() == 1) {
						((Street) grid[i][j]).setSpeedLimit(Integer.parseInt(y[j]));

					}
				}
			}

			/*
			 * for (int i = 0; i < grid.length; i++) { for (int j = 0; j <grid[0].length;
			 * j++) { if (grid[i][j].getValue() == 1) { System.out.println("row: " + i +
			 * " col: " +j + "  " + ((Street) grid[i][j]).getSpeedLimit()); } } }
			 */

			// Einlesen der Richtungen
			InputStreamReader isr2 = new InputStreamReader(input2, StandardCharsets.UTF_8);
			BufferedReader reader2 = new BufferedReader(isr2);
			for (int i = 0; i < grid.length; i++) {
				String s2 = reader2.readLine();
				String[] y = s2.split(";");
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j].getValue() == 1) {

						if (y[j].equals("XN")) {
							((Street) grid[i][j]).setDirection(North);
						}
						if (y[j].equals("XW")) {
							((Street) grid[i][j]).setDirection(West);
						}
						if (y[j].equals("XE")) {
							((Street) grid[i][j]).setDirection(East);
						}
						if (y[j].equals("XS")) {
							((Street) grid[i][j]).setDirection(South);
						}
						if (y[j].equals("NW")) {
							((Street) grid[i][j]).setDirection(North);
							((Street) grid[i][j]).setDirection(West);
						}
						if (y[j].equals("SW")) {
							((Street) grid[i][j]).setDirection(South);
							((Street) grid[i][j]).setDirection(West);
						}
						if (y[j].equals("NE")) {
							((Street) grid[i][j]).setDirection(North);
							((Street) grid[i][j]).setDirection(East);
						}
						if (y[j].equals("SE")) {
							((Street) grid[i][j]).setDirection(South);
							((Street) grid[i][j]).setDirection(East);
						}

					}
				}
			}
			/*
			 * for (int i = 0; i <grid.length; i++) { for (int j = 0; j < grid.length ; j++)
			 * { if (grid[i][j].getValue() == 1) { System.out.println( "row: " + (i+1) +
			 * " col: " + (j+1) + "  " + ((Street) grid[i][j]).getDirection()); } } }
			 */
			// hinzufuegen der Verbindungsstrassen fuer ms2
			Node n2 = new Node(7, 3);
			addConnectionRoad(n2, Direction.West, 50);
			setDirection(n2);
			initSpeed(n2, 30);

			Node n33 = new Node(3, 33);
			addConnectionRoad(n33, Direction.North, 50);
			setDirection(n33);
			initSpeed(n33, 100);

			Node newNode1 = new Node(7, 37);
			addConnectionRoad(newNode1, Direction.East, 50);
			setDirection(newNode1);
			initSpeed(newNode1, 100);

			Node n12 = new Node(27, 13);
			Node newNode2 = new Node(27, 15);
			init(newNode2);
			addConnectionRoad(newNode2, Direction.South, 50);
			setDirection(n12);
			setDirection(newNode2);
			setConnectionNode(newNode2);
			initSpeed(n12, 50);
			initSpeed(newNode2, 50);

			addPipeCapacity();
			addPipeCosts();
			// addConstructionSite();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * first constructor: initialize grid as a 2-dimensional array of type Field
	 * then implement the whole map with methods of this class. Hard coding
	 * 
	 * @param dim1 number of rows
	 * @param dim2 number of columns
	 */
	public Grid(int dim1, int dim2, double x, double y) {
		grid = new Field[dim1][dim2];
		for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
			for (int colIndex = 0; colIndex < grid[0].length; colIndex++) {
				// initialisiert alle Felder als Baum Felder
				grid[rowIndex][colIndex] = new Field(rowIndex + 1, colIndex + 1, 2);
			}
		}
		this.rowDimension = dim1;
		this.colDimension = dim2;

		// AB HIER SOLL EINGELESENS WERDEN
		WaterTank WT1 = new WaterTank(15, 3);
		WaterTank WT2 = new WaterTank(28, 39);
		FireStation FS1 = new FireStation(10, 15);
		FireStation FS2 = new FireStation(23, 37);
		ScrapYard SY = new ScrapYard(27, 23);
		Park PS = new Park(19, 3);
		Hospital H = new Hospital(3, 6);
		BanquetHall BH = new BanquetHall(6, 35);
		House H1 = new House(15, 9); // gelbes haus oben links
		House H2 = new House(26, 9); // gelbes haus unten links
		House H3 = new House(22, 24); // gelbes haus mitte rechts

		// fuegt Wassertank 1&2, Feuerwehrstation 1&2, Schrottplatz, Parkplatz,
		// Krankenhaus,Festhalle, und 3 Standardgebaeude dem grid hinzu
		addBigField(WT1);
		addBigField(WT2);
		addBigField(FS1);
		addBigField(FS2);
		addBigField(SY);
		addBigField(PS);
		addBigField(H);
		addField(BH);
		addField(H1);
		addField(H2);
		addField(H3);

		// erstellt die Kreuzungsknoten
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

		ArrayList<Node> nodes = new ArrayList<>();

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
		nodes.add(x_36);

		for (int i = 0; i < nodes.size(); i++) {
			init(nodes.get(i));
		}

		// erzeugt die Strassen zwischen zwei Knoten wenn keiner der Knoten auf der
		// Strasse liegt
		addStreet(x_20, x_11);
		addStreet(x_15, x_19);
		addStreet(x_7, x_13);
		addStreet(x_17, x_21);
		addStreet(x_26, x_32);
		addStreet(x_12, x_4);
		addStreet(x_13, x_18);
		addStreet(x_22, x_23);
		addStreet(x_23, x_24);
		addStreet(x_24, x_25);
		addStreet(x_31, x_33);
		addStreet(x_21, x_31);
		addStreet(x_28, x_36);

		// erzeugt die Strassen zwischen zwei Knoten wenn einer der Knoten auf der
		// Strasse liegt
		addStreet(x_1, x_2);
		addStreet(x_2, x_10);
		addStreet(x_6, x_14);
		addStreet(x_11, x_6);
		addStreet(x_27, x_19);
		addStreet(x_4, x_3);
		addStreet(x_7, x_5);
		addStreet(x_26, x_27);
		addStreet(x_17, x_30);
		addStreet(x_35, x_29);
		addStreet(x_9, x_8);
		addStreet(x_31, x_34);

		// erzeugt die Strassen zwischen zwei Knoten wenn beide auf der Strasse liegen
		addStreet(x_15, x_12);
		addStreet(x_28, x_25);
		addStreet(x_5, x_3);
		addStreet(x_21, x_20);
		addStreet(x_34, x_36);

		// erzeugt die Stra�en die zum Rand der Karte fuehren
		addStreet3(x_16, Direction.North, 50);
		addStreet3(x_34, Direction.South, 50);

		for (int i = 0; i < nodes.size(); i++) {
			setDirection(nodes.get(i));
		}

		// erzeugt die Geschwindigkeit wenn keiner der Knoten auf der Strasse liegt
		setSpeedLimit(x_20, x_11, 70, 0);
		setSpeedLimit(x_15, x_19, 50, 0);
		setSpeedLimit(x_7, x_13, 30, 0);
		setSpeedLimit(x_17, x_21, 30, 0);
		setSpeedLimit(x_26, x_32, 30, 0);
		setSpeedLimit(x_12, x_4, 70, 0);
		setSpeedLimit(x_13, x_18, 30, 0);
		setSpeedLimit(x_22, x_23, 30, 0);
		setSpeedLimit(x_23, x_24, 30, 0);
		setSpeedLimit(x_24, x_25, 100, 0);
		setSpeedLimit(x_31, x_33, 30, 0);
		setSpeedLimit(x_21, x_31, 70, 0);
		setSpeedLimit(x_28, x_36, 100, 0);

		// erzeugt die Geschwindigkeit wenn einer der Knoten auf der Strasse liegt:
		// es liegt immer der Startknoten auf der Strasse
		setSpeedLimit(x_1, x_2, 30, 1);
		setSpeedLimit(x_2, x_10, 30, 1);
		setSpeedLimit(x_6, x_14, 70, 1);
		setSpeedLimit(x_11, x_6, 70, 1);
		setSpeedLimit(x_27, x_19, 50, 1);
		setSpeedLimit(x_4, x_3, 30, 1);
		setSpeedLimit(x_7, x_5, 30, 1);
		setSpeedLimit(x_26, x_27, 30, 1);
		setSpeedLimit(x_17, x_30, 50, 1);
		setSpeedLimit(x_35, x_29, 30, 1);
		setSpeedLimit(x_9, x_8, 30, 1);
		setSpeedLimit(x_31, x_34, 70, 1);

		// erzeugt die Geschwindigkeit wenn beide Knoten auf der Strasse liegen
		setSpeedLimit(x_15, x_12, 50, 2);
		setSpeedLimit(x_28, x_25, 100, 2);
		setSpeedLimit(x_5, x_3, 30, 2);
		setSpeedLimit(x_21, x_20, 70, 2);
		setSpeedLimit(x_34, x_36, 70, 2);

		setSpeedLimit3(x_16, Direction.North, 50);
		setSpeedLimit3(x_34, Direction.South, 50);

		/*
		 * // setzt die Kosten fuer Rohre Vertex v12 = new Vertex("x12", 27, 13); Vertex
		 * v13 = new Vertex("x13", 19, 13); Vertex v14 = new Vertex("x14", 13, 13);
		 * Vertex v15 = new Vertex("x15", 7, 13); Vertex v17 = new Vertex("x17", 25,
		 * 19); Vertex v18 = new Vertex("x18", 19, 19); Vertex v19 = new Vertex("x19",
		 * 7, 19); Vertex v21 = new Vertex("x21", 15, 19); Vertex v22 = new
		 * Vertex("x22", 25, 25); Vertex v23 = new Vertex("x23", 15, 25); Vertex v24 =
		 * new Vertex("x24", 7, 25); Vertex v30 = new Vertex("x30", 25, 33); Vertex v31
		 * = new Vertex("x31", 15, 33); Vertex v34 = new Vertex("x34", 21, 33); //
		 * Senkrechte Rohre addPipeCosts(udg.getVertex(0), udg.getVertex(1));
		 * addPipeCosts(udg.getVertex(1), udg.getVertex(2));
		 * addPipeCosts(udg.getVertex(2), udg.getVertex(2));
		 * 
		 * addPipeCosts(v17, v18); addPipeCosts(v18, v21); addPipeCosts(v21, v19);
		 * 
		 * addPipeCosts(v22, v23); addPipeCosts(v23, v24);
		 * 
		 * addPipeCosts(v30, v34); addPipeCosts(v34, v31);
		 * 
		 * // Waagerechte Rohre addPipeCosts(v17, v22); addPipeCosts(v22, v30);
		 * 
		 * addPipeCosts(v13, v18);
		 * 
		 * addPipeCosts(v21, v23); addPipeCosts(v23, v31);
		 * 
		 * addPipeCosts(v15, v19); addPipeCosts(v19, v24);
		 */
		addPipeCapacity();
		addPipeCosts();

	}

	/**
	 * add a special field(parking spot, fire station..) with size 2end_node to the
	 * grid
	 * 
	 * @param bigfield field for e.g. parking spot or fire station
	 */
	public void addBigField(Field bigfield) {
		if (bigfield instanceof Hospital) {
			Hospital lo = new Hospital(bigfield.getRowIndex(), bigfield.getColIndex());
			Hospital lu = new Hospital(bigfield.getRowIndex() + 1, bigfield.getColIndex());
			Hospital ro = new Hospital(bigfield.getRowIndex(), bigfield.getColIndex() + 1);
			Hospital ru = new Hospital(bigfield.getRowIndex() + 1, bigfield.getColIndex() + 1);

			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex() - 1] = lo;
			grid[bigfield.getRowIndex()][bigfield.getColIndex() - 1] = lu;
			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex()] = ro;
			grid[bigfield.getRowIndex()][bigfield.getColIndex()] = ru;
		}

		if (bigfield instanceof FireStation) {
			FireStation lo = new FireStation(bigfield.getRowIndex(), bigfield.getColIndex());
			FireStation lu = new FireStation(bigfield.getRowIndex() + 1, bigfield.getColIndex());
			FireStation ro = new FireStation(bigfield.getRowIndex(), bigfield.getColIndex() + 1);
			FireStation ru = new FireStation(bigfield.getRowIndex() + 1, bigfield.getColIndex() + 1);

			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex() - 1] = lo;
			grid[bigfield.getRowIndex()][bigfield.getColIndex() - 1] = lu;
			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex()] = ro;
			grid[bigfield.getRowIndex()][bigfield.getColIndex()] = ru;
		}

		if (bigfield instanceof ScrapYard) {
			ScrapYard lo = new ScrapYard(bigfield.getRowIndex(), bigfield.getColIndex());
			ScrapYard lu = new ScrapYard(bigfield.getRowIndex() + 1, bigfield.getColIndex());
			ScrapYard ro = new ScrapYard(bigfield.getRowIndex(), bigfield.getColIndex() + 1);
			ScrapYard ru = new ScrapYard(bigfield.getRowIndex() + 1, bigfield.getColIndex() + 1);

			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex() - 1] = lo;
			grid[bigfield.getRowIndex()][bigfield.getColIndex() - 1] = lu;
			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex()] = ro;
			grid[bigfield.getRowIndex()][bigfield.getColIndex()] = ru;
		}
		if (bigfield instanceof Park) {
			Park lo = new Park(bigfield.getRowIndex(), bigfield.getColIndex());
			Park lu = new Park(bigfield.getRowIndex() + 1, bigfield.getColIndex());
			Park ro = new Park(bigfield.getRowIndex(), bigfield.getColIndex() + 1);
			Park ru = new Park(bigfield.getRowIndex() + 1, bigfield.getColIndex() + 1);

			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex() - 1] = lo;
			grid[bigfield.getRowIndex()][bigfield.getColIndex() - 1] = lu;
			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex()] = ro;
			grid[bigfield.getRowIndex()][bigfield.getColIndex()] = ru;
		}
		if (bigfield instanceof WaterTank) {
			WaterTank lo = new WaterTank(bigfield.getRowIndex(), bigfield.getColIndex());
			WaterTank lu = new WaterTank(bigfield.getRowIndex() + 1, bigfield.getColIndex());
			WaterTank ro = new WaterTank(bigfield.getRowIndex(), bigfield.getColIndex() + 1);
			WaterTank ru = new WaterTank(bigfield.getRowIndex() + 1, bigfield.getColIndex() + 1);

			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex() - 1] = lo;
			grid[bigfield.getRowIndex()][bigfield.getColIndex() - 1] = lu;
			grid[bigfield.getRowIndex() - 1][bigfield.getColIndex()] = ro;
			grid[bigfield.getRowIndex()][bigfield.getColIndex()] = ru;
		}
	}

	/**
	 * adds a type of field and ensure that is connected to a street field
	 * 
	 * @param field type of field, e.g house, fire station, hospital...
	 */
	public void addFieldMS2(Field field) {
		int north_counter = 0;
		int east_counter = 0;
		int south_counter = 0;
		int west_counter = 0;
		// kleines Feld
		if (field instanceof House || field instanceof BanquetHall) {
			ArrayList<Direction> posdir = setDirSmallField(field);
			// Zaehle die Anzahl benachbarter Straßen
			int count_neighbors = 0;

			if (posdir.contains(North) && grid[field.getRowIndex() - 2][field.getColIndex() - 1].getValue() == 1) {
				count_neighbors++;
			}
			if (posdir.contains(East) && grid[field.getRowIndex() - 1][field.getColIndex()].getValue() == 1) {
				count_neighbors++;
			}
			if (posdir.contains(South) && grid[field.getRowIndex()][field.getColIndex() - 1].getValue() == 1) {
				count_neighbors++;
			}
			if (posdir.contains(West) && grid[field.getRowIndex() - 1][field.getColIndex() - 2].getValue() == 1) {
				count_neighbors++;
			}
			// falls min ein Strassenfeld an field grenzt rufe addField auf
			if (count_neighbors > 0) {
				addField(field);
			} else {

				// posdir enthaelt alle Richtungen und es grenzt kein Stassenfeld an field
				// -|- alle richtungen enthalten
				if (posdir.size() == 4 && count_neighbors == 0) {
					while (field.getRowIndex() - north_counter - 1 > 0
							&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1].getValue() != 1) {
						north_counter++;
					}
					while (field.getColIndex() + east_counter < grid[0].length
							&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter - 1].getValue() != 1) {
						east_counter++;
					}
					while (field.getRowIndex() + south_counter < grid.length
							&& grid[field.getRowIndex() + south_counter - 1][field.getColIndex() - 1].getValue() != 1) {
						south_counter++;
					}
					while (field.getColIndex() - west_counter - 1 > 0
							&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1].getValue() != 1) {
						west_counter++;
					}
				}
				// posdir enhaelt drei Richtungen
				if (posdir.size() == 3 && count_neighbors == 0) {
					// |-
					if (posdir.contains(Direction.North) && posdir.contains(Direction.East)
							&& posdir.contains(Direction.South)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter - 1]
										.getValue() != 1) {
							east_counter++;
						}
						while (field.getRowIndex() + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							south_counter++;
						}
					}
					// T
					if (posdir.contains(Direction.West) && posdir.contains(Direction.East)
							&& posdir.contains(Direction.South)) {
						while (field.getColIndex() + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter - 1]
										.getValue() != 1) {
							east_counter++;
						}
						while (field.getRowIndex() + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							south_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
					// -|
					if (posdir.contains(Direction.North) && posdir.contains(Direction.West)
							&& posdir.contains(Direction.South)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getRowIndex() + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							south_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
					if (posdir.contains(Direction.North) && posdir.contains(Direction.West)
							&& posdir.contains(Direction.East)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter - 1]
										.getValue() != 1) {
							east_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
				}
				// posdir enthaelt 2 Richtungen
				if (posdir.size() == 2 && count_neighbors == 0) {
					if (posdir.contains(Direction.North) && posdir.contains(Direction.East)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter - 1]
										.getValue() != 1) {
							east_counter++;
						}
					}
					if (posdir.contains(Direction.East) && posdir.contains(Direction.South)) {
						while (field.getColIndex() + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter - 1]
										.getValue() != 1) {
							east_counter++;
						}
						while (field.getRowIndex() + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							south_counter++;
						}
					}
					if (posdir.contains(Direction.South) && posdir.contains(Direction.West)) {
						while (field.getRowIndex() + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							south_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
					if (posdir.contains(Direction.West) && posdir.contains(Direction.North)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
				}
			}

			

			// bestimmt alle Richtungen in denen man ein Strassenfeld vorfindet
			// und fuegt sie posdirStreet hinzu
			ArrayList<Direction> posdirStreet = new ArrayList<Direction>();
			if (grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1].getValue() == 1) {
				posdirStreet.add(Direction.North);
			}
			if (grid[field.getRowIndex() - 1][field.getColIndex() - 1 + east_counter].getValue() == 1) {
				posdirStreet.add(Direction.East);
			}
			if (grid[field.getRowIndex() - 1 + south_counter][field.getColIndex() - 1].getValue() == 1) {
				posdirStreet.add(Direction.South);
			}
			if (grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter].getValue() == 1) {
				posdirStreet.add(Direction.West);
			}

			// prueft ob Richtungen enthalten sind und wenn nicht wird der counter auf 1000
			// gesetzt
			if (!posdirStreet.contains(North)) {
				north_counter = 1000;
			}
			if (!posdirStreet.contains(East)) {
				east_counter = 1000;
			}
			if (!posdirStreet.contains(South)) {
				south_counter = 1000;
			}
			if (!posdirStreet.contains(West)) {
				west_counter = 1000;
			}
			
			
			  System.out.println("north counter " + north_counter);
			  System.out.println("east counter " + east_counter);
			  System.out.println("south counter " + south_counter);
			  System.out.println("west counter " + west_counter);
			 
			
			// direction_counter ist genau dann 0 wenn das Feld am jeweiligen Rand liegt
			// prueft ob der Abstand von Norden kleiner gleich allen anderen Abstaenden ist
			// und ob in noerdlicher Richtung ein Strassenfeld liegt
			if (north_counter != 0 && north_counter <= east_counter && north_counter <= south_counter
					&& north_counter <= west_counter && posdirStreet.contains(North)) {
				int topspeed = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex()])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex()])
						.getFlowDirection();

				// Fliesskapazitaeten
				int flowCap_lo = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex()])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex()])
						.getPipeCapacity();

				// Rohrkosten
				int cost_lo = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex()])
						.getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex()])
						.getPipeCosts();

				Node top = new Node(field.getRowIndex() - 1 - north_counter, field.getColIndex());
				Node butt = new Node(field.getRowIndex() - 2, field.getColIndex());
				addStreet(top, butt);
				init(butt);
				init(top);

				if (butt.getRowIndex() >= top.getRowIndex() + 2) {
					setDirection(butt);
				}
				if (butt.getRowIndex() == top.getRowIndex() + 1) {
					((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setDirection(East);
					((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setDirection(North);
				}
				setDirection(top);

				// setze die Daten der urspruenglichen Karte im Knoten top
				// Fliessrichtung
				((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[top.getRowIndex()][top.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[top.getRowIndex()][top.getColIndex()]).setFlowDirection(flowDir_ru);
				// Fliesskapazität
				((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[top.getRowIndex()][top.getColIndex() - 1]).setPipeCapacity(flowCap_lu);
				((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[top.getRowIndex()][top.getColIndex()]).setPipeCapacity(flowCap_ru);
				// Kosten
				((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[top.getRowIndex()][top.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[top.getRowIndex()][top.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(butt, top, topspeed, 2);
				addField(field);
			}

			if (east_counter != 0 && east_counter < north_counter && east_counter <= south_counter
					&& east_counter <= west_counter && posdirStreet.contains(East)) {
				int topspeed = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 + east_counter])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 + east_counter])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 + east_counter])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + east_counter])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() + east_counter])
						.getFlowDirection();

				// Fliesskapazitaeten
				int flowCap_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 + east_counter])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 + east_counter])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + east_counter])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() + east_counter])
						.getPipeCapacity();

				// Rohrkosten
				int cost_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 + east_counter])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 + east_counter])
						.getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + east_counter])
						.getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() + east_counter]).getPipeCosts();

				Node left = new Node(field.getRowIndex(), field.getColIndex() + 1);
				Node right = new Node(field.getRowIndex(), field.getColIndex() + east_counter);
				addStreet(left, right);
				init(left);
				init(right);

				if (left.getColIndex() <= right.getColIndex() + 2) {
					setDirection(left);
				}
				if (left.getColIndex() == right.getColIndex() + 1) {
					((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setDirection(South);
					((Street) grid[left.getRowIndex()][left.getColIndex() - 1]).setDirection(East);
				}
				setDirection(right);

				// setze die Daten der urspruenglichen Karte im Knoten right
				// Fliessrichtung
				((Street) grid[right.getRowIndex() - 1][right.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[right.getRowIndex()][right.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[right.getRowIndex()][right.getColIndex()]).setFlowDirection(flowDir_ru);

				// Fliesskapazitaet
				((Street) grid[right.getRowIndex() - 1][right.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[right.getRowIndex()][right.getColIndex() - 1]).setPipeCapacity((flowCap_lu));
				((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[right.getRowIndex()][right.getColIndex()]).setPipeCapacity(flowCap_ru);

				// Kosten
				((Street) grid[right.getRowIndex() - 1][right.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[right.getRowIndex()][right.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[right.getRowIndex()][right.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(left, right, topspeed, 2);
				addField(field);
			}
			if (south_counter != 0 && south_counter < north_counter && south_counter < east_counter
					&& south_counter <= west_counter && posdirStreet.contains(South)) {
				int topspeed = ((Street) grid[field.getRowIndex() - 1 + south_counter][field.getColIndex() - 1])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() - 1 + south_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() - 1 + south_counter][field.getColIndex()])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex()])
						.getFlowDirection();

				// Fliesskapazitaeten
				int flowCap_lo = ((Street) grid[field.getRowIndex() - 1 + south_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() - 1 + south_counter][field.getColIndex()])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex()])
						.getPipeCapacity();

				// Rohrkosten
				int cost_lo = ((Street) grid[field.getRowIndex() - 1 + south_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() - 1 + south_counter][field.getColIndex()])
						.getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex()]).getPipeCosts();

				Node top = new Node(field.getRowIndex() + 1, field.getColIndex());
				Node butt = new Node(field.getRowIndex() + south_counter, field.getColIndex());
				addStreet(top, butt);
				init(top);
				init(butt);

				// setDirection(butt);
				if (top.getRowIndex() <= butt.getRowIndex() - 2) {
					setDirection(top);
				}
				if (top.getRowIndex() == butt.getRowIndex() - 1) {
					((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setDirection(South);
					((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setDirection(West);
				}
				setDirection(butt);

				// setze die Daten der urspruenglichen Karte im Knoten top
				// Fliessrichtung
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setFlowDirection(flowDir_ru);

				// Fliesskapazitaet
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setPipeCapacity(flowCap_lu);
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setPipeCapacity(flowCap_ru);

				// Kosten
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(top, butt, topspeed, 2);
				addField(field);
			}
			if (west_counter != 0 && west_counter < north_counter && west_counter < east_counter
					&& west_counter < south_counter && posdirStreet.contains(West)) {
				int topspeed = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 2 - west_counter])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 2 - west_counter])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 - west_counter])
						.getFlowDirection();

				// Fliesskapazitaeten
				int flowCap_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 2 - west_counter])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 2 - west_counter])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 - west_counter])
						.getPipeCapacity();

				// Rohrkosten
				int cost_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 2 - west_counter])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 2 - west_counter])
						.getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 - west_counter])
						.getPipeCosts();

				Node right = new Node(field.getRowIndex(), field.getColIndex() - 2);
				Node left = new Node(field.getRowIndex(), field.getColIndex() - west_counter - 1);
				addStreet(right, left);
				init(right);
				init(left);

				if (right.getColIndex() >= left.getColIndex() + 2) {
					setDirection(right);
				}
				if (right.getColIndex() == left.getColIndex() + 1) {
					((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setDirection(West);
					((Street) grid[right.getRowIndex()][right.getColIndex()]).setDirection(North);
				}
				setDirection(left);

				// Fliessrichtung
				((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[left.getRowIndex()][left.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[left.getRowIndex() - 1][left.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[left.getRowIndex()][left.getColIndex()]).setFlowDirection(flowDir_ru);

				// Flieskapazitaeten
				((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[left.getRowIndex()][left.getColIndex() - 1]).setPipeCapacity(flowCap_lu);
				((Street) grid[left.getRowIndex() - 1][left.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[left.getRowIndex()][left.getColIndex()]).setPipeCapacity(flowCap_ru);

				// Kosten
				((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[left.getRowIndex()][left.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[left.getRowIndex() - 1][left.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[left.getRowIndex()][left.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(right, left, topspeed, 2);
				addField(field);
			}

		}
		if (field instanceof WaterTank || field instanceof FireStation || field instanceof ScrapYard
				|| field instanceof Park || field instanceof Hospital || field instanceof WaterTank) {
			ArrayList<Direction> posdir = setDirBigField(field);
			int count_neighbors = 0;

			if (posdir.contains(North) && grid[field.getRowIndex() - 2][field.getColIndex() - 1].getValue() == 1) {
				count_neighbors++;
			}
			if (posdir.contains(East) && grid[field.getRowIndex() - 1][field.getColIndex() + 1].getValue() == 1) {
				count_neighbors++;
			}
			if (posdir.contains(South) && grid[field.getRowIndex() + 1][field.getColIndex() - 1].getValue() == 1) {
				count_neighbors++;
			}
			if (posdir.contains(West) && grid[field.getRowIndex() - 1][field.getColIndex() - 2].getValue() == 1) {
				count_neighbors++;
			}
			// falls min ein Strassenfeld an field grenzt rufe addField auf
			if (count_neighbors > 0) {
				addBigField(field);
			} else {
				// posdir enthaelt alle Richtungen und es grenzt kein Stassenfeld an field
				// -|- alle richtungen enthalten
				if (posdir.size() == 4 && count_neighbors == 0) {
					while (field.getRowIndex() - north_counter - 1 > 0
							&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1].getValue() != 1) {
						north_counter++;
					}
					while (field.getColIndex() + 1 + east_counter < grid[0].length
							&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter].getValue() != 1) {
						east_counter++;
					}
					while (field.getRowIndex() + 1 + south_counter < grid.length
							&& grid[field.getRowIndex() + south_counter][field.getColIndex() - 1].getValue() != 1) {
						south_counter++;
					}
					while (field.getColIndex() - west_counter - 1 > 0
							&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1].getValue() != 1) {
						west_counter++;
					}
				}
				// posdir enhaelt drei Richtungen
				if (posdir.size() == 3 && count_neighbors == 0) {
					// |-
					if (posdir.contains(Direction.North) && posdir.contains(Direction.East)
							&& posdir.contains(Direction.South)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() + 1 + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter].getValue() != 1) {
							east_counter++;
						}
						while (field.getRowIndex() + 1 + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter][field.getColIndex() - 1].getValue() != 1) {
							south_counter++;
						}
					}
					// T
					if (posdir.contains(Direction.West) && posdir.contains(Direction.East)
							&& posdir.contains(Direction.South)) {
						while (field.getColIndex() + 1 + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter].getValue() != 1) {
							east_counter++;
						}
						while (field.getRowIndex() + 1 + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter][field.getColIndex() - 1].getValue() != 1) {
							south_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
					// -|
					if (posdir.contains(Direction.North) && posdir.contains(Direction.West)
							&& posdir.contains(Direction.South)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getRowIndex() + 1 + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter][field.getColIndex() - 1].getValue() != 1) {
							south_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
					if (posdir.contains(Direction.North) && posdir.contains(Direction.West)
							&& posdir.contains(Direction.East)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() + 1 + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter].getValue() != 1) {
							east_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
				}
				// posdir enthaelt 2 Richtungen
				if (posdir.size() == 2 && count_neighbors == 0) {
					if (posdir.contains(Direction.North) && posdir.contains(Direction.East)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() + 1 + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter].getValue() != 1) {
							east_counter++;
						}
					}
					if (posdir.contains(Direction.East) && posdir.contains(Direction.South)) {
						while (field.getColIndex() + 1 + east_counter < grid[0].length
								&& grid[field.getRowIndex() - 1][field.getColIndex() + east_counter].getValue() != 1) {
							east_counter++;
						}
						while (field.getRowIndex() + 1 + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter][field.getColIndex() - 1].getValue() != 1) {
							south_counter++;
						}
					}
					if (posdir.contains(Direction.South) && posdir.contains(Direction.West)) {
						while (field.getRowIndex() + 1 + south_counter < grid.length
								&& grid[field.getRowIndex() + south_counter][field.getColIndex() - 1].getValue() != 1) {
							south_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
					if (posdir.contains(Direction.West) && posdir.contains(Direction.North)) {
						while (field.getRowIndex() - north_counter - 1 > 0
								&& grid[field.getRowIndex() - north_counter - 1][field.getColIndex() - 1]
										.getValue() != 1) {
							north_counter++;
						}
						while (field.getColIndex() - west_counter - 1 > 0
								&& grid[field.getRowIndex() - 1][field.getColIndex() - west_counter - 1]
										.getValue() != 1) {
							west_counter++;
						}
					}
				}
			}

			/*
			 * System.out.println("north counter " + north_counter);
			 * System.out.println("east counter " + east_counter);
			 * System.out.println("south counter " + south_counter);
			 * System.out.println("west counter " + west_counter);
			 */

			// bestimmt alle Richtungen in denen man ein Strassenfeld vorfindet
			// und fuegt sie posdirStreet hinzu
			ArrayList<Direction> posdirStreet = new ArrayList<Direction>();
			if (grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1].getValue() == 1) {
				posdirStreet.add(Direction.North);
			}
			if (grid[field.getRowIndex() - 1][field.getColIndex() + east_counter].getValue() == 1) {
				posdirStreet.add(Direction.East);
			}
			if (grid[field.getRowIndex() + south_counter][field.getColIndex() - 1].getValue() == 1) {
				posdirStreet.add(Direction.South);
			}
			if (grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter].getValue() == 1) {
				posdirStreet.add(Direction.West);
			}

			// prueft ob Richtungen enthalten sind und wenn nicht wird der counter auf 1000
			// gesetzt
			if (!posdirStreet.contains(North)) {
				north_counter = 1000;
			}
			if (!posdirStreet.contains(East)) {
				east_counter = 1000;
			}
			if (!posdirStreet.contains(South)) {
				south_counter = 1000;
			}
			if (!posdirStreet.contains(West)) {
				west_counter = 1000;
			}
			// direction_counter ist genau dann 0 wenn das Feld am jeweiligen Rand liegt
			// prueft ob der Abstand von Norden kleiner gleich allen anderen Abstaenden ist
			// und ob in noerdlicher Richtung ein Strassenfeld liegt
			if (north_counter != 0 && north_counter <= east_counter && north_counter <= south_counter
					&& north_counter <= west_counter && posdirStreet.contains(North)) {
				int topspeed = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex()])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex()])
						.getFlowDirection();

				// Fliesskapazitaeten
				int flowCap_lo = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex()])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex()])
						.getPipeCapacity();

				// Rohrkosten
				int cost_lo = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() - 2 - north_counter][field.getColIndex()])
						.getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex() - 1 - north_counter][field.getColIndex()])
						.getPipeCosts();

				Node top = new Node(field.getRowIndex() - 1 - north_counter, field.getColIndex());
				Node butt = new Node(field.getRowIndex() - 2, field.getColIndex());
				// System.out.println(butt.getRowIndex() + " " + butt.getColIndex());
				addStreet(top, butt);
				init(top);
				init(butt);

				if (butt.getRowIndex() >= top.getRowIndex() + 2) {
					setDirection(butt);
				}
				if (butt.getRowIndex() == top.getRowIndex() + 1) {
					((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setDirection(East);
					((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setDirection(North);
				}
				setDirection(top);

				// setze die Daten der urspruenglichen Karte im Knoten top
				// Fliessrichtung
				((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[top.getRowIndex()][top.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[top.getRowIndex()][top.getColIndex()]).setFlowDirection(flowDir_ru);
				// Fliesskapazität
				((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[top.getRowIndex()][top.getColIndex() - 1]).setPipeCapacity(flowCap_lu);
				((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[top.getRowIndex()][top.getColIndex()]).setPipeCapacity(flowCap_ru);
				// Kosten
				((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[top.getRowIndex()][top.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[top.getRowIndex()][top.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(butt, top, topspeed, 2);
				addBigField(field);
			}
			if (east_counter != 0 && east_counter < north_counter && east_counter <= south_counter
					&& east_counter <= west_counter && posdirStreet.contains(East)) {
				int topspeed = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + east_counter])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + east_counter])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() + east_counter])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + 1 + east_counter])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() + 1 + east_counter])
						.getFlowDirection();

				// Fliesskapazitaet
				int flowCap_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + east_counter])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() + east_counter])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + 1 + east_counter])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() + 1 + east_counter])
						.getPipeCapacity();

				// Kosten
				int cost_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + east_counter])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() + east_counter]).getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() + 1 + east_counter])
						.getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() + 1 + east_counter])
						.getPipeCosts();

				Node left = new Node(field.getRowIndex(), field.getColIndex() + 2);
				Node right = new Node(field.getRowIndex(), field.getColIndex() + 1 + east_counter);
				addStreet(left, right);
				init(left);
				init(right);

				if (left.getColIndex() <= right.getColIndex() + 2) {
					setDirection(left);
				}
				if (left.getColIndex() == right.getColIndex() + 1) {
					((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setDirection(South);
					((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setDirection(East);
				}
				setDirection(right);
				// setzen der urspruenglichen Daten
				// Fiessrichtung
				((Street) grid[right.getRowIndex() - 1][right.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[right.getRowIndex()][right.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[right.getRowIndex()][right.getColIndex()]).setFlowDirection(flowDir_ru);

				// Fliesskapazitaeten
				((Street) grid[right.getRowIndex() - 1][right.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[right.getRowIndex()][right.getColIndex() - 1]).setPipeCapacity(flowCap_lu);
				((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[right.getRowIndex()][right.getColIndex()]).setPipeCapacity(flowCap_ru);

				// Kosten
				((Street) grid[right.getRowIndex() - 1][right.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[right.getRowIndex()][right.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[right.getRowIndex()][right.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(left, right, topspeed, 2);
				addBigField(field);
			}
			if (south_counter != 0 && south_counter < north_counter && south_counter < east_counter
					&& south_counter <= west_counter && posdirStreet.contains(South)) {
				int topspeed = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex() - 1])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex() + 1 + south_counter][field.getColIndex() - 1])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex()])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex() + 1 + south_counter][field.getColIndex()])
						.getFlowDirection();

				// Fliesskapazitaet
				int flowCap_lo = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex() + 1 + south_counter][field.getColIndex() - 1])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex()])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex() + 1 + south_counter][field.getColIndex()])
						.getPipeCapacity();

				// Kosten
				int cost_lo = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex() + 1 + south_counter][field.getColIndex() - 1])
						.getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() + south_counter][field.getColIndex()]).getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex() + 1 + south_counter][field.getColIndex()])
						.getPipeCosts();

				Node top = new Node(field.getRowIndex() + 2, field.getColIndex());
				Node butt = new Node(field.getRowIndex() + 1 + south_counter, field.getColIndex());
				addStreet(top, butt);
				init(top);
				init(butt);

				if (top.getRowIndex() <= butt.getRowIndex() - 2) {
					setDirection(top);
				}
				if (top.getRowIndex() == butt.getRowIndex() - 1) {
					((Street) grid[top.getRowIndex() - 1][top.getColIndex() - 1]).setDirection(South);
					((Street) grid[top.getRowIndex() - 1][top.getColIndex()]).setDirection(West);
				}
				setDirection(butt);

				// setze die Daten der urspruenglichen Karte im Knoten top
				// Fliessrichtung
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setFlowDirection(flowDir_ru);

				// Fliesskapazitaet
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setPipeCapacity(flowCap_lu);
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setPipeCapacity(flowCap_ru);

				// Kosten
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[butt.getRowIndex()][butt.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[butt.getRowIndex() - 1][butt.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[butt.getRowIndex()][butt.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(top, butt, topspeed, 2);
				addBigField(field);
			}
			if (west_counter != 0 && west_counter < north_counter && west_counter < east_counter
					&& west_counter < south_counter && posdirStreet.contains(West)) {
				int topspeed = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getSpeedLimit();

				// Fliessrichtung
				Direction flowDir_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 2 - west_counter])
						.getFlowDirection();
				Direction flowDir_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 2 - west_counter])
						.getFlowDirection();
				Direction flowDir_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getFlowDirection();
				Direction flowDir_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 - west_counter])
						.getFlowDirection();

				// Fliesskapazitaeten
				int flowCap_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 2 - west_counter])
						.getPipeCapacity();
				int flowCap_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 2 - west_counter])
						.getPipeCapacity();
				int flowCap_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getPipeCapacity();
				int flowCap_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 - west_counter])
						.getPipeCapacity();

				// Rohrkosten
				int cost_lo = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 2 - west_counter])
						.getPipeCosts();
				int cost_lu = ((Street) grid[field.getRowIndex()][field.getColIndex() - 2 - west_counter])
						.getPipeCosts();
				int cost_ro = ((Street) grid[field.getRowIndex() - 1][field.getColIndex() - 1 - west_counter])
						.getPipeCosts();
				int cost_ru = ((Street) grid[field.getRowIndex()][field.getColIndex() - 1 - west_counter])
						.getPipeCosts();

				Node right = new Node(field.getRowIndex(), field.getColIndex() - 2);
				Node left = new Node(field.getRowIndex(), field.getColIndex() - 1 - west_counter);
				addStreet(right, left);
				init(right);
				init(left);

				if (right.getColIndex() >= left.getColIndex() + 2) {
					setDirection(right);
				}
				if (right.getColIndex() == left.getColIndex() + 1) {
					((Street) grid[right.getRowIndex() - 1][right.getColIndex()]).setDirection(West);
					((Street) grid[right.getRowIndex()][right.getColIndex()]).setDirection(North);
				}
				setDirection(left);

				// Fliessrichtung
				((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setFlowDirection(flowDir_lo);
				((Street) grid[left.getRowIndex()][left.getColIndex() - 1]).setFlowDirection(flowDir_lu);
				((Street) grid[left.getRowIndex() - 1][left.getColIndex()]).setFlowDirection(flowDir_ro);
				((Street) grid[left.getRowIndex()][left.getColIndex()]).setFlowDirection(flowDir_ru);

				// Flieskapazitaeten
				((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setPipeCapacity(flowCap_lo);
				((Street) grid[left.getRowIndex()][left.getColIndex() - 1]).setPipeCapacity(flowCap_lu);
				((Street) grid[left.getRowIndex() - 1][left.getColIndex()]).setPipeCapacity(flowCap_ro);
				((Street) grid[left.getRowIndex()][left.getColIndex()]).setPipeCapacity(flowCap_ru);

				// Kosten
				((Street) grid[left.getRowIndex() - 1][left.getColIndex() - 1]).setPipeCosts(cost_lo);
				((Street) grid[left.getRowIndex()][left.getColIndex() - 1]).setPipeCosts(cost_lu);
				((Street) grid[left.getRowIndex() - 1][left.getColIndex()]).setPipeCosts(cost_ro);
				((Street) grid[left.getRowIndex()][left.getColIndex()]).setPipeCosts(cost_ru);

				setSpeedLimit(right, left, topspeed, 2);
				addBigField(field);
			}
		}
	}

	/**
	 * adds a field of type Field to the grid
	 * 
	 * @param field for default buildings
	 */
	public void addField(Field field) {
		grid[field.getRowIndex() - 1][field.getColIndex() - 1] = field;
	}

	/**
	 * 
	 * @return array with SpeedLimits
	 */
	public void readSpeedLimits() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].getValue() == 1) {
					this.speedLimits[i][j] = ((Street) grid[i][j]).getSpeedLimit();
				}
			}
		}

	}

	/**
	 * sets for all street fields isConstructionSite to false
	 * and the speed limit to the old value
	 */
	public void removeConstructionSite() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].getValue() == 1) {
					((Street) grid[i][j]).removeConstructionSite();
					((Street) grid[i][j]).setSpeedLimit(speedLimits[i][j]);
				}
			}
		}
		
	}

	/**
	 * adds a construction site after the algorithm determines a minimal spanning
	 * tree for
	 */
	public int addConstructionSite() {

		removeConstructionSite();
		UndirectedGraph ug = new UndirectedGraph(0.0);
		Prim p = new Prim();

		ArrayList<Vertex> mst = p.prim(ug, ug.getGraph().get(0)).getGraph();

		for (int i = 1; i < mst.size(); i++) {
			if (mst.get(i).getPriorityQueueElement().getParent() != null
					&& mst.get(i).getRowIndex() == mst.get(i).getPriorityQueueElement().getParent().getRowIndex()
					&& mst.get(i).getColIndex() < mst.get(i).getPriorityQueueElement().getParent().getColIndex()) {
				for (int colIndex = mst.get(i).getColIndex() - 1; colIndex < mst.get(i).getPriorityQueueElement()
						.getParent().getColIndex() + 1; colIndex++) {
					((Street) grid[mst.get(i).getRowIndex() - 1][colIndex]).setSpeedLimit(3);
					((Street) grid[mst.get(i).getRowIndex()][colIndex]).setSpeedLimit(3);
					((Street) grid[mst.get(i).getRowIndex() - 1][colIndex]).setConstructionSite();
					((Street) grid[mst.get(i).getRowIndex()][colIndex]).setConstructionSite();
				}
			}
			if (mst.get(i).getPriorityQueueElement().getParent() != null
					&& mst.get(i).getRowIndex() == mst.get(i).getPriorityQueueElement().getParent().getRowIndex()
					&& mst.get(i).getColIndex() > mst.get(i).getPriorityQueueElement().getParent().getColIndex()) {
				for (int colIndex = mst.get(i).getPriorityQueueElement().getParent().getColIndex() - 1; colIndex < mst
						.get(i).getColIndex() + 1; colIndex++) {
					((Street) grid[mst.get(i).getRowIndex() - 1][colIndex]).setSpeedLimit(3);
					((Street) grid[mst.get(i).getRowIndex()][colIndex]).setSpeedLimit(3);
					((Street) grid[mst.get(i).getRowIndex() - 1][colIndex]).setConstructionSite();
					((Street) grid[mst.get(i).getRowIndex()][colIndex]).setConstructionSite();
				}
			}
			if (mst.get(i).getPriorityQueueElement().getParent() != null
					&& mst.get(i).getColIndex() == mst.get(i).getPriorityQueueElement().getParent().getColIndex()
					&& mst.get(i).getRowIndex() < mst.get(i).getPriorityQueueElement().getParent().getRowIndex()) {
				for (int rowIndex = mst.get(i).getRowIndex() - 1; rowIndex < mst.get(i).getPriorityQueueElement()
						.getParent().getRowIndex() + 1; rowIndex++) {
					((Street) grid[rowIndex][mst.get(i).getColIndex() - 1]).setSpeedLimit(3);
					((Street) grid[rowIndex][mst.get(i).getColIndex()]).setSpeedLimit(3);
					((Street) grid[rowIndex][mst.get(i).getColIndex() - 1]).setConstructionSite();
					((Street) grid[rowIndex][mst.get(i).getColIndex()]).setConstructionSite();
				}
			}
			if (mst.get(i).getPriorityQueueElement().getParent() != null
					&& mst.get(i).getColIndex() == mst.get(i).getPriorityQueueElement().getParent().getColIndex()
					&& mst.get(i).getRowIndex() > mst.get(i).getPriorityQueueElement().getParent().getRowIndex()) {
				for (int rowIndex = mst.get(i).getPriorityQueueElement().getParent().getRowIndex() - 1; rowIndex < mst
						.get(i).getRowIndex() + 1; rowIndex++) {
					((Street) grid[rowIndex][mst.get(i).getColIndex() - 1]).setSpeedLimit(3);
					((Street) grid[rowIndex][mst.get(i).getColIndex()]).setSpeedLimit(3);
					((Street) grid[rowIndex][mst.get(i).getColIndex() - 1]).setConstructionSite();
					((Street) grid[rowIndex][mst.get(i).getColIndex()]).setConstructionSite();
				}
			}
		}
		return p.getTotalCosts()*100000;
	}

	public void deleteAllFlowCapAndDir() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].getValue() == 1) {
					((Street) grid[i][j]).setPipeCapacity(0);
					((Street) grid[i][j]).resetPipeElement();

				}
			}
		}
	}

	/**
	 * adds the pipe capacity and flow direction to every street field
	 * 
	 * @param s_node start node of the street
	 * @param e_node end node of the street
	 */
	public void addPipeCapacity() {
		deleteAllFlowCapAndDir();
		int a[][] = new int[dg.getSize()][dg.getSize()];
		a = dg.getGraph();
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < a[0].length; j++) {
				if (a[i][j] != 0) {
					// Fall 1: Knoten liegen in der selben Zeile und s_node liegt links
					// von e_node
					if (dg.getNodes().get(i - 1).getRowIndex() == dg.getNodes().get(j - 1).getRowIndex()
							&& dg.getNodes().get(i - 1).getColIndex() < dg.getNodes().get(j - 1).getColIndex()) {
						for (int colIndex = dg.getNodes().get(i - 1).getColIndex() + 1; colIndex < dg.getNodes()
								.get(j - 1).getColIndex() - 1; colIndex++) {
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex() - 1][colIndex])
									.setPipeCapacity(a[i][j]);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex()][colIndex]).setPipeCapacity(a[i][j]);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex() - 1][colIndex])
									.setFlowDirection(East);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex()][colIndex]).setFlowDirection(East);
						}
					}

					// Fall 2: Knoten liegen in der selben Zeile s_node liegt rechts von e_node
					if (dg.getNodes().get(i - 1).getRowIndex() == dg.getNodes().get(j - 1).getRowIndex()
							&& dg.getNodes().get(i - 1).getColIndex() > dg.getNodes().get(j - 1).getColIndex()) {
						for (int colIndex = dg.getNodes().get(i - 1).getColIndex() - 2; colIndex > dg.getNodes()
								.get(j - 1).getColIndex(); colIndex--) {

							((Street) grid[dg.getNodes().get(i - 1).getRowIndex() - 1][colIndex])
									.setPipeCapacity(a[i][j]);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex()][colIndex]).setPipeCapacity(a[i][j]);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex() - 1][colIndex])
									.setFlowDirection(West);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex()][colIndex]).setFlowDirection(West);
						}
					}

					// Fall 3: Knoten liegen in der selben Spalte und Startknoten liegt
					// oberhalb von Endknoten
					if (dg.getNodes().get(i - 1).getColIndex() == dg.getNodes().get(j - 1).getColIndex()
							&& dg.getNodes().get(i - 1).getRowIndex() < dg.getNodes().get(j - 1).getRowIndex()) {
						for (int rowIndex = dg.getNodes().get(i - 1).getRowIndex() + 1; rowIndex < dg.getNodes()
								.get(j - 1).getRowIndex() - 1; rowIndex++) {
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex() - 1])
									.setPipeCapacity(a[i][j]);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex()]).setPipeCapacity(a[i][j]);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex() - 1])
									.setFlowDirection(South);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex()]).setFlowDirection(South);
						}
					}

					// Fall 4: Knoten liegen in der selben Spalte, s_node liegt
					// unterhalb von e_node
					if (dg.getNodes().get(i - 1).getColIndex() == dg.getNodes().get(j - 1).getColIndex()
							&& dg.getNodes().get(i - 1).getRowIndex() > dg.getNodes().get(j - 1).getRowIndex()) {
						for (int rowIndex = dg.getNodes().get(i - 1).getRowIndex() - 2; rowIndex > dg.getNodes()
								.get(j - 1).getRowIndex(); rowIndex--) {
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex() - 1])
									.setPipeCapacity(a[i][j]);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex()]).setPipeCapacity(a[i][j]);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex() - 1])
									.setFlowDirection(North);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex()]).setFlowDirection(North);
						}
					}
				}
			}
		}
	}

	/**
	 * sets for each street field the pipe cost 0
	 */
	public void removeAllPipeCosts() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].getValue() == 1) {
					((Street) grid[i][j]).setPipeCosts(0);
				}
			}
		}
	}

	/**
	 * adds the pipe costs to every street field
	 */
	public void addPipeCosts() {
		removeAllPipeCosts();
		int a[][] = new int[udgm.getSize()][udgm.getSize()];
		a = udgm.getGraph();
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < a[0].length; j++) {
				if (a[i][j] != 0) {
					// Fall 1: Knoten liegen in der selben Zeile und s_node liegt links
					// von e_node
					// dg, da die selben Nodes verwendent werden wie fuer den gerichteten graph
					if (dg.getNodes().get(i - 1).getRowIndex() == dg.getNodes().get(j - 1).getRowIndex()
							&& dg.getNodes().get(i - 1).getColIndex() < dg.getNodes().get(j - 1).getColIndex()) {
						for (int colIndex = dg.getNodes().get(i - 1).getColIndex() + 1; colIndex < dg.getNodes()
								.get(j - 1).getColIndex() - 1; colIndex++) {
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex() - 1][colIndex]).setPipeCosts(a[i][j]);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex()][colIndex]).setPipeCosts(a[i][j]);

						}
					}

					// Fall 2: Knoten liegen in der selben Zeile s_node liegt rechts von e_node
					if (dg.getNodes().get(i - 1).getRowIndex() == dg.getNodes().get(j - 1).getRowIndex()
							&& dg.getNodes().get(i - 1).getColIndex() > dg.getNodes().get(j - 1).getColIndex()) {
						for (int colIndex = dg.getNodes().get(i - 1).getColIndex() - 2; colIndex > dg.getNodes()
								.get(j - 1).getColIndex(); colIndex--) {

							((Street) grid[dg.getNodes().get(i - 1).getRowIndex() - 1][colIndex]).setPipeCosts(a[i][j]);
							((Street) grid[dg.getNodes().get(i - 1).getRowIndex()][colIndex]).setPipeCosts(a[i][j]);
						}
					}

					// Fall 3: Knoten liegen in der selben Spalte und Startknoten liegt
					// oberhalb von Endknoten
					if (dg.getNodes().get(i - 1).getColIndex() == dg.getNodes().get(j - 1).getColIndex()
							&& dg.getNodes().get(i - 1).getRowIndex() < dg.getNodes().get(j - 1).getRowIndex()) {
						for (int rowIndex = dg.getNodes().get(i - 1).getRowIndex() + 1; rowIndex < dg.getNodes()
								.get(j - 1).getRowIndex() - 1; rowIndex++) {
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex() - 1]).setPipeCosts(a[i][j]);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex()]).setPipeCosts(a[i][j]);
						}
					}

					// Fall 4: Knoten liegen in der selben Spalte, s_node liegt
					// unterhalb von e_node
					if (dg.getNodes().get(i - 1).getColIndex() == dg.getNodes().get(j - 1).getColIndex()
							&& dg.getNodes().get(i - 1).getRowIndex() > dg.getNodes().get(j - 1).getRowIndex()) {
						for (int rowIndex = dg.getNodes().get(i - 1).getRowIndex() - 2; rowIndex > dg.getNodes()
								.get(j - 1).getRowIndex(); rowIndex--) {
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex() - 1]).setPipeCosts(a[i][j]);
							((Street) grid[rowIndex][dg.getNodes().get(i - 1).getColIndex()]).setPipeCosts(a[i][j]);
						}
					}
				}
			}
		}
	}

	/**
	 * addStreet GUI compatible
	 */
	public void addStreet(int startNodeRowIndex, int startNodeColIndex, int endNodeRowIndex, int endNodeColIndex) {

		boolean containsStartNode = false;
		boolean containsEndNode = false;
		int startNodeIndex = 0;
		int endNodeIndex = 0;
		System.out.println(startNodeRowIndex);
		System.out.println(startNodeColIndex);
		System.out.println(endNodeRowIndex);
		System.out.println(endNodeColIndex);
		for (int i = 0; i < dg.getNodes().size(); i++) {
			if (dg.getNodes().get(i).getRowIndex() == startNodeRowIndex
					&& dg.getNodes().get(i).getColIndex() == startNodeColIndex) {
				containsStartNode = true;
				startNodeIndex = i;

			}
		}

		for (int i = 0; i < dg.getNodes().size(); i++) {
			if (dg.getNodes().get(i).getRowIndex() == endNodeRowIndex
					&& dg.getNodes().get(i).getColIndex() == endNodeColIndex) {
				containsEndNode = true;
				endNodeIndex = i;

			}
		}

		if (containsStartNode && containsEndNode) {
			addStreet(dg.getNodes().get(startNodeIndex), dg.getNodes().get(endNodeIndex));
			setDirection(dg.getNodes().get(startNodeIndex));
			setDirection(dg.getNodes().get(endNodeIndex));
			// System.out.println("hallo");
		}

		if (containsStartNode && !containsEndNode) {
			Node newEndNode = new Node(endNodeRowIndex, endNodeColIndex);
			addStreet(dg.getNodes().get(startNodeIndex), newEndNode);
			init(newEndNode);
			setDirection(dg.getNodes().get(startNodeIndex));
			setDirection(newEndNode);
			// System.out.println("hallo");
		}

		if (!containsStartNode && containsEndNode) {
			Node newStartNode = new Node(startNodeRowIndex, startNodeColIndex);
			addStreet(newStartNode, dg.getNodes().get(endNodeIndex));
			init(newStartNode);
			setDirection(dg.getNodes().get(endNodeIndex));
			setDirection(newStartNode);
			// System.out.println("hallo");
		}

		if (!containsStartNode && !containsEndNode) {
			Node newStartNode = new Node(startNodeRowIndex, startNodeColIndex);
			Node newEndNode = new Node(endNodeRowIndex, endNodeColIndex);
			addStreet(newStartNode, newEndNode);
			init(newStartNode);
			init(newEndNode);
			setDirection(newStartNode);
			setDirection(newEndNode);
		}

	}

	public void addStreet(int startNodeRowIndex, int startNodeColIndex, int endNodeRowIndex, int endNodeColIndex,
			String s) {
		Node s_node = new Node(startNodeRowIndex, startNodeColIndex);
		Node e_node = new Node(endNodeRowIndex, endNodeColIndex);

		// Fall 1: Knoten liegen in der selben Zeile und s_node liegt links
		// von e_node
		if (s_node.rowIndex == e_node.rowIndex && s_node.colIndex < e_node.colIndex) {
			for (int colIndex = s_node.colIndex + 1; colIndex < e_node.colIndex - 1; colIndex++) {
				Street street1 = new Street(s_node.rowIndex, colIndex + 1);
				Street street2 = new Street(s_node.rowIndex + 1, colIndex + 1);
				grid[s_node.rowIndex - 1][colIndex] = street1;
				grid[s_node.rowIndex][colIndex] = street2;
				((Street) grid[s_node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[s_node.rowIndex][colIndex]).setDirection(East);
				// System.out.println(grid[s_node.rowIndex-1][colIndex] instanceof Street);
			}

		}
		// Fall 2: Knoten liegen in der selben Zeile und Startknoten liegt
		// rechts vom Endknoten
		if (s_node.rowIndex == e_node.rowIndex && s_node.colIndex > e_node.colIndex) {
			for (int colIndex = s_node.colIndex - 2; colIndex > e_node.colIndex; colIndex--) {
				Street street1 = new Street(s_node.rowIndex, colIndex + 1);
				Street street2 = new Street(s_node.rowIndex + 1, colIndex + 1);
				grid[s_node.rowIndex - 1][colIndex] = street1;
				grid[s_node.rowIndex][colIndex] = street2;
				((Street) grid[s_node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[s_node.rowIndex][colIndex]).setDirection(East);
				// System.out.println(s_node.rowIndex+1 +" "+(colIndex+1) +
				// " " + ((Street)
				// grid[s_node.rowIndex][colIndex]).getDirection());
			}
		}

		// Fall 3: Knoten liegen in der selben Spalte und Startknoten liegt
		// oberhalb von Endknoten
		if (s_node.colIndex == e_node.colIndex && s_node.rowIndex < e_node.rowIndex) {
			for (int rowIndex = s_node.rowIndex + 1; rowIndex < e_node.rowIndex - 1; rowIndex++) {
				Street street1 = new Street(rowIndex + 1, s_node.colIndex);
				Street street2 = new Street(rowIndex + 1, s_node.colIndex + 1);
				grid[rowIndex][s_node.colIndex - 1] = street1;
				grid[rowIndex][s_node.colIndex] = street2;
				((Street) grid[rowIndex][s_node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][s_node.colIndex]).setDirection(North);
				// System.out.println(s_node.rowIndex + " " + s_node.colIndex);
			}
		}

		// Fall 4: Knoten liegen in der selben Spalte, s_node liegt
		// unterhalb von e_node
		if (s_node.colIndex == e_node.colIndex && s_node.rowIndex > e_node.rowIndex) {
			for (int rowIndex = s_node.rowIndex - 2; rowIndex > e_node.rowIndex; rowIndex--) {
				Street street1 = new Street(rowIndex + 1, s_node.colIndex);
				Street street2 = new Street(rowIndex + 1, s_node.colIndex + 1);
				grid[rowIndex][s_node.colIndex - 1] = street1;
				grid[rowIndex][s_node.colIndex] = street2;
				((Street) grid[rowIndex][s_node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][s_node.colIndex]).setDirection(North);
			}
		}
		init(s_node);
		init(e_node);
		setDirection(s_node);
		setDirection(e_node);
	}

	/**
	 * adds a street to grid between two Nodes witch represents crossing
	 * 
	 * @param s_node start node of the street
	 * @param e_node end node of the street
	 */
	public void addStreet(Node s_node, Node e_node) {
		// Fall 1: Knoten liegen in der selben Zeile und s_node liegt links
		// von e_node
		if (s_node.rowIndex == e_node.rowIndex && s_node.colIndex < e_node.colIndex) {
			for (int colIndex = s_node.colIndex + 1; colIndex < e_node.colIndex - 1; colIndex++) {
				Street street1 = new Street(s_node.rowIndex, colIndex + 1);
				Street street2 = new Street(s_node.rowIndex + 1, colIndex + 1);
				grid[s_node.rowIndex - 1][colIndex] = street1;
				grid[s_node.rowIndex][colIndex] = street2;
				((Street) grid[s_node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[s_node.rowIndex][colIndex]).setDirection(East);
				// System.out.println(grid[s_node.rowIndex-1][colIndex] instanceof Street);
			}

		}
		// Fall 2: Knoten liegen in der selben Zeile und Startknoten liegt
		// rechts vom Endknoten
		if (s_node.rowIndex == e_node.rowIndex && s_node.colIndex > e_node.colIndex) {
			for (int colIndex = s_node.colIndex - 2; colIndex > e_node.colIndex; colIndex--) {
				Street street1 = new Street(s_node.rowIndex, colIndex + 1);
				Street street2 = new Street(s_node.rowIndex + 1, colIndex + 1);
				grid[s_node.rowIndex - 1][colIndex] = street1;
				grid[s_node.rowIndex][colIndex] = street2;
				((Street) grid[s_node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[s_node.rowIndex][colIndex]).setDirection(East);
				// System.out.println(s_node.rowIndex+1 +" "+(colIndex+1) +
				// " " + ((Street)
				// grid[s_node.rowIndex][colIndex]).getDirection());
			}
		}

		// Fall 3: Knoten liegen in der selben Spalte und Startknoten liegt
		// oberhalb von Endknoten
		if (s_node.colIndex == e_node.colIndex && s_node.rowIndex < e_node.rowIndex) {
			for (int rowIndex = s_node.rowIndex + 1; rowIndex < e_node.rowIndex - 1; rowIndex++) {
				Street street1 = new Street(rowIndex + 1, s_node.colIndex);
				Street street2 = new Street(rowIndex + 1, s_node.colIndex + 1);
				grid[rowIndex][s_node.colIndex - 1] = street1;
				grid[rowIndex][s_node.colIndex] = street2;
				((Street) grid[rowIndex][s_node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][s_node.colIndex]).setDirection(North);
				// System.out.println(s_node.rowIndex + " " + s_node.colIndex);
			}
		}

		// Fall 4: Knoten liegen in der selben Spalte, s_node liegt
		// unterhalb von e_node
		if (s_node.colIndex == e_node.colIndex && s_node.rowIndex > e_node.rowIndex) {
			for (int rowIndex = s_node.rowIndex - 2; rowIndex > e_node.rowIndex; rowIndex--) {
				Street street1 = new Street(rowIndex + 1, s_node.colIndex);
				Street street2 = new Street(rowIndex + 1, s_node.colIndex + 1);
				grid[rowIndex][s_node.colIndex - 1] = street1;
				grid[rowIndex][s_node.colIndex] = street2;
				((Street) grid[rowIndex][s_node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][s_node.colIndex]).setDirection(North);
			}
		}

	}

	/**
	 * creates a street to the border of the map, sets the speed limit and direction
	 * of each street field
	 * 
	 * @param node      start node of the street
	 * @param direction direction of the street
	 * @param topspeed  speed limit of the street
	 */
	public void addStreet3(Node node, Direction direction, int topspeed) {
		if (direction == North) {
			for (int rowIndex = node.rowIndex - 2; rowIndex >= 0; rowIndex--) {
				Street street1 = new Street(rowIndex + 1, node.colIndex);
				Street street2 = new Street(rowIndex + 1, node.colIndex + 1);
				grid[rowIndex][node.colIndex - 1] = street1;
				grid[rowIndex][node.colIndex] = street2;
				((Street) grid[rowIndex][node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][node.colIndex]).setDirection(North);

			}
		}
		if (direction == South) {
			for (int rowIndex = node.rowIndex + 1; rowIndex < grid.length; rowIndex++) {
				Street street1 = new Street(rowIndex + 1, node.colIndex);
				Street street2 = new Street(rowIndex + 1, node.colIndex + 1);
				grid[rowIndex][node.colIndex - 1] = street1;
				grid[rowIndex][node.colIndex] = street2;
				((Street) grid[rowIndex][node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][node.colIndex]).setDirection(North);

			}
		}
		if (direction == West) {
			for (int colIndex = node.colIndex - 2; colIndex >= 0; colIndex--) {
				Street street1 = new Street(node.rowIndex, colIndex + 1);
				Street street2 = new Street(node.rowIndex + 1, colIndex + 1);
				grid[node.rowIndex - 1][colIndex] = street1;
				grid[node.rowIndex][colIndex] = street2;
				((Street) grid[node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[node.rowIndex][colIndex]).setDirection(East);

			}
		}
		if (direction == East) {
			for (int colIndex = node.colIndex + 1; colIndex < grid[0].length; colIndex++) {
				Street street1 = new Street(node.rowIndex, colIndex + 1);
				Street street2 = new Street(node.rowIndex + 1, colIndex + 1);
				grid[node.rowIndex - 1][colIndex] = street1;
				grid[node.rowIndex][colIndex] = street2;
				((Street) grid[node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[node.rowIndex][colIndex]).setDirection(East);

			}
		}

	}

	/**
	 * creates a street to the border of the map, sets the speed limit and direction
	 * of each street field
	 * 
	 * @param node      start node of the street
	 * @param direction direction of the street
	 * @param topspeed  speed limit of the street
	 */
	public void addConnectionRoad(Node node, Direction direction, int topspeed) {
		if (direction == North) {
			for (int rowIndex = node.rowIndex - 2; rowIndex >= 0; rowIndex--) {
				Street street1 = new Street(rowIndex + 1, node.colIndex);
				Street street2 = new Street(rowIndex + 1, node.colIndex + 1);
				grid[rowIndex][node.colIndex - 1] = street1;
				grid[rowIndex][node.colIndex] = street2;
				street1.setSpeedLimit(topspeed);
				street2.setSpeedLimit(topspeed);
				((Street) grid[rowIndex][node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][node.colIndex]).setDirection(North);
				((Street) grid[rowIndex][node.colIndex - 1]).setConnectingRoad();
				((Street) grid[rowIndex][node.colIndex]).setConnectingRoad();

			}
		}
		if (direction == South) {
			for (int rowIndex = node.rowIndex + 1; rowIndex < grid.length; rowIndex++) {
				Street street1 = new Street(rowIndex + 1, node.colIndex);
				Street street2 = new Street(rowIndex + 1, node.colIndex + 1);
				grid[rowIndex][node.colIndex - 1] = street1;
				grid[rowIndex][node.colIndex] = street2;
				street1.setSpeedLimit(topspeed);
				street2.setSpeedLimit(topspeed);
				((Street) grid[rowIndex][node.colIndex - 1]).setDirection(South);
				((Street) grid[rowIndex][node.colIndex]).setDirection(North);
				((Street) grid[rowIndex][node.colIndex - 1]).setConnectingRoad();
				((Street) grid[rowIndex][node.colIndex]).setConnectingRoad();

			}
		}
		if (direction == West) {
			for (int colIndex = node.colIndex - 2; colIndex >= 0; colIndex--) {
				Street street1 = new Street(node.rowIndex, colIndex + 1);
				Street street2 = new Street(node.rowIndex + 1, colIndex + 1);
				grid[node.rowIndex - 1][colIndex] = street1;
				grid[node.rowIndex][colIndex] = street2;
				street1.setSpeedLimit(topspeed);
				street2.setSpeedLimit(topspeed);
				((Street) grid[node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[node.rowIndex][colIndex]).setDirection(East);
				((Street) grid[node.rowIndex - 1][colIndex]).setConnectingRoad();
				((Street) grid[node.rowIndex][colIndex]).setConnectingRoad();

			}
		}
		if (direction == East) {
			for (int colIndex = node.colIndex + 1; colIndex < grid[0].length; colIndex++) {
				Street street1 = new Street(node.rowIndex, colIndex + 1);
				Street street2 = new Street(node.rowIndex + 1, colIndex + 1);
				grid[node.rowIndex - 1][colIndex] = street1;
				grid[node.rowIndex][colIndex] = street2;
				street1.setSpeedLimit(topspeed);
				street2.setSpeedLimit(topspeed);
				((Street) grid[node.rowIndex - 1][colIndex]).setDirection(West);
				((Street) grid[node.rowIndex][colIndex]).setDirection(East);
				((Street) grid[node.rowIndex - 1][colIndex]).setConnectingRoad();
				((Street) grid[node.rowIndex][colIndex]).setConnectingRoad();

			}
		}

	}

	/**
	 * sets the speed limit for a street where both nodes are outside the street
	 * 
	 * @param start_node start node of the street
	 * @param end_node   end node of the street
	 * @param speedlimit speed limit of the street
	 */
	public void setSpeedLimit0(Node start_node, Node end_node, int speedlimit) {
		// Fall 1: Start und Endknoten liegen in der selben Zeile und der Startknoten
		// liegt links vom Endknoten
		if (start_node.rowIndex == end_node.rowIndex && start_node.colIndex < end_node.colIndex) {
			for (int colIndex = start_node.colIndex + 1; colIndex < end_node.colIndex - 1; colIndex++) {
				((Street) grid[start_node.rowIndex - 1][colIndex]).setSpeedLimit(speedlimit);
				((Street) grid[start_node.rowIndex][colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 2: Start und Endknoten liegen in der selben Zeile und der Startknoten
		// liegt rechts vom Endknoten
		if (start_node.rowIndex == end_node.rowIndex && start_node.colIndex > end_node.colIndex) {
			for (int colIndex = start_node.colIndex - 2; colIndex > end_node.colIndex; colIndex--) {
				((Street) grid[start_node.rowIndex - 1][colIndex]).setSpeedLimit(speedlimit);
				((Street) grid[start_node.rowIndex][colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 3: Knoten liegen in der selben Spalte und Startknoten liegt oberhalb von
		// Endknoten
		if (start_node.colIndex == end_node.colIndex && start_node.rowIndex < end_node.rowIndex) {
			for (int rowIndex = start_node.rowIndex + 1; rowIndex < end_node.rowIndex - 1; rowIndex++) {
				((Street) grid[rowIndex][start_node.colIndex - 1]).setSpeedLimit(speedlimit);
				((Street) grid[rowIndex][start_node.colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 4: Knoten liegen in der selben Spalte, start_node liegt unterhalb von
		// end_node
		if (start_node.colIndex == end_node.colIndex && start_node.rowIndex > end_node.rowIndex) {
			for (int rowIndex = start_node.rowIndex - 1; rowIndex > end_node.rowIndex; rowIndex--) {
				((Street) grid[rowIndex][start_node.colIndex - 1]).setSpeedLimit(speedlimit);
				((Street) grid[rowIndex][start_node.colIndex]).setSpeedLimit(speedlimit);
			}
		}
	}

	/**
	 * sets the speed limit for a street where just one of the nodes belongs to the
	 * street. Always the start node belongs to the street
	 * 
	 * @param start_node
	 * @param end_node
	 * @param speedlimit
	 */
	public void setSpeedLimit1(Node start_node, Node end_node, int speedlimit) {
		// Fall 1: Knoten liegen in der selben Zeile und start_node liegt links von
		// end_node
		if (start_node.rowIndex == end_node.rowIndex && start_node.colIndex < end_node.colIndex) {
			for (int colIndex = start_node.colIndex - 1; colIndex < end_node.colIndex - 1; colIndex++) {
				((Street) grid[start_node.rowIndex - 1][colIndex]).setSpeedLimit(speedlimit);
				((Street) grid[start_node.rowIndex][colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 2: Knoten liegen in der selben Zeile und start_node liegt rechts von
		// end_node
		if (start_node.rowIndex == end_node.rowIndex && start_node.colIndex > end_node.colIndex) {
			for (int colIndex = start_node.colIndex; colIndex > end_node.colIndex; colIndex--) {
				((Street) grid[start_node.rowIndex - 1][colIndex]).setSpeedLimit(speedlimit);
				((Street) grid[start_node.rowIndex][colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 3: Knoten liegen in der selben spalte start_node liegt oberhalb von
		// end_node
		if (start_node.colIndex == end_node.colIndex && start_node.rowIndex < end_node.rowIndex) {
			for (int rowIndex = start_node.rowIndex - 1; rowIndex < end_node.rowIndex; rowIndex++) {
				((Street) grid[rowIndex][start_node.colIndex - 1]).setSpeedLimit(speedlimit);
				((Street) grid[rowIndex][start_node.colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 4: Knoten liegen in der selben Spalte, start_node liegt unterhalb von
		// end_node
		if (start_node.colIndex == end_node.colIndex && start_node.rowIndex > end_node.rowIndex) {
			for (int rowIndex = start_node.rowIndex; rowIndex > end_node.rowIndex; rowIndex--) {
				((Street) grid[rowIndex][start_node.colIndex - 1]).setSpeedLimit(speedlimit);
				((Street) grid[rowIndex][start_node.colIndex]).setSpeedLimit(speedlimit);
			}

		}
	}

	/**
	 * sets the speed limit for a street where both of the nodes belongs to the
	 * street.
	 * 
	 * @param start_node start node of the street
	 * @param end_node   end node of the street
	 * @param speedlimit speed limit of the street
	 */
	public void setSpeedLimit2(Node start_node, Node end_node, int speedlimit) {
		// Fall 1: Knoten liegen in der selben Zeile und start_node liegt links von
		// end_node
		if (start_node.rowIndex == end_node.rowIndex && start_node.colIndex < end_node.colIndex) {
			for (int colIndex = start_node.colIndex - 1; colIndex < end_node.colIndex + 1; colIndex++) {
				((Street) grid[start_node.rowIndex - 1][colIndex]).setSpeedLimit(speedlimit);
				((Street) grid[start_node.rowIndex][colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 2: Knoten liegen in der selben Zeile und start_node liegt rechts von
		// end_node
		if (start_node.rowIndex == end_node.rowIndex && start_node.colIndex > end_node.colIndex) {
			for (int colIndex = start_node.colIndex; colIndex > end_node.colIndex - 2; colIndex--) {
				((Street) grid[start_node.rowIndex - 1][colIndex]).setSpeedLimit(speedlimit);
				((Street) grid[start_node.rowIndex][colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 3: Knoten liegen in der selben spalte start_node liegt oberhalb von
		// end_node
		if (start_node.colIndex == end_node.colIndex && start_node.rowIndex < end_node.rowIndex) {
			for (int rowIndex = start_node.rowIndex - 1; rowIndex < end_node.rowIndex + 1; rowIndex++) {
				((Street) grid[rowIndex][start_node.colIndex - 1]).setSpeedLimit(speedlimit);
				((Street) grid[rowIndex][start_node.colIndex]).setSpeedLimit(speedlimit);
			}
		}

		// Fall 4: Knoten liegen in der selben Spalte, start_node liegt unterhalb von
		// end_node
		if (start_node.colIndex == end_node.colIndex && start_node.rowIndex > end_node.rowIndex) {
			for (int rowIndex = start_node.rowIndex; rowIndex > end_node.rowIndex - 2; rowIndex--) {
				((Street) grid[rowIndex][start_node.colIndex - 1]).setSpeedLimit(speedlimit);
				((Street) grid[rowIndex][start_node.colIndex]).setSpeedLimit(speedlimit);
			}

		}
	}

	/**
	 * creates a street to the border of the map, sets the speed limit and direction
	 * of each street field
	 * 
	 * @param node      start node of the street
	 * @param direction direction of the street
	 * @param topspeed  speed limit of the street
	 */
	public void setSpeedLimit3(Node node, Direction direction, int topspeed) {
		if (direction == North) {
			for (int rowIndex = node.rowIndex - 2; rowIndex >= 0; rowIndex--) {
				((Street) grid[rowIndex][node.colIndex - 1]).setSpeedLimit(topspeed);
				((Street) grid[rowIndex][node.colIndex]).setSpeedLimit(topspeed);
			}
		}
		if (direction == South) {
			for (int rowIndex = node.rowIndex + 1; rowIndex < grid.length; rowIndex++) {
				((Street) grid[rowIndex][node.colIndex - 1]).setSpeedLimit(topspeed);
				((Street) grid[rowIndex][node.colIndex]).setSpeedLimit(topspeed);
			}
		}
		if (direction == East) {
			for (int colIndex = node.colIndex - 2; colIndex >= 0; colIndex--) {
				((Street) grid[node.rowIndex - 1][colIndex]).setSpeedLimit(topspeed);
				((Street) grid[node.rowIndex][colIndex]).setSpeedLimit(topspeed);
			}
		}
		if (direction == West) {
			for (int colIndex = node.colIndex + 1; colIndex < grid[0].length; colIndex++) {
				((Street) grid[node.rowIndex - 1][colIndex]).setSpeedLimit(topspeed);
				((Street) grid[node.rowIndex][colIndex]).setSpeedLimit(topspeed);
			}
		}

	}

	/**
	 * setSpeedLimit GUI compatible
	 * 
	 * @param startNodeRowIndex row index of start node
	 * @param startNodeColIndex column index of start node
	 * @param endNodeRowIndex   row index of end node
	 * @param endNodeColIndex   column index of end node
	 * @param topspeed          speed limit
	 * @param number            number of nodes witch belongs to the street
	 */
	public void setSpeedLimit(int startNodeRowIndex, int startNodeColIndex, int endNodeRowIndex, int endNodeColIndex,
			int topspeed, int number) {

		boolean containsStartNode = false;
		boolean containsEndNode = false;
		int startNodeIndex = 0;
		int endNodeIndex = 0;
		// prueft, ob der Startknoten und Endknoten in der Liste aller Konten enthalten
		// ist
		// und setzt containsStartNode bzw EndNode auf true wenn dem so ist
		for (int i = 0; i < dg.getNodes().size(); i++) {
			if (dg.getNodes().get(i).getRowIndex() == startNodeRowIndex
					&& dg.getNodes().get(i).getColIndex() == startNodeColIndex) {
				containsStartNode = true;
				startNodeIndex = i;
			}
		}

		for (int i = 0; i < dg.getNodes().size(); i++) {
			if (dg.getNodes().get(i).getRowIndex() == endNodeRowIndex
					&& dg.getNodes().get(i).getColIndex() == endNodeColIndex) {
				containsEndNode = true;
				endNodeIndex = i;
			}
		}
		// wenn beide enthalten sind, erzeuge keinen neuen Knoten
		// sonder waehle jeweilige Knoten aus der Knotenliste
		if (containsStartNode && containsEndNode) {
			if (number == 0) {
				setSpeedLimit0(dg.getNodes().get(startNodeIndex), dg.getNodes().get(endNodeIndex), topspeed);
			}
			if (number == 1) {
				setSpeedLimit1(dg.getNodes().get(startNodeIndex), dg.getNodes().get(endNodeIndex), topspeed);
			}
			if (number == 2) {
				setSpeedLimit2(dg.getNodes().get(startNodeIndex), dg.getNodes().get(endNodeIndex), topspeed);

			}

		}
		// wenn nur der start knoten enthalten ist, erzeuge neuen end knoten und waehle
		// startknoten aus der liste
		if (containsStartNode && !containsEndNode) {
			Node newEndNode = new Node(endNodeRowIndex, endNodeColIndex);
			if (number == 0) {
				setSpeedLimit0(dg.getNodes().get(startNodeIndex), newEndNode, topspeed);
			}
			if (number == 1) {
				setSpeedLimit1(dg.getNodes().get(startNodeIndex), newEndNode, topspeed);
			}
			if (number == 2) {
				setSpeedLimit2(dg.getNodes().get(startNodeIndex), newEndNode, topspeed);
			}
		}
		// wenn nur der end knoten enthalten ist, erzeuge neuen start knoten und waehle
		// startknoten aus der liste
		if (!containsStartNode && containsEndNode) {
			Node newStartNode = new Node(startNodeRowIndex, startNodeColIndex);

			if (number == 0) {
				setSpeedLimit0(newStartNode, dg.getNodes().get(endNodeIndex), topspeed);
			}
			if (number == 1) {
				setSpeedLimit1(newStartNode, dg.getNodes().get(endNodeIndex), topspeed);
			}
			if (number == 2) {
				setSpeedLimit2(newStartNode, dg.getNodes().get(endNodeIndex), topspeed);
			}

		}
		// ist keiner von beiden enthalten, erzeuge beide Knoten neu
		if (!containsStartNode && !containsEndNode) {
			Node newStartNode = new Node(startNodeRowIndex, startNodeColIndex);
			Node newEndNode = new Node(endNodeRowIndex, endNodeColIndex);

			if (number == 0) {
				setSpeedLimit0(newStartNode, newEndNode, topspeed);
			}
			if (number == 1) {
				setSpeedLimit1(newStartNode, newEndNode, topspeed);
			}
			if (number == 2) {
				setSpeedLimit2(newStartNode, newEndNode, topspeed);
			}
			// setSpeedLimit(newStartNode, newEndNode, topspeed, number);
		}
	}

	/**
	 * Calls the method setSpeedlimit_number
	 * 
	 * @param start_node
	 * @param end_node
	 * @param topspeed
	 * @param number
	 */
	public void setSpeedLimit(Node start_node, Node end_node, int topspeed, int number) {
		if (number == 0) {
			setSpeedLimit0(start_node, end_node, topspeed);
		}
		if (number == 1) {
			setSpeedLimit1(start_node, end_node, topspeed);
		}
		if (number == 2) {
			setSpeedLimit2(start_node, end_node, topspeed);
		}

	}

	/**
	 * initialized a crossing as four street fields
	 * 
	 * @param n represents the crossing
	 */
	public void init(Node n) {

		// Initialisiert die 4 Keuzungsfelder als Strassenfleder
		grid[n.rowIndex - 1][n.colIndex - 1] = new Street(n.rowIndex, n.colIndex);
		grid[n.rowIndex - 1][n.colIndex] = new Street(n.rowIndex, n.colIndex + 1);
		grid[n.rowIndex][n.colIndex - 1] = new Street(n.rowIndex + 1, n.colIndex);
		grid[n.rowIndex][n.colIndex] = new Street(n.rowIndex + 1, n.colIndex + 1);
	}

	/**
	 * sets the speed limit for a street field witch is also a node field
	 * 
	 * @param n        node
	 * @param topSpeed speed limit
	 */
	public void initSpeed(Node n, int topSpeed) {
		((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setSpeedLimit(topSpeed);
		((Street) grid[n.rowIndex - 1][n.colIndex]).setSpeedLimit(topSpeed);
		((Street) grid[n.rowIndex][n.colIndex - 1]).setSpeedLimit(topSpeed);
		((Street) grid[n.rowIndex][n.colIndex]).setSpeedLimit(topSpeed);
	}

	/**
	 * sets the node fields as connecting roads
	 * 
	 * @param n node
	 */
	public void setConnectionNode(Node n) {
		((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setConnectingRoad();
		((Street) grid[n.rowIndex - 1][n.colIndex]).setConnectingRoad();
		((Street) grid[n.rowIndex][n.colIndex - 1]).setConnectingRoad();
		((Street) grid[n.rowIndex][n.colIndex]).setConnectingRoad();
	}

	/**
	 * Setting the Direction on Crossing represents by a Node
	 * 
	 * @param n represents the crossing
	 */
	public void setDirection(Node n) {
		init(n);
		ArrayList<Direction> direction = setDir(n);

		// Zaehle die Anzahl benachbarter Straßen
		int count_neighbours = 0;

		if (direction.contains(North) && grid[n.rowIndex - 2][n.colIndex - 1].getValue() == 1) {
			count_neighbours++;
		}
		if (direction.contains(East) && grid[n.rowIndex - 1][n.colIndex + 1].getValue() == 1) {
			count_neighbours++;
		}
		if (direction.contains(South) && grid[n.rowIndex + 1][n.colIndex - 1].getValue() == 1) {
			count_neighbours++;
		}
		if (direction.contains(West) && grid[n.rowIndex - 1][n.colIndex - 2].getValue() == 1) {
			count_neighbours++;
		}

		// 1. Fall: Echte Kreuzung
		if (count_neighbours == 4) {

			// links oben
			((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); // aeußerer Pfeil
			((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

			// links unten
			((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer Pfeil
			((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

			// rechts unten
			((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
			((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

			// rechts oben
			((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer Pfeil
			((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
		}

		// 2. Fall: T-Stueck
		else if (count_neighbours == 3) {
			/*
			 * Habe immer die unoetigen Pfeile rauskommentiert
			 */

			// Nord-Sued-Verbindung
			if (direction.contains(North) && direction.contains(South)) {
				if (grid[n.rowIndex - 2][n.colIndex - 1].getValue() == 1
						&& grid[n.rowIndex + 1][n.colIndex - 1].getValue() == 1) {
					// 2.1.1. Fall
					// |
					// |--
					// |
					if (direction.contains(East)) {
						if (grid[n.rowIndex - 1][n.colIndex + 1].getValue() == 1) {
							// links oben
							// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West);
							// //aeußerer Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

							// links unten
							((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer Pfeil
							((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

							// rechts unten
							((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
							((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

							// rechts oben
							((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeill

						}
						// 2.1.2. Fall
						// |
						// --|
						// |
						else {
							// links oben
							((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); // aeußerer Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

							// links unten
							((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer Pfeil
							((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

							// rechts unten
							// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
							((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

							// rechts oben
							((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
						}
					}
				}
			}
			// West-Ost-Verbidnung
			if (direction.contains(West) && direction.contains(East)) {
				if (grid[n.rowIndex - 1][n.colIndex - 2].getValue() == 1
						&& grid[n.rowIndex - 1][n.colIndex + 1].getValue() == 1) {
					// 2.2.1. Fall
					// |
					// -----
					if (direction.contains(North)) {
						if (grid[n.rowIndex - 2][n.colIndex - 1].getValue() == 1) {
							// links oben
							((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); // aeußerer Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

							// links unten
							// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer
							// Pfeil
							((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

							// rechts unten
							((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
							((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

							// rechts oben
							((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil

						}
						// 2.2.2. Fall
						// -----
						// |
						else {
							// links oben
							((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); // aeußerer Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

							// links unten
							((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer Pfeil
							((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

							// rechts unten
							((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
							((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

							// rechts oben
							// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer
							// Pfeil
							((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
						}
					}
				}
			}
		}

		// 3. Fall Kurve:
		//
		if (count_neighbours == 2) {
			// 3.1. Nord-Ost-Kurve
			// |__
			if (direction.contains(North) && direction.contains(East)) {
				if (grid[n.rowIndex - 2][n.colIndex - 1].getValue() == 1
						&& grid[n.rowIndex - 1][n.colIndex + 1].getValue() == 1) {

					// links oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); //
					// aeußerer Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

					// links unten
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

					// rechts unten
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer
					// Pfeil

				}
			}
			// 3.2. Sued-Ost-Kurve
			// __
			// |
			if (direction.contains(South) && direction.contains(East)) {
				if (grid[n.rowIndex + 1][n.colIndex - 1].getValue() == 1
						&& grid[n.rowIndex - 1][n.colIndex + 1].getValue() == 1) {
					// links oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); //
					// aeußerer Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

					// links unten
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

					// rechts unten
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
				}
			}
			// 3.3. Sued-West-Kurve
			// __
			// |
			if (direction.contains(South) && direction.contains(West)) {
				if (grid[n.rowIndex + 1][n.colIndex - 1].getValue() == 1
						&& grid[n.rowIndex - 1][n.colIndex - 2].getValue() == 1) {
					// links oben
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); //
					// innerer Pfeil

					// links unten
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer
					// Pfeil

					// rechts unten
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
				}
			}
			// 3.4. Nord-West-Kurve
			// __|
			if (direction.contains(North) && direction.contains(West)) {
				if (grid[n.rowIndex - 2][n.colIndex - 1].getValue() == 1
						&& grid[n.rowIndex - 1][n.colIndex + 1].getValue() == 1) {
					// links oben
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); //
					// innerer Pfeil

					// links unten
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

					// rechts unten
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer
					// Pfeil
				}
			}
		}
		// 4. Fall Sackgasse (Siehe Knoten X9)
		// Man kann den Koten als Wendehammer nutzen, falls nicht muss der Quer-Pfeil
		// gesperrt werden
		if (count_neighbours == 1) {
			// 4.1 von Norden
			if (direction.contains(North)) {
				if (grid[n.rowIndex - 2][n.colIndex - 1].getValue() == 1) {
					// links oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West);
					// //aeußerer Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

					// links unten
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

					// rechts unten
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer
					// Pfeil
				}
			}
			// 4.2 von Osten
			if (direction.contains(East)) {
				if (grid[n.rowIndex - 1][n.colIndex + 1].getValue() == 1) {
					// links oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West);
					// //aeußerer Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

					// links unten
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

					// rechts unten
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
				}
			}
			// 4.3 von Sueden (X9)
			if (direction.contains(South)) {
				if (grid[n.rowIndex + 1][n.colIndex - 1].getValue() == 1) {
					// links oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West);
					// //aeußerer Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); // innerer Pfeil

					// links unten
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer
					// Pfeil

					// rechts unten
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
				}
			}
			// 4.4 von Westen
			if (direction.contains(West)) {
				if (grid[n.rowIndex - 1][n.colIndex - 2].getValue() == 1) {
					// links oben
					((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(West); // aeußerer Pfeil
					// ((Street) grid[n.rowIndex - 1][n.colIndex - 1]).setDirection(South); //
					// innerer Pfeil

					// links unten
					// ((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(South); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex][n.colIndex - 1]).setDirection(East); // innerer Pfeil

					// rechts unten
					// ((Street) grid[n.rowIndex][n.colIndex]).setDirection(East); // aeußerer Pfeil
					((Street) grid[n.rowIndex][n.colIndex]).setDirection(North); // innerer Pfeil

					// rechts oben
					// ((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(North); // aeußerer
					// Pfeil
					((Street) grid[n.rowIndex - 1][n.colIndex]).setDirection(West); // innerer Pfeil
				}
			}
		}
		if (count_neighbours == 0) {
			System.out.println(
					"Keine gültige Anzahl Nachbarn für einen Kreuzungsknoten -> Wahrscheinlich Falscher Knoten");
			System.out.println();

		}

	}

	/**
	 * returns all directions in which it is possible to go from the respective
	 * house field
	 * 
	 * @param house field of type House
	 * @return list of possible directions
	 */
	public ArrayList<Direction> setDirSmallField(Field field) {
		ArrayList<Direction> posdir = new ArrayList<Direction>();
		// Knoten liegt an keinem Randstueck
		if (field.getRowIndex() > 1 && field.getColIndex() > 1 && field.getRowIndex() < grid.length
				&& field.getColIndex() < grid[0].length) {
			posdir.add(Direction.East);
			posdir.add(Direction.North);
			posdir.add(Direction.West);
			posdir.add(Direction.South);
		}
		// linkes obere Eck
		if (field.getRowIndex() == 1 && field.getColIndex() == 1) {
			posdir.add(Direction.South);
			posdir.add(Direction.East);
		}
		// rechtes obere Eck
		if (field.getRowIndex() == 1 && field.getColIndex() == grid[0].length) {
			posdir.add(Direction.West);
			posdir.add(Direction.South);
		}
		// linkes unteres Eck
		if (field.getRowIndex() == grid.length && field.getColIndex() == 1) {
			posdir.add(Direction.East);
			posdir.add(Direction.North);
		}
		// rechtes unteres Eck
		if (field.getRowIndex() == grid.length && field.getColIndex() == grid[0].length) {
			posdir.add(Direction.North);
			posdir.add(Direction.West);
		}
		// oberere Rand
		if (field.getRowIndex() == 1 && field.getColIndex() > 1 && field.getColIndex() < grid[0].length) {
			posdir.add(Direction.West);
			posdir.add(Direction.East);
			posdir.add(Direction.South);
		}
		// linker Rand
		if (field.getRowIndex() > 1 && field.getColIndex() == 1 && field.getRowIndex() < grid.length) {
			posdir.add(Direction.North);
			posdir.add(Direction.East);
			posdir.add(Direction.South);
		}
		// rechter Rand
		if (field.getRowIndex() > 1 && field.getRowIndex() < grid.length && field.getColIndex() == grid[0].length) {
			posdir.add(Direction.West);
			posdir.add(Direction.South);
			posdir.add(Direction.North);
		}
		// unterer Rand
		if (field.getRowIndex() == grid.length && field.getColIndex() > 1 && field.getColIndex() < grid[0].length) {
			posdir.add(Direction.North);
			posdir.add(Direction.East);
			posdir.add(Direction.West);
		}

		return posdir;
	}

	/**
	 * 
	 * @param field type of field, e.g. hospital, fire station..
	 * @return
	 */
	public ArrayList<Direction> setDirBigField(Field field) {
		ArrayList<Direction> dir = new ArrayList<Direction>();
		// Knoten liegt an keinem Randstueck
		if (field.getRowIndex() > 1 && field.getColIndex() > 1 && field.getRowIndex() < grid.length - 1
				&& field.getColIndex() < grid[0].length - 1) {
			dir.add(Direction.East);
			dir.add(Direction.North);
			dir.add(Direction.West);
			dir.add(Direction.South);

		}
		// linkes obere Eck
		if (field.getRowIndex() == 1 && field.getColIndex() == 1) {
			dir.add(Direction.South);
			dir.add(Direction.East);
		}
		// rechtes obere Eck
		if (field.getRowIndex() == 1 && field.getColIndex() == grid[0].length - 1) {
			dir.add(Direction.West);
			dir.add(Direction.South);
		}
		// linkes unteres Eck
		if (field.getRowIndex() == grid.length - 1 && field.getColIndex() == 1) {
			dir.add(Direction.East);
			dir.add(Direction.North);
		}
		// rechtes unteres Eck
		if (field.getRowIndex() == grid.length - 1 && field.getColIndex() == grid[0].length - 1) {
			dir.add(Direction.North);
			dir.add(Direction.West);
		}
		// oberere Rand
		if (field.getRowIndex() == 1 && field.getColIndex() > 1 && field.getColIndex() < grid[0].length - 1) {
			dir.add(Direction.West);
			dir.add(Direction.East);
			dir.add(Direction.South);
		}
		// linker Rand
		if (field.getRowIndex() > 1 && field.getColIndex() == 1 && field.getRowIndex() < grid.length - 1) {
			dir.add(Direction.North);
			dir.add(Direction.East);
			dir.add(Direction.South);
		}
		// rechter Rand
		if (field.getRowIndex() > 1 && field.getRowIndex() < grid.length - 1
				&& field.getColIndex() == grid[0].length - 1) {
			dir.add(Direction.West);
			dir.add(Direction.South);
			dir.add(Direction.North);
		}
		// unterer Rand
		if (field.getRowIndex() == grid.length - 1 && field.getColIndex() > 1
				&& field.getColIndex() < grid[0].length - 1) {
			dir.add(Direction.North);
			dir.add(Direction.East);
			dir.add(Direction.West);
		}

		return dir;
	}

	/**
	 * returns all directions in which it is possible to go from the respective Node
	 * 
	 * @param n Node
	 * @return list with possible directions
	 */
	public ArrayList<Direction> setDir(Node n) {
		ArrayList<Direction> dir = new ArrayList<Direction>();
		// Knoten liegt an keinem Randstueck
		if (n.getRowIndex() > 1 && n.getColIndex() > 1 && n.getRowIndex() < grid.length - 1
				&& n.getColIndex() < grid[0].length - 1) {
			dir.add(Direction.East);
			dir.add(Direction.North);
			dir.add(Direction.West);
			dir.add(Direction.South);

		}
		// linkes obere Eck
		if (n.getRowIndex() == 1 && n.getColIndex() == 1) {
			dir.add(Direction.South);
			dir.add(Direction.East);
		}
		// rechtes obere Eck
		if (n.getRowIndex() == 1 && n.getColIndex() == grid[0].length - 1) {
			dir.add(Direction.West);
			dir.add(Direction.South);
		}
		// linkes unteres Eck
		if (n.getRowIndex() == grid.length - 1 && n.getColIndex() == 1) {
			dir.add(Direction.East);
			dir.add(Direction.North);
		}
		// rechtes unteres Eck
		if (n.getRowIndex() == grid.length - 1 && n.getColIndex() == grid[0].length - 1) {
			dir.add(Direction.North);
			dir.add(Direction.West);
		}
		// oberere Rand
		if (n.getRowIndex() == 1 && n.getColIndex() > 1 && n.getColIndex() < grid[0].length - 1) {
			dir.add(Direction.West);
			dir.add(Direction.East);
			dir.add(Direction.South);
		}
		// linker Rand
		if (n.getRowIndex() > 1 && n.getColIndex() == 1 && n.getRowIndex() < grid.length - 1) {
			dir.add(Direction.North);
			dir.add(Direction.East);
			dir.add(Direction.South);
		}
		// rechter Rand
		if (n.getRowIndex() > 1 && n.getRowIndex() < grid.length - 1 && n.getColIndex() == grid[0].length - 1) {
			dir.add(Direction.West);
			dir.add(Direction.South);
			dir.add(Direction.North);
		}
		// unterer Rand
		if (n.getRowIndex() == grid.length - 1 && n.getColIndex() > 1 && n.getColIndex() < grid[0].length - 1) {
			dir.add(Direction.North);
			dir.add(Direction.East);
			dir.add(Direction.West);
		}

		return dir;
	}

	/**
	 * gets the coordinates for a street field in front of a specific field type
	 * 
	 * @param f a Field in map Coordinates
	 * @return a street field in front of a specific field type
	 */
	public Field getStreetField(Field f) {

		Field frontfield;
		// Schrottplatz
		if (f instanceof ScrapYard) {
			int xValue = 22;
			int yValue = 4;
			frontfield = new Field(xValue, yValue, 1);
			return frontfield;
		}
		// Park
		if (f instanceof Park) {
			int xValue = 4;
			int yValue = 11;
			frontfield = new Field(xValue, yValue, 1);
			return frontfield;

		}
		// Krankenhaus
		if (f instanceof Hospital) {
			int xValue = 6;
			int yValue = 25;
			frontfield = new Field(xValue, yValue, 1);
			return frontfield;
		}

		// Festhalle
		if (f instanceof BanquetHall) {
			int xValue = 33;
			int yValue = 24;
			frontfield = new Field(xValue, yValue, 1);
			return frontfield;
		}
		if (f instanceof FireStation) {
			// Falls Feuerwache 1
			if (f.getRowIndex() == 10 || f.getRowIndex() == 11) {
				int xValue = 13;
				int yValue = 19;
				frontfield = new Field(xValue, yValue, 1);
				return frontfield;
			}
			if (f.getRowIndex() == 23 || f.getRowIndex() == 24) {
				int xValue = 36;
				int yValue = 8;
				frontfield = new Field(xValue, yValue, 1);
				return frontfield;
			}
		}
		return null;
	}

	/**
	 * returns a random street field and transform to Cartesian coordinates for cars
	 * 
	 * @return a random street field in grid
	 */
	public Field getRandomStreetField() {
		ArrayList<Field> street_fields = new ArrayList<Field>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				// falls das Feld ein Strassenfeld ist wird es zu steet_fields hinzugefuegt
				if (grid[i][j].getValue() == 1) {
					Field f = new Field(i + 1, j + 1, 1);
					street_fields.add(f);
				}
			}
		}

		/*
		 * System.out.println("Alle Strassenfelder in MapKoordinaten"); for (int i = 0;
		 * i < street_fields.size(); i++) {
		 * 
		 * System.out.println( "row " + street_fields.get(i).getRowIndex() + " col " +
		 * street_fields.get(i).getColIndex()); }
		 */

		// neues Random Objekt, namens random
		Random random = new Random();
		// Ganzahlige Zufallszahl zwischen 0 und Anzahl der Strassen Felder
		int random_number = random.nextInt(street_fields.size() - 1);
		// Transformation in die Koordinaten von cars
		// System.out.println("Zeile" + street_fields.get(random_number).getRowIndex() +
		// "Spalte" + street_fields.get(random_number).getColIndex() );

		// System.out.println(random_number);
		int xValue = x_Value(street_fields.get(random_number));
		int yValue = y_Value(street_fields.get(random_number));
		/*
		 * System.out.println("zufaellig gewaehlte Strassenfeld in MapKoordinaten" +
		 * "");
		 * 
		 * System.out.println( street_fields.get(random_number).getRowIndex() + " " +
		 * street_fields.get(random_number).getColIndex());
		 */
		// Field car_Field = new Field(xValue, yValue, 1);
		return grid[xValue - 1][yValue - 1];
		// return car_Field;
	}

	/**
	 * returns for a field the x value in Cartesian coordinates
	 * 
	 * @param f Field
	 * @return x_Value of car
	 */
	public int x_Value(Field f) {

		return f.getColIndex() - 1;
	}

	/**
	 * returns for a field the y value in Cartesian coordinates
	 * 
	 * @param f Field
	 * @return y_Value of car
	 */
	public int y_Value(Field f) {
		// System.out.println("spalte" + f.getRowIndex());
		return grid.length - f.getRowIndex();
	}

	/**
	 * writes in map.text the Dimension of the grid in the first line and the values
	 * of the fields in the next lines writes in mapSpeed.txt the speed limit for
	 * every field, 0 for no street field writes in mapDirec.txt the direction for
	 * every field, empty list for no street field
	 */
	public void writeFile() {
		try {
			// Schreiben der FeldIDs
			FileWriter writer = new FileWriter("map.txt");
			BufferedWriter bwriter = new BufferedWriter(writer);
			// Schreibt in die erste Zeile die Dimension des Grids
			bwriter.write(this.rowDimension + ";" + this.colDimension);
			bwriter.newLine();
			for (int i = 0; i < this.rowDimension; i++) {
				for (int j = 0; j < this.colDimension; j++) {
					bwriter.write(grid[i][j].toDataString());
					// bwriter.newLine();
				}
				bwriter.newLine();
			}
			bwriter.close();
			// Schreiben der Geschwindigkeitsbegrenzung
			FileWriter speedWriter = new FileWriter("mapSpeed.txt");
			BufferedWriter bSpeedWriter = new BufferedWriter(speedWriter);
			for (int i = 0; i < this.rowDimension; i++) {
				for (int j = 0; j < this.colDimension; j++) {

					bSpeedWriter.write(grid[i][j].toDataString1());

				}
				bSpeedWriter.newLine();
			}
			bSpeedWriter.close();
			// Schreiben der Fahrtrichtung
			FileWriter directWriter = new FileWriter("mapDirection.txt");
			BufferedWriter bDirectWriter = new BufferedWriter(directWriter);
			for (int i = 0; i < this.rowDimension; i++) {
				for (int j = 0; j < this.colDimension; j++) {
					bDirectWriter.write(grid[i][j].toDataString2());
				}
				bDirectWriter.newLine();
			}
			bDirectWriter.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * reads the field values, speed limits and the direction for every Field
	 */

	public static void readFile() {

		InputStream input = Main.class.getResourceAsStream("map.txt");
		InputStream input1 = Main.class.getResourceAsStream("mapSpeed.txt");
		InputStream input2 = Main.class.getResourceAsStream("mapDirection.txt");

		// Read the InputStream in UTF-8 and use a BufferedReader to read lines
		try {
			// Einlesen der FeldIDs
			InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			String s = reader.readLine();
			String[] x = s.split(";");

			Grid newGrid = new Grid(Integer.parseInt(x[0]), Integer.parseInt(x[1]));

			// Read new lines until readLine() returns null
			for (int i = 0; i < newGrid.getRowDimension(); i++) {
				s = reader.readLine();
				String[] y = s.split(";");
				for (int j = 0; j < y.length; j++) {
					newGrid.getField(i + 1, j + 1).setValue(Integer.parseInt(y[j]));
					// Field muss zu Streetfield gecastestet werden
					if (newGrid.getField(i + 1, j + 1).getValue() == 1) {
						Street newStreetField = new Street(i + 1, j + 1);
						newGrid.setField(newStreetField);
					}
				}

			}

			// Einlesen der Geschwindigkeitsbegrenzungen
			InputStreamReader isr1 = new InputStreamReader(input1, StandardCharsets.UTF_8);
			BufferedReader reader1 = new BufferedReader(isr1);

			// Read new lines until readLine() returns null
			for (int i = 0; i < newGrid.getRowDimension(); i++) {
				String s1 = reader1.readLine();
				String[] y = s1.split(";");
				for (int j = 0; j < y.length; j++) {
					if (newGrid.getField(i + 1, j + 1).getValue() == 1) {
						((Street) newGrid.getField(i + 1, j + 1)).setSpeedLimit(Integer.parseInt(y[j]));
					}
				}
			}

			/*
			 * for (int i = 1; i <= newGrid.getRowDimension(); i++) { for (int j = 1; j <=
			 * newGrid.getColDimension(); j++) { if (newGrid.getField(i, j).getValue() == 1)
			 * { System.out.println( "row: " + i + " col: " + j + "  " + ((Street)
			 * newGrid.getField(i, j)).getSpeedLimit()); } } }
			 */

			// Einlesen der Richtungen
			InputStreamReader isr2 = new InputStreamReader(input2, StandardCharsets.UTF_8);
			BufferedReader reader2 = new BufferedReader(isr2);
			for (int i = 0; i < newGrid.getRowDimension(); i++) {
				String s2 = reader2.readLine();
				String[] y = s2.split(";");
				for (int j = 0; j < newGrid.getColDimension(); j++) {
					if (newGrid.getField(i + 1, j + 1).getValue() == 1) {

						if (y[j].equals("XN")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(North);
						}
						if (y[j].equals("XW")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(West);
						}
						if (y[j].equals("XE")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(East);
						}
						if (y[j].equals("XS")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(South);
						}
						if (y[j].equals("NW")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(North);
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(West);
						}
						if (y[j].equals("SW")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(South);
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(West);
						}
						if (y[j].equals("NE")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(North);
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(East);
						}
						if (y[j].equals("SE")) {
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(South);
							((Street) newGrid.getField(i + 1, j + 1)).setDirection(East);
						}
					}
				}
			}
			newGrid.print();

			for (int i = 1; i <= newGrid.getRowDimension(); i++) {
				for (int j = 1; j <= newGrid.getColDimension(); j++) {
					if (newGrid.getField(i, j).getValue() == 1) {
						System.out.println(
								"row: " + i + " col: " + j + "  " + ((Street) newGrid.getField(i, j)).getDirection());
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * sets a field in grid
	 * 
	 * @param f type of Field
	 */
	public void setField(Field f) {
		grid[f.getRowIndex() - 1][f.getColIndex() - 1] = f;
	}

	/**
	 * returns a certain Field in the 2-dimensional Array of type Field returns a
	 * Field in grid
	 */
	public Field getField(int rowIndex, int colIndex) {
		return grid[rowIndex - 1][colIndex - 1];
	}

	/**
	 * returns the row dimension of the map which is saved in grid
	 * 
	 * @return row dimension of grid
	 */
	public int getRowDimension() {
		return rowDimension;
	}

	public void setRowDimension(int rowDimension) {
		this.rowDimension = rowDimension;
	}

	/**
	 * returns the column dimension of the map which is saved in grid
	 * 
	 * @return row dimension of grid
	 */
	public int getColDimension() {
		return colDimension;
	}

	/**
	 * returns string representation of grid
	 * 
	 * @return grid as string
	 */
	public String toString() {
		return Arrays.deepToString(grid);
	}

	/**
	 * returns the
	 * 
	 * @return
	 */
	public String toDataString() {
		return Arrays.deepToString(grid);
	}

	/**
	 * prints the grid
	 */
	public void print() {
		for (Field[] row : grid)
			System.out.println(Arrays.deepToString(row));
	}

	/**
	 * @return grid as 2 dimensional array of Field
	 */
	public Field[][] getMap() {
		return grid;
	}

	@Override
	public UndirectedGraphMatrix getUndirGraph() {

		return udgm;
	}

	@Override
	public DirectedGraph getDirGraph() {

		return dg;
	}

}
