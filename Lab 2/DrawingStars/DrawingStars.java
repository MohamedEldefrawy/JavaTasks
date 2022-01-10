public class DrawingStars
{
	private int height;
	private int spaceBetweenCount = 100;


	public DrawingStars()
	{
		this.height = 4;
	}

	public DrawingStars(int height)
	{
		this.height = height;
	}


	public void draw()
	{
		for(int i = 0; i<height; i++)
		{
			for(int j=0; j<i; j++)
			{
				System.out.print("* ");	
							
			}
			
			for(int k=spaceBetweenCount; k>0; k--)
			{
			 	System.out.print(" ");				
			}
					

			for(int l=0; l<i; l++)
			{
				System.out.print("* ");					
			}


			System.out.println("\n");
			spaceBetweenCount -= 3;		
		}	
	}
}