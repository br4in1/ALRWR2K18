package Utils;

import java.security.SecureRandom;

public class TokenGenerator {

	public static String generateToken(String username) {
		SecureRandom random1 = new SecureRandom();
		long longToken = Math.abs(random1.nextLong());
		String random = Long.toString(longToken, 16);
		return (username + ":" + random);
	}
}
