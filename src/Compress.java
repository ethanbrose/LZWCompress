import java.io.*;
import java.util.*;

public class Compress {
	private BufferedReader reader;
	private HashMap<String, Integer> codeTable;
	private int counter;
	private FileOutputStream fos;
	private ArrayList<Integer> outputIntegerCodes;
	
	
	private static final int BIT_0 = 0x001;
	private static final int BIT_1 = 0x002;
	private static final int BIT_2 = 0x004;
	private static final int BIT_3 = 0x008;
	private static final int BIT_4 = 0x010;
	private static final int BIT_5 = 0x020;
	private static final int BIT_6 = 0x040;
	private static final int BIT_7 = 0x080;
	private static final int BIT_8 = 0x100;

	private static final int[] BITS = { BIT_0, BIT_1, BIT_2, BIT_3, BIT_4, BIT_5, BIT_6, BIT_7, BIT_8 };

	public Compress(String inputFileName) throws FileNotFoundException {
		//constructor; throw is needed for the FileReader
		codeTable = new HashMap<String,Integer>();
		//initialize codeTable with Strings of ASCII values starting with space(32) and ending with ~ (126)
		counter = 0;
		for (int i=0; i<256; i++) 
		{
			codeTable.put(String.valueOf((char)(i)),counter );
			counter++;
		}

		File inputFile = new File(inputFileName);
		reader = new BufferedReader(new FileReader(inputFile));
	}


	public void encode() throws IOException {
		int nextLetterInt = 0; 
		String currString, nextLetter;
		currString = "";

		while(nextLetterInt != -1) {
			nextLetterInt = reader.read();
			nextLetter = "" + (char)nextLetterInt;
			if(codeTable.containsKey(currString + nextLetter)) { 
				System.out.println("repeated: " + currString + nextLetter);
				currString += nextLetter;
			}
			else {
				codeTable.put(currString+nextLetter, counter);
				counter++;
				//HashSet does not add if object is already in the set
				currString = nextLetter;
			}
		}
		reader.close();

		Collection<Integer> intVals = codeTable.values(); // encoded int vals

		//		File outputFile = new File("EncodedOutput.dat");
		//		outputFile.createNewFile();
		//		
		//		fos = new FileOutputStream(outputFile);
		//
		//		char[] charArray = binString.toCharArray(); // for ascii to array of bytes method 
		//		//http://www.java2s.com/Tutorial/Java/0180__File/Translatesbetweenbytearraysandstringsof0sand1s.htm
		//		
		//		byte[] rawData = BinaryEncoder.fromAscii(charArray);
		//		
		//		fos.write(rawData);
		//		
		//		fos.close();
		// sorry! the binary data is not showing up in the file but it is stored in rawData, for some reason file output stream will not write to the specified file, but it is being populated with something (just not visible). 
		// the first working commit uses a print writer to output all of the correct integers that represent characters
	}



	public String intToBinary(int num)
	{
		return Integer.toBinaryString(num);
	}










}
