import java.io.IOException;

public class IMDBMoviesGraph extends IMDBGraph{

	IMDBMoviesGraph(String actorsFilename, String actressesFilename) throws IOException{
		super(actorsFilename, actressesFilename);
		nodes = FileData.getMoviesHashMap();
	}

}
