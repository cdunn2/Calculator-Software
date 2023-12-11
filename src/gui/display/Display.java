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
public class Display extends JPanel
{

  public static final ResourceBundle STRINGS = ResourceBundle.getBundle("gui.Strings");
  private static final long serialVersionUID = 1L;
  private static int gridY = 0;
  private static final String SPACE = " ";
  private static final String IS = " is ";
  private static final String PLUS = "+";
  private static final String TIMES = "x";
  private static final String DIVIDE = "\u00F7";
  private static final String MINUS = "-";
  private static final String MEDIANT = "\u21F9";
  private static final String GREATER = ">";
  private static final String LESSER = "<";
  private static final String EQUALTO = "≝";
  private static final String EQUALS = "=";
  private static final String EXPONENT = "x\u207F";
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
  private JLabel exponent = new JLabel(SPACE);
  private JPanel history = new JPanel();

  private GridBagLayout grid = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();
  private boolean equationCompleted = false;
  private JFrame dialogFrame = new JFrame();
  private FragileWindow fw;

  /**
   * Creates a new Display to be added to the calculator.
   *
   * @param style
   *          The fraction display style to use for the display.
   * @param thousandsSeparators
   *          True if separators are on, false otherwise.
   * @param fw
   *          The window this display is in.
   */
  public Display(final TypesettingStyles style, final boolean thousandsSeparators,
      final FragileWindow fw)
  {
    super();
    this.fw = fw;
    this.style = style;
    setup();
  }

  /**
   * Returns the style of this display.
   *
   * @return TypesettingStyles The style of the display.
   */
  public TypesettingStyles getStyle()
  {
    return this.style;
  }

  /**
   * Sets the style of the display.
   *
   * @param style
   *          The style
   */
  public void setStyle(final TypesettingStyles style)
  {
    this.style = style;
  }

