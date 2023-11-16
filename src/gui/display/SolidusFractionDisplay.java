package gui.display;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

public class SolidusFractionDisplay extends FractionDisplay {
	
	private JLabel slash = new JLabel("/");
	private String currentNumerator;
	private String currentDenominator;
	
	public SolidusFractionDisplay() {
		this(" ", " ", " ", FocusLocation.WHOLE);
	}
	
	public SolidusFractionDisplay(String whole, String numerator, String denominator, FocusLocation loc) {
		this.whole = new JLabel(whole);
		this.numerator = new JLabel("<html><sup>" + numerator + "</sup></html>");
		this.denominator = new JLabel("<html><sub>" + denominator + "</sub></html>");
		this.currentNumerator = numerator;
		this.currentDenominator = denominator;
		draw();
	}

	@Override
	public void draw() {
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
	
	@Override
	public void addDigit(String num) {
		if(this.loc == FocusLocation.WHOLE) {
			this.whole.setText(this.whole.getText().replace(" ", "") + num);
		}
		else if(this.loc == FocusLocation.NUMERATOR) {
			this.numerator.setText("<html><sup>" + currentNumerator + num + "</sup></html>");
			currentNumerator = currentNumerator + num;
		}
		else if(this.loc == FocusLocation.DENOMINATOR) {
			this.denominator.setText("<html><sub>" + currentDenominator + num + "</sub></html>");
			currentDenominator = currentDenominator + num;
		}
	}

}
