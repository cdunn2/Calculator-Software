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
public class SlashFractionDisplay extends FractionDisplay {

  private JLabel slash = new JLabel("/");
  private FocusLocation loc = FocusLocation.WHOLE;

  public SlashFractionDisplay() {
    this(" ", " ", " ", FocusLocation.WHOLE);
  }

  /**
   * Creates a display with given values.
   *
   * @param whole The whole number of the fraction
   * @param numerator The numerator of the fraction.
   * @param denominator The denominator of the fraction.
   * @param loc The location of the focus indicator.
   */
  public SlashFractionDisplay(String whole, String numerator, 
        String denominator, FocusLocation loc) {
    this.whole = new JLabel(whole);
    this.numerator = new JLabel(numerator);
    this.denominator = new JLabel(denominator);
    this.loc = loc;
    if (this.loc == null) {
      this.whole.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    }
    draw();
  }

  @Override
  public void draw() {
    numerator.setHorizontalAlignment(SwingConstants.CENTER);
    denominator.setHorizontalAlignment(SwingConstants.CENTER);

    if (this.numerator.getText().length() > 0) {
      numerator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    }
    if (this.denominator.getText().length() > 0) {
      denominator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    }
    if (this.whole.getText().length() == 0) {
      whole.setBorder(null);
    }
    setLayout(new FlowLayout());
    if (!this.whole.getText().equals("0")) {
      add(whole);
    }
    //Creates a gap between numerator and whole to make them easier to differentiate.
    add(new JLabel("  "));
    add(numerator);
    if (this.numerator.getText().length() > 0 && this.denominator.getText().length() > 0) {
      add(slash);
    }
    add(denominator);


  }

}
