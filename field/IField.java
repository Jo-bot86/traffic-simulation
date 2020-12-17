package map.field;
//
/**
 * Interface for getting Information about the Field
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public interface IField {
	/**
	 * 
	 * @return column index of the field
	 */
	public int getColIndex();
	/**
	 * 
	 * @return row index of the field
	 */
	public int getRowIndex();
	/**
	 * Fields are characterized by values
	 * 0-empty Field
	 * 1-street 
	 * 2-tree
	 * 3-house
	 * 4-water tank
	 * 5-fire station 
	 * 6-scrap yard
	 * 7-park
	 * 8-hospital
	 * 9-banquet hall
	 * 
	 * @return value of the field
	 */
	public int getValue();
}
