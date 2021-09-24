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

    /*
	* Contructor to create Dijstras Algorithm instance for a graph.
	* @param {Graph} graph
	*/
    public DijkstrasAlgorithm(Graph graph) {
        this.graph = graph;
        this.vertices = graph.getVertices();
    }

    /*
	* Perform Dijkstra's traversal and construct a distances hashmap for each vertex.
	* @param {Graph} graph
	*/
    public void traverse(Vertex start) {
        distances = new HashMap<Vertex, Long>();
        unvisitedVertices = new HashSet<Vertex>();

        //Start by setting the distance to the starting vertex to 0.
        distances.put(start, 0L);
        //Add start vertex to unvisited vertices queue.
        unvisitedVertices.add(start);
        while(unvisitedVertices.size() > 0) {
            //Find the vertex with the closest tenative distance
            Vertex v = getShortestTenative(unvisitedVertices);
            //Remove vertex from unvisited queue.
            unvisitedVertices.remove(v);
            //Iterate over all edges of vertex, if the distance to it
            //including the current distance is less than the stored distance
            //set the stored index to the sum of current vertex distance and edge weight
            //and mark vertex as unvisited.
            findShortestDistances(v);
        }
    }

    /*
	* Get the stored shortest distance.
	* @param {Vertex} v
    * @return {long} - Returns the shortest distance or the max numerical value akin to infiniti.
	*/
    public long getShortestDistance(Vertex v) {
        Long distance = distances.get(v);
        if(distance == null) {
            return Long.MAX_VALUE;
        } else {
            return distance;
        }
    }

    /*
	* Get the distance between two vertices, or infinite (MAX_VALUE) if they have no,
    * direct connection.
	* @param {Vertex} start
    * @param {Vertex} end
    * @return {long} - Returns the distance between tow vertices.
	*/
    private long getDistance(Vertex start, Vertex end) {
        List<Edge> edges = this.graph.getEdges(start);
        for(Edge edge: edges) {
            if(edge.getDestination().equals(end)) return edge.getWeight();
        }
        return Long.MAX_VALUE;
    }

    /*
	* Get the vertiex closest amognst a given set of vertices.
	* @param {Set<Vertex>} vertices
    * @return {Vertex} - Returns closest vertex.
	*/
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

    /*Iterate over all edges of vertex, if the distance to it
    * including the current distance is less than the stored distance
    * set the stored index to the sum of current vertex distance and edge weight
    * and mark vertex as unvisited. 
    * @param {Vertex} - The vertex whose edges will be iterated to set distances for
    * adjacent vertices, which are marked as unvisited if needed.
    */
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