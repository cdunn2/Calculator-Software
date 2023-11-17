package gui.display;

import gui.FragileWindow;
import gui.MenuListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilities.Calculations;
import utilities.Fractions;

/**
 * Represents the display of the calculator.
 */
public class Display extends JPanel {

  private TypesettingStyles style;
  private FocusLocation loc = FocusLocation.WHOLE;
  private boolean currIsNegative = false;
  private boolean otherIsNegative = false;
  private boolean exponentMode = false;
  private boolean answerIsNegative = false;
  private String currOperation = "";
  private JPanel lowerPanel = new JPanel();
  private JPanel upperPanel = new JPanel();
  private FractionDisplay lowerOperand;
  private FractionDisplay upperOperand;
  private JPanel exponentPanel = new JPanel();
  private JPanel signPanel = new JPanel();
  private JLabel exponent = new JLabel(" ");
  private HistoryEntry historyEntry = new HistoryEntry();

  /**
   * Constructs an empty display with the given style.
   *
   * @param style The style of the fractions in the display.
   */
  public Display(TypesettingStyles style) {
    super();
    this.style = style;
    setup();
  }


  public TypesettingStyles getStyle() {
    return this.style;
  }

  public void setStyle(TypesettingStyles style) {
    this.style = style;
  }

  private void setup() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    clear(upperPanel);
    clear(lowerPanel);
    upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    exponentPanel.setLayout(new BoxLayout(exponentPanel, BoxLayout.Y_AXIS));
    exponent.setBorder(null);
    clear(exponentPanel);
    exponentPanel.add(exponent);
    exponentPanel.add(new JLabel(" "));
    exponentPanel.add(new JLabel(" "));
    exponentPanel.add(new JLabel(" "));
    exponentPanel.add(new JLabel(" "));
    setEmptyLowerOperandDisplay(this.style);
    lowerPanel.add(signPanel);
    lowerPanel.add(this.lowerOperand);
    lowerPanel.add(exponentPanel);
    add(upperPanel);
    add(lowerPanel);
    setBorder(BorderFactory.createLineBorder(Color.black));

