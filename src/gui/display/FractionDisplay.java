package gui.display;

import java.awt.Color;
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
  protected FocusLocation loc = FocusLocation.WHOLE;
  protected String thousandsSeparator;


  abstract void draw();

  public String getWhole() {
    return this.whole.getText();
  }

  public String getNumerator() {
    return this.numerator.getText();
  }

  public String getDenominator() {
    return this.denominator.getText();
  }

  public FocusLocation getFocusLocation() {
    return this.loc;
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
      whole = Integer.parseInt(this.whole.getText());
    } catch (NumberFormatException e) {
      whole = 0;
    }
    try {
      numerator = Integer.parseInt(this.numerator.getText());
    } catch (NumberFormatException e) {
      numerator = 0;
    }
    try {
      denominator = Integer.parseInt(this.denominator.getText());
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
        this.whole.setText(this.whole.getText().substring(0, this.whole.getText().length() - 1));
        if (this.whole.getText().length() == 0) {
          this.whole.setText(" ");
        }
      }
    } else if (this.loc == FocusLocation.NUMERATOR) {
      if (this.numerator.getText().equals(" ")) {
        updateLoc(this.loc);
      } else {
        this.numerator.setText(this.numerator.getText().substring(0, 
            this.numerator.getText().length() - 1));
        if (this.numerator.getText().length() == 0) {
          this.numerator.setText(" ");
        }
      }
    } else if (this.loc == FocusLocation.DENOMINATOR) {
      if (this.denominator.getText().equals(" ")) {
        updateLoc(this.loc);
      } else {
        this.denominator.setText(
            this.denominator.getText().substring(0, this.denominator.getText().length() - 1));
        if (this.denominator.getText().length() == 0) {
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
      this.whole.setText(this.whole.getText().replace(" ", "") + num);
    } else if (this.loc == FocusLocation.NUMERATOR) {
      this.numerator.setText(this.numerator.getText().replace(" ", "") + num);
    } else if (this.loc == FocusLocation.DENOMINATOR) {
      this.denominator.setText(this.denominator.getText().replace(" ", "") + num);
    }
  }

  public SlashFractionDisplay copy() {
    return new SlashFractionDisplay(this.whole.getText(), 
        this.numerator.getText(), this.denominator.getText(), null);
  }
  
  public boolean isComplete() {
    if((this.numerator.getText().equals(" ") && !this.denominator.getText().equals(" ")) || 
        (this.denominator.getText().equals(" ") && !this.numerator.getText().equals(" "))) {
      return false;
    }
    return true;
  }

}
