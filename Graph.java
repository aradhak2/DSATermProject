package graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Graph {
    protected static Map<String, Vertex> vertexMap = new HashMap<>();
    public static Set<Edge> edgeSet = new HashSet<>();
    boolean findReachable = false;

    /*public Graph() throws Exception {
        constructGraph("/Users/Aswathi/IdeaProjects/Graph/weighted_edges.txt");
    }*/

    public static void main(String[] args) throws GraphException {

        try {

            constructGraph(args[0]);
            readUserInput();

        } catch (Exception e) {  /* e.printStackTrace(); */ }
    }

    private static void readUserInput() throws GraphException, Exception {
        Graph g = new Graph();
        String[] args;
        Scanner in = new Scanner(System.in);

        read:
        while (true) {
            try {
                System.out.print("Enter a command: ");
                String input = in.nextLine();
                args = input.split(" ");

                switch (args[0]) {
                    case "print":
                        g.printGraph();
                        break;
                    case "quit":
                        g.cleanUp(in);
                        break read;
                    case "addedge":
                        Edge.addEdge(args[1], args[2], Float.valueOf(args[3]), false);
                        break;
                    case "deleteedge":
                        Edge.deleteEdge(args[1], args[2]);
                        break;
                    case "edgedown":
                        Edge.edgeDown(args[1], args[2]);
                        break;
                    case "edgeup":
                        Edge.edgeUp(args[1], args[2]);
                        break;
                    case "vertexdown":
                        Vertex.vertexDown(args[1]);
                        break;
                    case "vertexup":
                        Vertex.vertexUp(args[1]);
                        break;
                    case "reachable":
                        g.findReachable();
                        break;
                    default:
                        System.err.println("Undefined input");
                }
                Thread.sleep(100);
            } catch (ArrayIndexOutOfBoundsException e1) {
                System.err.println("Unexpected number of arguments");
            } catch (IllegalStateException e2) {
                throw new GraphException("Exiting program");
            }
        }
    }

    private static void constructGraph(String arg) throws FileNotFoundException {
        FileReader fin = new FileReader(arg);
        Scanner graphFile = new Scanner(fin);

        String line;
        while (graphFile.hasNextLine()) {
            line = graphFile.nextLine();
            StringTokenizer st = new StringTokenizer(line);

            try {
                if (st.countTokens() != 3) {
                    System.err.println("Skipping ill-formatted line " + line);
                    continue;
                }
                String from = st.nextToken();
                String to = st.nextToken();
                Float transmit_time = Float.valueOf(st.nextToken());

                Edge.addEdge(from, to, transmit_time, true);

            } catch (NumberFormatException e) {
                System.err.println("Skipping ill-formatted line " + line);
            }
        }

        System.out.println("Constructed a graph.Graph with " + vertexMap.size() + " vertices and " +
                edgeSet.size() + " weighted edges");
    }

    private void findReachable() throws GraphException {
        findReachable = true;
        printGraph();
    }

    private void cleanUp(Scanner in) {
        in.close();
    }

    /*private void addEdge(String u, String v, Float transmit_time, boolean isCreation) //throws Exception
    {
        Vertex from = addVertex(u);
        Vertex to = addVertex(v);

        if (from.adj.contains(to)) {
            updateEdge(from, to, transmit_time);
        } else {
            from.setAdj(to);

            graph.Edge e = new graph.Edge(from, to, transmit_time);
            edgeSet.add(e);

            if (isCreation) {
                to.setAdj(from);

                e = new graph.Edge(to, from, transmit_time);
                edgeSet.add(e);
            }
        }
    }*/

    /*private void deleteEdge(String u, String v) throws graph.GraphException {
        Vertex from = getVertex(u);
        Vertex to = getVertex(v);

        try {
            if (from.adj.contains(to)) {
                for (graph.Edge edge : edgeSet) {
                    if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                        edgeSet.remove(edge);
                        from.adj.remove(to);
                    }
                }
            }
        } catch (ConcurrentModificationException e) { *//* e.printStackTrace(); *//* }
    }*/

    /*private void updateEdge(Vertex from, Vertex to, float transmit_time) {
        if (from.adj.contains(to)) {
            for (graph.Edge edge : edgeSet) {
                if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                    edge.weight = transmit_time;
                }
            }
        }
    }

    private void edgeDown(String u, String v) throws graph.GraphException {
        Vertex from = getVertex(u);
        Vertex to = getVertex(v);

        if (from != null) {
            if (from.adj.contains(to)) {
                for (graph.Edge edge : edgeSet) {
                    if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                        edge.state = graph.State.DOWN;
                    }
                }
            }
        }
    }

    private void edgeUp(String u, String v) throws graph.GraphException {
        Vertex from = getVertex(u);
        Vertex to = getVertex(v);

        if (from != null) {
            if (from.adj.contains(to)) {
                for (graph.Edge edge : edgeSet) {
                    if (edge.from.name.equals(from.name) && edge.to.name.equals(to.name)) {
                        edge.state = graph.State.UP;
                    }
                }
            }
        }
    }*/

    /*private void vertexDown(String name) throws graph.GraphException {
        Vertex v = getVertex(name);
        if (v != null) {
            v.setState(graph.State.DOWN);
        }
    }

    private void vertexUp(String name) throws graph.GraphException {
        Vertex v = getVertex(name);
        if (v != null) {
            v.setState(graph.State.UP);
        }
    }

    private Vertex addVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }

    private Vertex getVertex(String vertexName) throws graph.GraphException {
        Vertex v = vertexMap.get(vertexName);
        try {
            if (v == null) {
                throw new graph.GraphException("Invalid vertex: " + vertexName);
            }
        } catch (graph.GraphException g) { *//* g.printStackTrace(); *//* }
        return v;
    }*/

    private void printGraph() throws GraphException {
        Map<String, Vertex> sortedVertices = new TreeMap<>(vertexMap);

        for (Object o : sortedVertices.keySet()) {
            String key = o.toString();
            Vertex from = sortedVertices.get(key);

            Vertex.sortAdjacencyList(from);

            //String format = "%-40s%s%n";

            if (!findReachable) {
                System.out.print(key + "\t\t");
                if (isDown(key) == State.DOWN)
                    System.out.print(State.DOWN);
            } else {
                if (isDown(key) == State.DOWN)
                    continue;
                else
                    System.out.print(key);
            }
            System.out.println("");

            for (Vertex to : from.adj) {
                if (!findReachable) {
                    System.out.print("\t" + to.name + " " + Edge.getWeight(from, to) + "\t\t");
                    if (isDown(from, to) == State.DOWN) {
                        System.out.print(State.DOWN);
                    }
                } else {
                    if (isDown(from, to) == State.DOWN) {
                        continue;
                    } else {
                        System.out.print("\t" + to.name + " " + Edge.getWeight(from, to));
                    }
                }
                System.out.println();
            }
        }
    }

    private State isDown(Vertex from, Vertex to) {
        for (Edge e : edgeSet) {
            if (e.from.name.equals(from.name) && e.to.name.equals(to.name)) {
                if (e.state == State.DOWN) {
                    return State.DOWN;
                }
            }
        }
        return State.UP;
    }

    private State isDown(String vertexName) throws GraphException {
        Vertex vertex = Vertex.getVertex(vertexName);
        if (vertex.state == State.DOWN) {
            return State.DOWN;
        }
        return State.UP;
    }
}