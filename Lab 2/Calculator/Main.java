public class Main
{
	public static void main(String [] args)
	{
		Calculator calculator ;
		
		if(args.length != 3)
		{
			System.out.println("Please insert valid input");	
		}

		else
		{
			try
			{
		  		calculator = new Calculator(args[0],args[1],args[2]);
				System.out.println(calculator.calculate());
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please insert valid input");
			}
		}
	}
}