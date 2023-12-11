package gui.display;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.PreferencesDialog;

/**
 * Represents a fraction in the solidus style.
 */
public class SolidusFractionDisplay extends FractionDisplay
{
  private static final String SLASH = "/";
  private static final String SPACE = " ";
  private JLabel slash = new JLabel(SLASH);
  private JPanel numeratorPanel = new JPanel();
  private JPanel denominatorPanel = new JPanel();

  /**
   * Creates a new empty fraction display in the solidus style.
   */
  public SolidusFractionDisplay()
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
  public SolidusFractionDisplay(final String whole, final String numerator,
      final String denominator, final FocusLocation loc)
  {
    this.wholeNum = whole;
    this.whole = new JLabel(wholeNum);
    this.numeratorNum = numerator;
    this.numerator = new JLabel(numeratorNum);
    this.denominatorNum = denominator;
    this.denominator = new JLabel(denominatorNum);
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
      }
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
    numeratorPanel.add(new JLabel(SPACE));

    denominatorPanel.setLayout(new BoxLayout(denominatorPanel, BoxLayout.Y_AXIS));
    denominatorPanel.add(new JLabel(SPACE));
    denominatorPanel.add(denominator);

    setLayout(new FlowLayout());
    // Creates a gap between numerator and whole to make them easier to differentiate.
    add(new JLabel("  "));
    add(numeratorPanel);
    add(slash);
    add(denominatorPanel);
  }
}
