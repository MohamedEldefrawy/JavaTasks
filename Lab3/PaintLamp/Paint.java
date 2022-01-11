import java.awt.*;
import java.applet.*;

public class Paint extends Applet
{	
	public void paint(Graphics g) 
	{  
		// draw rect
		g.drawRect(400,485,240,25);
		
		// draw line 
		g.drawLine(490,485,510,390);
		
		// draw line
		g.drawLine(550,485,530,390);

		//draw arc
		g.drawArc(360,270,330,120,0,-180);

		// line
		g.drawLine(400,145,360,330);

		// line
		g.drawLine(645,145,690,330);

		//draw elipse
		g.setColor(Color.YELLOW);
		g.fillOval(400,120,245,50);
		g.setColor(Color.BLACK);
		g.drawOval(400,120,245,50);

		//draw elipse
		g.setColor(Color.YELLOW);
		g.fillOval(385,235,45,65);
		g.setColor(Color.BLACK);
		g.drawOval(385,235,45,65);
		
		
		//draw elipse
		g.setColor(Color.YELLOW);
		g.fillOval(620,235,45,65);
		g.setColor(Color.BLACK);
		g.drawOval(620,235,45,65);

		//draw elipse
		g.setColor(Color.YELLOW);
		g.fillOval(475,195,100,155);
		g.setColor(Color.BLACK);
		g.drawOval(475,195,100,155);

  	}

}