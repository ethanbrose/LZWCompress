import java.io.*;
import java.util.*;

public class Compress {
	private BufferedReader reader;
	private HashMap<String, Integer> codeTable;
	private int counter;
	FileOutputStream fos;
	private String binString;
	



	
	public Compress(String inputFileName, String outputFileName) throws FileNotFoundException {
		//constructor; throw is needed for the FileReader
		codeTable = new HashMap<String,Integer>();
		//initialize codeTable with Strings of ASCII values starting with space(32) and ending with ~ (126)
		counter = 1;
		for (int i=0; i<256; i++) 
		{
			codeTable.put(String.valueOf((char)(i)),counter );
			counter++;
		}
		
		File inputFile = new File(inputFileName);
		reader = new BufferedReader(new FileReader(inputFile));		
		fos = new FileOutputStream(outputFileName);
		binString = "";
		
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
				binString+= intToBinary(codeTable.get(currLetter));
				
				
				
			}
			else {
				codeTable.put(currLetter+nextLetter, counter);
				counter++;
				//HashSet does not add if object is already in the set
				currLetter = nextLetter;
			}
			
			
		}
		reader.close();
		
		
		char[] charArray = binString.toCharArray(); // for ascii to array of bytes method 
		//http://www.java2s.com/Tutorial/Java/0180__File/Translatesbetweenbytearraysandstringsof0sand1s.htm
		
		byte[] rawData = BinaryEncoder.fromAscii(charArray);
		
		fos.write(rawData);
		
		fos.close();
	}
	

	
	  public String intToBinary(int num)
	    {
	        return Integer.toBinaryString(num);
	    }
	  
	  
	

			
	        
		
	
	
	
}