    historyEntry.setLayout(new FlowLayout());
    updateDisplay();
  }

  private void draw(JPanel currOp, JPanel otherOp) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    clear(upperPanel);
    clear(lowerPanel);
    upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    if (otherOp != null) {
      upperPanel.add(otherOp);
    }
    if (this.currOperation.length() > 0) {
      upperPanel.add(new JLabel(this.currOperation));
    }
    lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    exponentPanel.setLayout(new BoxLayout(exponentPanel, BoxLayout.Y_AXIS));
    exponent.setBorder(null);
    clear(exponentPanel);
    exponentPanel.add(exponent);
    exponentPanel.add(new JLabel(" "));
    exponentPanel.add(new JLabel(" "));
    exponentPanel.add(new JLabel(" "));
    exponentPanel.add(new JLabel(" "));
    lowerPanel.add(signPanel);
    lowerPanel.add(currOp);
    lowerPanel.add(exponentPanel);
    add(upperPanel);
    add(lowerPanel);
    setBorder(BorderFactory.createLineBorder(Color.black));
    updateDisplay();
  }

  /**
   * Updates the display according to button presses.
   *
   * @param button The button that was pressed.
   */
  public void manageButtons(String button) {
    try {
      Integer.parseInt(button);
      manageNumberButtons(button);
    } catch (NumberFormatException e) {
      if (button == "+" || button == "x" || button == "\u00F7"
          || button == "-" || button == "\u21F9") {
        this.currOperation = button;
        manageBinaryOperationButtons(button);
      } else if (button == "Pos") {
        updateLoc(this.loc);
      } else if (button == "\u2190") {
        backspace();
      } else if (button == "=") {
        if (!this.currOperation.equals("")) {
          calculate();
          this.exponentMode = false;
        }
      } else if (button == "\u00B1") {
        switchSign();
      } else if (button == "R") {
        this.upperOperand = null;
        this.exponentMode = false;
        setup();
      } else if (button == "C") {
        setEmptyLowerOperandDisplay(this.style);
        clear(lowerPanel);
        lowerPanel.add(this.lowerOperand);
      } else if (button == "x\u207F") {
        clear(upperPanel);
        exponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lowerOperand.setFocusLocation(null);
        this.exponentMode = true;
        this.currOperation = button;
      } else if (button == "Inv") {
        clear(upperPanel);
        Fractions result = Calculations.inverse(lowerOperand.getFraction());
        setUpperOperandDisplay(this.style, result.getWholeNumber().toString(), 
            result.getNumerator().toString(), result.getDenominator().toString());
        upperPanel.add((Component) upperOperand);
      } else if (button == "\u2193") {
        clear(lowerPanel);
        Fractions result = Calculations.reduce(lowerOperand.getFraction());
        setLowerOperandDisplay(this.style, lowerOperand.getWhole(), 
            result.getNumerator().toString(), result.getDenominator().toString(), 
            lowerOperand.getFocusLocation());
        lowerPanel.add(this.lowerOperand);
      }
    }
    updateDisplay();
  }

  private void manageNumberButtons(String button) {
    if (this.exponentMode) {
      this.exponent.setText(this.exponent.getText().replace(" ", "") + button);
    } else {
      this.lowerOperand.addDigit(button);
    }
  }

  private void manageBinaryOperationButtons(String button) {
    clear(upperPanel);
    if (upperOperand != null) {
      upperPanel.add(upperOperand);
    } else {
      setUpperOperandDisplay(this.style, this.lowerOperand.getWhole().replace(" ", ""), 
          this.lowerOperand.getNumerator().replace(" ", ""), 
          this.lowerOperand.getDenominator().replace(" ", ""));

    }
    if (currIsNegative || otherIsNegative) {
      upperPanel.add(new JLabel("-"));
      otherIsNegative = true;
    }
    upperPanel.add(this.upperOperand);
    historyEntry = new HistoryEntry();
    historyEntry.setOp1(this.upperOperand.copy());
    historyEntry.setOperation(new JLabel(button));
    upperPanel.add(new JLabel(button));
    clear(lowerPanel);
    setEmptyLowerOperandDisplay(this.style);
    clear(signPanel);
    signPanel.add(new JLabel());
    this.currIsNegative = false;
    lowerPanel.add(signPanel);
    lowerPanel.add(this.lowerOperand);
  }

  private void updateLoc(FocusLocation loc) {
    this.lowerOperand.updateLoc(loc);
  }

  private void backspace() {
    if (this.exponentMode) {
      if (! this.exponent.getText().equals(" ")) {
        this.exponent.setText(this.exponent.getText().substring(0, 
            this.exponent.getText().length() - 1));
        if (this.exponent.getText().length() == 0) {
          this.exponent.setText(" ");
        }
      }
    }
    this.lowerOperand.removeDigit();
  }


  private void switchSign() {
    if (this.currIsNegative == false) {
      this.signPanel.removeAll();
      this.signPanel.add(new JLabel("-"));
      this.currIsNegative = true;
    } else {
      this.signPanel.removeAll();
      this.currIsNegative = false;
    }
  }

  private void calculate() {
    Fractions operand1 =  null;
    Fractions result = null;
    if (!this.exponentMode) {
      operand1 = this.upperOperand.getFraction();
      if (otherIsNegative) {
        operand1.setSign();
      }
    }
    Fractions operand2 = this.lowerOperand.getFraction();
    historyEntry.setOp2(lowerOperand.copy());
    if (currIsNegative) {
      operand2.setSign();
    }
    if (this.currOperation == "+") {
      result = Calculations.addition(operand1, operand2);
    } else if (this.currOperation == "-") {
      result = Calculations.subtraction(operand1, operand2);
    } else if (this.currOperation == "x") {
      result = Calculations.multiplication(operand1, operand2);
    } else if (this.currOperation == "\u00F7") {
      result = Calculations.division(operand1, operand2);
    } else if (this.currOperation == "\u21F9") {
      result = Calculations.mediant(operand1, operand2);
    } else if (this.currOperation == "x\u207F") {
      result = Calculations.power(operand2, Integer.parseInt(this.exponent.getText()));
    }
    clear(upperPanel);
    if (result.getIsNegative()) {
      upperPanel.add(new JLabel("-"));
      this.answerIsNegative = true;
    } else {
      this.answerIsNegative = false;
    }
    if (MenuListener.reduce) {
      result = Calculations.reduce(result);
    }
    if (MenuListener.proper) {
      result = Calculations.proper(result);
    }
    this.upperOperand = new BarFractionDisplay();
    setUpperOperandDisplay(this.style, result.getWholeNumber().toString().replace(" ", ""), 
         result.getNumerator().toString().replace(" ", ""), 
         result.getDenominator().toString().replace(" ", ""));
    upperPanel.add(this.upperOperand);
    setEmptyLowerOperandDisplay(this.style);
    clear(lowerPanel);
    lowerPanel.add(this.lowerOperand);
    historyEntry.setAnswer(upperOperand.copy());
    this.currOperation = "";
    FragileWindow.calcHistoryArea.add(getHistoryEntry());
  }


  private void updateDisplay() {
    updateLoc(loc);
    updateLoc(loc);
    updateLoc(loc);
  }

  private void clear(JPanel panel) {
    panel.removeAll();
    panel.repaint();
  }

  private void setEmptyLowerOperandDisplay(TypesettingStyles style) {
    if (style == TypesettingStyles.BAR) {
      this.lowerOperand = new BarFractionDisplay();
    } else if (style == TypesettingStyles.SLASH) {
      this.lowerOperand = new SlashFractionDisplay();
    } else if (style == TypesettingStyles.SOLIDUS) {
      this.lowerOperand = new SolidusFractionDisplay();
    }
  }

  private void setUpperOperandDisplay(TypesettingStyles style, String whole, String numerator, 
        String denominator) {
    if (style == TypesettingStyles.BAR) {
      this.upperOperand = new BarFractionDisplay(whole, numerator, denominator, null);
    } else if (style == TypesettingStyles.SLASH) {
      this.upperOperand = new SlashFractionDisplay(whole, numerator, denominator, null);
    } else if (style == TypesettingStyles.SOLIDUS) {
      this.upperOperand = new SolidusFractionDisplay(whole, numerator, denominator, null);
    }
  }

  private void setLowerOperandDisplay(TypesettingStyles style, String whole, String numerator, 
        String denominator, FocusLocation loc) {
    if (style == TypesettingStyles.BAR) {
      this.lowerOperand = new BarFractionDisplay(whole, numerator, denominator, loc);
    } else if (style == TypesettingStyles.SLASH) {
      this.lowerOperand = new SlashFractionDisplay(whole, numerator, denominator, loc);
    } else if (style == TypesettingStyles.SOLIDUS) {
      this.lowerOperand = new SolidusFractionDisplay(whole, numerator, denominator, loc);
    }
  }
  
  /**
   * Changes the style of the fractions in the display.
   *
   * @param style The style to change the fractions to.
   */
  public void changeStyle(TypesettingStyles style) {
    this.style = style;
    setLowerOperandDisplay(style, this.lowerOperand.getWhole(), this.lowerOperand.getNumerator(), 
         this.lowerOperand.getDenominator(), this.lowerOperand.getFocusLocation());
    if (this.upperOperand != null) {
      setUpperOperandDisplay(style, this.upperOperand.getWhole(), 
          this.upperOperand.getNumerator(), this.upperOperand.getDenominator());
      clear(upperPanel);
      upperPanel.add(this.upperOperand);
    }
    clear(lowerPanel);
    lowerPanel.add(this.lowerOperand);
    draw(this.lowerOperand, this.upperOperand);
    updateDisplay();
  }

  /**
   * Returns a copy of the history entry stored by the display.
   *
   * @return JPanel The history entry.
   */
  public JPanel getHistoryEntry() {
    JPanel temp = new JPanel();
    if (this.currIsNegative) {
      temp.add(new JLabel("-"));
    }
    temp.add(historyEntry.getOp1());
    temp.add(historyEntry.getOperation());
    if (this.otherIsNegative) {
      temp.add(new JLabel("-"));
    }
    temp.add(historyEntry.getOp2());
    temp.add(new JLabel("="));
    if (this.answerIsNegative) {
      temp.add(new JLabel("-"));
    }
    temp.add(historyEntry.getAnswer());
    return temp;
  }
}
