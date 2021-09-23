import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Graph {
    private Map<Vertex, List<Edge>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    public void addVertex(char id) {
        adjVertices.putIfAbsent(new Vertex(id), new ArrayList<>());
    }
    
    void addEdge(char id1, char id2, long weight) {
        Vertex v1 = new Vertex(id1);
        Vertex v2 = new Vertex(id2);
        adjVertices.get(v1).add(new Edge(v2, weight));
        System.out.println(adjVertices.toString());
    }
}