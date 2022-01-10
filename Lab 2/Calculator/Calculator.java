public class Calculator
{
	private Double firstOperand;
	private Double secondOperand;
	private Double result;
	private String operator;

	public Calculator(String firstOperand,String operator,String secondOperand)
	{
		this.firstOperand = Double.parseDouble(firstOperand);
		this.secondOperand = Double.parseDouble(secondOperand);
		this.operator = operator;	
	}

	public Double calculate()
	{
	
		switch(operator)
		{
			case "+" : result = this.firstOperand + this.secondOperand;
			break;
			case "-" : result = this.firstOperand - this.secondOperand;
			break;
			case "*" : result =  this.firstOperand * this.secondOperand;
			break;
			case "/" : result =  this.firstOperand / this.secondOperand;
			break;
			case "%" : result =  this.firstOperand % this.secondOperand;
			break;
		}

		return result;
	}
}