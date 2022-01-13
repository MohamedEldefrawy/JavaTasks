import java.awt.*;
import java.applet.*;

public class BallBoundries extends Applet implements Runnable
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
		drawButton();
	}

	public void paint(Graphics g) 
	{
		//draw elipse
		g.setColor(Color.RED);
		g.fillOval(xPos,yPos,radius*2,radius*2);
	}



  private void drawButton() {
        Button btnPlay = new Button("Play");
        Button btnPause = new Button("Pause");

        btnPlay.setBounds(50, 500, 80, 30);
        btnPause.setBounds(100, 500, 80, 30);

        btnPlay.addActionListener(e -> {
            thread.resume();
        });


        btnPause.addActionListener(e -> {
            thread.suspend();
        });

        this.add(btnPlay);
        this.add(btnPause);
    }

	public void run()
	{
		while(true)
		{	

			xPos += xOffset;
			yPos += yOffset;			

			if( (xPos + (2*radius) ) > getWidth() )
			{

				xPos = getWidth() - (2*radius);
				xOffset *= -1;

			}

			if ( (xPos) < 0 )
			{
				xPos = 0;
				xOffset *= -1;

			}

			if ( (yPos) < 0 )
			{
				yPos = 0;
				yOffset *= -1;												
			}

			 if ( yPos+ (2*radius) > getHeight() ) 
			{	

				yPos = getHeight() - 2*radius;
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