package gui;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import utilities.Calculations;
import utilities.Fractions;

public class DisplayDriver extends Container
{
	private static final long serialVersionUID = 1L;
	static JLabel top_text;
	static JLabel bottom_text;
	static String botmtext;
	String toptext = "";
	static String curFocus = "W";
	private static final String FOCUS = "[]";
	private static Fractions op1;
	private static Fractions op2;
	private static String operator;
	private static Boolean operationComplete = false;
	
	public DisplayDriver() {
		super();
		top_text = new JLabel(" ");
		bottom_text = new JLabel("[] /");
		botmtext = bottom_text.getText();
		bottom_text.setHorizontalAlignment(SwingConstants.RIGHT);
		top_text.setHorizontalAlignment(SwingConstants.LEFT);
		setLayout(new GridLayout(2,1));
		add(top_text);
		add(bottom_text);
	}
	
	public void topManager(String buttonpressed) {
		//TODO
	}

	public static void bottomManager(String buttonpressed) {
		try {
			int num = Integer.parseInt(buttonpressed);

			if (bottom_text.getText() == "[] /")
			{
				bottom_text.setText(String.format("%d[] /", num));

				botmtext = bottom_text.getText();
			} else {
			  //places number before the "[]" in the string.
			  bottom_text.setText(botmtext.substring(0, botmtext.indexOf("[]")) + buttonpressed + botmtext.substring(botmtext.indexOf("[]")));
				botmtext = bottom_text.getText();
			}
			if(operationComplete) {
        operationComplete = false;
        top_text.setText("");
      }


		}
		catch (NumberFormatException e) {
			if (buttonpressed.equals("x") || buttonpressed.equals("-") || buttonpressed.equals("+")
					|| buttonpressed.equals("\u00F7") )
			{
			  operator = buttonpressed;
			  if(operationComplete) {
			    operationComplete = false;
			    op1 = Fractions.parseFractions(top_text.getText());
			    top_text.setText(top_text.getText() + operator);
			  }
			  else { 
	        botmtext = bottom_text.getText();
	        botmtext = botmtext.replace("[]", "");
	        op1 = Fractions.parseFractions(botmtext);
	        top_text.setText(Fractions.parseFractions(botmtext).toString() + operator);
			  }
			  botmtext = "[] /";
        bottom_text.setText(botmtext);
        curFocus = "W";
			  
				
			}
			else if (buttonpressed.equals("\u00B1")) {
			  if(botmtext.substring(0, 1).equals("-"))
			    botmtext = botmtext.replace("-", "");
			  else
			    botmtext = "-" + botmtext;
			  bottom_text.setText(botmtext);
			}
			else if (buttonpressed.equals("Pos")) {
			  //Check where focus currently is and update it accordingly.
			  botmtext = botmtext.replace("[]", "");
			  if(curFocus == "W") {
			    curFocus = "N";	    
			    botmtext = (botmtext.substring(0, botmtext.indexOf("/")) + "[]" + botmtext.substring(botmtext.indexOf("/")));
			  }
			  else if(curFocus == "N") {
			    curFocus = "D";
          botmtext = botmtext + "[]";
			  }
			  else if(curFocus == "D") {
			    curFocus = "W";
          botmtext = (botmtext.substring(0, botmtext.indexOf(" ")) + "[]" + botmtext.substring(botmtext.indexOf(" ")));
			  }
			  bottom_text.setText(botmtext);
			}
			else if (buttonpressed.equals("=")) {
			  if(operationComplete) {
			    //Unsure what to do here, this prevents errors being thrown.
			    return;
			  }
			  Fractions ans = null;
			  System.out.println(operator);
			  botmtext = botmtext.replace("[]", "");
			  op2 = Fractions.parseFractions(botmtext);
			  if(operator.equals("+"))
			    ans = Calculations.addition(op1, op2);
			  else if (operator.equals("-"))
			    ans = Calculations.subtraction(op1, op2);
			  else if (operator.equals("x"))
			    ans = Calculations.multiplication(op1,  op2);
			  else if (operator.equals("\u00F7"))
			    ans = Calculations.division(op1, op2);
			  top_text.setText(ans.toString());
			  operationComplete = true;
			  botmtext = "[] /";
        bottom_text.setText(botmtext);
        curFocus = "W";
			}
			else if (buttonpressed.equals("\u2190")) {
			  try {
			    Integer.parseInt(String.valueOf(botmtext.charAt(botmtext.indexOf("[")-1)));
			    System.out.println(Integer.parseInt(String.valueOf(botmtext.charAt(botmtext.indexOf("[")-1))));
			    botmtext = botmtext.substring(0, botmtext.indexOf("[")-1) + botmtext.substring(botmtext.indexOf("["));
			    bottom_text.setText(botmtext);
			    
			  } catch (StringIndexOutOfBoundsException ex) {
			    botmtext = botmtext.replace("[]", "");
			    if(curFocus == "W") {
			      curFocus = "D";
	          botmtext = botmtext + "[]";   
	        }
	        else if(curFocus == "N") {
	          curFocus = "W";
	          botmtext = (botmtext.substring(0, botmtext.indexOf(" ")) + "[]" + botmtext.substring(botmtext.indexOf(" ")));
	        }
	        else if(curFocus == "D") {
	          curFocus = "N";
	          botmtext = (botmtext.substring(0, botmtext.indexOf("/")) + "[]" + botmtext.substring(botmtext.indexOf("/")));
	        }
			    bottom_text.setText(botmtext);
			  } catch (NumberFormatException ex2) {
			    botmtext = botmtext.replace("[]", "");
			    if(curFocus == "W") {
            curFocus = "D";
            botmtext = botmtext + "[]";
          }
          else if(curFocus == "N") {
            curFocus = "W";
            botmtext = (botmtext.substring(0, botmtext.indexOf(" ")) + "[]" + botmtext.substring(botmtext.indexOf(" ")));
          }
          else if(curFocus == "D") {
            curFocus = "N";
            botmtext = (botmtext.substring(0, botmtext.indexOf("/")) + "[]" + botmtext.substring(botmtext.indexOf("/")));      
          }
			    bottom_text.setText(botmtext);
			  }
			}
		}
	}
}


