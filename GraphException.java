package graph;

public class GraphException extends Throwable {
    public GraphException(String msg)
    {
        System.err.println(msg);
    }
}
