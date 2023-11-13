package gui.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import utilities.Fractions;

public class DisplayTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		Fractions frac = new Fractions(100, 2, 3);
		/*
		FractionDisplay solidus = new SolidusFractionDisplay(frac);
		frame.add((JPanel) solidus);
		FractionDisplay bar = new BarFractionDisplay(frac);
		frame.add((JPanel) bar);
		frame.add(new JLabel("20"));
		JLabel numerator = new JLabel("<html><sup>100</sup></html>");
		numerator.setVerticalAlignment(SwingConstants.TOP);
		numerator.setBorder(BorderFactory.createLineBorder(Color.black,2));
		
		frame.add(numerator);
		frame.add(new JLabel("<html>\u29F8</html>"));
		frame.add(new JLabel("<html><sub>100</sub></html>"));
		FractionDisplay slash = new SlashFractionDisplay(frac);
		frame.add((JPanel) slash);
		FractionDisplay bar2 = new BarFractionDisplay(1);
		frame.add((JPanel)bar2);
		*/
		Display display = new Display(TypesettingStyles.BAR, frac, frac, frac, "+");
		frame.add(display);
		Display display2 = new Display(TypesettingStyles.SLASH, frac, frac, frac, "/");
		frame.add(display2);
		frame.setVisible(true);
		frame.setSize(400, 900);

	}

}
