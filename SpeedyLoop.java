import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SpeedyLoop {
	private Graph graph;
	public long totalTrips;
	public SpeedyLoop() {
		this.graph = new Graph();
	}

	public void numberOfTripsWithinStopRange(Vertex startTown, Vertex endTown,
										long stopRangeStart, long stopRangeEnd,
										long currentStops) {
											if(startTown.equals(endTown) && currentStops > stopRangeStart && currentStops <= stopRangeEnd) totalTrips++;
											if(currentStops < stopRangeEnd) {
												for(Edge e: this.graph.getEdges(startTown)) {
													numberOfTripsWithinStopRange(e.getDestination(),
																							endTown, stopRangeStart,
																							stopRangeEnd, currentStops + 1);
												}
											}
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
					//Add vertex of start and end town
					sp.graph.addVertex(startTown);
					sp.graph.addVertex(endTown);
					//Add edge between two parsed towns
					sp.graph.addEdge(startTown, endTown, distance);
					// read next line
					line = reader.readLine();
				}
				reader.close();
				sp.totalTrips = 0;
				sp.numberOfTripsWithinStopRange(new Vertex('C'), new Vertex('C'),
										0, 3,
										0);
				System.out.println(sp.totalTrips);
				sp.totalTrips = 0;
				sp.numberOfTripsWithinStopRange(new Vertex('A'), new Vertex('C'),
										3, 4,
										0);
				System.out.println(sp.totalTrips);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
