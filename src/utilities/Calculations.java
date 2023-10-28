package utilities;

public class Calculations 
{
	public static Fractions addition(Fractions number1, Fractions number2)
	{
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);
		
		Fractions newOne = new Fractions(one.getNumerator() * two.getDenominator(),
				one.getDenominator() * two.getDenominator());
		Fractions newTwo = new Fractions(two.getNumerator() * one.getDenominator(),
				two.getDenominator() * one.getDenominator());
		
		Fractions result = new Fractions(newOne.getNumerator() + newTwo.getNumerator(),
				newOne.getDenominator());
		
		return Calculations.simplify(result);
	}
	
	public static Fractions subtraction(Fractions number1, Fractions number2)
	{
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);
		
		Fractions newOne = new Fractions(one.getNumerator() * two.getDenominator(),
				one.getDenominator() * two.getDenominator());
		Fractions newTwo = new Fractions(two.getNumerator() * one.getDenominator(),
				two.getDenominator() * one.getDenominator());
		
		Fractions result = new Fractions(newOne.getNumerator() - newTwo.getNumerator(),
				newOne.getDenominator());
		
		return Calculations.simplify(result);
	}
	
	public static Fractions multiplication(Fractions number1, Fractions number2)
	{
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);
		
		Fractions result = new Fractions(one.getNumerator() * two.getNumerator(),
				one.getDenominator() * two.getDenominator());
		
		return Calculations.simplify(result);
	}
	
	public static Fractions division(Fractions number1, Fractions number2)
	{
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);
		
		Fractions reciprocal = new Fractions(two.getDenominator(), two.getNumerator());
		
		return Calculations.multiplication(one, reciprocal);
	}
	
	public static Fractions improper(Fractions mixedNumber)
	{
		Integer whole = mixedNumber.getWholeNumber();
		Integer top = mixedNumber.getNumerator();
		Integer bottom = mixedNumber.getDenominator();
		
		Integer numerator = (whole * bottom) + top;
		
		return new Fractions(numerator, bottom);
	}
	
	public static Fractions simplify(Fractions improper)
	{
		int newNum1 = improper.getNumerator();
	    int newNum2 = improper.getDenominator();

	    if (newNum1 < 0){
	        newNum1 = newNum1 * -1;
	    }
	    if (newNum2 < 0){
	        newNum2 = newNum2 * -1;
	    }
	    
	    while (newNum1 != newNum2){
	        if (newNum1 > newNum2){
	            newNum1 -= newNum2;
	        } else if (newNum2 > newNum1){
	            newNum2 -= newNum1;
	        }
	    } 
	    
	    Integer numerator = improper.getNumerator() / newNum1;
	    Integer denominator = improper.getDenominator()/ newNum1;
	    Integer whole = (numerator - (numerator % denominator)) / denominator;
	    return new Fractions(whole, numerator % denominator, denominator);
	}
}
