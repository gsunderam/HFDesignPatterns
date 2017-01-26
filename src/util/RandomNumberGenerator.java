package util;

/**
 * User: gsunderam
 * Date: Feb 27, 2015
 */
public class RandomNumberGenerator {
	public static int xorShift(int y) {
		  y ^= (y << 6);
			y ^= (y >>> 21);
			y ^= (y << 7);
		
			return y;
	}
}
