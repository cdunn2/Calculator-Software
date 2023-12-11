package utilities;

/**
 * Class representing mixed fractions.
 */
public class Fractions
{
  
  private static final String SLASH = "/";
  private static final String MINUS = "-";
  private static final String SPACE = " ";
  private Boolean isNegative;
  private Integer wholeNumber;
  private Integer numerator;
  private Integer denominator;

  /**
   * Turns a whole number into an improper fraction.
   * 
   * @param whole
   *          The whole number.
   */
  public Fractions(final Integer whole)
  {
    this(false, 0, whole * whole, whole);
  }

  /**
   * Turns a whole number into a negative improper fraction.
   * 
   * @param negative
   *          determines sign of fraction.
   * @param whole
   *          The whole number.
   */
  public Fractions(final Boolean negative, final Integer whole)
  {
    this(negative, 0, whole * whole, whole);
  }

  /**
   * Creates a fraction with no whole number.
   * 
   * @param numerator
   *          The top number of a fraction.
   * @param denominator
   *          The bottom number of a fraction.
   */
  public Fractions(final Integer numerator, final Integer denominator)
  {
    this(false, 0, numerator, denominator);
  }

  /**
   * Creates a fraction with a whole number.
   * 
   * @param wholeNumber
   *          The whole number.
   * @param numerator
   *          The top number of the fraction.
   * @param denominator
   *          The bottom number of the fraction.
   */
  public Fractions(final Integer wholeNumber, final Integer numerator, final Integer denominator)
  {
    this(false, wholeNumber, numerator, denominator);
  }

  /**
   * Creates a negative fraction with no whole number.
   * 
   * @param negative
   *          Determines the sign of the fraction.
   * @param numerator
   *          The top number of a fraction.
   * @param denominator
   *          The bottom number of a fraction.
   */
  public Fractions(final Boolean negative, final Integer numerator, final Integer denominator)
  {
    this(negative, 0, numerator, denominator);
  }

  /**
   * Creates a negative fraction with a whole number.
   * 
   * @param negative
   *          Determines the sign of the fraction.
   * @param wholeNumber
   *          The whole number of a fraction.
   * @param numerator
   *          The top number of a fraction.
   * @param denominator
   *          The bottom number of a fraction.
   */
  public Fractions(final Boolean negative, final Integer wholeNumber, final Integer numerator,
      final Integer denominator)
  {
    Integer num = numerator;
    Integer den = denominator;
    if (negative == null || num == null || den == null)
    {
      throw new IllegalArgumentException();
    }
    else if (den == 0)
    {
      throw new IllegalArgumentException();
    }
    else if (num < 0 && den < 0)
    {
      num *= -1;
      den *= -1;
    }

    this.isNegative = negative;
    this.wholeNumber = wholeNumber;
    this.numerator = num;
    this.denominator = den;
  }

  /**
   * Gets the boolean that determines if a fraction is negative.
   * 
   * @return Boolean Whether this fraction is negative.
   */
  public Boolean getIsNegative()
  {
    return this.isNegative;
  }

  /**
   * Gets the numerator of a fraction.
   * 
   * @return Integer The numerator.
   */
  public Integer getNumerator()
  {
    return this.numerator;
  }

  /**
   * Gets the denominator of a fraction.
   * 
   * @return Intger The denominator.
   */
  public Integer getDenominator()
  {
    return this.denominator;
  }

  /**
   * Gets the whole number.
   * 
   * @return Integer The whole number.
   */
  public Integer getWholeNumber()
  {
    return this.wholeNumber;
  }

  /**
   * Changes the boolean of the sign to the opposite boolean.
   * 
   * @param fraction
   *          The fraction to have its sign changed.
   */
  public static void changeSign(final Fractions fraction)
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
    if (!this.isNegative)
    {
      this.isNegative = true;
    }
    else
    {
      this.isNegative = false;
    }
  }

  /**
   * Helper method to convert a string to a fraction.
   * 
   * @param s
   *          The fraction in string form from the display.
   * @return Fractions fraction object.
   */
  public static Fractions parseFractions(final String s)
  {
    boolean isNegative = false;
    int numerator = -1;
    int denominator = -1;
    int whole = -1;
    // if fraction is negative
    if (s.substring(0, 1).equals(MINUS))
    {
      try
      {
        whole = Integer.parseInt(s.substring(1, s.indexOf(SPACE)));
      }
      catch (NumberFormatException e)
      {
        whole = 0;
      }
      isNegative = true;
    }
    else
    {
      try
      {
        whole = Integer.parseInt(s.substring(0, s.indexOf(SPACE)));
      }
      catch (NumberFormatException e)
      {
        whole = 0;
      }
      isNegative = false;
    }
    try
    {
      numerator = Integer.parseInt(s.substring(s.indexOf(SPACE) + 1, s.indexOf(SLASH)));
    }
    catch (NumberFormatException e)
    {
      numerator = 0;
    }
    try
    {
      denominator = Integer.parseInt(s.substring(s.indexOf(SLASH) + 1));
    }
    catch (NumberFormatException e)
    {
      denominator = 1;
    }
    return new Fractions(isNegative, whole, numerator, denominator);
  }

  /**
   * Converts a fraction object into a string.
   * 
   * @return String String representation of the fraction.
   */
  public String toString()
  {
    String str = "";
    if (this.isNegative)
      str += MINUS;
    str += this.wholeNumber + SPACE + this.numerator + SLASH + this.denominator;
    return str;
  }

  /**
   * Compares a fraction object to another fraction object.
   * 
   * @param other
   *          The other fraction.
   * @return boolean true if two fractions are equal and false otherwise.
   */
  public boolean equals(Fractions other)
  {
    if (this.isNegative == other.getIsNegative() && this.wholeNumber == other.getWholeNumber()
        && this.numerator == other.getNumerator() && this.denominator == other.getDenominator())
    {
      return true;
    }
    return false;
  }
}
