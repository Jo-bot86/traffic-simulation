package map.graph.directed;

import java.util.ArrayList;

import map.Node;
/**
 * Interface for the directed graph witch represented the 
 * pipe capacity of the streets and flow direction. Input always one Node more.
 * e.g a Graph with 36 Nodes initialize the graph with 37
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public interface IDirectedGraph {

	/**
	 * returns a list with all nodes. A node represent the north western field of a
	 * square of four fields on a crossing
	 * 
	 * @return list of nodes
	 */
	public ArrayList<Node> getNodes();

	/**
	 * returns the weight of the edge between Node i and Node j
	 * with flow direction i->j
	 * 0 for no edge
	 * 
	 * @param i Node number i
	 * @param j Node number j
	 * @return weight of the edge
	 */
	public Integer getWeight(int i, int j);
	
	/**
	 * sets the weight of the edge between Node i and Node j
	 * with flow direction i->j
	 * 0 for no edge
	 * 
	 * @param i Node number i
	 * @param j Node number j
	 * @param b weight of the edge
	 */
	public void setWeight(int i, int j, Integer b) ;
}
