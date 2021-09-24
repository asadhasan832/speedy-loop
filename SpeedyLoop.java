import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class SpeedyLoop {
	private Graph graph;
	private long totalTrips;
	private DijkstrasAlgorithm dijkstrasAlgorithmSingleton;

	public SpeedyLoop() {
		this.graph = new Graph();
		this.dijkstrasAlgorithmSingleton = new DijkstrasAlgorithm(this.graph);
	}

	public long shortestDistance(char startTown, char endTown) {
		if(startTown == endTown) { 
			Vertex start = new Vertex(startTown);
			Vertex closest = findClosestEdge(start).getDestination();
			this.dijkstrasAlgorithmSingleton.traverse(start);
			long distance1 = this.dijkstrasAlgorithmSingleton.getShortestDistance(closest);
			this.dijkstrasAlgorithmSingleton.traverse(closest);
			long distance2 = this.dijkstrasAlgorithmSingleton.getShortestDistance(start);

			return distance1+distance2;
		} else {
			this.dijkstrasAlgorithmSingleton.traverse(new Vertex(startTown));
			return this.dijkstrasAlgorithmSingleton.getShortestDistance(new Vertex(endTown));
		}
	}

	private Edge findClosestEdge(Vertex start) {
		List<Edge> edges = this.graph.getEdges(start);
		Edge closest = null;
		for(Edge e: edges) {
			if(closest == null || closest.getWeight() > e.getWeight()) {
				closest = e;
			}
		}
		return closest;
	}

	/*
	* This method calculates the distance of a given route as an ArrayList of Characters.
	* If the route does not exist, the method returns a -1 or else it returns the path's distance.
	* @param {ArrayList<Character>} route - An ArrayList of towns given by their character as a route path.
	* @return {long}
	*/
	private long routeDistance(ArrayList<Character> route) {
		long distance = 0;
		if(route.size() == 0) return distance;
		char town = route.remove(0);
		Vertex startTown;
		boolean found;
		while(route.size() > 0) {
			found = false;
			startTown = new Vertex(town);
			town = route.remove(0);
			for(Edge e: this.graph.getEdges(startTown)) {
				if(e.getDestination().equals(new Vertex(town))) {
					distance += e.getWeight();
					found = true;
					break;
				}
			}
			if(!found) return -1;
		}
		return distance;
	}

	/*
	* This method converts the List of towns given as a route to an ArrayList, to be passed to a
	* polymorphic method of the same name which accepts ArrayList, and takes the resultant distance
	* and returns it as a String representing the numeric distance. If a negative number is found a
	* message stating 'NO SUCH ROUTE' is returned instead.
	* @return {String}
	*/
	public String routeDistance(List<Character> route) {
		long distance = routeDistance(new ArrayList<>(route));
		if(distance < 0) {
			return "NO SUCH ROUTE";
		} else {
			return Long.toString(distance);
		}
	}

	/*
	* This method implements the calculation of the total number of routes uptill a 
	* maximum distance (non-inclusive). Current implementation is recursive hence
	* makes use of the Java call stack. When running in production a high stack-size with the 
	* Java flag '-Xss'. Method can be refactored to be implemented in Sala to take 
	* advantage of tail-recursion optimization, if the input size cannot be anticipated,
	* and extremely large input has to be handled, granted such large input may be a 
	* denial of service attack on the production systems, and rejection beyond the suitable
	* stack size may be reasonable. 
	* @param {Vertex} startTown - The starting vertex of graph to calculate number of trips from.
	* @param {Vertex} endTown - The ending vertex of graph to calculate number of trips to.
	* @param {long} minDistance - The minimum distance (non-inclusive) to account for.
	* @param {long} maxDistance - The maximum distance (inclusive) to account to calculate routes for.
	* @param {long} currentDistance - Must be called with 0, recursive sentinel to detect when
	*							   maximum distance has been reached.
	*/
	private void numberOfRoutesWithinDistanceRange(Vertex startTown, Vertex endTown,
										long minDistance, long maxDistance,
										long currentDistance) {
											if(startTown.equals(endTown) && currentDistance > minDistance && currentDistance < maxDistance) totalTrips++;
											if(currentDistance < maxDistance) {
												for(Edge e: this.graph.getEdges(startTown)) {
													numberOfRoutesWithinDistanceRange(e.getDestination(),
																							endTown, minDistance,
																							maxDistance, currentDistance + e.getWeight());
												}
											}
	}

	public long numberOfRoutesMaxDistance(char startTown, char endTown, long maxDistance) {
		totalTrips = 0;
		numberOfRoutesWithinDistanceRange(new Vertex(startTown), new Vertex(endTown),
										0, maxDistance,
										0);
		return totalTrips;
	}

	/*
	* This method implements the calculation of the total number of trips within a
	* given range of number of stops. Current implementation is recursive hence makes
	* use of the Java call stack. When running in production a high stack-size with the 
	* Java flag '-Xss'. Method can be refactored to be implemented in Sala to take 
	* advantage of tail-recursion optimization, if the input size cannot be anticipated,
	* and extremely large input has to be handled, granted such large input may be a 
	* denial of service attack on the production systems, and rejection beyond the suitable
	* stack size may be reasonable. 
	* @param {Vertex} startTown - The starting vertex of graph to calculate number of trips from.
	* @param {Vertex} endTown - The ending vertex of graph to calculate number of trips to.
	* @param {long} stopRangeStart - The minimum number of stops (non-inclusive) to account for.
	* @param {long} stopRangeEnd - The maximum number of stops (inclusive) to account for.
	* @param {long} currentStops - Must be called with 0, recursive sentinel to detect when
	*							   maximum number of stops has been reached.
	*/
	private void numberOfTripsWithinStopRange(Vertex startTown, Vertex endTown,
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

	/*
	* This method calculated the number of trips between two towns, given their one character
	* name, uptill a maximum number of stops.
	* @param {char} startTown - One character starting town's name to calculate trips from.
	* @param {char} endTown - One character ending town's name to calculate trips to.
	* @param {long} maxStops - Maximum number of stops between startTown (non-inclusive) and
	* endTown (inclusive).
	* @return {long} maxStops
	*/
	public long numberOfTripsMaxStops(char startTown, char endTown, long maxStops) {
		totalTrips = 0;
		numberOfTripsWithinStopRange(new Vertex(startTown), new Vertex(endTown),
										0, maxStops,
										0);
		return totalTrips;
	}

	/*
	* This method calculated the number of trips between two towns, given their one character
	* name, given the exact number of stops.
	* @param {char} startTown - One character starting town's name to calculate trips from.
	* @param {char} endTown - One character ending town's name to calculate trips to.
	* @param {long} exactStops - Exact number of stops between startTown (non-inclusive) and
	* endTown (inclusive).
	* @return {long} maxStops
	*/
	public long numberOfTripsExactStops(char startTown, char endTown, long exactStops) {
		totalTrips = 0;
		numberOfTripsWithinStopRange(new Vertex(startTown), new Vertex(endTown),
										exactStops-1, exactStops,
										0);
		return totalTrips;
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
				System.out.println(sp.routeDistance(List.of('A', 'B', 'C')));
				System.out.println(sp.routeDistance(List.of('A', 'D')));
				System.out.println(sp.routeDistance(List.of('A', 'D', 'C')));
				System.out.println(sp.routeDistance(List.of('A', 'E', 'B', 'C', 'D')));
				System.out.println(sp.routeDistance(List.of('A', 'E', 'D')));
				System.out.println(sp.numberOfTripsMaxStops('C', 'C', 3));
				System.out.println(sp.numberOfTripsExactStops('A', 'C', 4));
				System.out.println(sp.shortestDistance('A', 'C'));
				System.out.println(sp.shortestDistance('B', 'B'));
				System.out.println(sp.numberOfRoutesMaxDistance('C', 'C', 30));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
