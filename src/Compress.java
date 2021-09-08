import java.io.*;
import java.util.*;

public class Compress {
	private BufferedReader reader;
	private HashSet<String> codeTable;
	
	
	public Compress(String fileName) throws FileNotFoundException {
		//constructor; throw is needed for the FileReader
		codeTable = new HashSet<String>();
		//initialize codeTable with Strings of ASCII values starting with space(32) and ending with ~ (126)
		
		for (int i=32; i<127; i++) 
		{
			codeTable.add(String.valueOf((char)(i)));
		}
		
		File inputFile = new File(fileName);
		reader = new BufferedReader(new FileReader(inputFile));
		
	}
	
	
	public void encode() throws IOException {
		int nextLetterInt = 0;
		String currLetter, nextLetter;

		currLetter = "" + (char)reader.read();
		
		
		while(nextLetterInt != -1) {
			
			nextLetterInt = reader.read();
			nextLetter = "" + (char)nextLetterInt;
			if(codeTable.contains(currLetter + nextLetter)) {
				System.out.println("repeat " + currLetter + nextLetter);
				currLetter += nextLetter;
				
				
			}
			else {
				codeTable.add(currLetter+nextLetter);
				//hashSet does not add if it's already in there
				currLetter = nextLetter;
			}
			
			
		}
		
	}
	
}
