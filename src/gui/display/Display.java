package gui.display;

import gui.FragileWindow;
import gui.MenuListener;
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilities.Calculations;
import utilities.Fractions;

/**
 * Creates and maintains the display in the calculator.
 */
public class Display extends JPanel {

  private static final long serialVersionUID = 1L;
  private TypesettingStyles style;
  private FocusLocation loc = FocusLocation.WHOLE;
  private boolean currIsNegative = false;
  private boolean otherIsNegative = false;
  private boolean exponentMode = false;
  private String currOperation = "";
  private JPanel lowerPanel = new JPanel();
  private JPanel upperPanel = new JPanel();
  private FractionDisplay lowerOperand;
  private FractionDisplay upperOperand;
  private JPanel exponentPanel = new JPanel();
  private JPanel signPanel = new JPanel();
  private JLabel exponent = new JLabel(" ");
  private JPanel history = new JPanel();
  private static int gridY = 0;
  private GridBagLayout grid = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();
  private boolean equationCompleted = false;
  private JFrame dialogFrame = new JFrame();
  private FragileWindow fw;
  static final ResourceBundle STRINGS = ResourceBundle.getBundle("gui.Strings");

  /**
   * Creates a new Display to be added to the calculator.
   *
   * @param style The fraction display style to use for the display.
   * @param thousandsSeparators True if separators are on, false otherwise.
   */
  public Display(TypesettingStyles style, boolean thousandsSeparators, FragileWindow fw) {
    super();
    this.fw = fw;
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
    history.setLayout(this.grid);
    clear(upperPanel);
    clear(lowerPanel);
    this.currOperation = "";
    upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    if (style == TypesettingStyles.BAR) {
      this.upperOperand = new BarFractionDisplay();
    } else if (style == TypesettingStyles.SLASH) {
      this.upperOperand = new SlashFractionDisplay();
    } else if (style == TypesettingStyles.SOLIDUS) {
      this.upperOperand = new SolidusFractionDisplay();
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
    setEmptyLowerOperandDisplay(this.style);
    lowerPanel.add(signPanel);
    lowerPanel.add((Component) this.lowerOperand);
    lowerPanel.add(exponentPanel);
    add(upperPanel);
    add(lowerPanel);
    setBorder(BorderFactory.createLineBorder(Color.black));
    updateDisplay();
  }
  
  /**
   * Reads what button the user pressed, and updates the display accordingly.
   *
   * @param button The name of the button the user pressed.
   */
  public void manageButtons(String button) {
    try {
      Integer.parseInt(button);
      manageNumberButtons(button);
    } catch (NumberFormatException e) {
      if (button == "+" || button == "x" || button == "\u00F7" || button == "-"
          || button == "\u21F9" || button == ">" || button == "<" || button == "≝") {
        manageBinaryOperationButtons(button);
      } else if (button == "Pos") {
        updateLoc(this.loc);
      } else if (button == "\u2190") {
        backspace();
      } else if (button == "=" || button == "ENTER") {
        if (!this.currOperation.equals("")) {
          calculate();
          this.exponentMode = false;
        }
      } else if (button == "\u00B1") {
        switchSign();
      } else if (button == "R") {
        this.exponentMode = false;
        setup();
      } else if (button == "C") {
        setEmptyLowerOperandDisplay(this.style);
        clear(lowerPanel);
        lowerPanel.add((Component) this.lowerOperand);
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
    if (this.equationCompleted) {
      setup();
      this.equationCompleted = false;
    }
    if (this.exponentMode) {
      this.exponent.setText(this.exponent.getText().replace(" ", "") + button);
    } else {
      this.lowerOperand.addDigit(button);
    }
  }

  private void manageBinaryOperationButtons(String button) {
    if (!this.equationCompleted) {
      clear(upperPanel);
      if (currIsNegative) {
        upperPanel.add(new JLabel("-"));
        otherIsNegative = true;
        clear(this.history);
        this.history.add(new JLabel("-"));
      }
      if (this.currOperation.equals("")) {
        if (!this.lowerOperand.isComplete()) {
          JOptionPane.showMessageDialog(dialogFrame, "Please enter a valid fraction.", "Error",
              JOptionPane.ERROR_MESSAGE);
          return;
        }
        setUpperOperandDisplay(this.style, this.lowerOperand.getWhole(),
            this.lowerOperand.getNumerator(), this.lowerOperand.getDenominator());
        upperPanel.add(this.upperOperand);
      } else {
        upperPanel.add(this.upperOperand);
      }
    }
    this.equationCompleted = false;
    upperPanel.add(new JLabel(button));
    this.currOperation = button;
    clear(lowerPanel);
    setEmptyLowerOperandDisplay(this.style);
    clear(signPanel);
    signPanel.add(new JLabel());
    this.currIsNegative = false;
    lowerPanel.add(signPanel);
    lowerPanel.add((Component) this.lowerOperand);
  }

  private void updateLoc(FocusLocation loc) {
    this.lowerOperand.updateLoc(loc);
  }

  private void backspace() {
    if (this.exponentMode) {
      if (!this.exponent.getText().equals(" "))  {
        this.exponent
            .setText(this.exponent.getText().substring(0, this.exponent.getText().length() - 1));
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
    if (!this.lowerOperand.isComplete()) {
      JOptionPane.showMessageDialog(dialogFrame, STRINGS.getString("COMPLETE"),
    		  STRINGS.getString("ERROR"),
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    Fractions operand1 = null;
    Fractions result = null;
    this.gbc.gridy = gridY;
    this.history.add(new SlashFractionDisplay(this.upperOperand.getWhole().replace(" ", ""),
        this.upperOperand.getNumerator().replace(" ", ""),
        this.upperOperand.getDenominator().replace(" ", ""), null), gbc);
    this.history.add(new JLabel(this.currOperation), gbc);
    this.history.add(new SlashFractionDisplay(this.lowerOperand.getWhole().replace(" ", ""),
        this.lowerOperand.getNumerator().replace(" ", ""),
        this.lowerOperand.getDenominator().replace(" ", ""), null), gbc);
    if (!this.exponentMode) {
      operand1 = this.upperOperand.getFraction();
      if (otherIsNegative) {
        operand1.setSign();
      }
    }
    Fractions operand2 = this.lowerOperand.getFraction();
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
    } else if (this.currOperation == ">" || this.currOperation == "<" 
        || this.currOperation == "≝") {
      if (this.currOperation == ">") {
        JOptionPane.showMessageDialog(this,
            operand1.toString() + " > " + operand2.toString() + " is "
                + Calculations.greater(operand1, operand2),
            "Greater Than", JOptionPane.INFORMATION_MESSAGE);
      } else if (this.currOperation == "<") {
        JOptionPane.showMessageDialog(this,
            operand1.toString() + " < " + operand2.toString() + " is "
                + Calculations.less(operand1, operand2),
            "Less Than", JOptionPane.INFORMATION_MESSAGE);
      } else if (this.currOperation == "≝") {
        JOptionPane.showMessageDialog(this,
            operand1.toString() + " = " + operand2.toString() + " is "
                + Calculations.equal(operand1, operand2),
            "Equal To", JOptionPane.INFORMATION_MESSAGE);
      }

      clear(upperPanel);
      setEmptyLowerOperandDisplay(this.style);
      clear(lowerPanel);
      lowerPanel.add(this.lowerOperand);
      gridY++;
      this.equationCompleted = true;
      this.currOperation = "";
      return;
    }
    clear(upperPanel);
    if (result.getIsNegative()) {
      upperPanel.add(new JLabel("-"));
    }
    if (MenuListener.reduce) {
      result = Calculations.reduce(result);
    }
    if (MenuListener.proper) {
      result = Calculations.proper(result);
    }
    this.upperOperand = new BarFractionDisplay();
    setUpperOperandDisplay(this.style, result.getWholeNumber().toString(),
        result.getNumerator().toString(), result.getDenominator().toString());
    upperPanel.add(this.upperOperand);
    setEmptyLowerOperandDisplay(this.style);
    clear(lowerPanel);
    lowerPanel.add(this.lowerOperand);

    // This is the stuff for copying over into the calculation history
    this.history.add(new JLabel("= "), gbc);
    if (result.getIsNegative()) {
      this.history.add(new JLabel("-"));
    }
    this.history.add(new SlashFractionDisplay(result.getWholeNumber().toString().replace(" ", ""),
        result.getNumerator().toString().replace(" ", ""),
        result.getDenominator().toString().replace(" ", ""), null), gbc);

    fw.addHistoryEntry(history);
    fw.calcHistoryArea.add(history);
    fw.calcHistoryArea.revalidate();
    fw.calcHistoryArea.repaint();
    // clear(history);
    gridY++;
    this.equationCompleted = true;
    this.currOperation = "";
    this.exponentMode = false;
    this.exponent.setText(" ");
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
    if (whole == " " && numerator == " " && denominator == " ") {
      if (style == TypesettingStyles.BAR) {
        this.lowerOperand = new BarFractionDisplay();
      } else if (style == TypesettingStyles.SLASH) {
        this.lowerOperand = new SlashFractionDisplay();
      } else if (style == TypesettingStyles.SOLIDUS) {
        this.lowerOperand = new SolidusFractionDisplay();
      }
    } else {
      if (style == TypesettingStyles.BAR) {
        this.lowerOperand = new BarFractionDisplay(whole, numerator, denominator, loc);
      } else if (style == TypesettingStyles.SLASH) {
        this.lowerOperand = new SlashFractionDisplay(whole, numerator, denominator, loc);
      } else if (style == TypesettingStyles.SOLIDUS) {
        this.lowerOperand = new SolidusFractionDisplay(whole, numerator, denominator, loc);
      }
    }
  }
  
  /**
   * Changes the style of all fraction displays in the display to the given style.
   *
   * @param style The fraction display style to change to.
   */
  public void changeStyle(TypesettingStyles style) {
    this.style = style;

    setLowerOperandDisplay(style, this.lowerOperand.getWhole(), this.lowerOperand.getNumerator(),
        this.lowerOperand.getDenominator(), this.lowerOperand.getFocusLocation());
    if (this.upperOperand != null) {
      setUpperOperandDisplay(style, this.upperOperand.getWhole(), this.upperOperand.getNumerator(),
          this.upperOperand.getDenominator());
      clear(upperPanel);
      upperPanel.add(this.upperOperand);
    }
    clear(lowerPanel);
    lowerPanel.add(this.lowerOperand);
    updateDisplay();
  }

  public void separatorsOn(boolean on) {
    if(on) {
      this.lowerOperand.addSeparators();
      if(this.upperOperand != null) {
        this.upperOperand.addSeparators();
      }
    } else {
      this.lowerOperand.removeSeparators();
    }
  }

}
