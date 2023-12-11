package utilities;

/**
 * Class for performing calculator operations and calculations.
 */
public class Calculations
{
  /**
   * Adds two fractions together.
   * 
   * @param number1
   *          First Fraction.
   * @param number2
   *          Second Fraction.
   * @return Fractions the addition of the two fractions.
   */
  public static Fractions addition(final Fractions number1, final Fractions number2)
  {
    if (number1 == null || number2 == null)
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
   * 
   * @param number1
   *          The fraction being subtracted from.
   * @param number2
   *          The fraction that is being taken.
   * @return Fractions the sbutraction of the two fractions.
   */
  public static Fractions subtraction(final Fractions number1, final Fractions number2)
  {
    if (number1 == null || number2 == null)
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
   * 
   * @param number1
   *          The first fraction.
   * @param number2
   *          The second fraction.
   * @return Fractions the product of the two fractions.
   */
  public static Fractions multiplication(final Fractions number1, final Fractions number2)
  {
    if (number1 == null || number2 == null)
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
   * 
   * @param number1
   *          The fraction being divided.
   * @param number2
   *          The fraction that is the amount of dividing.
   * @return Fractions the quotient of the two fractions.
   */
  public static Fractions division(final Fractions number1, final Fractions number2)
  {
    Fractions reciprocal;

    if (number1 == null || number2 == null)
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
   * 
   * @param mixedNumber
   *          The fraction to be inverted.
   * @return Fractions that inverted fraction.
   */
  public static Fractions inverse(final Fractions mixedNumber)
  {
    if (mixedNumber == null)
    {
      throw new IllegalArgumentException();
    }

    Fractions improper = Calculations.improper(mixedNumber);
    Integer numerator = improper.getNumerator();
    Integer denominator = improper.getDenominator();

    if (numerator < 0)
    {
      return new Fractions(true, denominator, numerator * -1);
    }
    return new Fractions(false, denominator, numerator);
  }

  /**
   * Finds the mediant of two fractions.
   * 
   * @param number1
   *          The first fraction.
   * @param number2
   *          The second fraction.
   * @return Fractions the medaint of the two fractions.
   */
  public static Fractions mediant(final Fractions number1, final Fractions number2)
  {
    if (number1 == null || number2 == null)
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
   * 
   * @param fraction
   *          The fraction being applied the exponent.
   * @param power
   *          The power of the exponent.
   * @return Fractions the power multiplied fraction.
   */
  public static Fractions power(final Fractions fraction, final Integer power)
  {
    if (fraction == null || power == null)
    {
      throw new IllegalArgumentException();
    }

    Fractions improper = Calculations.improper(fraction);
    Integer originN = improper.getNumerator();
    Integer originD = improper.getDenominator();

    Integer newN = 1;
    Integer newD = 1;
    Boolean isNegative = false;

    for (int i = 0; i < power; i++)
    {
      newN *= originN;
      newD *= originD;
    }

    if (newN < 0)
    {
      isNegative = true;
      newN *= -1;
    }

    return new Fractions(isNegative, newN, newD);
  }

  /**
   * Checks if the first fraction is greater than the second fraction.
   * 
   * @param fraction1
   *          The fraction being checked.
   * @param fraction2
   *          The fraction comparing to.
   * @return Boolean true or false
   */
  public static Boolean greater(final Fractions fraction1, final Fractions fraction2)
  {
    Boolean answer = false;
    Fractions improper1 = Calculations.improper(fraction1);
    Fractions improper2 = Calculations.improper(fraction2);

    if (improper1.getNumerator() / improper1.getDenominator() > improper2.getNumerator()
        / improper2.getDenominator())
    {
      answer = true;
    }

    return answer;
  }

  /**
   * Checks if the first fraction is less than the second fraction.
   * 
   * @param fraction1
   *          The fraction being checked.
   * @param fraction2
   *          The fraction comparing to.
   * @return Boolean true or false
   */
  public static Boolean less(final Fractions fraction1, final Fractions fraction2)
  {
    Boolean answer = false;
    Fractions improper1 = Calculations.improper(fraction1);
    Fractions improper2 = Calculations.improper(fraction2);

    if (improper1.getNumerator() / improper1.getDenominator() < improper2.getNumerator()
        / improper2.getDenominator())
    {
      answer = true;
    }

    return answer;
  }

  /**
   * Checks if the first fraction is equal to the second fraction.
   * 
   * @param fraction1
   *          The fraction being checked.
   * @param fraction2
   *          The fraction comparing to.
   * @return Boolean true or false
   */
  public static Boolean equal(final Fractions fraction1, final Fractions fraction2)
  {
    Boolean answer = false;
    Fractions improper1 = Calculations.improper(fraction1);
    Fractions improper2 = Calculations.improper(fraction2);

    if (improper1.getNumerator() / improper1.getDenominator() == improper2.getNumerator()
        / improper2.getDenominator())
    {
      answer = true;
    }

    return answer;
  }

  /**
   * Turns a mixed fraction into an improper fraction.
   * 
   * @param mixedNumber
   *          the fraction to be converted.
   * @return Fractions the improper form of the fraction.
   */
  public static Fractions improper(final Fractions mixedNumber)
  {
    Integer whole = mixedNumber.getWholeNumber();
    Integer top = mixedNumber.getNumerator();
    Integer bottom = mixedNumber.getDenominator();

    Integer numerator = (whole * bottom) + top;

    if (mixedNumber.getIsNegative())
    {
      numerator *= -1;
    }

    return new Fractions(numerator, bottom);
  }

  /**
   * Reduces the fraction to simplest form.
   * 
   * @param irreduced
   *          The fraction to be reduced.
   * @return Fractions the simplified form of the fraction.
   */
  public static Fractions reduce(final Fractions irreduced)
  {
    int newNum1 = irreduced.getNumerator();
    int newNum2 = irreduced.getDenominator();

    if (newNum1 == 0)
    {
      return new Fractions(false, 0, 0, 1);
    }

    while (newNum1 != newNum2)
    {
      if (newNum1 > newNum2)
      {
        newNum1 -= newNum2;
      }
      else
      {
        newNum2 -= newNum1;
      }
    }

    Boolean isNegative = irreduced.getIsNegative();
    Integer numerator = irreduced.getNumerator() / newNum1;

    Integer denominator = irreduced.getDenominator() / newNum1;

    return new Fractions(isNegative, numerator, denominator);
  }

  /**
   * Places a fraction in proper form.
   * 
   * @param improper
   *          The fraction to be converted.
   * @return Fractions the proper form of the fraction.
   */
  public static Fractions proper(final Fractions improper)
  {
    Integer numerator = improper.getNumerator();
    Integer denominator = improper.getDenominator();
    Integer whole = (numerator - (numerator % denominator)) / denominator;
    boolean isNegative = improper.getIsNegative();

    return new Fractions(isNegative, whole, numerator % denominator, denominator);
  }
}
