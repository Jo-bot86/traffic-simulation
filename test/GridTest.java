package map;

import map.field.fieldtype.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import map.field.Field;
import map.Direction;

/**
 * Test class for methods in class grid
 * 
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
class GridTest {
	
	@Test
	void addStreetMS2Test() {
		System.out.println("addStreetdMS2 Test");
		System.out.println();
		Grid testGrid = new Grid(8, 8, 0.0);
		Node n1 = new Node(1, 2);
		Node n2 = new Node(1, 6);
		testGrid.addStreet(n1, n2);
		testGrid.addStreet(1, 1, 4, 1);
		testGrid.print();
	}

	@Test
	void addFieldMS2Test() {
		System.out.println("addFieldMS2 Test");
		System.out.println();
		Grid testGrid = new Grid(8, 8, 0.0);
		Node n1 = new Node(1, 2);
		Node n2 = new Node(1, 6);
//		Node n3 = new Node (7,2);
//		Node n4 = new Node (7,6);
		testGrid.addStreet(n1, n2);
//		testGrid.addStreet(n3, n4);
		testGrid.init(n1);
		testGrid.init(n2);
//		testGrid.init(n3);
//		testGrid.init(n4);
//		testGrid.setDirection(n1);
//		testGrid.setDirection(n2);
//		testGrid.setSpeedLimit(n1,n2,100,2);
//		testGrid.setSpeedLimit(n1,n2,50,2);
		
//		Hospital h = new Hospital(7, 4);
		
//		testGrid.addFieldMS2(h);
		testGrid.print();
	//	System.out.println(((Street)testGrid.getField(5, 4)).getDirection().get(0));
		System.out.println();
	}

	@Test
	void setFieldTest() {
		System.out.println("setField Test");
		System.out.println();
		Grid testGrid = new Grid(1, 1, 0.0);
		House h = new House(1, 1);
		testGrid.setField(h);
		// id fuer ein haus 3
		assertEquals(3, testGrid.getField(1, 1).getValue());
	}

	@Test
	void getFieldTest() {
		System.out.println("getField Test");
		System.out.println();
		Grid testGrid = new Grid(1, 1, 0.0);
		assertEquals(1, testGrid.getField(1, 1).getRowIndex());
		assertEquals(1, testGrid.getField(1, 1).getColIndex());

	}

	@Test
	void addPipeCapacityTest() {
		System.out.println("addPipeCapacity Test");
		System.out.println();
		Grid testGrid = new Grid(30, 40);
		testGrid.addPipeCapacity();
		// Kapzitaeten zwischen knoten x1 und x2, linke Spalte
		assertEquals(99, ((Street) testGrid.getField(12, 3)).getPipeCapacity());
		assertEquals(99, ((Street) testGrid.getField(11, 3)).getPipeCapacity());
		assertEquals(99, ((Street) testGrid.getField(10, 3)).getPipeCapacity());
		assertEquals(99, ((Street) testGrid.getField(9, 3)).getPipeCapacity());
		// Flussrichtung zwischen knoten x1 und x2, linke Spalte
		assertEquals(Direction.North, ((Street) testGrid.getField(12, 3)).getFlowDirection());
		assertEquals(Direction.North, ((Street) testGrid.getField(11, 3)).getFlowDirection());
		assertEquals(Direction.North, ((Street) testGrid.getField(10, 3)).getFlowDirection());
		assertEquals(Direction.North, ((Street) testGrid.getField(9, 3)).getFlowDirection());
	}

	@Test
	void addBigFieldTest() {

		Hospital h = new Hospital(4, 4);
		FireStation fs = new FireStation(1, 1);
		Park p = new Park(1, 3);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("addBigField Test");
		 System.out.println();

		testGrid.addBigField(h);
		testGrid.addBigField(fs);
		testGrid.addBigField(p);
		// System.out.println(testGrid.getStreetField(h).getRowIndex());
		testGrid.print();
		System.out.println();
		assertEquals(true, testGrid.getField(4, 4) instanceof Hospital);
		assertEquals(true, testGrid.getField(4, 5) instanceof Hospital);
		assertEquals(true, testGrid.getField(5, 4) instanceof Hospital);
		assertEquals(true, testGrid.getField(5, 5) instanceof Hospital);

		assertEquals(true, testGrid.getField(1, 1) instanceof FireStation);
		assertEquals(true, testGrid.getField(2, 1) instanceof FireStation);
		assertEquals(true, testGrid.getField(1, 2) instanceof FireStation);
		assertEquals(true, testGrid.getField(2, 2) instanceof FireStation);

		assertEquals(true, testGrid.getField(1, 3) instanceof Park);
		assertEquals(true, testGrid.getField(2, 3) instanceof Park);
		assertEquals(true, testGrid.getField(1, 4) instanceof Park);
		assertEquals(true, testGrid.getField(2, 4) instanceof Park);

	}

	@Test
	void addFieldTest() {

		Hospital h = new Hospital(4, 4);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("addField Test");
		System.out.println();

		testGrid.addField(h);
		// System.out.println(testGrid.getStreetField(h).getRowIndex());
		// testGrid.print();

		assertEquals(true, testGrid.getField(4, 4) instanceof Hospital);

		// testGrid.getField(rowIndex, colIndex)
	}

	@Test
	void addStreetTest() {
		Node n1 = new Node(2, 2);
		Node n2 = new Node(2, 6);
//		Node n3 = new Node(2, 3);
//		Node n4 = new Node(5, 5);
//		Node n5 = new Node(3, 3);

//		Hospital h = new Hospital(4, 4);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("Case 1: addStreet Test");
		System.out.println();
		testGrid.addStreet(n1, n2);
		// System.out.println(testGrid.getField(1, 3).getColIndex()) ;

		testGrid.init(n1);
		testGrid.init(n2);
		// testGrid.addField(h);
		// System.out.println(testGrid.getStreetField(h).getRowIndex());
		testGrid.print();
		System.out.println();

		for (int i = n1.rowIndex; i < n1.rowIndex + 2; i++) {
			for (int j = n1.colIndex; j < n2.colIndex; j++) {
				assertEquals(true, testGrid.getField(i, j) instanceof Street);
			}
		}

		// testGrid.getField(rowIndex, colIndex)
	}

	@Test
	void addStreet3Test() {
		Node n1 = new Node(4, 4);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("Case 1: addStreet3 Test");
		System.out.println();
		testGrid.init(n1);
		testGrid.addStreet3(n1, Direction.North, 30);
		testGrid.addStreet3(n1, Direction.East, 30);
		testGrid.addStreet3(n1, Direction.West, 30);
		testGrid.addStreet3(n1, Direction.South, 30);

		// System.out.println(testGrid.getField(1, 3).getColIndex()) ;
		testGrid.print();

	}

	@Test
	void setSpeedLimit3Test() {
		Node n1 = new Node(4, 4);
		Grid testGrid = new Grid(8, 8, 0.0);
		testGrid.addStreet3(n1, Direction.North, 0);
		testGrid.addStreet3(n1, Direction.East, 0);
		testGrid.addStreet3(n1, Direction.South, 0);
		testGrid.addStreet3(n1, Direction.West, 0);

		testGrid.setSpeedLimit3(n1, Direction.North, 100);
		testGrid.setSpeedLimit3(n1, Direction.East, 100);
		testGrid.setSpeedLimit3(n1, Direction.South, 100);
		testGrid.setSpeedLimit3(n1, Direction.West, 100);
		testGrid.print();
		System.out.println();
		// Norden
		for (int i = n1.rowIndex - 1; i > 0; i--) {
			for (int j = n1.colIndex; j < n1.colIndex + 2; j++) {
				assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
			}
		}
		// Osten
		for (int i = n1.rowIndex; i < n1.rowIndex + 1; i++) {
			for (int j = n1.colIndex + 2; j < testGrid.getColDimension() + 1; j++) {
				assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
			}
		}
		// Sueden
		for (int i = n1.rowIndex + 2; i < testGrid.getRowDimension() + 1; i++) {
			for (int j = n1.getColIndex(); j < n1.getColIndex() + 2; j++) {
				assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
			}
		}
		// Osten
		for (int i = n1.rowIndex; i < n1.rowIndex + 2; i++) {
			for (int j = n1.colIndex - 1; j > 0; j--) {
				assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
			}
		}

	}

	@Test
	void setSpeedLimit2Test() {
		Node n1 = new Node(2, 2);
		Node n2 = new Node(2, 6);
		Node n3 = new Node(6, 2);
		Node n4 = new Node(6, 6);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("setSpeedLimit2 Test");
		System.out.println();
		testGrid.addStreet(n1, n2);
		testGrid.addStreet(n1, n3);
		testGrid.addStreet(n2, n4);
		testGrid.addStreet(n3, n4);

		testGrid.init(n1);
		testGrid.init(n2);
		testGrid.init(n3);
		testGrid.init(n4);

		testGrid.setSpeedLimit2(n1, n2, 100);
		testGrid.setSpeedLimit2(n1, n3, 100);
		testGrid.setSpeedLimit2(n2, n4, 100);
		testGrid.setSpeedLimit2(n3, n4, 100);
		testGrid.print();
		System.out.println();
		for (int i = 1; i < testGrid.getRowDimension() + 1; i++) {
			for (int j = 1; j < testGrid.getColDimension() + 1; j++) {
				if (testGrid.getField(i, j) instanceof Street) {
					assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
				}
			}
		}

	}

	@Test
	void setSpeedLimit1Test() {
		Node n1 = new Node(2, 2);
		Node n2 = new Node(2, 6);
		Node n3 = new Node(6, 2);
		Node n4 = new Node(6, 6);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("setSpeedLimit1 Test");
		System.out.println();
		
		testGrid.addStreet(n1, n2);
		testGrid.addStreet(n1, n3);
		testGrid.addStreet(n2, n4);
		testGrid.addStreet(n3, n4);

		testGrid.init(n1);
		testGrid.init(n2);
		testGrid.init(n3);
		testGrid.init(n4);

		testGrid.setSpeedLimit1(n1, n2, 100);
		testGrid.setSpeedLimit1(n1, n3, 100);
		testGrid.setSpeedLimit1(n2, n4, 100);
		testGrid.setSpeedLimit1(n3, n4, 100);
		testGrid.print();
		System.out.println();

	
		for (int i = n1.getRowIndex(); i < n1.getRowIndex() + 1; i++) {
			for (int j = n1.getColIndex(); j < n2.getColIndex(); j++) {
				assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
			}
		}

		for (int i = n3.getRowIndex(); i < n3.getRowIndex() + 1; i++) {
			for (int j = n3.getColIndex(); j < n4.getColIndex(); j++) {
				assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
			}
		}
	}

	@Test
	void setSpeedLimit0Test() {
		Node n1 = new Node(2, 2);
		Node n2 = new Node(2, 6);
		Node n3 = new Node(6, 2);
		Node n4 = new Node(6, 6);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("setSpeedLimit0 Test");
		System.out.println();
		testGrid.addStreet(n1, n2);
		testGrid.addStreet(n1, n3);
		testGrid.addStreet(n2, n4);
		testGrid.addStreet(n3, n4);

		testGrid.setSpeedLimit0(n1, n2, 100);
		testGrid.setSpeedLimit0(n1, n3, 100);
		testGrid.setSpeedLimit0(n2, n4, 100);
		testGrid.setSpeedLimit0(n3, n4, 100);
		testGrid.print();
		System.out.println();
		for (int i = 1; i < testGrid.getRowDimension() + 1; i++) {
			for (int j = 1; j < testGrid.getColDimension() + 1; j++) {
				if (testGrid.getField(i, j) instanceof Street) {
					assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());
				}
			}
		}
	}

	@Test
	void setSpeedLimitTest() {
		Node n1 = new Node(2, 2);
		Node n2 = new Node(2, 6);
		Node n3 = new Node(6, 2);
		Node n4 = new Node(6, 6);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("setSpeedLimit Test");
		System.out.println();
		testGrid.addStreet(n1, n2);
		testGrid.addStreet(n1, n3);
		testGrid.addStreet(n3, n4);
		testGrid.init(n1);
		// testGrid.init(n2);
		testGrid.init(n3);
		testGrid.init(n4);
		// testGrid.setDirection(n1);
		// testGrid.setDirection(n2);
		// testGrid.setDirection(n3);
		testGrid.setSpeedLimit(n1, n2, 30, 1);
		testGrid.setSpeedLimit(n1, n3, 50, 0);
		testGrid.setSpeedLimit(n3, n4, 100, 2);
		testGrid.print();
		System.out.println();
		for (int i = n1.rowIndex; i < n1.rowIndex + 2; i++) {
			for (int j = n1.colIndex; j < n2.colIndex; j++) {
				assertEquals(30, ((Street) testGrid.getField(i, j)).getSpeedLimit());

			}
		}
		for (int i = n1.rowIndex + 2; i < n3.rowIndex; i++) {
			for (int j = n1.colIndex; j < n1.colIndex + 1; j++) {
				assertEquals(50, ((Street) testGrid.getField(i, j)).getSpeedLimit());
			}
		}
		for (int i = n3.rowIndex; i < n3.rowIndex + 2; i++) {
			for (int j = n3.colIndex; j < n4.colIndex + 1; j++) {
				assertEquals(100, ((Street) testGrid.getField(i, j)).getSpeedLimit());

			}
		}

	}

	@Test
	void intitTest() {
		System.out.println("init Test");
		System.out.println();

		Node n1 = new Node(2, 2);
		Grid testGrid = new Grid(8, 8, 0.0);
		testGrid.init(n1);

		assertEquals(true, testGrid.getField(2, 2) instanceof Street);
		assertEquals(true, testGrid.getField(2, 3) instanceof Street);
		assertEquals(true, testGrid.getField(3, 2) instanceof Street);
		assertEquals(true, testGrid.getField(3, 3) instanceof Street);

	}

	@Test
	void getDirectionTest() {
		Node n1 = new Node(2, 2);
		Node n2 = new Node(2, 6);
//		Node n3 = new Node(2, 3);
//		Node n4 = new Node(5, 5);
//		Node n5 = new Node(3, 3);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("getDirection Test");
		System.out.println();
		testGrid.addStreet(n1, n2);
		testGrid.init(n1); // initialisiert Knoten als Strassenfelder
		testGrid.init(n2);
		testGrid.setDirection(n1); // setzt die Richtungen auf den Knoten
		testGrid.setDirection(n2);
		// testGrid.setDirection(n3);
		testGrid.print();
		System.out.println();

	/*	// n1
		// links oben
		assertEquals(true, ((Street) testGrid.getField(2, 2)).getDirection().contains(Direction.South));
		// links unten
		assertEquals(true, ((Street) testGrid.getField(3, 2)).getDirection().contains(Direction.East));
		// rechts unten
		assertEquals(true, ((Street) testGrid.getField(3, 2)).getDirection().contains(Direction.East));
		// rechts oben
		assertEquals(true, ((Street) testGrid.getField(2, 3)).getDirection().contains(Direction.West));

		// zwischen den Knoten oben
		assertEquals(true, ((Street) testGrid.getField(2, 4)).getDirection().contains(Direction.West));
		assertEquals(true, ((Street) testGrid.getField(2, 5)).getDirection().contains(Direction.West));
		// zwischen den Knoten unten
		assertEquals(true, ((Street) testGrid.getField(3, 4)).getDirection().contains(Direction.East));
		assertEquals(true, ((Street) testGrid.getField(3, 5)).getDirection().contains(Direction.East));*/

	}

	@Test
	void setDirectionTest() {
		Node n1 = new Node(1, 1);
		Node n2 = new Node(1, 6);
//		Node n3 = new Node(2, 3);
//		Node n4 = new Node(5, 5);
//		Node n5 = new Node(3, 3);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("Case 1: setDirection Test");
		System.out.println();
		testGrid.addStreet(n1, n2);
		testGrid.init(n1);
		testGrid.init(n2);
		//testGrid.init(n4);
		testGrid.setDirection(n1);
		testGrid.setDirection(n2);
		testGrid.print();
		System.out.println();

		// n1
		// links oben
		//assertEquals(true, ((Street) testGrid.getField(1, 1)).getDirection().contains(Direction.South));
		// links unten
	//	assertEquals(true, ((Street) testGrid.getField(2, 1)).getDirection().contains(Direction.East));
		// rechts unten
	//	assertEquals(true, ((Street) testGrid.getField(3, 2)).getDirection().contains(Direction.East));
		// rechts oben
	//	assertEquals(true, ((Street) testGrid.getField(2, 3)).getDirection().contains(Direction.West));

		// n2
		// links oben
//		assertEquals(true, ((Street) testGrid.getField(2, 6)).getDirection().contains(Direction.West));
		// links unten
	//	assertEquals(true, ((Street) testGrid.getField(3, 6)).getDirection().contains(Direction.East));
		// rechts unten
//		assertEquals(true, ((Street) testGrid.getField(3, 7)).getDirection().contains(Direction.North));
		// rechts oben
//		assertEquals(true, ((Street) testGrid.getField(2, 7)).getDirection().contains(Direction.West));

	}

	@Test
	void setDirTest() {
		System.out.println("setDir Test");
		System.out.println();

		Grid testGrid = new Grid(8, 8, 0.0);
		Node n1 = new Node(1, 1);
		Node n2 = new Node(2, 7);
		testGrid.addStreet(n1, n2);
		System.out.println();
		// linkes oberes Eck
		assertEquals(true, testGrid.setDir(n1).contains(Direction.East));
		assertEquals(true, testGrid.setDir(n1).contains(Direction.South));
		// rechter Rand
		assertEquals(true, testGrid.setDir(n2).contains(Direction.North));
		assertEquals(true, testGrid.setDir(n2).contains(Direction.South));
		assertEquals(true, testGrid.setDir(n2).contains(Direction.West));

	}

	@Test
	void getRandomStreetFieldTest() {
		Node n1 = new Node(2, 2);
		Node n2 = new Node(2, 6);
		Node n3 = new Node(2, 3);
	//	Node n4 = new Node(5, 5);
//		Node n5 = new Node(3, 3);

		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("getRandomStreetField Test");
		System.out.println();
		testGrid.addStreet(n1, n2);
		testGrid.addStreet3(n3, Direction.North, 50);
		testGrid.init(n1);
		testGrid.print();
		Field f = testGrid.getRandomStreetField();

		System.out.println(" x Wert von cars:" + " " + f.getRowIndex());
		System.out.println(" y Wert von cars:" + " " + f.getColIndex());
		// System.out.println();
		
		System.out.println();

	}

	@Test
	void x_ValueTest() {
		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("x_ValueTest");
		System.out.println();


		assertEquals(0, testGrid.x_Value(new Field(1, 1, 2)));
	}

	@Test
	void y_ValueTest() {
		Grid testGrid = new Grid(8, 8, 0.0);
		System.out.println("y_ValueTest");
		System.out.println();

		assertEquals(7, testGrid.y_Value(new Field(1, 1, 2)));
	}

}
