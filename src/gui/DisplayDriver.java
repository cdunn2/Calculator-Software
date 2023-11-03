package gui;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import utilities.Calculations;
import utilities.Fractions;
/**
 * Creates and manages the display in the mixed fractions calculator.
 *
 * @author Jaxson Hannie, Ben Berry
 * @version 11/2/2023
 */
public class DisplayDriver extends Container
{
	private static final long serialVersionUID = 1L;
	private static final String FOCUS = "[]";
	private static final String FOCUSW = "W";
	private static final String FOCUSN = "N";
	private static final String FOCUSD = "D";
	private static final String MULTIPLY = "x";
	private static final String ADD = "+";
	private static final String SUBTRACT = "-";
	private static final String SLASH = "/";
	private static final String SPACE = " ";
	private static final String DIVIDE = "\\u00F7";
	private static final String EMPTYOPERAND = "[] /";
	private static final String LEFTBRACKET = "[";
	
	private static JLabel top_text;
	private static JLabel bottom_text;
	private static String curFocus = FOCUSW;
	private static Fractions op1;
	private static Fractions op2;
	private static String operator;
	private static Boolean operationComplete = false;
	
	private String toptext = "";
	
	/**
	 * Creates the display.
	 */
	public DisplayDriver() 
	{
		super();
		top_text = new JLabel("");
		bottom_text = new JLabel(EMPTYOPERAND);
		bottom_text.setHorizontalAlignment(SwingConstants.RIGHT);
		top_text.setHorizontalAlignment(SwingConstants.LEFT);
		setLayout(new GridLayout(2,1));
		add(top_text);
		add(bottom_text);
	}

