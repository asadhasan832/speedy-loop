class Vertex {
    private char id;
    public Vertex(char id) {
        this.id = id;
    }

    //Make a vertex comparable to another.
    public boolean equals(Object v) {
        //Typecast object in to a vertex for comparison.
        Vertex vc = (Vertex)v;
        //Determine objects to be the same if id is the same character.
        return Character.compare(this.id, vc.id) == 0;
    }

    public int hashCode() {
        //Return hashcode of character.
        return Character.valueOf(this.id).hashCode();
    }
}