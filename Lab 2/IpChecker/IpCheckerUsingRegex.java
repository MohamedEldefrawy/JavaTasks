public class IpCheckerUsingRegex implements IpChecker
{
	private String ip;
	private String[] result = new String[4];
	
	public IpCheckerUsingRegex(String ip)
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

	public String[] splitIpUsingSplit()
	{
		this.result = this.ip.split("\\.");

		return result;		
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