package map;

import java.util.LinkedList;

//
/**
 * the class Node describes the coordinates of a field. A node represent the
 * north western field of a square of four fields
 * 
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class Node {

	final int rowIndex;
	final int colIndex;

	/**
	 * 
	 * @param rowIndex row Index of the Node
	 * @param colIndex column Index of the Node
	 */
	public Node(int rowIndex, int colIndex) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
	}

	/**
	 * 
	 * @return row Index of the node
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * 
	 * @return column index of the node
	 */
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * returns the String representation of rowIndex and colIndex
	 * @return String representation
	 */
	public String toDataString() {
		return "" + rowIndex + ";" + colIndex + ";";
	}

}
