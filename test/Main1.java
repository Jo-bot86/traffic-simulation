package map;
//import java.util.*;

//


import map.field.fieldtype.*;



import map.field.Field;
//import map.*;
import map.field.fieldtype.BanquetHall;

import map.graph.undirected.Prim;
import map.field.fieldtype.FireStation;
import map.field.fieldtype.Hospital;
import map.field.fieldtype.House;
import map.field.fieldtype.Park;
import map.field.fieldtype.ScrapYard;
import map.field.fieldtype.WaterTank;
import map.graph.directed.DirectedGraph;
import map.graph.undirected.UndirectedGraph;
import map.graph.undirected.UndirectedGraphMatrix;
import navi.MaxFlowCalculator;

import java.util.ArrayList;
import java.util.Arrays;

import cars.Verwaltung;
import map.field.fieldtype.Street;
import map.Direction;
/**
 * 
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class Main1 {

	public static void main(String[] args) {
	
	
		//Erzeugt neue Karte der Groesse 30x40 mit leeren Feldern
		
/*		Grid grid = new Grid(30,40);
		
		
		
		
		WaterTank WT1= new WaterTank(15,3);			
		WaterTank WT2= new WaterTank(28,39);
		FireStation FS1= new FireStation(10,15);
		FireStation FS2= new FireStation(23,37);
		ScrapYard SY= new ScrapYard(27,23);
		ParkingSpot PS= new ParkingSpot(19,3);
		Hospital H = new Hospital(3,6);
		BanquetHall BH = new BanquetHall(6,35);
		House H1= new House(15,9);  // gelbes haus oben links
		House H2= new House(26,9);	// gelbes haus unten links
		House H3= new House(22,24);	// gelbes haus mitte rechts
		
		
		// fuegt Wassertank 1&2, Feuerwehrstation 1&2, Schrottplatz, Parkplatz, Krankenhaus,Festhalle, und 3 Standardgebaeude  dem grid hinzu
		grid.addBigField(WT1);
		grid.addBigField(WT2);
		grid.addBigField(FS1);
		grid.addBigField(FS2);
		grid.addBigField(SY);
		grid.addBigField(PS);
		grid.addBigField(H);
		grid.addField(BH);
		grid.addField(H1);
		grid.addField(H2);
		grid.addField(H3);
		
		
		
		//erstellt die Kreuzungsknoten
		Node x_1 = new Node(13,3);
		Node x_2 = new Node(7,3);
		Node x_3 = new Node(23,3);
		Node x_4 = new Node(27,3);
		Node x_5 = new Node(23,5);
		Node x_6 = new Node(13,9);
		Node x_7 = new Node(19,5);
		Node x_8 = new Node(7,6);
		Node x_9 = new Node(5,6);
		Node x_10 = new Node(7,9);
		Node x_11 = new Node(3,9);
		Node x_12 = new Node(27,13);
		Node x_13 = new Node(19,13);
		Node x_14 = new Node(13,13);
		Node x_15 = new Node(7,13);
		Node x_16 = new Node(3,15);
		Node x_17 = new Node(25,19);
		Node x_18 = new Node(19,19);
		Node x_19 = new Node(7,19);
		Node x_20 = new Node(3,19);
		Node x_21 = new Node(15,19);
		Node x_22 = new Node(25,25);
		Node x_23 = new Node(15,25);
		Node x_24 = new Node(7,25);
		Node x_25 = new Node(3,25);
		Node x_26 = new Node(11,29);
		Node x_27 = new Node(7,29);
		Node x_28 = new Node(3,37);
		Node x_29 = new Node(28,33);
		Node x_30 = new Node(25,33);
		Node x_31 = new Node(15,33);
		Node x_32 = new Node(11,33);
		Node x_33 = new Node(3,33);
		Node x_34 = new Node(21,33);
		Node x_35 = new Node(28,37);
		Node x_36 = new Node(21,37);
		
		
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
		
		for(int i=0; i<nodes.size(); i++) {
			grid.init(nodes.get(i));
		}
		
		// erzeugt die Strassen zwischen zwei Knoten wenn keiner der Knoten auf der Strasse liegt
		grid.addStreet(x_20, x_11);
		grid.setSpeedLimit(x_20, x_11, 70, 0);
		grid.addStreet(x_15, x_19);
		grid.setSpeedLimit(x_15,x_19,50,0);
		grid.addStreet(x_7, x_13);
		grid.setSpeedLimit(x_7, x_13, 30, 0);
		grid.addStreet(x_17, x_21);
		grid.setSpeedLimit(x_17, x_21, 30, 0);
		grid.addStreet(x_26, x_32);
		grid.setSpeedLimit(x_26, x_32, 30, 0);
		grid.addStreet(x_12, x_4);
		grid.setSpeedLimit(x_12, x_4, 70, 0);
		grid.addStreet(x_13, x_18);
		grid.setSpeedLimit(x_13, x_18, 30, 0);
		grid.addStreet(x_22, x_23);
		grid.setSpeedLimit(x_22, x_23, 30, 0);
		grid.addStreet(x_23, x_24);
		grid.setSpeedLimit(x_23, x_24, 30, 0);
		grid.addStreet(x_24, x_25);
		grid.setSpeedLimit(x_24, x_25, 100, 0);
		grid.addStreet(x_31, x_33);
		grid.setSpeedLimit(x_31, x_33, 30, 0);
		grid.addStreet(x_21,x_31);
		grid.setSpeedLimit(x_21, x_31, 70, 0);
		grid.addStreet(x_28, x_36);  
		grid.setSpeedLimit(x_28, x_36, 100, 0);  

		// erzeugt die Strassen zwischen zwei Knoten wenn einer der Knoten auf der Strasse liegt
		grid.addStreet(x_1, x_2);
		grid.setSpeedLimit(x_1, x_2, 30, 1);
		grid.addStreet(x_2, x_10);
		grid.setSpeedLimit(x_2, x_10, 30, 1);
		grid.addStreet(x_6, x_14);
		grid.setSpeedLimit(x_6, x_14, 70, 1);
		grid.addStreet(x_11, x_6);
		grid.setSpeedLimit(x_11, x_6, 70, 1);
		grid.addStreet(x_27, x_19);
		grid.setSpeedLimit(x_27, x_19, 50, 1);
		grid.addStreet(x_4, x_3);
		grid.setSpeedLimit(x_4, x_3, 30, 1);
		grid.addStreet(x_7, x_5);
		grid.setSpeedLimit(x_7, x_5, 30, 1);
		grid.addStreet(x_26, x_27);
		grid.setSpeedLimit(x_26, x_27, 30, 1);
		grid.addStreet(x_17, x_30);
		grid.setSpeedLimit(x_17, x_30, 50, 1);
		grid.addStreet(x_35, x_29);
		grid.setSpeedLimit(x_35, x_29, 30, 1);
		grid.addStreet(x_9, x_8);
		grid.setSpeedLimit(x_9, x_8, 30, 1);
		grid.addStreet(x_31, x_34);
		grid.setSpeedLimit(x_31, x_34, 70, 1);

		//erzeugt die Strassen zwischen zwei Knoten wenn beide auf der Strasse liegen
		grid.addStreet(x_15, x_12);
		grid.setSpeedLimit(x_15, x_12, 50, 2);
		grid.addStreet(x_28, x_25);
		grid.setSpeedLimit(x_28, x_25, 100, 2);
		grid.addStreet(x_5, x_3);
		grid.setSpeedLimit(x_5, x_3, 30, 2);
		grid.addStreet(x_21, x_20);
		grid.setSpeedLimit(x_21, x_20, 70, 2);
		grid.addStreet(x_34, x_36);
		grid.setSpeedLimit(x_34, x_36, 70, 2);


		//erzeugt die Strassen die zum Rand der Karte fuehren
		grid.addStreet3(x_16, Direction.North ,50);
		grid.setSpeedLimit3(x_16, Direction.North, 50);
		grid.addStreet3(x_34, Direction.South, 50);
		grid.setSpeedLimit3(x_34, Direction.South, 50);
		
		for(int i=0; i<nodes.size(); i++) {
			grid.setDirection(nodes.get(i));
		}
		
		
		
		grid.print();*/
		
		
		
		
		
		Grid grid = new Grid(30,40,0.0,0.0);
		//System.out.println(grid.speedLimits[6][2]);
		//System.out.println(((Street)grid.getField(7, 16)).is_constructionSite());
	/*	for(int i=1; i<60; i++) {
			for(int j=1; j<80; j++) {
				if(grid.getField(i, j).getValue()==1) {
					System.out.println(grid.getField(i, j).getRowIndex() + ";" + grid.getField(i, j).getColIndex() + "; " + ((Street)grid.getField(i, j)).is_constructionSite() );
				}
			}
		}*/
	//	System.out.println(((Street)grid.getField(7, 3)).getSpeedLimit());
	//	House h = new House(25,38);
	//	grid.addFieldMS2(h);
	//	grid.addStreet(15, 13, 15, 19);
		grid.print();
		
	//	grid.writeFile();
	//	Grid.readFile();
	//	System.out.println(grid.getStreetField(grid.getField(27, 23)).getRowIndex());
	//	System.out.println(((Street)grid.getField(19, 13)).getDirection());
	//	grid.print();
	
	//	Field f = grid.getRandomStreetField();
	//	System.out.println(" x Wert von cars:"+ " " +f.getRowIndex());
	//	System.out.println(" y Wert von cars:"+" "+f.getColIndex());
	
	//	DirectedGraph dg = new DirectedGraph(37);
	//	System.out.println(dg.getNodes().get(0).getRowIndex());
		
	
	/*	for(int i=0; i<37; i++) {
			for(int j=0; j<37; j++) {
				
					System.out.println("Kapaziät " + i + ";"+ j+ " "+dg.getWeight(i, j));
				
			}
		}*/
	
	//	dg.writeFile();
	//	DirectedGraph.readFile();
		
	//	System.out.println(dg.getWeight(1, 1));
	//	UndirectedGraph ug = new UndirectedGraph(0.0);
	/*	for(int i =0; i<ug.getSize(); i++) {
			System.out.println(ug.getGraph().get(i).getName() + " ; " +ug.getGraph().get(i).getRowIndex() + "; " + ug.getGraph().get(i).getColIndex());
		}*/
		
		
	//		System.out.println(ug.getGraph().get(13).getNeighbours().get(1).getEndNode().getName());
		
		
	//	Prim p = new Prim();
		
	//	System.out.println(p.prim(ug, ug.getGraph().get(0)).getGraph().get(0).getName());
	//	System.out.println(p.getTotalCosts());
		
		
	/*	for(int i=0; i< ug.getSize(); i++) {
			System.out.println(ug.getGraph().get(i).getName()+ ";"+ug.getGraph().get(i).getRowIndex() + " " + ug.getGraph().get(i).getColIndex());
			for(int j=0; j<ug.getGraph().get(i).getNeighbours().size();j++) {
				System.out.println(ug.getGraph().get(i).getNeighbours().get(j).getEndNode().getName() + " " +ug.getGraph().get(i).getNeighbours().get(j).getWeight() );
			}
		}*/
		
	//	System.out.println(grid.udgm.getWeight(15, 19));
	//	System.out.println(grid.getField(7, 15).getClass().getName());
		//UndirectedGraphMatrix ugm = Grid.udgm;
		//ugm.setWeight(15, 19, 0);
		//System.out.println(ugm.getWeight(15, 19));
		
	
	}
}
