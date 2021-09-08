import java.io.*;

public class LZWTester {

	public static void main(String[]args) throws IOException {
			Compress compressor = new Compress("lzw-file2.txt", "EncodedOutput");
			//remember the import statement at the top for BufferedReader and FileReader
			compressor.encode();
			//System.out.println("DONE");
		
	}
}
