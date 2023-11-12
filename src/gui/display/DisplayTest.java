package gui.display;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import utilities.Fractions;

public class DisplayTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		Fractions frac = new Fractions(100, 2, 3);
		FractionDisplay solidus = new SolidusFractionDisplay(frac);
		frame.add((JPanel) solidus);
		FractionDisplay bar = new BarFractionDisplay(frac);
		frame.add((JPanel) bar);
		frame.add(new JLabel("20"));
		frame.add(new JLabel("<html><sup>100</sup></html>"));
		frame.add(new JLabel("<html>\u29F8</html>"));
		frame.add(new JLabel("<html><sub>100</sub></html>"));
		frame.setVisible(true);
		frame.setSize(300, 300);

	}

}
