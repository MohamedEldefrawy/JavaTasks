public class Complex
{

	public static void main (String[] args)
	{
		Complex c1 = new Complex(5,5);
		Complex c2 = new Complex(2,2);

		Complex sum = Complex.add(c1,c2);
		Complex sub = Complex.sub(c1,c2);

		System.out.println("sum = " + sum.real + "+ " + sum.img + "j");
		System.out.println("sub = " + sub.real + "+ " + sub.img + "j");	
	}

	private int real;
	private int img;

	public Complex()
	{
		this.real = 0;
		this.img = 0;
	}

	public Complex(int real, int img)
	{
		this.real = real;
		this.img = img;
	}

	public static Complex add(Complex firstComplex, Complex secondComplex)
	{
		Complex result = new Complex();
		result.real = firstComplex.real + secondComplex.real;
		result.img = firstComplex.img + secondComplex.img;

		return result;
	}

	public static Complex sub(Complex firstComplex, Complex secondComplex)
	{
		Complex result = new Complex();
		result.real = firstComplex.real - secondComplex.real;
		result.img = firstComplex.img - secondComplex.img;

		return result;
	}


	public int getReal()
	{
		return this.real;	
	}

	public int getImg()
	{
		return this.img;	
	}
}