package cn.sunflyer.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Enc {
	
	public static byte[] intToByte(int len , int arrayLen){
		if(arrayLen <= 0)
			return null;
		byte[] data = new byte[arrayLen];
		
		for(int i = 0 ; i < arrayLen ; i ++){
			data[i] = (byte)(len >> (8 * (arrayLen - i - 1)));
		}
		
		return data;
	}
	
	public static byte[] hexToByte(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	   public static String byteArrayToHex(byte[] byteArray) {
		      char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
		      char[] resultCharArray =new char[byteArray.length * 2];
		      int index = 0;
		      for (byte b : byteArray) {
		         resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
		         resultCharArray[index++] = hexDigits[b& 0xf];
		      }
		      return new String(resultCharArray);
		}
	
	   public static String MD5(String input) {

		      try {
		         MessageDigest messageDigest =MessageDigest.getInstance("MD5");
		         byte[] inputByteArray = input.getBytes();
		         messageDigest.update(inputByteArray);
		         byte[] resultByteArray = messageDigest.digest();

		         return byteArrayToHex(resultByteArray);

		      } catch (NoSuchAlgorithmException e) {

		         return null;

		      }

		   }

}
