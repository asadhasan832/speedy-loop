import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SpeedyLoop {
	private Graph graph;
	public SpeedyLoop() {
		this.graph = new Graph();
	}

	public static void main(String argv[]) {
		if(argv.length != 1) {
			//Display error and instructions if an input file is not provided.
			System.out.println("Usage: java SpeedyLoop INPUT_TEXT_FILE");
			System.out.println("Example: java SpeedyLoop inputs.txt");
		} else {
			BufferedReader reader;
			char startTown;
			char endTown;
			long distance;
			SpeedyLoop sp = new SpeedyLoop();

			try {
				reader = new BufferedReader(new FileReader(argv[0]));
				String line = reader.readLine();
				while (line != null) {
					//Parse Start Town
					startTown = line.charAt(0);
					//Parse Start Town
					endTown = line.charAt(1);
					//Parse Distance between two towns
					distance = Long.parseLong(line.substring(2));
					//Add vertex to start town
					sp.graph.addVertex(startTown);
					//Add edge between two parsed towns
					sp.graph.addEdge(startTown, endTown, distance);
					// read next line
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
