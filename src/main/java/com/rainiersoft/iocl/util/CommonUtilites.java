package com.rainiersoft.iocl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;


/**
 * This is the class for CommonUtilites across all the methods.
 * @author RahulKumarPamidi
 */

public class CommonUtilites 
{
	public static int pinGen()
	{
		SecureRandom randomGenerator = null;
		try 
		{
			randomGenerator = SecureRandom.getInstance("SHA1PRNG");
		}
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		int pin=randomGenerator.nextInt(9999);
		return pin;
	}

	public static boolean checkPinHasFourDigits(int pin)
	{
		if(pin > 999 && pin <= 9999)
		{
			return true;
		}
		return false;
	}

	public static String encryption(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		String sha1 = "";
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(password.getBytes("UTF-8"));
		sha1 = byteToHex(crypt.digest());
		//return new BigInteger(1, crypt.digest()).toString(16);
		return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
		Formatter formatter = new Formatter();
		for (byte b : hash)
		{
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}