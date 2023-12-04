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
  private boolean addGap = true;

  public SlashFractionDisplay() {
	  //update this to not use other constructor.
	  //make this always show borders and slashes, have other constructor remove them if elements are ommitted.
	  //also check for invalid entries (i.e numerator and no denominator / incomplete fractions).
	  //repeat for other styles.
	  this.whole = new JLabel(" ");
	    this.numerator = new JLabel(" ");
	    this.denominator = new JLabel(" ");
	    draw();
    
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
    if(this.numerator.getText().equals(" ") && this.denominator.getText().equals(" ") || this.numerator.getText().equals("") && this.denominator.getText().equals("")) {
    	this.slash.setText("");
    	this.addGap = false;
    }
    draw();
    
  }

  @Override
  public void draw() {
    setLayout(new FlowLayout());
    add(whole);
    //Creates a gap between numerator and whole to make them easier to differentiate.
    if(this.addGap)
    	add(new JLabel("  "));
    add(numerator);
    add(slash);
    add(denominator);
  }

}
