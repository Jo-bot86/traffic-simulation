package map.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import map.graph.directed.DirectedGraph;

/**
 * Test class for the class DirectedGraph
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class DirectedGraphTest {

	@Test
	void getWeightTest() {
		DirectedGraph g = new DirectedGraph(37);
		assertEquals(0, g.getWeight(1, 1));
		assertEquals(99, g.getWeight(1, 2));
		assertEquals(13, g.getWeight(14, 13));

		for (int i = 0; i < g.getSize(); i++) {
			assertEquals(0, g.getWeight(i, i));
		}
		//falls die Kante (i,j) eine pos Gewicht hat
		//muss das Gewicht der Kante (j,i) Null ein
		for (int i = 0; i < g.getSize(); i++) {
			for (int j = 0; j < g.getSize(); j++) {
				if (g.getWeight(i, j) != 0) {
					assertEquals(0, g.getWeight(j, i));
				}
			}
		}

	}

	@Test
	void setWeightTest() {
		DirectedGraph g = new DirectedGraph(37);
		g.setWeight(1, 1, 100);
		assertEquals(100, g.getWeight(1, 1));

	}



	@Test
	void getNodesTest() {
		DirectedGraph g = new DirectedGraph(37);

		assertEquals(36, g.getNodes().size());
		// erster Knoten
		assertEquals(13, g.getNodes().get(0).getRowIndex());
		assertEquals(3, g.getNodes().get(0).getColIndex());
		// letzter Knoten
		assertEquals(21, g.getNodes().get(35).getRowIndex());
		assertEquals(37, g.getNodes().get(35).getColIndex());
	}

	@Test
	void getGraph() {
		DirectedGraph g = new DirectedGraph(37);
		for (int i = 0; i < g.getGraph().length; i++) {
			for (int j = 0; j < g.getGraph()[0].length; j++) {
				assertEquals(g.getWeight(i, j), g.getGraph()[i][j]);
			}
		}

	}

}
