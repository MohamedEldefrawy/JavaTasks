import java.util.*;
public class Main
{
	public static void main (String[] args)
	{
		System.out.println("Please enter the height of the star");
		Scanner sc = new Scanner(System.in);
		int height = sc.nextInt();
		DrawingStars drawingStars = new DrawingStars(height);
		drawingStars.draw();
	}
}