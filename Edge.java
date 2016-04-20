package graph;

import java.util.ConcurrentModificationException;

/**
 * Created by Aswathi on 4/18/16.
 */

class Edge extends Graph
{
    Vertex  from;
    Vertex  to;
    float   weight;
    State   state =     State.UP;

    Edge( Vertex from, Vertex to, float weight ) {
        this.from =     from;
        this.to =       to;
        this.weight =   weight;
    }

    Edge(Vertex from, Vertex to) {
        this.from =     from;
        this.to =       to;
    }

    static void addEdge(String u, String v, Float transmit_time, boolean isCreation) //throws Exception
    {
        Vertex from = Vertex.addVertex(u);
        Vertex to = Vertex.addVertex(v);

        if (from.adj.contains(to)) {
            updateEdge(from, to, transmit_time);
        } else {
            from.setAdj(to);

            Edge e = new Edge(from, to, transmit_time);
            edgeSet.add(e);

            if (isCreation) {
                to.setAdj(from);

                e = new Edge(to, from, transmit_time);
                edgeSet.add(e);
            }
        }
    }

    static void deleteEdge(String u, String v) throws GraphException {
        Vertex from = Vertex.getVertex(u);
        Vertex to = Vertex.getVertex(v);

        try {
            if (from.adj.contains(to)) {
                for (Edge edge : edgeSet) {
                    if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                        edgeSet.remove(edge);
                        from.adj.remove(to);
                    }
                }
            }
        } catch (ConcurrentModificationException e) { /* e.printStackTrace(); */ }
    }

    static void updateEdge(Vertex from, Vertex to, float transmit_time) {
        if (from.adj.contains(to)) {
            for (Edge edge : edgeSet) {
                if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                    edge.weight = transmit_time;
                }
            }
        }
    }

    static void edgeDown(String u, String v) throws GraphException {
        Vertex from = Vertex.getVertex(u);
        Vertex to = Vertex.getVertex(v);

        if (from != null) {
            if (from.adj.contains(to)) {
                for (Edge edge : edgeSet) {
                    if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                        edge.state = State.DOWN;
                    }
                }
            }
        }
    }

    static void edgeUp(String u, String v) throws GraphException {
        Vertex from = Vertex.getVertex(u);
        Vertex to = Vertex.getVertex(v);

        if (from != null) {
            if (from.adj.contains(to)) {
                for (Edge edge : edgeSet) {
                    if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                        edge.state = State.UP;
                    }
                }
            }
        }
    }

    static float getWeight(Vertex from, Vertex to) {
        float weight = 0;

        for (Edge e : edgeSet) {
            if (e.from.name.equals(from.name) && e.to.name.equals(to.name)) {
                return e.weight;
            }
        }
        return weight;
    }
}