package gui.display;

import java.awt.Color;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.PreferencesListener;
import utilities.Fractions;

/**
 * Abstract class representing a fraction display in the main display.
 */
public abstract class FractionDisplay extends JPanel
{

  private static final long serialVersionUID = 1L;
  private static final String SPACE = " ";
  private static final String FORMAT = "%,d";
  private static final String COMMA = ",";
  protected JLabel whole;
  protected JLabel numerator;
  protected JLabel denominator;
  protected String wholeNum;
  protected String numeratorNum;
  protected String denominatorNum;
  protected FocusLocation loc = FocusLocation.WHOLE;
  protected Locale locale = Locale.getDefault();
  protected String separator = String.valueOf(String.format(locale, FORMAT, 1000).charAt(1));

  abstract void draw();

  /**
   * Returns the whole number of the fraction.
   *
   * @return String The whole number.
   */
  public String getWhole()
  {
    return this.wholeNum;
  }

  /**
   * Returns the numerator of the fraction.
   *
   * @return String The numerator.
   */
  public String getNumerator()
  {
    return this.numeratorNum;
  }

  /**
   * Returns the denominator of the fraction.
   *
   * @return String The denominator.
   */
  public String getDenominator()
  {
    return this.denominatorNum;
  }

  /**
   * Returns the currently focused location of the fraction.
   *
   * @return String The currently focused location.
   */
  public FocusLocation getFocusLocation()
  {
    return this.loc;
  }

  /**
   * Updates the focus location in the mixed fraction.
   *
   * @param location
   *          The location to move the focus to.
   */
  public void updateLoc(final FocusLocation location)
  {
    if (this.loc == FocusLocation.WHOLE)
    {
      this.whole.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.numerator.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      this.loc = FocusLocation.NUMERATOR;
    }
    else if (this.loc == FocusLocation.NUMERATOR)
    {
      this.numerator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.denominator.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      this.loc = FocusLocation.DENOMINATOR;
    }
    else if (this.loc == FocusLocation.DENOMINATOR)
    {
      this.denominator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.whole.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      this.loc = FocusLocation.WHOLE;
    }
  }

  /**
   * Return the fraction represented by this fraction display.
   *
   * @return Fractions the fraction represented by this display.
   */
  public Fractions getFraction()
  {
    int num;
    int den;
    int wh;
    try
    {
      wh = Integer.parseInt(this.wholeNum);
    }
    catch (NumberFormatException e)
    {
      wh = 0;
    }
    try
    {
      num = Integer.parseInt(this.numeratorNum);
    }
    catch (NumberFormatException e)
    {
      num = 0;
    }
    try
    {
      den = Integer.parseInt(this.denominatorNum);
    }
    catch (NumberFormatException e)
    {
      den = 1;
    }
    return new Fractions(wh, num, den);
  }

  /**
   * Removes the last digit of the currently focused location, or moves the focus backwards if
   * location is empty.
   */
  public void removeDigit()
  {
    if (this.loc == FocusLocation.WHOLE)
    {
      if (this.whole.getText().equals(SPACE))
      {
        updateLoc(this.loc);
      }
      else
      {
        this.wholeNum = (this.wholeNum.substring(0, this.wholeNum.length() - 1));
        if (!this.wholeNum.equals(""))
        {
          this.whole.setText(
              String.format(FORMAT, Integer.parseInt(wholeNum)).replace(COMMA, this.separator));
        }
        if (this.wholeNum.length() == 0)
        {
          this.whole.setText(SPACE);
        }
      }
    }
    else if (this.loc == FocusLocation.NUMERATOR)
    {
      if (this.numerator.getText().equals(SPACE))
      {
        updateLoc(this.loc);
      }
      else
      {
        this.numeratorNum = (this.numeratorNum.substring(0, this.numeratorNum.length() - 1));
        if (!this.numeratorNum.equals(""))
        {
          this.numerator.setText(
              String.format(FORMAT, Integer.parseInt(numeratorNum)).replace(COMMA, this.separator));
        }
        if (this.numeratorNum.length() == 0)
        {
          this.numerator.setText(SPACE);
        }
      }
    }
    else if (this.loc == FocusLocation.DENOMINATOR)
    {
      if (this.denominator.getText().equals(SPACE))
      {
        updateLoc(this.loc);
      }
      else
      {
        this.denominatorNum = (this.denominatorNum.substring(0, this.denominatorNum.length() - 1));
        if (!this.denominatorNum.equals(""))
        {
          this.denominator.setText(String.format(FORMAT, Integer.parseInt(denominatorNum))
              .replace(COMMA, this.separator));
        }
        if (this.denominatorNum.length() == 0)
        {
          this.denominator.setText(SPACE);
        }

      }
    }
  }

