import java.util.StringTokenizer;
public class IpCheckerUsingStringTokenizer implements IpChecker
{

	private String ip;
	private StringTokenizer stringTokenizer;
	
	public IpCheckerUsingStringTokenizer(String ip)
	{
		this.ip = ip;
		this.stringTokenizer = new StringTokenizer(this.ip,".");
	}

	public void setIp(String ip)
	{
		this.ip = ip;
		this.stringTokenizer = new StringTokenizer(this.ip,".");
	}

	public String getIp()
	{
		return this.ip;
	}

	
	
	public void splitIpUsingStringTokenizer()
	{
		
		while(this.stringTokenizer.hasMoreTokens())
		{
			System.out.println(stringTokenizer.nextToken());
		}
	}

	
	// override
	public boolean check()
	{
		boolean flag = false;

		while(this.stringTokenizer.hasMoreTokens())
		{	
			String token = this.stringTokenizer.nextToken();
			if( Integer.parseInt(token) > 255 ||  Integer.parseInt(token) < 0)
				flag = false;
			else 
				flag = true;
		}
		
		return flag;
	}
	
}