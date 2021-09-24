class Vertex {
    private char id;
    /*
	* Contructor to create a Vertex.
	* @param {char} id
	*/
    public Vertex(char id) {
        this.id = id;
    }

    /* Method to make a vertex comparable to another.
    * @param {Object} Comparable object will be passed here.
    */
    public boolean equals(Object v) {
        //Typecast object in to a vertex for comparison.
        Vertex vc = (Vertex)v;
        //Determine objects to be the same if id is the same character.
        return Character.compare(this.id, vc.id) == 0;
    }

    /* Method to create hashCode for Vertex. */
    public int hashCode() {
        //Return hashcode of character.
        return Character.valueOf(this.id).hashCode();
    }
}