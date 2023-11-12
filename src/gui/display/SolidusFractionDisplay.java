package gui.display;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Fractions;

public class SolidusFractionDisplay extends JPanel implements FractionDisplay {
	
	private Fractions fraction;
	private int whole;
	private int numerator;
	private int denominator;
	private JLabel fractionLabel;
	
	public SolidusFractionDisplay() {
		this(null);
	}
	
	public SolidusFractionDisplay(Fractions fraction) {
		this.fraction = fraction;
		this.whole = fraction.getWholeNumber();
		this.numerator = fraction.getNumerator();
		this.denominator = fraction.getDenominator();
		this.fractionLabel = new JLabel();
		draw();
	}

	@Override
	public void draw() {
		create(whole, numerator, denominator);
		add(fractionLabel);
	}
	
	//temporary, will need to be reworked to implement focus later.
	private void create(int w, int n, int d) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><font size = 4>");
        sb.append(w);
        sb.append("<sup>");
        sb.append(n);
        sb.append("</sup>");
        sb.append("<font size=+1>/<font size=-1>");
        sb.append("<sub><font size = 4>");
        sb.append(d);
        sb.append("</sub>");
        sb.append("</html></body>");
        fractionLabel.setText(sb.toString());
        fractionLabel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }

}
