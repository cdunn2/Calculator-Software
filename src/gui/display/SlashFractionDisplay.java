package gui.display;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.Fractions;

public class SlashFractionDisplay extends FractionDisplay {

	private JLabel slash = new JLabel("/");
	private FocusLocation loc = FocusLocation.WHOLE;
	
	public SlashFractionDisplay() {
		this(" ", " ", " ", FocusLocation.WHOLE);
	}
	
	public SlashFractionDisplay(String whole, String numerator, String denominator, FocusLocation loc) {
		this.whole = new JLabel(whole);
		this.numerator = new JLabel(numerator);
		this.denominator = new JLabel(denominator);
		this.loc = loc;
		if(this.loc == null)
			this.whole.setBorder(BorderFactory.createDashedBorder(Color.gray,2, 2));
		draw();
		}
		
	@Override
	public void draw() {
		numerator.setHorizontalAlignment(SwingConstants.CENTER);
		denominator.setHorizontalAlignment(SwingConstants.CENTER);
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
