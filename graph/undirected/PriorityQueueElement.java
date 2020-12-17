package map.graph.undirected;

import java.util.Comparator;
/**
 * creates a PriorityQueueElement class witch includes a node of type Vertex, the parent node and a value.
 * Needs for the priority queue in class Prim
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class PriorityQueueElement implements Comparator<PriorityQueueElement>, Comparable<PriorityQueueElement>{

	Vertex node;
	Vertex parent = null;
	int value;
	/**
	 * initialize the PriorityQueueElement with a node and value
	 * @param node	vertex
	 * @param value	value priority queue element
	 */
	public PriorityQueueElement(Vertex node, int value) {
		this.node=node;
		this.value=value;
	}
	/**
	 * gets the node of type Vertex
	 * @return the node of type Vertex
	 */
	public Vertex getNode(){
		return node;
	}
	/**
	 * sets a node of type Vertex
	 * @param node node of type Vertex
	 */
	public void setNode(Vertex node){
		this.node=node;
	}
	/**
	 * gets the parent of a node
	 * @return parent 
	 */
	public Vertex getParent(){
		return parent;
	}
	/**
	 * sets the parent for a node
	 * @param parent  parent of type Vertex
	 */
	public void setParent(Vertex parent){
		this.parent=parent;
	}
	/**
	 * gets the value for a node
	 * @return value of a node
	 */
	public int getValue(){
		return value;
	}
	/**
	 * sets the value for a node
	 * @param value value of a node
	 */
	public void setValue(int value){
		this.value=value;
	}
	
	@Override
	public int compare(PriorityQueueElement pqe1, PriorityQueueElement pqe2) {
		
		return Double.compare(pqe1.getValue(), pqe2.getValue());
	}
	@Override
	public int compareTo(PriorityQueueElement o) {
		
		return compare(this,o);
	}
}
