package gui.display;

import java.awt.FlowLayout;
import javax.swing.JLabel;

/**
 * Represents a fraction displayed in the slash style.
 */
public class SlashFractionDisplay extends FractionDisplay {

  private JLabel slash = new JLabel("/");
  private boolean addGap = true;

  /**
   * Creates an empty fraction display in the slash style.
   */
  public SlashFractionDisplay() {
    super();
    
    
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
  public SlashFractionDisplay(String whole, String numerator, String denominator, 
      FocusLocation loc) {
    this.wholeNum = whole;
    this.whole = new JLabel(wholeNum);
    this.numeratorNum = numerator;
    this.numerator = new JLabel(numeratorNum);
    this.denominatorNum = denominator;
    this.denominator = new JLabel(denominatorNum);
    super.addSeparators();
    this.loc = loc;
    if (this.numerator.getText().equals(" ") && this.denominator.getText().equals(" ")
        || this.numerator.getText().equals("") && this.denominator.getText().equals("")) {
      this.slash.setText("");
      this.addGap = false;
    }
    draw();

  }

  @Override
  public void draw() {
    setLayout(new FlowLayout());
    add(whole);
    // Creates a gap between numerator and whole to make them easier to differentiate.
    if (this.addGap) {
      add(new JLabel("  "));
    }
    add(numerator);
    add(slash);
    add(denominator);
  }

}
