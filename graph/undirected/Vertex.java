package map.graph.undirected;


import java.util.LinkedList;
/**
 * represents a node in the undirected graph
 * 
 * 
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class Vertex {

	String name;
	PriorityQueueElement pqe;
	LinkedList<Edge> neighbours = new LinkedList<Edge>();
	
	private int rowIndex, colIndex;
	/**
	 * initialize the vertex with name e.g. x1, x2 or x14
	 * @param name name of the vertex
	 */
	public Vertex(String name){
		this.name = name;
		
	}
	/**
	 * initialize the vertex with name row index and column index
	 * @param name name of the vertex
	 * @param rowIndex row index of the vertex
	 * @param colIndex column index of the vertex
	 */
	public Vertex(String name, int rowIndex, int colIndex){
		this.name = name;
		this.rowIndex=rowIndex;
		this.colIndex=colIndex;
	}
	/**
	 * 
	 * @return
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	/**
	 * 
	 * @return
	 */
	public int getColIndex() {
		return colIndex;
	}
	/**
	 * 
	 * @param rowIndex
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex=rowIndex;
	}
	
	public void setColIndex(int colIndex) {
		this.colIndex=colIndex;
	}
	/**
	 * 
	 * @return name
	 */
	public String getName(){
		return name;
	}
	/**
	 * 
	 * @param name name of Vertex
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * adds a edge to the neighbors of the vertex
	 * @param edge 
	 */
	public void addNeighbour(Edge edge){
		neighbours.add(edge);
	}
	/**
	 * 
	 * @return neighbours list with all neighbors of the vertex
	 */
	public LinkedList<Edge> getNeighbours(){
		return this.neighbours;
	}
	/**
	 * 
	 * @param pqe PriorityQueueElement
	 */
	public void setPriorityQueueElement(PriorityQueueElement pqe) {
		this.pqe=pqe;
	}
	/**
	 * 
	 * @return PriorityQueueElement
	 */
	public PriorityQueueElement getPriorityQueueElement() {
		return pqe;
	}
	 
	
}

