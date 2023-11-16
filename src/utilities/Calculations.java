package utilities;

public class Calculations 
{
	/**
	 * Adds two fractions together.
	 * @param number1 First Fraction.
	 * @param number2 Second Fraction.
	 * @return Fractions the addition of the two fractions.
	 */
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
	
	/**
	 * Subtracts two fractions.
	 * @param number1 The fraction being subtracted from.
	 * @param number2 The fraction that is being taken.
	 * @return Fractions the sbutraction of the two fractions.
	 */
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
	
	/**
	 * Multiplies two fractions together.
	 * @param number1 The first fraction.
	 * @param number2 The second fraction.
	 * @return Fractions the product of the two fractions.
	 */
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
	
	/**
	 * Divides two fractions together.
 	 * @param number1 The fraction being divided.
	 * @param number2 The fraction that is the amount of dividing.
	 * @return Fractions the quotient of the two fractions.
	 */
	public static Fractions division(Fractions number1, Fractions number2)
	{
		Fractions reciprocal;
		
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);
		
		reciprocal = Calculations.inverse(two);
		
		return Calculations.multiplication(one, reciprocal);
	}
	
	/**
	 * Turns a fraction into its multiplicative inverse.
	 * @param mixedNumber The fraction to be inverted.
	 * @return Fractions that inverted fraction.
	 */
	public static Fractions inverse(Fractions mixedNumber) 
	{
		if (mixedNumber == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions improper = Calculations.improper(mixedNumber);
		Integer numerator = improper.getNumerator();
		Integer denominator = improper.getDenominator();
		
		if(numerator < 0)
		{
			return new Fractions(true, denominator, numerator * -1);
		}
		return new Fractions(false, denominator, numerator);
	}
	
	/**
	 * Finds the mediant of two fractions.
	 * @param number1 The first fraction.
	 * @param number2 The second fraction.
	 * @return Fractions the medaint of the two fractions.
	 */
	public static Fractions mediant(Fractions number1, Fractions number2)
	{
		if(number1 == null || number2 == null)
		{
			throw new IllegalArgumentException();
		}
			
		Fractions one = Calculations.improper(number1);
		Fractions two = Calculations.improper(number2);		
		Integer numerator = one.getNumerator() + two.getNumerator();
		Integer denominator = one.getDenominator() + two.getDenominator();
		Boolean isNegative = false;
			
		if (numerator < 0)
		{
			isNegative = true;
			numerator *= -1;
		}
			
		Fractions result = new Fractions(isNegative, numerator, denominator);
			
		return result;
	}
	
	/**
	 * Find the fraction to a certain power.
	 * @param fraction The fraction being applied the exponent.
	 * @param power The power of the exponent.
	 * @return Fractions the power multiplied fraction.
	 */
	public static Fractions power(Fractions fraction, Integer power)
	{
		if (fraction == null || power == null)
		{
			throw new IllegalArgumentException();
		}
		
		Fractions improper = Calculations.improper(fraction);
		Integer origin_N = improper.getNumerator();
		Integer origin_D = improper.getDenominator();
		
		Integer new_N = 1;
		Integer new_D = 1;
		Boolean isNegative = false;
		
		for (int i = 0; i < power; i++)
		{
			new_N *= origin_N;
			new_D *= origin_D;
		}
		
		if(new_N < 0)
		{
			isNegative = true;
			new_N *= -1;
		}
		
		return new Fractions(isNegative, new_N, new_D);
	}

	/**
	 * Turns a mixed fraction into an improper fraction.
	 * @param mixedNumber the fraction to be converted.
	 * @return Fractions the improper form of the fraction.
	 */
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
	
	/**
	 * Reduces the fraction to simplest form.
	 * @param irreduced The fraction to be reduced.
	 * @return Fractions the simplified form of the fraction.
	 */
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
	
	/**
	 * Places a fraction in proper form.
	 * @param improper The fraction to be converted.
	 * @return Fractions the proper form of the fraction.
	 */
	public static Fractions proper(Fractions improper)
	{		
		Integer numerator = improper.getNumerator();
		Integer denominator = improper.getDenominator();
	    Integer whole = (numerator - (numerator % denominator)) / denominator;
	    boolean isNegative = improper.getIsNegative();
	    
	    return new Fractions(isNegative, whole, numerator % denominator, denominator);
	}
}
