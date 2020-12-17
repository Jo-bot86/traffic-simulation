package map.field.fieldtype;

//
import map.field.Field;
import map.graph.directed.*;

import map.Direction;

import java.util.*;

/**
 * 
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class Street extends Field implements IStreet {

	int speedlimit;
	ArrayList<Direction> direction = new ArrayList<Direction>();
	//DatenFeld fuer die Rohrkosten(ungerich. graph)
	int pipecosts;
	//Datenfeld PipeElement bestehend aus pipecapacity und direction
	PipeElement pe = new PipeElement(0 , null);
	boolean isConnectingRoad = false;
	int mapValue;          // 1=links oben, 2= links unten, 3= rechts oben, 4= rechts unten , noch nicht sicher ob ueberhaupt noetig
	boolean isConstructionSite = false;
	/**
	 * 
	 * @param rowIndex   row index
	 * @param colIndex   column index
	 * @param speedlimit top Speed of the street
	 * 
	 */
	public Street(int rowIndex, int colIndex) {

		super(rowIndex, colIndex, 1); // Ruft den Konstrktor der Oberklasse mit Wert 1(ID fuer Strasse) auf

	}
	/**
	 * sets isCostructionSite to false 
	 */
	public void removeConstructionSite() {
		this.isConstructionSite=false;
	}
	
	public void resetPipeElement() {
		this.pe = new PipeElement(0 , null);
	}
	/**
	 * 
	 * @return true if the street field is a construction site
	 */
	public boolean is_constructionSite() {
		return isConstructionSite;
	}
	/**
	 * sets the boolean isConstructionSite true
	 */
	public void setConstructionSite() {
		this.isConstructionSite=true;
	}
	/**
	 * sets the value for the map id. 1=left top, 2= left button, 3= right top, 4= right button

	 * @param value id value for the map
	 */
	public void setMapValue(int value) {
		this.mapValue=value;
	}
	/**
	 * 
	 * @return
	 */
	public int getMapValue() {
		return mapValue;
	}
	/**
	 * 
	 * @return pipeCapacity for a streetfield
	 */
	public int getPipeCapacity() {
		return pe.getCapacity();
	}
	/**
	 * 
	 * @return
	 */
	public Direction getFlowDirection() {
		return pe.getFlowDirection();
	}
	/**
	 * 
	 * @param capacity
	 */
	public void setPipeCapacity(int capacity) {
		this.pe.setCapacity(capacity);
	}
	
	public void setFlowDirection(Direction flowdirection) {
		this.pe.setFlowDirection(flowdirection);
	}

	/**
	 * 
	 * @return pipe costs for 
	 */
	public int getPipeCosts() {
		return pipecosts;
	}
	/**
	 * 
	 * @param pipecosts pipe costs for a streetfield
	 */
	public void setPipeCosts(int pipecosts) {
		this.pipecosts=pipecosts;
	}
	/**
	 * @param speedlimit speed limit of the street
	 */
	public void setSpeedLimit(int speedlimit) {
		this.speedlimit = speedlimit;
	}

	/**
	 * @return speed limit of the street
	 */
	public int getSpeedLimit() {
		return speedlimit;
	}

	/**
	 * sets the Direction of a Street field
	 * @param direc List of Directions
	 */
	public void setDirection(Direction direc) {
		direction.add(direc);
	}
	/**
	 * sets a whole list of directions
	 * @param directions list of directions for the street field
	 */
	public void setDirectionAll(ArrayList<Direction> directions) {
		this.direction=directions;
	}

	/**
	 * get a list of Direction for a Street field
	 * @return list of directions of the Street field
	 */
	public ArrayList<Direction> getDirection() {
		return direction;
	}
	public void deleteDirection() {
		this.direction.removeAll(direction);
	}
	/**
	 * 
	 * @return true if the street field is a connecting road of the maps
	 */
	public boolean isConnectingRoad() {
		return isConnectingRoad;
	}
	/**
	 * sets isConnecting true for a certain street field
	 */
	public void setConnectingRoad() {
		this.isConnectingRoad=true;
	}
	@Override
	public String toDataString1() {
		if (this.speedlimit < 10) {
			return "" + 0 + "" + 0 + "" + speedlimit + ";";
		}
		if (this.speedlimit < 100) {
			return "" + 0 + "" + speedlimit + ";";
		}
		return "" + speedlimit + ";";
	}

	@Override
	public String toDataString2() {
		if (direction.size() == 1) {
			if (this.direction.contains(Direction.North)) {
				return "" + "X" + "N" + ";";
			}
			if (this.direction.contains(Direction.East)) {
				return "" + "X" + "E" + ";";
			}
			if (this.direction.contains(Direction.West)) {
				return "" + "X" + "W" + ";";
			}
			if (this.direction.contains(Direction.South)) {
				return "" + "X" + "S" + ";";
			}
		} else if (this.direction.contains(Direction.South) && this.direction.contains(Direction.West)) {
			return "" + "S" + "W" + ";";
		}
		if (this.direction.contains(Direction.North) && this.direction.contains(Direction.West)) {
			return "" + "N" + "W" + ";";
		}
		if (this.direction.contains(Direction.North) && this.direction.contains(Direction.East)) {
			return "" + "N" + "E" + ";";
		}
		if (this.direction.contains(Direction.South) && this.direction.contains(Direction.East)) {
			return "" + "S" + "E" + ";";
		}
		return null;
	}

}
