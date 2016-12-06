import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import com.cs210x.*;

/**
  * Class to deduce the identity of mystery data structures.
  */
public class ExperimentRunner {
	enum Mode {ADD, REMOVE, SEARCH};
	
	//These "throws" declarations are apparently needed by PrintWriter. Why you'd want to have main()
	//throw any exceptions, I don't know.
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		final int teamID = 1036;
		final int TRIALS = 2500;	//How many trials to run
		final int MAX_N = 1000;		//The maximum size of a collection

		for (int algoID = 0; algoID < 5; algoID++){ //Run tests for all 5 data structures
			Collection210X<Integer> dataStructure = MysteryDataStructure.getMysteryDataStructure(teamID, algoID, new Integer(0));
			//The eclipse debugger is extremely good at figuring these out!
			//0 - Binary Tree
			//1 - Heap
			//2 - Linked List
			//3 - Hash table
			//4 - Linked List
			//In our defense, the instructions didn't say that we couldn't do this,
			//they just said that we couldn't use the debugger as evidence...
			
			for (Mode mode : Mode.values()){ //Run tests for all modes
				Random rand = new Random(0);
				long[] times = new long[MAX_N]; //cumulative times, divide by #trials to get averages

				for (int trial = 0; trial < TRIALS; trial++){
					if (mode.equals(Mode.REMOVE) || mode.equals(Mode.SEARCH)){
						//Populate collection with random values that can be searched for or removed.
						for (int n = 0; n < MAX_N; n++)
							dataStructure.add(rand.nextInt(MAX_N));
					}

					for (int n = 0; n < MAX_N; n++){
						final long startTime = CPUClock.getNumTicks();

						//Run the actual experiments. Note that REMOVE and SEARCH will generate
						//data in reverse order, i.e. with DESCENDING n instead of ASCENDING n
						if (mode.equals(Mode.ADD))
							dataStructure.add(rand.nextInt());
						else if (mode.equals(Mode.REMOVE))
							dataStructure.remove(0); //Remove random element
						else if (mode.equals(Mode.SEARCH)){
							//This needs special handling because of the "remove" call
							dataStructure.contains(rand.nextInt(MAX_N));
							times[n] += CPUClock.getNumTicks() - startTime;
							dataStructure.remove(rand.nextInt() % dataStructure.size());
							continue;
						}
						times[n] += CPUClock.getNumTicks() - startTime;
					}
					dataStructure.clear();
				}

				//Print averages
				String typestr = "ADD";
				if (mode.equals(Mode.REMOVE))
					typestr = "REMOVE";
				else if (mode.equals(Mode.SEARCH))
					typestr = "SEARCH";
				PrintWriter writer = new PrintWriter(String.format("%d-%s.csv", algoID, typestr), "UTF-8");
				for (int n = 0; n < MAX_N; n++)
					writer.printf("%d, %d\n", n, times[n] / TRIALS);
				writer.close();
				System.out.printf("Finished writing '%s'!\n", String.format("%d-%s.csv", algoID, typestr));
			}
		}
	}
}
