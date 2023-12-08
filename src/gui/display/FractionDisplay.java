package gui.display;

import java.awt.Color;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilities.Fractions;

/**
 * Abstract class representing a fraction display in the main display.
 */
public abstract class FractionDisplay extends JPanel {

  private static final long serialVersionUID = 1L;
  protected JLabel whole;
  protected JLabel numerator;
  protected JLabel denominator;
  protected String wholeNum;
  protected String numeratorNum;
  protected String denominatorNum;
  protected FocusLocation loc = FocusLocation.WHOLE;
  protected Locale locale = Locale.getDefault();
  protected String separator = String.valueOf(String.format(locale, "%,d", 1000).charAt(1));

  abstract void draw();

  public String getWhole() {
    return this.wholeNum;
  }

  public String getNumerator() {
    return this.numeratorNum;
  }

  public String getDenominator() {
    return this.denominatorNum;
  }

  public FocusLocation getFocusLocation() {
    return this.loc;
  }
  
  /**
   * Constructs a blank fraction display. Specifies the JLabels and values of the parts
   * of the fraction, then the GUI construction is handled by the individual classes'
   * "draw" functions.
   */
  public FractionDisplay() {
    this.wholeNum = " ";
    this.whole = new JLabel(wholeNum);
    this.numeratorNum = " ";
    this.numerator = new JLabel(numeratorNum);
    this.denominatorNum = " ";
    this.denominator = new JLabel(denominatorNum);
    draw();
  }

  /**
   * Updates the focus location in the mixed fraction.
   *
   * @param loc The location to move the focus to.
   */
  public void updateLoc(FocusLocation loc) {
    if (this.loc == FocusLocation.WHOLE) {
      this.whole.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.numerator.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      this.loc = FocusLocation.NUMERATOR;
    } else if (this.loc == FocusLocation.NUMERATOR) {
      this.numerator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.denominator.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      this.loc = FocusLocation.DENOMINATOR;
    } else if (this.loc == FocusLocation.DENOMINATOR) {
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
  public Fractions getFraction() {
    int numerator;
    int denominator;
    int whole;
    try {
      whole = Integer.parseInt(this.wholeNum);
    } catch (NumberFormatException e) {
      whole = 0;
    }
    try {
      numerator = Integer.parseInt(this.numeratorNum);
    } catch (NumberFormatException e) {
      numerator = 0;
    }
    try {
      denominator = Integer.parseInt(this.denominatorNum);
    } catch (NumberFormatException e) {
      denominator = 1;
    }
    return new Fractions(whole, numerator, denominator);
  }

  /**
   * Removes the last digit of the currently focused location, 
   * or moves the focus backwards if location is empty.
   */
  public void removeDigit() {
    if (this.loc == FocusLocation.WHOLE) {
      if (this.whole.getText().equals(" ")) {
        updateLoc(this.loc);
      } else {
        this.wholeNum = (this.wholeNum.substring(0, this.wholeNum.length() - 1));
        if (!this.wholeNum.equals("")) {
          this.whole.setText(String.format("%,d", Integer.parseInt(wholeNum))
              .replace(",", this.separator));
        }
        if (this.wholeNum.length() == 0) {
          this.whole.setText(" ");
        }
      }
    } else if (this.loc == FocusLocation.NUMERATOR) {
      if (this.numerator.getText().equals(" ")) {
        updateLoc(this.loc);
      } else {
        this.numeratorNum = (this.numeratorNum.substring(0, this.numeratorNum.length() - 1));
        if (!this.numeratorNum.equals("")) {
          this.numerator.setText(String.format("%,d", Integer.parseInt(numeratorNum))
              .replace(",", this.separator));
        }
        if (this.numeratorNum.length() == 0) {
          this.numerator.setText(" ");
        }
      }
    } else if (this.loc == FocusLocation.DENOMINATOR) {
      if (this.denominator.getText().equals(" ")) {
        updateLoc(this.loc);
      } else {
        this.denominatorNum = (this.denominatorNum.substring(0, this.denominatorNum.length() - 1));
        if (!this.denominatorNum.equals("")) {
          this.denominator.setText(String.format("%,d", Integer.parseInt(denominatorNum))
              .replace(",", this.separator));
        }
        if (this.denominatorNum.length() == 0) {
          this.denominator.setText(" ");
        }

      }
    }
  }

  /**
   * Sets the focus location to the specified location.
   *
   * @param loc The new focus location.
   */
  public void setFocusLocation(FocusLocation loc) {
    if (loc == null) {
      this.whole.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.numerator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
      this.denominator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    }
    this.loc = loc;
  }

  /**
   * Appends a digit to the currently focused location.
   *
   * @param num The digit to append.
   */
  public void addDigit(String num) {
    if (this.loc == FocusLocation.WHOLE) {
      this.wholeNum = (this.wholeNum.replace(" ", "") + num);
      this.whole.setText(String.format("%,d", Integer.parseInt(wholeNum))
          .replace(",", this.separator));
    } else if (this.loc == FocusLocation.NUMERATOR) {
      this.numeratorNum = (this.numeratorNum.replace(" ", "") + num);
      this.numerator.setText(String.format("%,d", Integer.parseInt(numeratorNum))
          .replace(",", this.separator));
    } else if (this.loc == FocusLocation.DENOMINATOR) {
      this.denominatorNum = (this.denominatorNum.replace(" ", "") + num);
      this.denominator.setText(String.format("%,d", Integer.parseInt(denominatorNum))
          .replace(",", this.separator));
    }
  }

  public SlashFractionDisplay copy() {
    return new SlashFractionDisplay(this.whole.getText(), 
        this.numerator.getText(), this.denominator.getText(), null);
  }
  
  /**
   * Check if the currently focus fraction has a valid numerator AND denominator.
   * If one or of the other is missing, return false indicating the fraction is not complete.
   *
   * @return boolean True if the fraction contains a valid numerator and denominator,
   *         False otherwise.
   */
  public boolean isComplete() {
    if ((this.numerator.getText().equals(" ") && !this.denominator.getText().equals(" ")) 
        || (this.denominator.getText().equals(" ") && !this.numerator.getText().equals(" "))) {
      return false;
    }
    return true;
  }
  
  /**
   * Update the separator to be the current locale's separator 
   * or the empty string if separators are turned off.
   *
   * @param on True if separators are on, false if they are off.
   */
  public void changeSeparators(boolean on) {
    if (on) {
      this.separator = String.valueOf(String.format(locale, "%,d", 1000).charAt(1));
      
    } else {
      this.separator = "";
    }
  }
  
  protected void addSeparators() {
    //Catch blocks are empty to avoid adding separators to empty strings
    //If the string cant be converted to an int, the NFE error is thrown and the 
    //text will remain the same as before.
    try {
      this.whole.setText(String.format("%,d", Integer.parseInt(this.whole.getText()))
          .replace(",", this.separator));
    } catch (NumberFormatException e) { }
    try {
      this.numerator.setText(String.format("%,d", Integer.parseInt(this.numerator.getText()))
          .replace(",", this.separator));
    } catch (NumberFormatException e) { }
    try {
      this.denominator.setText(String.format("%,d", Integer.parseInt(this.denominator.getText()))
          .replace(",", this.separator));
    } catch (NumberFormatException e) { }
  }
}
