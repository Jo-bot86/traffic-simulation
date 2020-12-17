package map.graph.directed;
/**
 *@author Josef Weldemariam, Matr. 7316168
 *source: http://www.inf.fh-flensburg.de/lang/algorithmen/graph/graph-implementierung.htm

 */
import java.util.Iterator;

import map.graph.Graph;

public class NeighbourIterator implements Iterator<Integer> {

	private Graph<?> g;
    private int i, j;

    public NeighbourIterator(Graph<?> g_, int i_)
    {
        g=g_;
        i=i_;
        j=0;
        //Falls ein Knoten keine Nachbarn hat
        tryNext();
    }
    
    public boolean hasNext()
    {
        return j<g.getSize();
    }

    /**
     * 
     */
    public Integer next()
    {
        int k=j;
        j++;
        tryNext();
        return k;
    }

    /**
     * schaut nach dem naechsten Knoten j der eine Kante mit Knoten i hat
     */
    private void tryNext()
    {
        while (j<g.getSize())
            if (g.isEdge(i, j))
                return;
            else
                j++;
    }

    public void remove()
    {
        // not implemented
    }
}
