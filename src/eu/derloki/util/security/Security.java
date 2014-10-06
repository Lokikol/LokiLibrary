package eu.derloki.util.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Security {
	private static final SecureRandom ran = new SecureRandom();
	private static final int ITER = 9999;
	private static final int KEY_LEN = 256;
	
	
	public static byte[] getNextSalt() {
        byte[] salt = new byte[32];
        ran.nextBytes(salt);
        return salt;
    }
	
	 private static char[] cloneArrEraseOrig(char[] password) {
	        char[] ret = password.clone();
	        Arrays.fill(password, Character.MIN_VALUE);
	        return ret;
	 }
	 
	 public static byte[] getSHA(char[] password, byte[] salt) {
	        char[] pwd = cloneArrEraseOrig(password);
	        KeySpec spec = new PBEKeySpec(pwd, salt, ITER,KEY_LEN);
	        try {
	            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	            return f.generateSecret(spec).getEncoded();
	        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
	        }
	    }
	 
	 public static String getSHAString(char[] password, byte[] salt){
		 return Hex.encodeHexString(getSHA(password,salt));
	 }
	 
	 public static String generateID(String staticStuff){
		 DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		 Calendar cal = Calendar.getInstance();
		 
		 String sDate = dateFormat.format(cal.getTime());
				 
		 char[] pw = (staticStuff+sDate).toCharArray();
		 byte[] salt = getNextSalt();
		 
		 String ret = getSHAString(pw,salt);
		 
		 return ret;
	 }
}
