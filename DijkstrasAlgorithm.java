import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

class DijkstrasAlgorithm {
    private Graph graph;
    private Set<Vertex> vertices;
    private Set<Vertex> unvisitedVertices;
    private Map<Vertex, Long> distances;

    public DijkstrasAlgorithm(Graph graph) {
        this.graph = graph;
        this.vertices = graph.getVertices();
    }

    public void traverse(Vertex start) {
        distances = new HashMap<Vertex, Long>();
        unvisitedVertices = new HashSet<Vertex>();

        distances.put(start, 0L);
        unvisitedVertices.add(start);
        while(unvisitedVertices.size() > 0) {
            Vertex v = getShortestTenative(unvisitedVertices);
            unvisitedVertices.remove(v);
            findShortestDistances(v);
        }
    }

    public long getShortestDistance(Vertex v) {
        Long distance = distances.get(v);
        if(distance == null) {
            return Long.MAX_VALUE;
        } else {
            return distance;
        }
    }

    private long getDistance(Vertex start, Vertex end) {
        List<Edge> edges = this.graph.getEdges(start);
        for(Edge edge: edges) {
            if(edge.getDestination().equals(end)) return edge.getWeight();
        }
        return Long.MAX_VALUE;
    }

    private Vertex getShortestTenative(Set<Vertex> vertices) {
        Vertex closest = null;
        for(Vertex v: vertices) {
            if(closest == null) {
                closest = v;
            } else {
                if(getShortestDistance(v) < getShortestDistance(closest)) {
                    closest = v;
                }
            }
        }
        return closest;
    }

    private void findShortestDistances(Vertex v) {
        List<Edge> edges = this.graph.getEdges(v);
        for(Edge e: edges) {
            if(getShortestDistance(e.getDestination()) > getShortestDistance(v) + getDistance(v, e.getDestination())) {
                distances.put(e.getDestination(), getShortestDistance(v) + getDistance(v, e.getDestination()));
                unvisitedVertices.add(e.getDestination());
            }
        }
    }
}