  private void setup()
  {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    history.setLayout(this.grid);
    clear(upperPanel);
    clear(lowerPanel);
    this.currOperation = "";
    upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    if (style == TypesettingStyles.BAR)
    {
      this.upperOperand = new BarFractionDisplay();
    }
    else if (style == TypesettingStyles.SLASH)
    {
      this.upperOperand = new SlashFractionDisplay();
    }
    else if (style == TypesettingStyles.SOLIDUS)
    {
      this.upperOperand = new SolidusFractionDisplay();
    }
    lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    exponentPanel.setLayout(new BoxLayout(exponentPanel, BoxLayout.Y_AXIS));
    exponent.setBorder(null);
    exponent.setText(SPACE);
    clear(exponentPanel);
    exponentPanel.add(exponent);
    exponentPanel.add(new JLabel(SPACE));
    exponentPanel.add(new JLabel(SPACE));
    exponentPanel.add(new JLabel(SPACE));
    exponentPanel.add(new JLabel(SPACE));
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
   * @param button
   *          The name of the button the user pressed.
   */
  public void manageButtons(final String button)
  {
    try
    {
      Integer.parseInt(button);
      manageNumberButtons(button);
    }
    catch (NumberFormatException e)
    {
      if (button.equals(PLUS) || button.equals(TIMES) || button.equals(DIVIDE)
          || button.equals(MINUS) || button.equals(MEDIANT) || button.equals(GREATER)
          || button.equals(LESSER) || button.equals(EQUALTO))
      {
        manageBinaryOperationButtons(button);
      }
      else if (button.equals("Pos"))
      {
        updateLoc(this.loc);
      }
      else if (button.equals("\u2190"))
      {
        backspace();
      }
      else if (button.equals(EQUALS) || button.equals("ENTER"))
      {
        if (!this.currOperation.equals(""))
        {
          calculate();
          this.exponentMode = false;
        }
      }
      else if (button.equals("\u00B1"))
      {
        switchSign();
      }
      else if (button.equals("R"))
      {
        this.exponentMode = false;
        setup();
      }
      else if (button.equals("C"))
      {
        exponentPanel.setLayout(new BoxLayout(exponentPanel, BoxLayout.Y_AXIS));
        exponent.setBorder(null);
        exponent.setText(SPACE);
        clear(exponentPanel);
        exponentPanel.add(exponent);
        exponentPanel.add(new JLabel(SPACE));
        exponentPanel.add(new JLabel(SPACE));
        exponentPanel.add(new JLabel(SPACE));
        exponentPanel.add(new JLabel(SPACE));
        this.exponentMode = false;
        this.lowerOperand.setFocusLocation(FocusLocation.WHOLE);
        setEmptyLowerOperandDisplay(this.style);
        clear(lowerPanel);
        lowerPanel.add(signPanel);
        lowerPanel.add((Component) this.lowerOperand);
        lowerPanel.add(exponentPanel);
      }
      else if (button.equals(EXPONENT))
      {
        clear(upperPanel);
        exponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lowerOperand.setFocusLocation(null);
        this.exponentMode = true;
        // this.equationCompleted = true;
        this.currOperation = button;
      }
      else if (button.equals("Inv"))
      {
        clear(upperPanel);
        Fractions result = Calculations.inverse(lowerOperand.getFraction());
        setUpperOperandDisplay(this.style, result.getWholeNumber().toString(),
            result.getNumerator().toString(), result.getDenominator().toString());
        upperPanel.add((Component) upperOperand);
      }
      else if (button.equals("\u2193"))
      {
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

  private void manageNumberButtons(final String button)
  {
    if (this.equationCompleted)
    {
      setup();
      this.equationCompleted = false;
    }
    if (this.exponentMode)
    {
      this.exponent.setText(this.exponent.getText().replace(SPACE, "") + button);
    }
    else
    {
      this.lowerOperand.addDigit(button);
    }
  }

  private void manageBinaryOperationButtons(final String button)
  {
    if (this.equationCompleted)
    {
      upperPanel.add(new JLabel(button));
      this.currOperation = button;
      this.equationCompleted = false;
      return;
    }
    clear(upperPanel);
    if (currIsNegative)
    {
      upperPanel.add(new JLabel(MINUS));
      otherIsNegative = true;
    }
    if (this.currOperation.equals(""))
    {
      if (!this.lowerOperand.isComplete())
      {
        JOptionPane.showMessageDialog(dialogFrame, "Please enter a valid fraction.", "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
      setUpperOperandDisplay(this.style, this.lowerOperand.getWhole(),
          this.lowerOperand.getNumerator(), this.lowerOperand.getDenominator());
      upperPanel.add(this.upperOperand);
    }
    else
    {
      upperPanel.add(this.upperOperand);
    }
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

  private void updateLoc(final FocusLocation loc1)
  {
    this.lowerOperand.updateLoc(loc1);
  }

  private void backspace()
  {
    if (this.exponentMode)
    {
      if (!this.exponent.getText().equals(SPACE))
      {
        this.exponent
            .setText(this.exponent.getText().substring(0, this.exponent.getText().length() - 1));
        if (this.exponent.getText().length() == 0)
        {
          this.exponent.setText(SPACE);
        }
      }
    }
    this.lowerOperand.removeDigit();
  }

  private void switchSign()
  {
    if (!this.currIsNegative)
    {
      this.signPanel.removeAll();
      this.signPanel.add(new JLabel(MINUS));
      this.currIsNegative = true;
    }
    else
    {
      this.signPanel.removeAll();
      this.currIsNegative = false;
    }
  }

  private void calculate()
  {
    if (!this.lowerOperand.isComplete())
    {
      JOptionPane.showMessageDialog(dialogFrame, STRINGS.getString("COMPLETE"),
          STRINGS.getString("ERROR"), JOptionPane.ERROR_MESSAGE);
      return;
    }
    Fractions operand1 = null;
    Fractions result = null;
    this.gbc.gridy = gridY;
    this.history.add(new SlashFractionDisplay(this.upperOperand.getWhole().replace(SPACE, ""),
        this.upperOperand.getNumerator().replace(SPACE, ""),
        this.upperOperand.getDenominator().replace(SPACE, ""), null), gbc);
    this.history.add(new JLabel(this.currOperation), gbc);
    this.history.add(new SlashFractionDisplay(this.lowerOperand.getWhole().replace(SPACE, ""),
        this.lowerOperand.getNumerator().replace(SPACE, ""),
        this.lowerOperand.getDenominator().replace(SPACE, ""), null), gbc);
    if (!this.exponentMode)
    {
      operand1 = this.upperOperand.getFraction();
      if (otherIsNegative)
      {
        operand1.setSign();
      }
    }
    Fractions operand2 = this.lowerOperand.getFraction();
    if (currIsNegative)
    {
      operand2.setSign();
    }
    if (this.currOperation.equals(PLUS))
    {
      result = Calculations.addition(operand1, operand2);
    }
    else if (this.currOperation.equals(MINUS))
    {
      result = Calculations.subtraction(operand1, operand2);
    }
    else if (this.currOperation.equals(TIMES))
    {
      result = Calculations.multiplication(operand1, operand2);
    }
    else if (this.currOperation.equals(DIVIDE))
    {
      result = Calculations.division(operand1, operand2);
    }
    else if (this.currOperation.equals(MEDIANT))
    {
      result = Calculations.mediant(operand1, operand2);
    }
    else if (this.currOperation.equals(EXPONENT))
    {
      result = Calculations.power(operand2, Integer.parseInt(this.exponent.getText()));
    }
    else if (this.currOperation.equals(GREATER) || this.currOperation.equals(LESSER)
        || this.currOperation.equals(EQUALTO))
    {
      if (this.currOperation.equals(GREATER))
      {
        JOptionPane.showMessageDialog(this,
            operand1.toString() + " > " + operand2.toString() + IS
                + Calculations.greater(operand1, operand2),
            "Greater Than", JOptionPane.INFORMATION_MESSAGE);
      }
      else if (this.currOperation.equals(LESSER))
      {
        JOptionPane.showMessageDialog(this,
            operand1.toString() + " < " + operand2.toString() + IS
                + Calculations.less(operand1, operand2),
            "Less Than", JOptionPane.INFORMATION_MESSAGE);
      }
      else if (this.currOperation.equals(EQUALTO))
      {
        JOptionPane.showMessageDialog(this,
            operand1.toString() + " = " + operand2.toString() + IS
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
    if (result.getIsNegative())
    {
      upperPanel.add(new JLabel(MINUS));
    }
    if (MenuListener.getReduce())
    {
      result = Calculations.reduce(result);
    }
    if (MenuListener.getProper())
    {
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
    this.history.add(new JLabel(EQUALS), gbc);
    this.history.add(new SlashFractionDisplay(result.getWholeNumber().toString().replace(SPACE, ""),
        result.getNumerator().toString().replace(SPACE, ""),
        result.getDenominator().toString().replace(SPACE, ""), null), gbc);

    fw.addHistoryEntry(history);
    fw.getHistoryArea().add(history);
    fw.getHistoryArea().revalidate();
    fw.getHistoryArea().repaint();
    // clear(history);
    gridY++;
    this.equationCompleted = true;
    this.currOperation = "";
    this.exponentMode = false;
    this.exponent.setText(SPACE);
  }

  private void updateDisplay()
  {
    updateLoc(loc);
    updateLoc(loc);
    updateLoc(loc);
  }

  private void clear(final JPanel panel)
  {
    panel.removeAll();
    panel.repaint();
  }

  private void setEmptyLowerOperandDisplay(final TypesettingStyles style1)
  {
    if (style1 == TypesettingStyles.BAR)
    {
      this.lowerOperand = new BarFractionDisplay();
    }
    else if (style1 == TypesettingStyles.SLASH)
    {
      this.lowerOperand = new SlashFractionDisplay();
    }
    else if (style1 == TypesettingStyles.SOLIDUS)
    {
      this.lowerOperand = new SolidusFractionDisplay();
    }
  }

  private void setUpperOperandDisplay(final TypesettingStyles style1, final String whole1,
      final String numerator1, final String denominator1)
  {

    if (style1 == TypesettingStyles.BAR)
    {
      this.upperOperand = new BarFractionDisplay(whole1, numerator1, denominator1, null);
    }
    else if (style1 == TypesettingStyles.SLASH)
    {
      this.upperOperand = new SlashFractionDisplay(whole1, numerator1, denominator1, null);
    }
    else if (style1 == TypesettingStyles.SOLIDUS)
    {
      this.upperOperand = new SolidusFractionDisplay(whole1, numerator1, denominator1, null);
    }
  }

  private void setLowerOperandDisplay(final TypesettingStyles style1, final String whole1,
      final String numerator, final String denominator, final FocusLocation loc1)
  {
    if (whole1 == SPACE && numerator == SPACE && denominator == SPACE)
    {
      if (style1 == TypesettingStyles.BAR)
      {
        this.lowerOperand = new BarFractionDisplay();
      }
      else if (style1 == TypesettingStyles.SLASH)
      {
        this.lowerOperand = new SlashFractionDisplay();
      }
      else if (style1 == TypesettingStyles.SOLIDUS)
      {
        this.lowerOperand = new SolidusFractionDisplay();
      }
    }
    else
    {
      if (style1 == TypesettingStyles.BAR)
      {
        this.lowerOperand = new BarFractionDisplay(whole1, numerator, denominator, loc1);
      }
      else if (style1 == TypesettingStyles.SLASH)
      {
        this.lowerOperand = new SlashFractionDisplay(whole1, numerator, denominator, loc1);
      }
      else if (style1 == TypesettingStyles.SOLIDUS)
      {
        this.lowerOperand = new SolidusFractionDisplay(whole1, numerator, denominator, loc1);
      }
    }
  }

  /**
   * Changes the style of all fraction displays in the display to the given style.
   *
   * @param style1
   *          The fraction display style to change to.
   */
  public void changeStyle(final TypesettingStyles style1)
  {
    this.style = style1;

    setLowerOperandDisplay(style1, this.lowerOperand.getWhole(), this.lowerOperand.getNumerator(),
        this.lowerOperand.getDenominator(), this.lowerOperand.getFocusLocation());
    if (this.upperOperand != null)
    {
      setUpperOperandDisplay(style1, this.upperOperand.getWhole(), this.upperOperand.getNumerator(),
          this.upperOperand.getDenominator());
      clear(upperPanel);
      upperPanel.add(this.upperOperand);
    }
    clear(lowerPanel);
    lowerPanel.add(this.lowerOperand);
    updateDisplay();
  }

  /**
   * Changes whether the thousands separators are on or off in the display.
   *
   * @param on
   *          True if on, false if off.
   */
  public void separatorsOn(final boolean on)
  {
    if (on)
    {
      this.lowerOperand.addSeparators();
      if (this.upperOperand != null)
      {
        this.upperOperand.addSeparators();
      }
    }
    else
    {
      this.lowerOperand.removeSeparators();
    }
  }

  /**
   * Returns the fragile window this display is in.
   *
   * @return FragileWindow The window this display is in.
   */
  public FragileWindow getWindow()
  {
    return this.fw;
  }
}
