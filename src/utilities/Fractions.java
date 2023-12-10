package utilities;

public class Fractions 
{
	private Boolean isNegative;
	private Integer wholeNumber;
	private Integer numerator;
	private Integer denominator;
	
	/**
	 * Turns a whole number into an improper fraction.
	 * @param whole The whole number.
	 */
	public Fractions(Integer whole)
	{
		this(false, 0, whole * whole, whole);
	}
	
	/**
	 * Turns a whole number into a negative improper fraction.
	 * @param negative determines sign of fraction.
	 * @param whole The whole number.
	 */
	public Fractions(Boolean negative, Integer whole) 
	{
		this(negative, 0, whole * whole, whole);
	}
	
	/**
	 * Creates a fraction with no whole number.
	 * @param numerator The top number of a fraction.
	 * @param denominator The bottom number of a fraction.
	 */
	public Fractions(Integer numerator, Integer denominator)
	{
		this(false, 0, numerator, denominator);
	}
	
	/**
	 * Creates a fraction with a whole number.
	 * @param wholeNumber The whole number.
	 * @param numerator The top number of the fraction.
	 * @param denominator The bottom number of the fraction.
	 */
	public Fractions(Integer wholeNumber, Integer numerator, Integer denominator)
	{
		this(false, wholeNumber, numerator, denominator);
	}
	
	/**
	 * Creates a negative fraction with no whole number
	 * @param negative Determines the sign of the fraction.
	 * @param numerator The top number of a fraction.
	 * @param denominator The bottom number of a fraction.
	 */
	public Fractions(Boolean negative, Integer numerator, Integer denominator)
	{
		this(negative, 0, numerator, denominator);
	}
	
	/**
	 * Creates a negative fraction with a whole number.
	 * @param negative Determines the sign of the fraction.
	 * @param wholeNumber The whole number of a fraction.
	 * @param numerator The top number of a fraction.
	 * @param denominator The bottom number of a fraction.
	 */
	public Fractions(Boolean negative, Integer wholeNumber, Integer numerator, Integer denominator)
	{
		if(negative == null || numerator == null || denominator == null)
		{
			throw new IllegalArgumentException();
		} else if (denominator == 0)
		{
			throw new IllegalArgumentException();
		} else if (numerator < 0 && denominator < 0)
		{
			numerator *= -1;
			denominator *= -1;
		}
		
		this.isNegative = negative;
		this.wholeNumber = wholeNumber;
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	/**
	 * Gets the boolean that determines if a fraction is negative.
	 * @return
	 */
	public Boolean getIsNegative()
	{
		return this.isNegative;
	}
	
	/**
	 * Gets the numerator of a fraction.
	 * @return
	 */
	public Integer getNumerator()
	{
		return this.numerator;
	}
	
	/**
	 * Gets the denominator of a fraction.
	 * @return
	 */
	public Integer getDenominator()
	{
		return this.denominator;
	}
	
	/**
	 * Gets the whole number.
	 * @return
	 */
	public Integer getWholeNumber()
	{
		return this.wholeNumber;
	}
	
	/**
	 * Changes the boolean of the sign to the opposite boolean.
	 * @param fraction The fraction to have its sign changed.
	 */
	public static void changeSign(Fractions fraction) 
	{
		if (fraction == null)
		{
			throw new IllegalArgumentException();
		}
		
		fraction.setSign();
	}
	
	/**
	 * Sets the sign.
	 */
	public void setSign()
	{
		if(this.isNegative == false)
		{
			this.isNegative = true;
		} else
		{
			this.isNegative = false;
		}
	}
	
	/**
	 * Helper method to convert a string to a fraction.
	 * @param s The fraction in string form from the display.
	 * @return Fractions fraction object.
	 */
	public static Fractions parseFractions(String s) {
	  boolean isNegative = false;
	  int numerator = -1;
	  int denominator = -1;
	  int whole = -1;
	  //if fraction is negative
	  if(s.substring(0, 1).equals("-")) {
	    try {
	    whole = Integer.parseInt(s.substring(1, s.indexOf(" ")));
	    } catch(NumberFormatException e) {
	      whole = 0;
	    }
	    isNegative = true;
	  }
	  else {
	    try {
	    whole = Integer.parseInt(s.substring(0, s.indexOf(" ")));
	    } catch(NumberFormatException e) {
	      whole = 0;
	    }
	    isNegative = false;
	  }
	  try {
	  numerator = Integer.parseInt(s.substring(s.indexOf(" ")+1, s.indexOf("/")));
	  }
	  catch(NumberFormatException e) {
	    numerator = 0;
	  }
	  try {
		  denominator = Integer.parseInt(s.substring(s.indexOf("/") + 1));
	  } catch(NumberFormatException e) {
	    denominator = 1;
	  }
	  return new Fractions(isNegative, whole, numerator, denominator);
	}
	
	/**
	 * Converts a fraction object into a string.
	 */
	public String toString() {
	  String str = "";
	  if(this.isNegative)
	    str += "-";
	  str += this.wholeNumber + " " + this.numerator + "/" + this.denominator;
	  return str;
	}
	
	/**
	 * Compares a fraction object to another fraction object.
	 * @param other The other fraction.
	 * @return boolean true if two fractions are equal and false otherwise.
	 */
	public boolean equals(Fractions other) {
	  if(this.isNegative == other.getIsNegative() && this.wholeNumber == other.getWholeNumber() && 
	      this.numerator == other.getNumerator() && this.denominator == other.getDenominator()){
	    return true;
	  }
	  return false;
	}
}
