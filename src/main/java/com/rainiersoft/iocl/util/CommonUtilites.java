package com.rainiersoft.iocl.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(password.getBytes("UTF-8"));
		return new BigInteger(1, crypt.digest()).toString(16);
	}
}
