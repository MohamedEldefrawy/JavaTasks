import java.awt.*;
import java.applet.*;

public class BouncingBall extends Applet implements Runnable
{
	private Thread thread;
	int xPos = 50;
	int yPos = 100;
	int xOffset = 10;
	int yOffset = 10;
	int radius = 40;

	public void init()
	{
		thread = new Thread(this);
		thread.start();	
	}

	public void paint(Graphics g) 
	{
		//draw elipse
		g.setColor(Color.RED);
		g.fillOval(xPos,yPos,radius*2,radius*2);
	}

	public void run()
	{
		while(true)
		{	

			xPos += xOffset;
			yPos += yOffset;			

			if( (xPos + (2*radius) ) >= getWidth() )
			{
					
				xOffset *= -1;
				
			}
			
			if ( (xPos) <= 0 )
			{
				xOffset *= -1;
									
			}
			
			if ( (yPos) <= 0 )
			{
				yOffset *= -1;												
			}
			
			 if ( yPos+ (2*radius) >= getHeight() ) 
			{	
				
				yOffset *= -1;
				
			}

			repaint();
			
			
			try
			{
				Thread.sleep(10);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
