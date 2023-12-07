package gui.display;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

/**
 * Represents a fraction displayed in the slash style.
 */
public class SlashFractionDisplay extends FractionDisplay
{

  private JLabel slash = new JLabel("/");
  private boolean addGap = true;

  public SlashFractionDisplay()
  {
      
    
    this.wholeNum = " ";
    this.whole = new JLabel(wholeNum);
    this.numeratorNum = " ";
    this.numerator = new JLabel(numeratorNum);
    this.denominatorNum = " ";
    this.denominator = new JLabel(denominatorNum);
    draw();
  }

  /**
   * Creates a display with given values.
   *
   * @param whole
   *          The whole number of the fraction
   * @param numerator
   *          The numerator of the fraction.
   * @param denominator
   *          The denominator of the fraction.
   * @param loc
   *          The location of the focus indicator.
   */
  public SlashFractionDisplay(String whole, String numerator, String denominator, FocusLocation loc)
  {
    this.wholeNum = whole;
    this.whole = new JLabel(wholeNum);
    this.numeratorNum = numerator;
    this.numerator = new JLabel(numeratorNum);
    this.denominatorNum = denominator;
    this.denominator = new JLabel(denominatorNum);
    super.addSeparators();
    this.loc = loc;
    if (this.numerator.getText().equals(" ") && this.denominator.getText().equals(" ")
        || this.numerator.getText().equals("") && this.denominator.getText().equals(""))
    {
      this.slash.setText("");
      this.addGap = false;
    }
    draw();

  }

  @Override
  public void draw()
  {
    setLayout(new FlowLayout());
    add(whole);
    // Creates a gap between numerator and whole to make them easier to differentiate.
    if (this.addGap)
      add(new JLabel("  "));
    add(numerator);
    add(slash);
    add(denominator);
  }

}
