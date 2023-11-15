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
	private String operation;
	private JPanel[][] panelLayout = new JPanel[2][4];
	private FocusLocation loc = FocusLocation.WHOLE;
	
	public Display(TypesettingStyles style, Fractions currentOperand, Fractions op1, String operation, FocusLocation loc) {
		this(style, currentOperand, op1, operation);
		this.loc = loc;
		draw();
	}
	
	public Display(TypesettingStyles style, Fractions currentOperand, Fractions op1, String operation) {
		super();
		this.style = style;
		this.currentOperand = currentOperand;
		this.op1 = op1;
		this.operation = operation;
		draw();
	}
	
	public Fractions getCurrentOperand() {
		return this.currentOperand;
	}
	
	public Fractions getOp1() {
		return this.currentOperand;
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
		panelLayout[1][3].add(new ExponentDisplay(""));
		if(this.style == TypesettingStyles.BAR) {
			//panelLayout[0][0].add(new BarFractionDisplay(op1));
			panelLayout[1][2].add(new BarFractionDisplay(currentOperand, loc));
			}
		else if (this.style == TypesettingStyles.SLASH) {
			panelLayout[0][0].add(new SlashFractionDisplay(op1));
			panelLayout[1][2].add(new SlashFractionDisplay(currentOperand));
		}
		else if (this.style == TypesettingStyles.SOLIDUS) {
			panelLayout[0][0].add(new SolidusFractionDisplay(op1));
			panelLayout[1][2].add(new SolidusFractionDisplay(currentOperand));
		}
		JPanel operationPanel = new JPanel();
		operationPanel.add(new JLabel(operation));
		panelLayout[0][1].add(operationPanel);
	}
	
	public void setOp1(Fractions fraction) {
		
	}
	
	public void manageButtons(String buttonpressed) {
		try {
			Integer.parseInt(buttonpressed);
			manageNumberButtons(buttonpressed);
		} catch (NumberFormatException e) {
			
		}
	}
	
	private void manageNumberButtons(String buttonpressed) {
		
		
	}
	
	private void manageOperationButtons(String buttonpressed) {
		
	}
	
}
