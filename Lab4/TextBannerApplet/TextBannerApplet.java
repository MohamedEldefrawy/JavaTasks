import java.awt.*;
import java.applet.*;
import java.util.Date;

public class TextBannerApplet extends Applet implements Runnable
{
	private Date date;
	private Thread thread;
	int xPos = 100;
	int offset = 200;
	String text = "Hello World!!!!!";

	public void init()
	{
		date = new Date();
	}

	public void paint(Graphics g) 
	{
		g.drawString(text,xPos,100);
	}

	public void start()
	{
		thread = new Thread(this);
		thread.start();				
	}

	public void run()
	{
		while(true)
		{	
			xPos += offset;
			repaint();
			
			if( (xPos - text.length() ) >= getWidth())
			{
				xPos = 0;
			}
			
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
