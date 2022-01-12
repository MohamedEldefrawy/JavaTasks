import java.awt.*;
import java.applet.*;

public class BallBoundries extends Applet implements Runnable
{
	private Thread thread;
	private Ball initBall;
	
	public void init()
	{
		thread = new Thread(this);
		thread.start();	
		initBall = new Ball();
	}

	public void paint(Graphics g) 
	{
		//draw elipse
		initBall.paintBall(g);
	}

	public void run()
	{
		while(true)
		{	
			

			initBall.setXPos(initBall.getXPos() + initBall.getXOffset());
			initBall.setYPos(initBall.getYPos() + initBall.getYOffset());

		
					
			if( (initBall.getXPos() + (2*initBall.getRadius()) ) == getWidth() )
			{
				initBall.setXOffset(initBall.getXOffset() * -1);
				initBall.setYOffset(Math.abs(initBall.getYOffset()));	
				
				
			}
			
			if ( (initBall.getXPos()) == 0 )
			{
				initBall.setXOffset(initBall.getXOffset() * -1);
				initBall.setYOffset(Math.abs(initBall.getYOffset()));
									
			}
			
			if ( (initBall.getYPos()) == 0 )
			{
				initBall.setYOffset(initBall.getYOffset() * -1);												
			}
			
			 if ( initBall.getYPos() + (2*initBall.getRadius() ) >= getHeight() ) 
			{	
				
				initBall.setYOffset(initBall.getYOffset() * -1);
				
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
