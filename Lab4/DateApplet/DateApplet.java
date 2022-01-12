import java.awt.*;
import java.applet.*;
import java.util.Date;

public class DateApplet extends Applet implements Runnable
{
	private Date date;
	private Thread thread; 

	public void init()
	{
		date = new Date();
	}

	public void paint(Graphics g) 
	{
		g.drawString(date.toString(),100,100);
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
			date = new Date();
			repaint();
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
