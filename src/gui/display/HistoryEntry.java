package gui.display;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class representing an entry to the calculator history.
 */
public class HistoryEntry extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel op1;
    private JLabel operation;
    private JPanel op2;
    private JPanel answer;

    private HistoryEntry(JPanel op1, JLabel operation, JPanel op2, JPanel answer) {
        this.op1 = op1;
        this.operation = operation;
        this.op2 = op2;
        this.answer = answer;
    }

    // Removed the Builder stuff because I ended up actually not using it

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
