package gui.display;

import java.awt.FlowLayout;
import javax.swing.JLabel;

import gui.PreferencesDialog;

/**
 * Represents a fraction displayed in the slash style.
 */
public class SlashFractionDisplay extends FractionDisplay
{
  private static final String SPACE = " ";
  private static final String SLASH = "/";
  private JLabel slash = new JLabel(SLASH);
  private boolean addGap = true;

  /**
   * Creates an empty fraction display in the slash style.
   */
  public SlashFractionDisplay()
  {
    this.wholeNum = SPACE;
    this.whole = new JLabel(wholeNum);
    this.numeratorNum = SPACE;
    this.numerator = new JLabel(numeratorNum);
    this.denominatorNum = SPACE;
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
  public SlashFractionDisplay(final String whole, final String numerator, final String denominator,
      final FocusLocation loc)
  {
    this.wholeNum = whole;
    this.whole = new JLabel(wholeNum);
    this.numeratorNum = numerator;
    this.numerator = new JLabel(numeratorNum);
    this.denominatorNum = denominator;
    this.denominator = new JLabel(denominatorNum);
    this.loc = loc;
    if (PreferencesDialog.getSeparatorsCheckbox().isSelected())
    {
      super.addSeparators();
    }
    else if (!this.whole.getText().equals(SPACE))
    {
      this.slash.setText(SLASH);
    }
    else
    {
      if (this.numerator.getText().equals(SPACE) && this.denominator.getText().equals(SPACE)
          || this.numerator.getText().equals("") && this.denominator.getText().equals(""))
      {
        this.slash.setText("");
        this.addGap = false;
      }
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
    {
      add(new JLabel("  "));
    }
    add(numerator);
    add(slash);
    add(denominator);
  }

}
