package map;
import map.field.*;
import map.graph.directed.DirectedGraph;
import map.graph.undirected.UndirectedGraphMatrix;

/**
 * Interface for methods to work with the grid
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public interface IGrid {
	/**
	 * adds a field of type Field to the grid
	 * @param field
	 */
	public void addField(Field field);

	/**
	 * returns a field in grid  with row Index rowIndex and column Index colIndex
	 * @param rowIndex row index of the field in grid
	 * @param colIndex column index of the field in grid
	 * @return a Field in grid
	 */
	public Field getField(int rowIndex, int colIndex);

	/**
	 * 
	 * @param f field in grid with coordinates of grid
	 * @return x coordinate for Cartesian coordinates
	 */
	public int x_Value(Field f);
	/**
	 * 
	 * @param f field in grid with coordinates of grid
	 * @return y coordinate for Cartesian coordinates
	 */
	public int y_Value(Field f);
	/**
	 * returns the grid as a 2 dimensional array of type Field
	 * @return the 
	 */
	public Field[][] getMap();
	
	
	/**
	 * returns a random street field and transform to Cartesian coordinates for cars
	 * @return a random street field
	 */
	public Field getRandomStreetField();
	
	/**
	 * 
	 * returns the street field in front of a building 
	 * implement for hospital, scrap yard, banquet hall, fire station and park
	 * returns field in Cartesian coordinates for cars
	 * @param f a Field in grid with coordinates of grid
	 * @return a street field with the Cartesian coordinates 
	 */
	public Field getStreetField(Field f);
	
	
	
	/**
	 *  implemented for house fields
	 *  adds a field of type house or banquet hall to grid and connect it to the 
	 *  existing traffic network
	 * 
	 * @param field
	 */
	public void addFieldMS2(Field field);
	
	/**
	 * adds a construction site to the grid  determined
	 * by the prim algorithm
	 */
	public int addConstructionSite();
	
	
	/**
	 * returns the undirected graph which represents the pipe costs
	 * @return undirected Graph
	 */
	public UndirectedGraphMatrix getUndirGraph(); 
	/**
	 * returns the directed graph which represents the pipe costs
	 * @return directed Graph
	 */
	public DirectedGraph getDirGraph();
	
	/**
	 * adds the pipe costs to every street field
	 */
	public void addPipeCosts();
	
	/**
	 * sets for all street fields isConstructionSite to false
	 * and the speed limit to the old value
	 */
	public void removeConstructionSite();
	
	/**
	 * adds the pipe capacity and flow direction to every street field
	 * 
	 * @param s_node start node of the street
	 * @param e_node end node of the street
	 */
	public void addPipeCapacity();
	
	/**
	 * adds a street to grid between two Nodes witch represents crossing
	 * 
	 * @param startNodeRowIndex row index of start node
	 * @param startNodeColIndex column index of start node
	 * @param endNodeRowIndex row index of end node
	 * @param endNodeColIndex column index of end node
	 */
	public void addStreet(int startNodeRowIndex, int startNodeColIndex, int endNodeRowIndex, int endNodeColIndex);
	
	/**
	 * creates two new nodes and calls setSpeedLimit_number
	 * @param startNodeRowIndex row index of start node
	 * @param startNodeColIndex column index of start node
	 * @param endNodeRowIndex row index of end node 
	 * @param endNodeColIndex column index of end node
	 * @param topspeed speed limit 
	 * @param number number of nodes witch belongs to the street
	 */
	public void setSpeedLimit(int startNodeRowIndex, int startNodeColIndex, int endNodeRowIndex, int endNodeColIndex, int topspeed, int number);
}
