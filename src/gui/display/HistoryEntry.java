package gui.display;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HistoryEntry extends JPanel{
	
	private JPanel op1;
	private JLabel operation = new JLabel();
	private JPanel op2 = new JPanel();
	private JPanel answer = new JPanel();
	
	public HistoryEntry() {
		super();
	}
	
	public void setOp1(JPanel op1) {
		this.op1 = op1;
	}
	
	public void setOperation(JLabel operation) {
		this.operation = operation;
	}
	
	public void setOp2(JPanel op2) {
		this.op2 = op2;
	}
	
	public void setAnswer(JPanel answer) {
		this.answer = answer;
	}
	
	public JPanel getOp1() {
		return this.op1;
	}
	
	public JLabel getOperation() {
		return this.operation;
	}
	
	public JPanel getOp2() {
		return this.op2;
	}
	
	public JPanel getAnswer() {
		return this.answer;
	}

}
