package map.field.fieldtype;

import java.util.ArrayList;

import map.Direction;
/**
 * this interface provides methods for street fields
 * e.g. speed limit getter or direction getter..
 * @author Josef Weldemariam, Matr. 7316168
 */
public interface IStreet {

	/**
	 * returns the speed limit of a street field
	 * @return speed limit of the street field
	 */
	public int getSpeedLimit();
	
	/**
	 * get a list of Direction for a Street field
	 * @return list of directions of the Street field
	 */
	public ArrayList<Direction> getDirection();
	/**
	 * returns the pipe capacity for a street field
	 * @return pipe capacity for a street field
	 */
	public int getPipeCapacity();
	/**
	 * returns the pipe capacity for a street field
	 * @return flow direction for the pipe of a street field
	 */
	public Direction getFlowDirection();
	/**
	 * returns the pipe costs for a street field
	 * @return pipe cost for the street field
	 */
	public int getPipeCosts();
	/**
	 * returns true if the street field is a connecting street field of the maps
	 * @return true if the street field is a connecting road of the maps
	 */
	public boolean isConnectingRoad();
	
	/**
	 * 
	 * @return true if the street field is a construction site
	 */
	public boolean is_constructionSite();
}
