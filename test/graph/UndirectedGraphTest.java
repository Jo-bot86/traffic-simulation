package map.graph;


//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import map.graph.undirected.UndirectedGraph;


public class UndirectedGraphTest {
	
	@Test
	void setVertexTest() {
		UndirectedGraph g = new UndirectedGraph(0.0);
		System.out.println(g.getGraph().get(0).getNeighbours().get(0).getWeight());
	}
	
}
