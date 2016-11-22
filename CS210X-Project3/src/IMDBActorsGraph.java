import java.io.IOException;

public class IMDBActorsGraph extends IMDBGraph {
	//What, you thought there was a difference?
	IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException{
		super(actorsFilename, actressesFilename);
		nodes = IMDBParser.getActors();
	}
}
