package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aswathi on 4/18/16.
 */
public class Vertex extends Graph {

    String       name;                                 // Vertex name
    List<Vertex> adj = new ArrayList<Vertex>();      // Adjacent vertices
    State        state = State.UP;

    Vertex(String vertexName) {
        this.name = vertexName;
    }

    public void setAdj(Vertex vertex) {
        this.adj.add( vertex );
    }

    public void setState( State state ) {
        this.state = state;
    }

    static void vertexDown(String name) throws GraphException {
        Vertex v = getVertex(name);
        if (v != null) {
            v.setState(State.DOWN);
        }
    }

    static void vertexUp(String name) throws GraphException {
        Vertex v = getVertex(name);
        if (v != null) {
            v.setState(State.UP);
        }
    }

    static Vertex addVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }

    static Vertex getVertex(String vertexName) throws GraphException {
        Vertex v = vertexMap.get(vertexName);
        try {
            if (v == null) {
                throw new GraphException("Invalid vertex: " + vertexName);
            }
        } catch (GraphException g) { /* g.printStackTrace(); */ }
        return v;
    }

    static void sortAdjacencyList(Vertex v) {
        Collections.sort(v.adj, (o1, o2) -> o1.name.compareTo(o2.name));
    }

}
