package map.graph.undirected;

//import java.util.ArrayList;
//import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim implements IPrim{

	PriorityQueue<PriorityQueueElement> pq = new PriorityQueue<PriorityQueueElement>();
	UndirectedGraph graph = new UndirectedGraph(0.0);
	int totalCosts = 0;
	
	public Prim(UndirectedGraph g) {
		prim(g, g.getVertex(g.getGraph().get(0)));
	}

	// Konstruktor wird mit einem eingelesenen Graphen und Startknoten aufgerufen
	/**
	 * calls the mehtod prim 
	 */
	public Prim() {
		//prim(graph, graph.adjazenzList.get(0));
	}
	/**
	 * 
	 * @return the total Costs of construction site
	 */
	public int getTotalCosts() {
		return totalCosts;
	}
	
	/**
	 * 
	 * @param g undirected graph
	 * @param startnode start node
	 * @return 
	 */
	public UndirectedGraph prim(UndirectedGraph g, Vertex startnode) {
		// Erzeugt einen neuen leeren Graphen
		UndirectedGraph MST = new UndirectedGraph();
		// Initialaisiert die PriorityQueue
		for (int i = 1; i < g.adjazenzList.size(); i++) {
			// erzeugt fuer jeden Knoten ein PriorityQueueElement und
			// initialisiert den Wert mit Unendlich
			PriorityQueueElement pqe = new PriorityQueueElement(g.adjazenzList.get(i), Integer.MAX_VALUE);
			g.adjazenzList.get(i).setPriorityQueueElement(pqe);
			pq.add(pqe);

		}

		// Setze den Wert des PriorityQueueElements fuer den Startknoten auf 0
		PriorityQueueElement pqeStart = new PriorityQueueElement(g.adjazenzList.get(0), 0);
		pq.add(pqeStart);
		
		while (pq.size() > 0) {
			// System.out.println(pq.peek().getValue());
			totalCosts= totalCosts + pq.peek().getValue();

			// extract Min
			Vertex u = pq.poll().node;
		
			// fuegt Knoten mit minimalen Wert zum Graphen hinzu
			MST.adjazenzList.add(u);
			for (int j = 0; j < u.neighbours.size(); j++) {
				// Falls die Queue einen Knoten enhaelt der eine Kante mit u hat und das Gewicht
				// dieser Kante ist kleiner als der Value der pqe
				// setze u als Parent dieses knoten
				// System.out.println(j + " "+u.getNeighbours().get(j).getEndNode().getName());
				if (pq.contains(u.getNeighbours().get(j).getEndNode().getPriorityQueueElement())
						&& u.getNeighbours().get(j).getWeight() < u.getNeighbours().get(j).getEndNode()
								.getPriorityQueueElement().getValue()) {
					{
						pq.remove(u.getNeighbours().get(j).getEndNode().getPriorityQueueElement());
						u.getNeighbours().get(j).getEndNode().getPriorityQueueElement().setParent(u);
						u.getNeighbours().get(j).getEndNode().getPriorityQueueElement()
								.setValue(u.getNeighbours().get(j).getWeight());
						pq.add(u.getNeighbours().get(j).getEndNode().getPriorityQueueElement());
					//	u.addNeighbour(edge);
						// System.out.println(u.getPriorityQueueElement().getParent().getName());
					}
				}
			}

		}
		
		
		return MST;
	}
}
