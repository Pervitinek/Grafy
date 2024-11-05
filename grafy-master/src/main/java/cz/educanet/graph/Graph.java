package cz.educanet.graph;

import cz.educanet.queue.LIFOQueue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Graph
 * A structure describing a graph. The underlying
 * structure is a list of nodes with linked neighbours.
 *
 * Each vertex in the graph has a collection (list) of all
 * other vertices it has a connection (edge) with
 */
public class Graph {
    // A list of all the vertices in the graph
    private final List<Vertex> vertices;

    // The name of this graph
    private final String name;

    /**
     * Construct a new instance of a graph
     * @param name the name of the graph
     */
    public Graph(String name) {
        vertices = new LinkedList<>();
        this.name = name;
    }

    /**
     * Add a new node/vertex to the graph
     * @param v the node to insert
     */
    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    /**
     * Traverses the graph by DFS and returns the
     * vertices in the order they were visited in
     *
     * @return list of vertices
     */
    public List<Vertex> depthFirstSearch() {
        LIFOQueue<Vertex> LIFOQueue = new LIFOQueue<>(); // LIFO summon
        List<Vertex> sortedVisitedList = new LinkedList<>(); // List bodů ve správném orderu
        HashSet<Vertex> visitedList = new HashSet<>(); // List bodů v hashsetu

        for (Vertex vertex : vertices) {
            if (!visitedList.contains(vertex)) {
                LIFOQueue.enqueue(vertex);
            }
        }

        while (!LIFOQueue.isEmpty()) {
            Vertex thisVertex = LIFOQueue.dequeue();

            if (visitedList.contains(thisVertex)) {
                continue;
            }

            visitedList.add(thisVertex);
            sortedVisitedList.add(thisVertex);

            List<Vertex> neighbours = thisVertex.getNeighbours();
            System.out.println("Jsem " + thisVertex.getLabel() + " a moji sousedi jsou: ");

            for (Vertex neighbour : neighbours) {
                System.out.print(neighbour.getLabel() + " ");
                if (!visitedList.contains(neighbour)) {
                    LIFOQueue.enqueue(neighbour);
                }
            }
            System.out.println();
        }
        return sortedVisitedList;
    }
}
