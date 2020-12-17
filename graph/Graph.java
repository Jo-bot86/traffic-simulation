package map.graph;

import java.util.Iterator;

import map.graph.directed.NeighbourIterator;

/**
 * 
 * @author Josef Weldemariam, Matr. 7316168
 * source: http://www.inf.fh-flensburg.de/lang/algorithmen/graph/graph-implementierung.htm
 * @param <Type> generic Type for the Graph
 */
	public abstract class Graph<Type> {
		protected int n;
	    public Type noedge;
	    
	    /**
	     * initialize the graph with the number of nodes and type noedge
	     * for marking no edges they are not in the graph
	     * @param n_ number of vertexes
	     * @param noedge_ value of a no edge
	     */
	    public Graph(int n_, Type noedge_)
	    {
	        n=n_;
	        noedge=noedge_;
	    }
	    
	    /**
	     * 
	     * @return the number of nodes in the graph
	     */
	    public int getSize()
	    {
	        return n;
	    }

	    /**
	     * deletes all edges
	     */
	    protected void initialize()
	    {
	        for (int i=0; i<n; i++)
	            for (int j=0; j<n; j++)
	                deleteEdge(i, j);
	    }
	    
	    
	    /**
	     * checked if (i,j) is a edge in the graph
	     * @param i start node of the edge
	     * @param j end node of the edge
	     * @return	true if (i,j) is a edge in the graph
	     */
	    public boolean isEdge(int i, int j)
	    {
	        return !getWeight(i, j).equals(noedge);     // true, wenn (i, j) eine Kante ist
	    }

	    
	    /**
	     * deletes the edge (i,j)
	     * @param i start node of the edge
	     * @param j end node of the edge
	     */
	    public void deleteEdge(int i, int j)
	    {
	        setWeight(i, j, noedge);	// loescht die Kante (i, j)
	    }
	    
	 // liefert die Markierung w(i, j)
	    public abstract Type getWeight(int i, int j);

	    // setzt w(i, j)=x
	    public abstract void setWeight(int i, int j, Type x);

	    
	    /**
	     * iterates over all neighbors of node i
	     * @param i node
	     * @return neighbors of i
	     */
	    public Iterator<Integer> getNeighbourIterator(int i)
	    {
	        return new NeighbourIterator(this, i);	// iteriert ueber alle Nachbarn des Knotens i
	    }

	    @Override
	    public String toString()
	    {
	        Iterator<Integer> ni;
	        String s="\n";
	        for (int i=0; i<n; i++ )
	        {
	            s+=i+" -> ";
	            ni=getNeighbourIterator(i);
	            while (ni.hasNext())
	                s+=ni.next()+" ";
	            s+="\n";
	        }
	        return s;
	    }



	}

