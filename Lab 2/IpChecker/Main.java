import java.util.*;
import java.util.regex.*;

public class Main
{
	public static void main(String args[])
	{

 		// Regex for digit from 0 to 255.
       		String zeroTo255 = "(\\d{1,2}|(0|1)\\"
                 + "d{2}|2[0-4]\\d|25[0-5])";
		// Regex for a digit from 0 to 255 and
        	// followed by a dot, repeat 4 times.
	        // this is the regex to validate an IP address.
        	String regex = zeroTo255 + "\\."
              	+ zeroTo255 + "\\."
              	+ zeroTo255 + "\\."
              	+ zeroTo255;

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
	
		Matcher m = p.matcher(args[0]);
		if(m.matches())
		{
			IpCheckerUsingIndexOf indexOfApproch = new IpCheckerUsingIndexOf(args[0]);
			IpCheckerUsingRegex regexApproch = new IpCheckerUsingRegex(args[0]);
			IpCheckerUsingStringTokenizer stringTokenizerApproch = new IpCheckerUsingStringTokenizer(args[0]);

			System.out.println("Index of:");
			for(String s : indexOfApproch.splitIpUsingIndexOf())
			{
				System.out.println(s);
			}	
			System.out.println("is valid ip :" + indexOfApproch.check());

			System.out.println("Tokenizer:");
			stringTokenizerApproch.splitIpUsingStringTokenizer();
			System.out.println("is valid ip :" + stringTokenizerApproch.check());

			System.out.println("Split():");
		
			for(String s : regexApproch.splitIpUsingSplit())
			{
				System.out.println(s);
			}
			System.out.println("is valid ip :" + regexApproch.check());
		}
		else 
		{
			System.out.println("Please Enter a valid ip address");
		}

	}
		
}
