

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile {
	private String path;
	private boolean twice = false;
	private boolean start = false;
	private int beforeStop = 0;

	public  ReadFile(String file_path){
		path = file_path;
	}

	public void OpenFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];

		for(int i=0;i<numberOfLines;i++){
			String currentLine = textReader.readLine();
			currentLine = formatStringForIMDB(currentLine);
			textData[i] = currentLine;
			if(beforeStop>1){
				i = numberOfLines;
			}
		}
		FileData.setActorsAndMovies(textData);
		textReader.close();
	}

	public int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);

		String aline;
		int numberOfLines = 0;

		while((aline = bf.readLine()) != null){
			numberOfLines++;
		}

		bf.close();

		return numberOfLines;
	}

	public String formatStringForIMDB(String line){
		if(line.length()>5 && line.substring(0,5).equals("----\t")){
			start = true;
			beforeStop++;
			return "";
		}
		else if(start){
			
			final int LENGTH = line.length();
			if(LENGTH==0){
				return "";
			}
			else if(line.contains("TV") || line.contains("\"")){
				return "";
			}
			else if(blankLine(line)){
				return "";
			}

			else{
				String name = "";
				String movie = "";
				int index = 0;
				// get name
				while(index<LENGTH-1 && !line.substring(index,index+1).equals("\t")){
					index++;
				}
				name = line.substring(0,index);
				// get movie
				int movieIndex = index+1;
				while(index<LENGTH-1 && (!line.substring(index,index+1).equals(")"))){// test for )
					index++;
				}
				movie = line.substring(movieIndex,index+1);
				return name + ":" + movie;
			}
		}
		return "";
	}

	private boolean blankLine(String line) {
		for(int i=0;i<line.length();i++){
			if(!(line.substring(i,i+1).equals(" "))){
				return false;
			}
		}
		return true; 
	}

	private String formatString(String name, String movie) {
		String result = name + " " + movie;
		return movie;
	}
	private String removeSpace(String line){
		return line.replaceAll("\\t+","\n\t").replaceAll(" \n\n", " ");
	}
	private String removeSpaces(String line){
		return line.replaceAll("::\n","");
	}
}
