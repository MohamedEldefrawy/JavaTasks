import java.applet.*;
import java.awt.*;

public class FontsDisplayUsingGraphics extends Applet
{
	private String[] fonts;

	public void init() {  
		this.fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
  	} 

	public void paint(Graphics g)
	{
		int y = 30;


		for(String s : this.fonts)
		{
			g.setFont(new Font(s,Font.BOLD,16));
			g.drawString(s,30,y);
			y += 20;
		}
		
	}
}