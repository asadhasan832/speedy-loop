import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SpeedyLoop {
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
			try {
				reader = new BufferedReader(new FileReader(argv[0]));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					startTown = line.charAt(0);
					endTown = line.charAt(1);
					distance = Long.parseLong(line.substring(2));
					System.out.println(startTown);
					System.out.println(endTown);
					System.out.println(distance);
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
