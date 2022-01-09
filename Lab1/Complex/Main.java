public class Main
{
	public static void main (String[] args)
	{
		Complex c1 = new Complex(5,5);
		Complex c2 = new Complex(2,2);

		Complex sum = Complex.add(c1,c2);
		Complex sub = Complex.sub(c1,c2);

		System.out.println("sum = " + sum.getReal() + " + " + sum.getImg() + "j");
		System.out.println("sub = " + sub.getReal() + " - " + sub.getImg() + "j");	
	}
}