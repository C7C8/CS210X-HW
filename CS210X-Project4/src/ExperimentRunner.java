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
		final int TRIALS = 10000;	//How many trials to run
		final int MAX_N = 1000;		//The maximum size of a collection
		
		final int ADD = 0;
		final int REMOVE = 1;

		for (int algoID = 0; algoID < 5; algoID++){
			for (int mode = ADD; mode <= REMOVE; mode++){

				@SuppressWarnings("unchecked") 
				Collection210X<Integer> dataStructure = MysteryDataStructure.getMysteryDataStructure(teamID, algoID, new Integer(0));

				Random rand = new Random(0);
				long[] times = new long[MAX_N];


				for (int trial = 0; trial < TRIALS; trial++){
					if (mode == REMOVE){
						//Populate collection with random values
						for (int n = 0; n < MAX_N; n++)
							dataStructure.add(rand.nextInt());
					}

					for (int n = 0; n < MAX_N; n++){
						final long startTime = CPUClock.getNumTicks();

						if (mode == ADD)
							dataStructure.add(rand.nextInt());
						else if (mode == REMOVE)
							dataStructure.remove(rand.nextInt() % dataStructure.size());
						
						times[n] += CPUClock.getNumTicks() - startTime;
					}
					dataStructure.clear();
				}

				//Now print averages
				PrintWriter writer = new PrintWriter(String.format("%d-%s.csv", algoID, mode == ADD ? "ADD" : "REMOVE"), "UTF-8");
				for (int n = 0; n < MAX_N; n++)
					writer.printf("%d, %d\n", n, times[n] / TRIALS);
				writer.close();
				System.out.printf("Finished writing '%s'!\n", String.format("%d-%s.csv", algoID, mode == ADD ? "ADD" : "REMOVE"));
			}
		}
	}
}
