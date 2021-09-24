class Edge {
    private long weight;
    private Vertex destination;

    public Edge(Vertex destination, long weight) {
        this.weight = weight;
        this.destination = destination;
    }

    public long getWeight() {
        return weight;
    }

    public Vertex getDestination() {
        return destination;
    }
}