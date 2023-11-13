package gui.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Fractions;

public class Spacer extends JPanel implements FractionDisplay{
	private Fractions fraction;
	private JLabel whole;
	private JLabel numerator;
	private JLabel denominator;
	private JLabel bar;
	
	public Spacer() {
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
}
