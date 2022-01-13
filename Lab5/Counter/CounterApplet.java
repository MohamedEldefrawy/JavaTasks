import java.applet.Applet;
import java.awt.*;

public class CounterApplet extends Applet {

       private int counter = 0;

	public void init() {  
		drawUi();
  	} 

	public void paint(Graphics g)
	{
		g.drawString("Counter = " + counter,400,500);
			
	}


    private void drawUi() {
        Button btnDecrease = new Button("Decrease");
        Button btnIncrease = new Button("Increase");

        btnIncrease.setBounds(50, 500, 80, 30);
        btnDecrease.setBounds(100, 500, 80, 30);

        btnIncrease.addActionListener( e -> {
            this.counter++;
            repaint();
        });


        btnDecrease.addActionListener( e -> {
            this.counter--;
            repaint();
        });

        this.add(btnIncrease);
        this.add(btnDecrease);

    }
}
