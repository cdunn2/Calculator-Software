package gui.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

public class BarFractionDisplay extends JPanel implements FractionDisplay {

	private Fractions fraction;
	private JLabel whole;
	private JLabel numerator;
	private JLabel denominator;
	private JLabel bar;
	private FocusLocation loc;
	
	public BarFractionDisplay(Fractions fraction, FocusLocation loc) {
		if(fraction == null) {
			this.whole = new JLabel(" ");
			this.numerator = new JLabel(" ");
			this.denominator = new JLabel(" ");
			this.bar = new JLabel(" ");
			
		}
		else {
			this.fraction = fraction;
			this.whole = new JLabel(fraction.getWholeNumber().toString());
			this.numerator = new JLabel(fraction.getNumerator().toString());
			this.denominator = new JLabel(fraction.getDenominator().toString());
			
			int barLength = Math.max(fraction.getNumerator().toString().length(), fraction.getDenominator().toString().length());
			String barString = "";
			for(int i = 0; i < barLength; i++)
				barString += "\u23AF\u23AF";
			this.bar = new JLabel(barString);
		}
		this.loc = loc;
		numerator.setHorizontalAlignment(SwingConstants.CENTER);
		denominator.setHorizontalAlignment(SwingConstants.CENTER);
		whole.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		numerator.setBorder(BorderFactory.createDashedBorder(Color.gray,2, 2));
		denominator.setBorder(BorderFactory.createDashedBorder(Color.gray,2, 2));
		draw();
	}
	
	public BarFractionDisplay(int i) {
		this.whole = new JLabel(" ");
		this.numerator = new JLabel(" ");
		this.denominator = new JLabel(" ");
		this.bar = new JLabel(" ");
		draw();
	}
	
	

	public void draw() {
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
	
	public void addDigit(String num) {
		if(this.loc == FocusLocation.WHOLE)
			this.whole.setText(this.whole.getText() + num);
		else if(this.loc == FocusLocation.NUMERATOR)
			this.numerator.setText(this.numerator.getText() + num);
	}

}
