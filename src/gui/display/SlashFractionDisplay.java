package gui.display;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

public class SlashFractionDisplay extends JPanel implements FractionDisplay {

	private Fractions fraction;
	private JLabel whole;
	private JLabel numerator;
	private JLabel denominator;
	private JLabel slash;
	
	public SlashFractionDisplay() {
		this(null);
	}
	
	public SlashFractionDisplay(Fractions fraction) {
		if(fraction == null) {
			this.whole = new JLabel("");
			this.numerator = new JLabel("");
			this.denominator = new JLabel("");
			this.slash = new JLabel("");
		}
		else {
			this.fraction = fraction;
			this.whole = new JLabel(fraction.getWholeNumber().toString());
			this.numerator = new JLabel(fraction.getNumerator().toString());
			this.denominator = new JLabel(fraction.getDenominator().toString());
			
			this.slash = new JLabel("/");
		}
		draw();
	}
	@Override
	public void draw() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		numerator.setHorizontalAlignment(SwingConstants.CENTER);
		denominator.setHorizontalAlignment(SwingConstants.CENTER);
		whole.setBorder(BorderFactory.createDashedBorder(Color.gray,2, 2));
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
