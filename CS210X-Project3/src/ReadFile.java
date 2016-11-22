

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
			//System.out.println(currentLine);
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
		if(line.length()>=5 && line.substring(0,5).equals("----\t")){
			start = true;
			beforeStop++;
			return "";
		}
		else if(start){
			int LENGTH = line.length();
			if(LENGTH<1){
				return "";
			}
			else if(line.contains("(TV)") || line.contains("\"")){
				return "";
			}
			else{
				int index = 0;
				while(!line.substring(index,index+1).equals("\t")){
					index++;
				}
				String name = line.substring(0,index);
				int movieInd = index;
				while(!line.substring(movieInd,movieInd+1).equals(")")){
					movieInd++;
				}
				String movie = removeTabs(line.substring(index+1,movieInd+1));
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
	private String removeTabs(String line){
		return line.replaceAll("\t+","");
	}
}
