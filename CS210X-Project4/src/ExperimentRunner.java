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
	enum MODES {ADD, REMOVE, SEARCH};
	
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		if (args.length != 3){
			System.out.println("Not enough arguments!");
			System.out.println("Usage: java ExperimentRunner [teamID] [algorithmID] [testID]");
			System.out.println("Test ID options are \"add\", \"remove\", and \"search\"");
			throw new InvalidParameterException();
		}

		final int TRIALS = 10;	//How many trials to run
		final int MAX_N = 10000;	//The maximum size of a collection
		final int teamID = Integer.parseInt(args[0]); // TODO CHANGE THIS TO THE TEAM ID YOU USE TO SUBMIT YOUR PROJECT3 ON INSTRUCT-ASSIST.
		final int algoID = Integer.parseInt(args[1]);
		MODES mode = MODES.ADD;
	
		switch (args[2]){
		case "add":
			mode = MODES.ADD;
			break;
		case "remove":
			mode = MODES.REMOVE;
			break;
		default:
			System.out.println("Bad mode specified, switching to ADD");
			mode = MODES.ADD;
		}
		
		
		@SuppressWarnings("unchecked") 
		Collection210X<Integer> dataStructure = MysteryDataStructure.getMysteryDataStructure(teamID, algoID, new Integer(0));
		
		Random rand = new Random(0);
		long[] times = new long[MAX_N];
		
		
		for (int trial = 0; trial < TRIALS; trial++){
			if (mode == MODES.REMOVE){
				//Populate collection with random values
				for (int n = 0; n < MAX_N; n++)
					dataStructure.add(rand.nextInt());
			}
			
			for (int n = 0; n < MAX_N; n++){
				final long startTime = CPUClock.getNumTicks();
				
				if (mode == MODES.ADD)
					dataStructure.add(rand.nextInt());
				else if (mode == MODES.REMOVE)
					dataStructure.remove(rand.nextInt() % dataStructure.size());
				//No search, because we don't need searches... right?
				
				times[n] += CPUClock.getNumTicks() - startTime;
			}
			dataStructure.clear();
		}
		
		//Now print averages
		PrintWriter writer = new PrintWriter("0-ADD.csv", "UTF-8");
		for (int n = 0; n < MAX_N; n++)
			writer.printf("%d, %d\n", n, times[n] / TRIALS);
		writer.close();
			
	}
}
