package graph;

import java.util.ArrayList;

public class Dijkstra extends Graph
{
    public static final int INFINITY = Integer.MAX_VALUE;

    static String[][] vertexWeight = new String[vertexMap.size()][2];

    public static void main(String args[])
    {
        for(String[] i : vertexWeight)
        {
            for(String j : i)
            {
                System.out.println(j);
            }
        }
    }
}
