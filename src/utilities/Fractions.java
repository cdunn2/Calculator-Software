package utilities;

public class Fractions 
{
	private Integer wholeNumber;
	private Integer numerator;
	private Integer denominator;
	
	public Fractions(Integer numerator, Integer denominator)
	{
		this(0, numerator, denominator);
	}
	
	public Fractions(Integer wholeNumber, Integer numerator, Integer denominator)
	{
		if(numerator == null || denominator == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.wholeNumber = wholeNumber;
		this.numerator = numerator;
		this.denominator = denominator;
		
		setSign();
	}
	
	public Integer getNumerator()
	{
		return this.numerator;
	}
	
	public Integer getDenominator()
	{
		return this.denominator;
	}
	
	public Integer getWholeNumber()
	{
		return this.wholeNumber;
	}
	
	public static Integer changeSign(Integer number) 
	{
		if(number == null)
		{
			throw new IllegalArgumentException();
		}
		return number *= -1;
	}
	
	/**
	 * Sets the Fraction to positive if both numerator and denominator
	 * are negative.
	 */
	public void setSign()
	{
		if(this.numerator < 0 && this.denominator < 0)
		{
			this.numerator *= -1;
			this.denominator *= -1;
		}
	}
	
	public void setDenominator(Integer number)
	{
		this.denominator = number;
	}
	
	public void setNumerator(Integer number)
	{
		this.numerator = number;
	}
	
	public void setWholeNumber(Integer number)
	{
		this.wholeNumber = number;
	}
}
