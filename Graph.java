import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

class Graph {
    private Map<Vertex, List<Edge>> adjVertices;

    /*
	* Contructor to create a Graph.
	*/
    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    /*
	* Add a vertex to a graph.
	*/
    public void addVertex(char id) {
        adjVertices.putIfAbsent(new Vertex(id), new ArrayList<>());
    }

    /*
	* Add an edge to a a vertex.
    * @param {char} - The one character id of starting vertex.
    * @param {char} - The one character id of destination vertex.
    * @param {long}
	*/
    public void addEdge(char id1, char id2, long weight) {
        Vertex v1 = new Vertex(id1);
        Vertex v2 = new Vertex(id2);
        adjVertices.get(v1).add(new Edge(v2, weight));
    }

    /*
    * Get edges for a given vertex.
    * @param {Vertex}
    */
    public List<Edge> getEdges(Vertex v) {
        return adjVertices.get(v);
    }

    /*
    * Get all vertices as a set.
    */
    public Set<Vertex> getVertices() {
        return adjVertices.keySet();
    }
}