class Edge {
    public long weight;
    public Vertex destination;

    public Edge(Vertex destination, long weight) {
        this.weight = weight;
        this.destination = destination;
    }
}