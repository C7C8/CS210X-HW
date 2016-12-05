import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.*;
import com.cs210x.*;

/**
  * Class to deduce the identity of mystery data structures.
  */
public class ExperimentRunner {
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		final int teamID = 1036;
		final int TRIALS = 5000;	//How many trials to run
		final int MAX_N = 1000;		//The maximum size of a collection
		
		final int ADD = 0, REMOVE = 1, SEARCH = 2;

		for (int algoID = 0; algoID < 5; algoID++){
			@SuppressWarnings("unchecked") 
			Collection210X<Integer> dataStructure = MysteryDataStructure.getMysteryDataStructure(teamID, algoID, new Integer(0));
			//The eclipse debugger is extremely good at figuring these out!
			//0 - Binary Tree
			//1 - Heap
			//2 - Linked List
			//3 - Hash table
			//4 - Linked List
			
			for (int mode = ADD; mode <= SEARCH; mode++){
				Random rand = new Random(0);
				long[] times = new long[MAX_N];

				for (int trial = 0; trial < TRIALS; trial++){
					if (mode == REMOVE || mode == SEARCH){
						//Populate collection with random values
						for (int n = 0; n < MAX_N; n++)
							dataStructure.add(rand.nextInt(MAX_N));
					}

					for (int n = 0; n < MAX_N; n++){
						final long startTime = CPUClock.getNumTicks();

						if (mode == ADD)
							dataStructure.add(rand.nextInt());
						else if (mode == REMOVE)
							dataStructure.remove(rand.nextInt() % dataStructure.size()); //Remove random element
						else if (mode == SEARCH){
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

				//Now print averages
				String typestr = "ADD";
				if (mode == REMOVE)
					typestr = "REMOVE";
				else if (mode == SEARCH)
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
