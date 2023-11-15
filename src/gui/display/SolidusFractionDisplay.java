package gui.display;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

public class SolidusFractionDisplay extends JPanel implements FractionDisplay {
	
	private Fractions fraction;
	private JLabel whole;
	private JLabel numerator;
	private JLabel denominator;
	private JLabel slash;
	
	public SolidusFractionDisplay() {
		this(null);
	}
	
	public SolidusFractionDisplay(Fractions fraction) {
		if(fraction == null) {
			this.whole = new JLabel("");
			this.numerator = new JLabel("");
			this.denominator = new JLabel("");
			this.slash = new JLabel("");
		}
		else {
			this.fraction = fraction;
			this.whole = new JLabel(fraction.getWholeNumber().toString());
			this.numerator = new JLabel("<html><sup>" + fraction.getNumerator().toString() + "</sup></html>");
			this.denominator = new JLabel("<html><sub>" + fraction.getDenominator().toString() + "</sub></html>");
			
			this.slash = new JLabel("/");
		}
		draw();
	}

	@Override
	public void draw() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		numerator.setHorizontalAlignment(SwingConstants.CENTER);
		denominator.setHorizontalAlignment(SwingConstants.CENTER);
		whole.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		numerator.setBorder(BorderFactory.createDashedBorder(Color.gray,2, 2));
		denominator.setBorder(BorderFactory.createDashedBorder(Color.gray,2, 2));
		setLayout(new FlowLayout());
		add(whole);
		//Creates a gap between numerator and whole to make them easier to differentiate.
		add(new JLabel("  "));
		add(numerator);
		add(slash);
		add(denominator);
	}
	

}
