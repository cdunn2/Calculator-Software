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
		Display display = new Display(TypesettingStyles.BAR, frac, frac, "+");
		frame.add(display);
		Display display2 = new Display(TypesettingStyles.SLASH, frac, frac, "/");
		frame.add(display2);
		Display display3 = new Display(TypesettingStyles.SOLIDUS, frac, frac, "*");
		frame.add(display3);
		Display display4 = new Display(TypesettingStyles.BAR, null, null, null);
		frame.add(display4);
		frame.setVisible(true);
		frame.setSize(400, 900);

	}

}
