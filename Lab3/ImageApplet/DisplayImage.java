import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class DisplayImage extends Applet
{
 
  Image picture;  
  
  public void init() {  
	picture = getImage(getDocumentBase(),"eren.gif");  
  }  
    
  public void paint(Graphics g) {  
    g.drawImage(picture, 0,0,this.getSize().height,this.getSize().width, this);

  }  

}