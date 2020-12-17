package map.graph.undirected;
/**
 * represents a edge in a undirected graph
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class Edge  {
	
	int weight;
	Vertex  endnode;
	/**
	 * initialize every edge with a end node and a weight
	 * @param endnode end node of the edge
	 * @param weight weight of the edge
	 */
	public Edge(Vertex endnode, int weight){
		this.weight=weight;
		this.endnode=endnode;
	}
	/**
	 * returns the weight on a edge
	 * @return weight of the edge
	 */
	public int getWeight(){
		return weight;
	}
	/**
	 * sets the weight for a edge
	 * @param weight weight of the edge
	 */
	public void setWeight(int weight){
		this.weight= weight;
	}
	/**
	 * gets the end node of an edge
	 * @return end node of the edge
	 */
	public Vertex getEndNode(){
		return this.endnode;
	}
	/**
	 * sets the end node of an edge
	 * @param endnode end node of the edge
	 */
	public void setEndNode(Vertex endnode){
		this.endnode=endnode;
	}

}