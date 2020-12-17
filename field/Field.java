package map.field;

/**
 * 
 * @author Josef Weldemariam, Matr. 7316168
 *
 */
public class Field implements IField {
	private int value;
	private int rowIndex, colIndex;

	/**
	 * represents a field in the map with is characterized by his row Index, column
	 * Index and a value
	 * 
	 * @param rowIndex rowIndex of the field
	 * @param colIndex columnIndex of the field
	 * @param value    Value to identify the field
	 */
	public Field(int rowIndex, int colIndex, int value) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.value = value; // zur Identifizierung eines Feldes
	}

	/**
	 * @return rowIndex of the field
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * @return columnIndex of the field
	 */
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * @return the value of the field (0 for empty, 1 for street..)
	 */
	public int getValue() {
		return value;
	}

	/**
	 * sets the value for a field
	 * 
	 * @param value of the Field
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return string representation of value
	 */
	public String toString() {
		return Integer.toString(value);
	}

	/**
	 * string representation of the value of the field
	 * 
	 * @return string representation of map info
	 */
	public String toDataString() {
		return "" + value + ";";
	}

	/**
	 * string representation of 000 with represent the speedl imit for a noStreet
	 * field
	 * 
	 * @return String representation of 000 for no speed limit
	 */
	public String toDataString1() {
		return "" + 0 + "" + 0 + "" + 0 + ";";
	}

	/**
	 * string representation of XX if the field is a no Street field with no
	 * direction
	 * 
	 * @return String representation of XX for no direction
	 */
	public String toDataString2() {
		return "" + "X" + "X" + ";";
	}

}
