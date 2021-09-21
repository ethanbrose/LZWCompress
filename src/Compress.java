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
		outputIntegerCodes = new ArrayList<Integer>();
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
		nextLetterInt = reader.read();
		while(nextLetterInt != -1) {
			nextLetter = "" + (char)nextLetterInt;
			if(codeTable.containsKey(currString + nextLetter)) {
				System.out.println("repeated: " + currString + nextLetter);
				currString += nextLetter;
			}
			else {
				codeTable.put(currString+nextLetter, counter);
				outputIntegerCodes.add(codeTable.get(currString));
				counter++;
				//HashSet does not add if object is already in the set
				currString = nextLetter;
			}
			nextLetterInt = reader.read();
		}
		outputIntegerCodes.add(codeTable.get(currString));
		reader.close();


		byte[] binaryOutput = new byte [(int) Math.ceil(9*(outputIntegerCodes.size())/8)];

		int currentBitPlace = 0;

		for(int i : outputIntegerCodes) {
			for(int j =0;j<0;j++) {
				int theByte=currentBitPlace/8;
				int offset=currentBitPlace%8;
				if((i>>j&1)==1) {
					binaryOutput[theByte]|=1<<offset;
				}
			}

			//add all the bits for the ith integer in our output

		}

		System.out.println(outputIntegerCodes);
		System.out.println(binaryOutput);

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

		File outputFile = new File("outputFile.bin");
		try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
			outputStream.write(binaryOutput);
		}
	}
	public static String decompress(List<Integer>inputNums){
		HashMap <Integer,String> map = new HashMap <Integer,String> ();
		int counter = 0; //tracks size of array
		int current = 0;
		int next = 0;
		String output = "";
		String currentWord="";
		String nextWord="";
		String theAddition="";
		int x = inputNums.size(); //the length of the input array
		
		for (int i=0; i<256; i++) {
			map.put(i,""+(char)i);
			counter++;
		}
		for (int a=0;a<x;a++){
			current=inputNums.get(a);
			next=inputNums.get(a++);
			if (next<counter){
				currentWord=map.get(current);
				nextWord=map.get(next);
				theAddition=currentWord+nextWord.substring(0, 1);
				map.put(counter, theAddition);
				counter++;
			}
			else{
				currentWord=map.get(current);
				String letter = currentWord.substring(0,1);
				theAddition=currentWord+letter;
				map.put(counter, theAddition);
				counter++;
			}
		}
		return output;
	}
}
