package Utils;

import java.security.SecureRandom;

public class TokenGenerator {

	public static String generateToken(String username) {
		SecureRandom random1 = new SecureRandom();
		long longToken = Math.abs(random1.nextLong());
		String random = Long.toString(longToken, 16);
		return (username + ":" + random);
	}
	
	public static String generateToken2() {
		SecureRandom random1 = new SecureRandom();
		long longToken = Math.abs(random1.nextLong());
		String random2 = Long.toString(longToken, 16);
		String random4 = Long.toString(longToken, 16);
		String random3 = Long.toString(longToken, 16);
		return (random2+random3+random4);
	}
}
