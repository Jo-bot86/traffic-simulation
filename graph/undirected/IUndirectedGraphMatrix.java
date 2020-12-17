package map.graph.undirected;
/**
 * 
 * Interface for methods to transform the undirected graph
 * 
 * @author Josef Weldemariam, Matr. 7316168
 * 
 */
public interface IUndirectedGraphMatrix {
	/**
	 * sets the costs for a pipe between two nodes.
	 * e.g. connecting node x1 and x12 call
	 * setWeight(1,12)
	 * @param i map index of node 
	 * @param j map index of node
	 * @param b costs for the pipes
	 */
	public void setWeight(int i, int j, Integer b);
}
