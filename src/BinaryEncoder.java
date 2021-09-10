
public class BinaryEncoder {

	private static byte[] EMPTY_BYTE_ARRAY = new byte[0];

	private static final int BIT_0 = 1;

	/** Mask for bit 1 of a byte. */
	private static final int BIT_1 = 0x02;

	/** Mask for bit 2 of a byte. */
	private static final int BIT_2 = 0x04;

	/** Mask for bit 3 of a byte. */
	private static final int BIT_3 = 0x08;

	/** Mask for bit 4 of a byte. */
	private static final int BIT_4 = 0x10;

	/** Mask for bit 5 of a byte. */
	private static final int BIT_5 = 0x20;

	/** Mask for bit 6 of a byte. */
	private static final int BIT_6 = 0x40;

	/** Mask for bit 7 of a byte. */
	private static final int BIT_7 = 0x80;

	private static final int[] BITS = { BIT_0, BIT_1, BIT_2, BIT_3, BIT_4, BIT_5, BIT_6, BIT_7 };

	/**
	 * Decodes a byte array where each char represents an ascii '0' or '1'.
	 * 
	 * @param ascii
	 *          each char represents an ascii '0' or '1'
	 * @return the raw encoded binary where each bit corresponds to a char in the
	 *         char array argument
	 */
	public static byte[] fromAscii(char[] ascii) {
		if (ascii == null || ascii.length == 0) {
			return EMPTY_BYTE_ARRAY;
		}
		// get length/8 times bytes with 3 bit shifts to the right of the length
		byte[] l_raw = new byte[ascii.length >> 3];
		/*
		 * We decr index jj by 8 as we go along to not recompute indices using
		 * multiplication every time inside the loop.
		 */
		for (int ii = 0, jj = ascii.length - 1; ii < l_raw.length; ii++, jj -= 8) {
			for (int bits = 0; bits < BITS.length; ++bits) {
				if (ascii[jj - bits] == '1') {
					l_raw[ii] |= BITS[bits];
				}
			}
		}
		return l_raw;
	}
	//http://www.java2s.com/Tutorial/Java/0180__File/Translatesbetweenbytearraysandstringsof0sand1s.htm 





	//		private byte[] intToByteArray ( int i ) throws IOException {      
	//	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	//	    DataOutputStream dos = new DataOutputStream(bos);
	//	    dos.writeInt(i);
	//	    dos.flush();
	//	    return bos.toByteArray();
	//	}
	//https://stackoverflow.com/questions/33069182/how-to-write-binary-to-a-file-in-java


}
