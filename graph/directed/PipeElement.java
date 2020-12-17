package map.graph.directed;
import map.Direction;

public class PipeElement {

	int capacity;
	Direction flowdirection;
	
	public PipeElement(int capacity , Direction flowdirection) {
		this.capacity=capacity;
		this.flowdirection=flowdirection;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public Direction getFlowDirection() {
		return flowdirection;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
	
	public void setFlowDirection(Direction flowdirection) {
		this.flowdirection=flowdirection;
	}
}
