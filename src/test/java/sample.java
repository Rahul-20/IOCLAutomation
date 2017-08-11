import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

public class sample 
{
	public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String createdTimeStamp=df.format(dateobj).toString();

		System.out.println("This is the Actual Date:"+dateobj);
		Calendar cal = new GregorianCalendar();
		cal.setTime(dateobj);
		cal.add(Calendar.YEAR, 1);
		System.out.println("This is Hours Added Date:"+cal.getTime());
		String cTimeStamp=df.format(cal.getTime()).toString();

		System.out.println(cTimeStamp);
		System.out.println("::::::::"+encryptPassword("iocl"));


		//Decode the Base64 into byte[]
		byte[] decodedBytes = DatatypeConverter.parseBase64Binary("aW9jbDppb2Ns");

		//Now we can convert the byte[] into a splitted array :
		//  - the first one is login,
		//  - the second one password
		String[] s=new String(decodedBytes).split(":", 2);
		
		System.out.println(s[0]+"::::::"+s[1]);
		
		
		try 
		{
		     SecureRandom randomGenerator = SecureRandom.getInstance("SHA1PRNG");         
		     HashSet<Integer> set = new HashSet<>();
		     while(set.size()<10)
		     {
		    	 System.out.println(randomGenerator.nextInt(9999));
		         set.add(randomGenerator.nextInt(9999));
		     }

		} catch (NoSuchAlgorithmException nsae) {
		  // Forward to handler
		}
		
		String exp="2019/09/08 00:51:14";
		
		DateFormat daf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateaobj = new Date();
		
		
		Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(dateaobj);
        cal2.setTime(new Date(exp));
		
        System.out.println(cal1+"::::::::"+cal2);
        if(!(cal1.before(cal2)))
        {
        	System.out.println("Ca;;;;;;");
        }
        else if(cal2.after(cal1))
        {
        	System.out.println("aaaaaa");
        }
        
        
        
        ArrayList<String>  arr=new ArrayList<String>();
        arr.add("Super Admin");
        
        String u="Super Admin";
        
        System.out.println(":::::::"+(arr.contains(u)));
        
        Set<String> rolesSet=new HashSet<>();
        rolesSet.add("Super Admi");
        
        System.out.println(":::::::::"+rolesSet.contains(u));


	}

	private static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		password="e4fc4f8704b6c82b24518dc207c616b7a921e9ef";
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(password.getBytes("UTF-8"));
		return new BigInteger(1, crypt.digest()).toString(16);
	}
}