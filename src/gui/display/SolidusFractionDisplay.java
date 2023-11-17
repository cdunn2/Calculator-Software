package gui.display;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

public class SolidusFractionDisplay extends FractionDisplay {
	
	private JLabel slash = new JLabel("/");
	private String currentNumerator;
	private String currentDenominator;
	private JPanel numeratorPanel = new JPanel();
	private JPanel denominatorPanel = new JPanel();
	
	public SolidusFractionDisplay() {
		this(" ", " ", " ", FocusLocation.WHOLE);
	}
	
	public SolidusFractionDisplay(String whole, String numerator, String denominator, FocusLocation loc) {
		this.whole = new JLabel(whole);
		this.numerator = new JLabel(numerator);
		this.denominator = new JLabel(denominator);
		if(numerator == null || numerator.equals(""))
			this.currentNumerator = " ";
		else
			this.currentNumerator = numerator;
		if(denominator == null)
			this.currentDenominator = " ";
		else
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
		numeratorPanel.setLayout(new BoxLayout(numeratorPanel, BoxLayout.Y_AXIS));
		numeratorPanel.add(numerator);
		numeratorPanel.add(new JLabel(" "));
		
		denominatorPanel.setLayout(new BoxLayout(denominatorPanel, BoxLayout.Y_AXIS));
		denominatorPanel.add(new JLabel(" "));
		denominatorPanel.add(denominator);
		
		setLayout(new FlowLayout());
		add(whole);
		//Creates a gap between numerator and whole to make them easier to differentiate.
		add(new JLabel("  "));
		add(numeratorPanel);
		add(slash);
		add(denominatorPanel);
		System.out.println();
	}
}
