import java.io.*;

public class LZWTester {

	public static void main(String[]args) throws IOException {
			Compress compressor = new Compress("lzw-file1.txt");
			//remember the import statement at the top for BufferedReader and FileReader
			compressor.encode();
			//System.out.println("DONE");
			
			
			//taken from https://www.geeksforgeeks.org/counting-number-lines-words-characters-paragraphs-text-file-using-java/
			
			File file = new File(
		            "lzw-file3.txt");
		        FileInputStream fileInputStream
		            = new FileInputStream(file);
		        InputStreamReader inputStreamReader
		            = new InputStreamReader(fileInputStream);
		        BufferedReader bufferedReader
		            = new BufferedReader(inputStreamReader);
		 
		        String line;
		        int characterCount = 0;
		     
		 
		        while ((line = bufferedReader.readLine()) != null) {
		         
		                characterCount += line.length();
		                String words[] = line.split("\\s+");

		        }
		        System.out.println("Total number of characters from input file = " + characterCount);
		      
		        
		        File file2 = new File(
		                "EncodedOutput.dat");
		            FileInputStream fileInputStream2
		                = new FileInputStream(file2);
		            InputStreamReader inputStreamReader2
		                = new InputStreamReader(fileInputStream2);
		            BufferedReader bufferedReader2
		                = new BufferedReader(inputStreamReader2);
		     
		            String line2;
		            int wordCount = 0;
		        
		     
		            while ((line2 = bufferedReader2.readLine()) != null) {
		                
		                   
		                    String words[] = line2.split("\\s+");
		                    wordCount += words.length;
		                 
		                }
		            
		         
		            System.out.println("Total word count in output file= "
		                               + wordCount);
		     
	}
}
