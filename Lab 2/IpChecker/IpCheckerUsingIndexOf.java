public class IpCheckerUsingIndexOf implements IpChecker
{
	private String ip;
	private String[] result = new String[4];
	
	public IpCheckerUsingIndexOf(String ip)
	{
		this.ip = ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getIp()
	{
		return this.ip;
	}

	public String[] splitIpUsingIndexOf()
	{
		int firstDot = this.ip.indexOf(".");

		int secondDot = this.ip.indexOf("." , firstDot + 1);

		int thirdDot = this.ip.indexOf(".",secondDot + 1);
		
		this.result[0] = this.ip.substring(0,firstDot);
		this.result[1] = this.ip.substring(firstDot  + 1,secondDot);
		this.result[2] = this.ip.substring(secondDot + 1,thirdDot);
		this.result[3] = this.ip.substring(thirdDot  + 1,ip.length());

		return this.result;
	}


	// override
	public boolean check()
	{
		boolean flag = false;

		for(String s : this.result)
		{
			if( (Integer.parseInt(s) > 255) ||  (Integer.parseInt(s) < 0) )
			{
				flag = false;
			}				
			else
			{
				flag =  true;
			} 		
		}
		
		return flag;
	}
		


	
			
}