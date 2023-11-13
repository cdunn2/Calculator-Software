package gui.display;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Fractions;

public class Display extends JPanel{
	
	private TypesettingStyles style;
	private Fractions currentOperand;
	private Fractions op1;
	private Fractions op2;
	private String operation;
	private JPanel[][] panelLayout = new JPanel[2][4];
	
	public Display(TypesettingStyles style, Fractions currentOperand, Fractions op1, Fractions op2, String operation) {
		super();
		this.style = style;
		this.currentOperand = currentOperand;
		this.op1 = op1;
		this.op2 = op2;
		this.operation = operation;
		draw();
	}
	
	public Fractions getCurrentOperand() {
		return this.currentOperand;
	}
	
	public Fractions getOp1() {
		return this.currentOperand;
	}
	
	public Fractions getOp2() {
		return this.op2;
	}
	
	public String getOperation() {
		return this.operation;
	}
	
	public TypesettingStyles getStyle() {
		return this.style;
	}
	
	private void draw() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridLayout(2, 4));
		for(int r = 0; r < panelLayout.length; r++) {
			for(int c = 0; c < panelLayout[r].length; c++ ) {
				panelLayout[r][c] = new Spacer();
				add(panelLayout[r][c]);
			}
		}
		if(this.style == TypesettingStyles.BAR) {
			panelLayout[0][0].add(new BarFractionDisplay(op1));
			JPanel operationPanel = new JPanel();
			operationPanel.add(new JLabel(operation));
			panelLayout[0][1].add(operationPanel);
			panelLayout[0][2].add(new BarFractionDisplay(op2));
			panelLayout[1][3].add(new BarFractionDisplay(currentOperand));
			}
		else if (this.style == TypesettingStyles.SLASH) {
			panelLayout[0][0].add(new SlashFractionDisplay(op1));
			JPanel operationPanel = new JPanel();
			operationPanel.add(new JLabel(operation));
			panelLayout[0][1].add(operationPanel);
			panelLayout[0][2].add(new SlashFractionDisplay(op2));
			panelLayout[1][3].add(new SlashFractionDisplay(currentOperand));
		}
			
		
		
	}
	
}
