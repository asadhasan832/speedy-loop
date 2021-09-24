class Edge {
    private long weight;
    private Vertex destination;

    /*
	* Contructor to create a graph edge.
	* @param {Vertex} destination
    * @param {Vertex} weight
	*/
    public Edge(Vertex destination, long weight) {
        this.weight = weight;
        this.destination = destination;
    }

    /*
	* Getter to get edge weight.
    * @return {long}
	*/
    public long getWeight() {
        return weight;
    }

    /*
	* Getter to get edge destination.
    * @return {Vertex}
	*/
    public Vertex getDestination() {
        return destination;
    }
}