package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadNonAsciiFile {
	private String path;

	public ReadNonAsciiFile(String file_path){
		path = file_path;
	}

	public String[] OpenFile() throws IOException{

		final Scanner scanner = new Scanner(new File(path), "ISO-8859-1");
		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];

		for(int i=0;i<numberOfLines;i++){
			String currentLine = scanner.nextLine();
			currentLine = formatStringForIMDB(currentLine);
			textData[i] = currentLine;
		}

		scanner.close();
		return textData;
	}

	public int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		final Scanner scanner = new Scanner(new File(path), "ISO-8859-1");

		String aline;
		int numberOfLines = 0;

		while(scanner.hasNextLine()){
			scanner.nextLine();
			numberOfLines++;
		}

		scanner.close();

		return numberOfLines;
	}

	public String formatStringForIMDB(String line){
		final int LENGTH = line.length();
		
		if(LENGTH==0){
			return "";
		}
		
		else if(anythingBad(line)){
			//return "";
		}

		return line;
	}

	private boolean anythingBad(String line) {
		return 	line.contains("\"") || line.contains("#") || 
				line.contains("-") || line.contains("#") ||
				line.contains("[") || line.contains("]") ||
				line.contains("{") || line.contains("TV");
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
		String result = name + "   " +movie;
		return result;
	}
}
