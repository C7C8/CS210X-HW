import java.security.InvalidParameterException;
import java.util.*;
import com.cs210x.*;

/**
  * Class to deduce the identity of mystery data structures.
  */
public class ExperimentRunner {
	enum MODES {ADD, REMOVE, SEARCH};
	final int TRIALS = 10000;
	final int MAXN = 10000;
	
	public static void main (String[] args) {
		if (args.length != 3){
			System.out.println("Not enough arguments!");
			System.out.println("Usage: java ExperimentRunner [teamID] [algorithmID] [testID]");
			System.out.println("Test ID options are \"add\", \"remove\", and \"search\"");
			throw new InvalidParameterException();
		}
		
		final int teamID = Integer.parseInt(args[0]); // TODO CHANGE THIS TO THE TEAM ID YOU USE TO SUBMIT YOUR PROJECT3 ON INSTRUCT-ASSIST.
		final int algoID = Integer.parseInt(args[1]);
		@SuppressWarnings("unchecked") Collection210X<Integer> dataStructure = MysteryDataStructure.getMysteryDataStructure(teamID, algoID, new Integer(0));
		for (int i = 0; i < algoID; i++){
			//This should allow us to cycle through to the correct algorithm to test.
			dataStructure = MysteryDataStructure.getMysteryDataStructure(teamID, algoID, new Integer(0));
		}
		CPUClock clock = new CPUClock();
		Random rand = new Random(0);
		
		
		
	}
}
