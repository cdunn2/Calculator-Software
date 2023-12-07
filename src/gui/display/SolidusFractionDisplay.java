package gui.display;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

/**
 * Represents a fraction in the solidus style.
 */
public class SolidusFractionDisplay extends FractionDisplay
{

  private JLabel slash = new JLabel("/");
  private JPanel numeratorPanel = new JPanel();
  private JPanel denominatorPanel = new JPanel();

  public SolidusFractionDisplay()
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
   * Constructs a new fraction in the solidus style.
   *
   * @param whole
   *          The whole number of the fraction.
   * @param numerator
   *          The numerator of the fraction.
   * @param denominator
   *          The denominator of the fraction.
   * @param loc
   *          The location of the focus indicator.
   */
  public SolidusFractionDisplay(String whole, String numerator, String denominator,
      FocusLocation loc)
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
    }
    draw();
  }

  @Override
  public void draw()
  {
    numerator.setHorizontalAlignment(SwingConstants.CENTER);
    denominator.setHorizontalAlignment(SwingConstants.CENTER);

    setLayout(new FlowLayout());
    if (!this.whole.getText().equals("0"))
    {
      add(whole);
    }
    numeratorPanel.setLayout(new BoxLayout(numeratorPanel, BoxLayout.Y_AXIS));

    numeratorPanel.add(numerator);
    numeratorPanel.add(new JLabel(" "));

    denominatorPanel.setLayout(new BoxLayout(denominatorPanel, BoxLayout.Y_AXIS));
    denominatorPanel.add(new JLabel(" "));
    denominatorPanel.add(denominator);

    setLayout(new FlowLayout());
    // Creates a gap between numerator and whole to make them easier to differentiate.
    add(new JLabel("  "));
    add(numeratorPanel);
    add(slash);
    add(denominatorPanel);
    System.out.println();
  }
}