	/**
	 * Manages button presses and updates the display accordingly.
	 * 
	 * @param buttonpressed The value of the button pressed.
	 */
	public static void bottomManager(final String buttonpressed) 
	{
	  //Handles each button press.
		try 
		{
		  //checks if button is a number
			int num = Integer.parseInt(buttonpressed);
			
			//checks if nothing has been entered yet.
			if (bottom_text.getText().equals(EMPTYOPERAND))
			{
				bottom_text.setText(num + EMPTYOPERAND);
			} else 
			{
			  //places number before the "[]" in the string.
			  bottom_text.setText(bottom_text.getText().substring(0, 
			      bottom_text.getText().indexOf(FOCUS)) + buttonpressed 
			      + bottom_text.getText().substring
			      (bottom_text.getText().indexOf(FOCUS)));
			}
			//if an operation was just completed, remove the old answer from the top.
			if(operationComplete) 
			{
        operationComplete = false;
        top_text.setText("");
      }


		}
		catch (NumberFormatException e) 
		{
		//button is not a number.
			if (buttonpressed.equals(MULTIPLY) || buttonpressed.equals(SUBTRACT) 
			    || buttonpressed.equals(ADD)
					|| buttonpressed.equals(DIVIDE) ) //operators
			{
			  operator = buttonpressed;
			  //if a calculation was just completed, 
			  //use the old answer as the first operand and continue.
			  if(operationComplete) 
			  {
			    operationComplete = false;
			    op1 = Fractions.parseFractions(top_text.getText());
			    top_text.setText(top_text.getText() + operator);
			  }
			  else 
			  { 
	        bottom_text.setText(bottom_text.getText().replace(FOCUS, ""));
	        op1 = Fractions.parseFractions(bottom_text.getText());
	        top_text.setText(Fractions.parseFractions(bottom_text.getText()).toString() 
	            + operator);
			  }
			  bottom_text.setText(EMPTYOPERAND);
        curFocus = FOCUSW;
			  
				
			}
			//sign change button.
			else if (buttonpressed.equals("\u00B1")) 
			{
			  //the "SUBTRACTS" here represent the negative sign.
			  //had to made variables to appease the style guide.
			  if(bottom_text.getText().substring(0, 1).equals(SUBTRACT))
			    bottom_text.setText(bottom_text.getText().replace(SUBTRACT, ""));
			  else
			    bottom_text.setText(SUBTRACT + bottom_text.getText());
			}
			//position button
			else if (buttonpressed.equals("Pos")) 
			{
			  //Check where focus currently is and update it accordingly.
			  bottom_text.setText(bottom_text.getText().replace(FOCUS, ""));
			  if(curFocus.equals(FOCUSW)) 
			  {
			    curFocus = FOCUSN;	    
			    bottom_text.setText(bottom_text.getText().substring(0, 
			        bottom_text.getText().indexOf(SLASH)) + FOCUS 
			        + bottom_text.getText().substring(
			            bottom_text.getText().indexOf(SLASH)));
			  }
			  else if(curFocus.equals(FOCUSN)) 
			  {
			    curFocus = FOCUSD;
			    bottom_text.setText(bottom_text.getText() + FOCUS);
			  }
			  else if(curFocus.equals(FOCUSD))
			  {
			    curFocus = FOCUSW;
			    bottom_text.setText(bottom_text.getText().substring(0, 
			        bottom_text.getText().indexOf(SPACE)) + FOCUS 
			        + bottom_text.getText().substring
			        (bottom_text.getText().indexOf(SPACE)));
			  }
			}
			//calculate current problem.
			else if (buttonpressed.equals("=")) 
			{
			  if(operationComplete) 
			  {
			    //Unsure what to do here, this prevents errors being thrown.
			    return;
			  }
			  Fractions ans = null;
			  bottom_text.setText(bottom_text.getText().replace(FOCUS, ""));
			  op2 = Fractions.parseFractions(bottom_text.getText());
			  if(operator == null) 
			  {
			    ans = Fractions.parseFractions(bottom_text.getText());
			  }
			  else if(operator.equals(ADD))
			    ans = Calculations.addition(op1, op2);
			  else if (operator.equals(SUBTRACT))
			    ans = Calculations.subtraction(op1, op2);
			  else if (operator.equals(MULTIPLY))
			    ans = Calculations.multiplication(op1,  op2);
			  else if (operator.equals(DIVIDE))
			    ans = Calculations.division(op1, op2);
			  top_text.setText(ans.toString());
			  operationComplete = true;
			  bottom_text.setText(EMPTYOPERAND);
        curFocus = FOCUSW;
			}
			//backspace.
			else if (buttonpressed.equals("\u2190")) 
			{
			  //check if there is a number in the section currently focused.
			  try 
			  {
			    Integer.parseInt(String.valueOf(bottom_text.getText().charAt
			        (bottom_text.getText().indexOf(LEFTBRACKET)-1)));
			    bottom_text.setText(bottom_text.getText().substring(0, 
			        bottom_text.getText().indexOf(LEFTBRACKET)-1) 
			        + bottom_text.getText().substring
			        (bottom_text.getText().indexOf(LEFTBRACKET)));
			    
			  } catch (StringIndexOutOfBoundsException ex) 
			  {
			    //whole number is empty, so the backspace would run out of bounds. 
			    //Instead, move focus backwards.
			    bottom_text.setText(bottom_text.getText().replace(FOCUS, ""));
			    curFocus = FOCUSD;
			    bottom_text.setText(bottom_text.getText() + FOCUS);   
			  } catch (NumberFormatException ex2) 
			  {
			  //current section (numerator or denominator) is empty, 
			  //move focus backwards to the next section.
			    bottom_text.setText(bottom_text.getText().replace(FOCUS, ""));
          if(curFocus == FOCUSN) 
          {
            curFocus = FOCUSW;
            bottom_text.setText(bottom_text.getText().substring(0, 
                bottom_text.getText().indexOf(SPACE)) + FOCUS 
                + bottom_text.getText().substring(bottom_text.getText().indexOf(SPACE)));
          }
          else if(curFocus == FOCUSD) 
          {
            curFocus = FOCUSN;
            bottom_text.setText(bottom_text.getText().substring(0, 
                bottom_text.getText().indexOf(SLASH)) + FOCUS 
                + bottom_text.getText().substring(bottom_text.getText().indexOf(SLASH)));      
          }
			  }
			  //clear current operand.
			} else if (buttonpressed.equals("C")) 
			{
			  bottom_text.setText(EMPTYOPERAND);
        curFocus = FOCUSW;
        //reset entire display.
			} else if (buttonpressed.equals("R")) 
			{
			  bottom_text.setText(EMPTYOPERAND);
        curFocus = FOCUSW;
        top_text.setText("");
			}
		}
	}
}


