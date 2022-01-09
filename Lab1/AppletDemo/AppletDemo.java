import java.applet.Applet;
import java.awt.Graphics;

public class AppletDemo extends Applet
{

	public void paint(Graphics g)
	{
		String param = getParameter("msg");
		g.drawString(param,500,500);
	}
		
}