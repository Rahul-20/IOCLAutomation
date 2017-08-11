package com.rainiersoft.iocl.security;
/*package com.bhavanee.iocl.security;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicAuth 
{
	private static final Logger LOG = LoggerFactory.getLogger(BasicAuth.class);
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	public static String[] decode(String auth) 
	{
		//Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
		auth = auth.replaceFirst("[B|b]asic ", "");

		//Decode the Base64 into byte[]
		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);

		//If the decode fails in any case
		if(decodedBytes == null || decodedBytes.length == 0)
		{
			return null;
		}

		//Now we can convert the byte[] into a splitted array :
		//  - the first one is login,
		//  - the second one password
		return new String(decodedBytes).split(":", 2);
	}
}
*/