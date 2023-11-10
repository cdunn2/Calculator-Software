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
		
		Integer numerator = newOne.getNumerator() + newTwo.getNumerator();
		Integer denominator = newOne.getDenominator();
		Boolean isNegative = false;
		
		if (numerator < 0)
	    {
	    	isNegative = true;
	    	numerator *= -1;
	    }
		
		Fractions result = new Fractions(isNegative, numerator, denominator);
		
		return result;
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
		
		Integer numerator = newOne.getNumerator() - newTwo.getNumerator();
		Integer denominator = newOne.getDenominator();
		Boolean isNegative = false;
		
		if (numerator < 0)
	    {
	    	isNegative = true;
	    	numerator *= -1;
	    }
		
		Fractions result = new Fractions(isNegative, numerator, denominator);
		
		return result;
	}
	
	public static Fractions multiplication(Fractions number1, Fractions number2)
	{
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);
		
		Integer numerator = one.getNumerator() * two.getNumerator();
		Integer denominator = one.getDenominator() * two.getDenominator();
		Boolean isNegative = false;
		
		
		
		if (numerator < 0)
	    {
	    	isNegative = true;
	    	numerator *= -1;
	    }
		
		Fractions result = new Fractions(isNegative, numerator, denominator);
		
		return result;
	}
	
	public static Fractions division(Fractions number1, Fractions number2)
	{
		Fractions reciprocal;
		
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);
		
		if (two.getNumerator() < 0)
		{
			reciprocal = new Fractions(two.getDenominator() * -1, two.getNumerator() * -1);
		} else
		{
			reciprocal = new Fractions(two.getDenominator(), two.getNumerator());
		}
		
		return Calculations.multiplication(one, reciprocal);
	}
	
	public static Fractions improper(Fractions mixedNumber)
	{
		Integer whole = mixedNumber.getWholeNumber();
		Integer top = mixedNumber.getNumerator();
		Integer bottom = mixedNumber.getDenominator();
		
		Integer numerator = (whole * bottom) + top;
		
		if (mixedNumber.getIsNegative() == true)
		{
			numerator *= -1;
		}
		
		return new Fractions(numerator, bottom);
	}
	
	public static Fractions reduce(Fractions irreduced)
	{		
		int newNum1 = irreduced.getNumerator();
	    int newNum2 = irreduced.getDenominator();
	    
	    if (newNum1 == 0)
	    {
	    	return new Fractions(false, 0, 0, 1);
	    }
	    
	    while (newNum1 != newNum2){
	        if (newNum1 > newNum2){
	            newNum1 -= newNum2;
	        } else
	        {
	            newNum2 -= newNum1;
	        }
	    } 
	    
	    Boolean isNegative = irreduced.getIsNegative();
	    Integer numerator = irreduced.getNumerator() / newNum1;
	    
	    Integer denominator = irreduced.getDenominator()/ newNum1;	    
	    
	    return new Fractions(isNegative, numerator, denominator);
	}
	
	public static Fractions proper(Fractions improper)
	{		
		Integer numerator = improper.getNumerator();
		Integer denominator = improper.getDenominator();
	    Integer whole = (numerator - (numerator % denominator)) / denominator;
	    boolean isNegative = improper.getIsNegative();
	    
	    return new Fractions(isNegative, whole, numerator % denominator, denominator);
	}
}