  /**
   * Sets the focus location to the specified location.
   *
   * @param location
   *          The new focus location.
   */
  public void setFocusLocation(final FocusLocation location)
  {
    if (location == null)
    {
      this.whole.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.numerator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.denominator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    }
    this.loc = location;
  }

  /**
   * Appends a digit to the currently focused location.
   *
   * @param num
   *          The digit to append.
   */
  public void addDigit(final String num)
  {
    if (PreferencesListener.separators)
    {
      if (this.loc == FocusLocation.WHOLE)
      {
        this.wholeNum = (this.wholeNum.replace(SPACE, "") + num);
        this.whole.setText(
            String.format(FORMAT, Integer.parseInt(wholeNum)).replace(COMMA, this.separator));
      }
      else if (this.loc == FocusLocation.NUMERATOR)
      {
        this.numeratorNum = (this.numeratorNum.replace(SPACE, "") + num);
        this.numerator.setText(
            String.format(FORMAT, Integer.parseInt(numeratorNum)).replace(COMMA, this.separator));
      }
      else if (this.loc == FocusLocation.DENOMINATOR)
      {
        this.denominatorNum = (this.denominatorNum.replace(SPACE, "") + num);
        this.denominator.setText(
            String.format(FORMAT, Integer.parseInt(denominatorNum)).replace(COMMA, this.separator));
      }
    }
    else
    {
      if (this.loc == FocusLocation.WHOLE)
      {
        this.wholeNum = (this.wholeNum.replace(SPACE, "") + num);
        this.whole.setText(
            String.format(FORMAT, Integer.parseInt(wholeNum)).replace(COMMA, this.separator));
      }
      else if (this.loc == FocusLocation.NUMERATOR)
      {
        this.numeratorNum = (this.numeratorNum.replace(SPACE, "") + num);
        this.numerator.setText(
            String.format(FORMAT, Integer.parseInt(numeratorNum)).replace(COMMA, this.separator));
      }
      else if (this.loc == FocusLocation.DENOMINATOR)
      {
        this.denominatorNum = (this.denominatorNum.replace(SPACE, "") + num);
        this.denominator.setText(
            String.format(FORMAT, Integer.parseInt(denominatorNum)).replace(COMMA, this.separator));
      }
    }
  }

  /**
   * Copies this fraction display into a new fraction display.
   *
   * @return SlashFractionDisplay A fraction display with identical fields as this one.
   */
  public SlashFractionDisplay copy()
  {
    return new SlashFractionDisplay(this.whole.getText(), this.numerator.getText(),
        this.denominator.getText(), null);
  }

  /**
   * Check if the currently focus fraction has a valid numerator AND denominator. If one or of the
   * other is missing, return false indicating the fraction is not complete.
   *
   * @return boolean True if the fraction contains a valid numerator and denominator, False
   *         otherwise.
   */
  public boolean isComplete()
  {
    if ((this.numerator.getText().equals(SPACE) && !this.denominator.getText().equals(SPACE))
        || (this.denominator.getText().equals(SPACE) && !this.numerator.getText().equals(SPACE))
        || this.denominator.getText().equals("0"))
    {
      return false;
    }
    return true;
  }

  protected void addSeparators()
  {
    // Catch blocks are empty to avoid adding separators to empty strings
    // If the string cant be converted to an int, the NFE error is thrown and the
    // text will remain the same as before.
    this.separator = String.valueOf(String.format(locale, FORMAT, 1000).charAt(1));
    try
    {
      this.whole.setText(String.format(FORMAT, Integer.parseInt(this.whole.getText()))
          .replace(COMMA, this.separator));
    }
    catch (NumberFormatException e)
    {
    }
    try
    {
      this.numerator.setText(String.format(FORMAT, Integer.parseInt(this.numerator.getText()))
          .replace(COMMA, this.separator));
    }
    catch (NumberFormatException e)
    {
    }
    try
    {
      this.denominator.setText(String.format(FORMAT, Integer.parseInt(this.denominator.getText()))
          .replace(COMMA, this.separator));
    }
    catch (NumberFormatException e)
    {
    }
  }

  protected void removeSeparators()
  {
    this.whole.setText(this.wholeNum);
    this.numerator.setText(this.whole.getText().replace(this.separator, ""));
    this.denominator.setText(this.whole.getText().replace(this.separator, ""));
    this.separator = "";
  }
}
