import java.io.*;
import java.util.*;

public class Compress {
	private BufferedReader reader;
	private PrintWriter writer;
	private HashMap<String, Integer> codeTable;
	private int counter;
	
	
	public Compress(String inputFileName, String outputFileName) throws FileNotFoundException {
		//constructor; throw is needed for the FileReader
		codeTable = new HashMap<String,Integer>();
		//initialize codeTable with Strings of ASCII values starting with space(32) and ending with ~ (126)
		counter = 1;
		for (int i=32; i<127; i++) 
		{
			codeTable.put(String.valueOf((char)(i)),counter );
			counter++;
		}
		
		File inputFile = new File(inputFileName);
		reader = new BufferedReader(new FileReader(inputFile));
		File outputFile = new File(outputFileName);
		writer = new PrintWriter(outputFile);
		
	}
	
	
	public void encode() throws IOException {
		int nextLetterInt = 0;
		String currLetter, nextLetter;

		currLetter = "" + (char)reader.read();
		
		
		while(nextLetterInt != -1) {
			
			nextLetterInt = reader.read();
			nextLetter = "" + (char)nextLetterInt;
			if(codeTable.containsKey(currLetter + nextLetter)) { 
				System.out.println("repeated: " + currLetter + nextLetter);
				currLetter += nextLetter;
				//do not add anything to the set, instead add nextLetter - it will add itself as the new current letter when the new (longer length)  string combo is checked
				writer.print("" + codeTable.get(currLetter)+ " ");
			}
			else {
				codeTable.put(currLetter+nextLetter, counter);
				counter++;
				//HashSet does not add if object is already in the set
				currLetter = nextLetter;
			}
			
			
		}
		reader.close();
		writer.close();
	}
	
	
	
	
}
