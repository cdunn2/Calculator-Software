package gui.display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * Displays a fraction in the bar style.
 */
public class BarFractionDisplay extends FractionDisplay {


  private JSeparator bar;
  private FocusLocation loc = FocusLocation.WHOLE;
	
  public BarFractionDisplay() {
    this(" ", " ", " ", FocusLocation.WHOLE);
  }
  
  /**
   * Constructs a new display with given fraction parts.
   * Used when changing styles.
   *
   * @param whole The whole number of the mixed fraction.
   * @param numerator The numerator of the mixed fraction.
   * @param denominator The denominator of the mixed fraction.
   * @param loc The current location of the focus.
   */
  public BarFractionDisplay(String whole, String numerator, String denominator, FocusLocation loc) {
    this.whole = new JLabel(whole);
    this.numerator = new JLabel(numerator);
    this.denominator = new JLabel(denominator);
    this.bar = new JSeparator();
    this.loc = loc;
    draw();
  }
	
	
  /**
   * Draws the fraction to the display.
   */
  public void draw() {
    this.numerator.setHorizontalAlignment(SwingConstants.CENTER);
    this.denominator.setHorizontalAlignment(SwingConstants.CENTER);
    this.whole.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    this.numerator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    this.denominator.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    if (this.loc == null) {
      this.whole.setBorder(BorderFactory.createDashedBorder(Color.gray, 2, 2));
    }
    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    setLayout(grid);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(whole, gbc);
    gbc.gridx = 1;
    add(new JLabel("  "), gbc);
    gbc.gridx = 2;
    gbc.gridy = 0;
    add(numerator, gbc);
    gbc.gridy = 1;
    add(bar, gbc);
    gbc.gridy = 2;
    add(denominator, gbc);	
  }
}
