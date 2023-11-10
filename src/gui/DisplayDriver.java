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
	//static String botmtext;
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
		//botmtext = bottom_text.getText();
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
			} else {
			  //places number before the "[]" in the string.
			  bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf("[]")) + 
			      buttonpressed + bottom_text.getText().substring(bottom_text.getText().indexOf("[]")));
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
	        bottom_text.setText(bottom_text.getText().replace("[]", ""));
	        op1 = Fractions.parseFractions(bottom_text.getText());
	        top_text.setText(Fractions.parseFractions(bottom_text.getText()).toString() + operator);
			  }
			  bottom_text.setText("[] /");
        curFocus = "W";
			  
				
			}
			else if (buttonpressed.equals("\u00B1")) {
			  if(bottom_text.getText().substring(0, 1).equals("-"))
			    bottom_text.setText(bottom_text.getText().replace("-", ""));
			  else
			    bottom_text.setText("-" + bottom_text.getText());
			}
			else if (buttonpressed.equals("Pos")) {
			  //Check where focus currently is and update it accordingly.
			  bottom_text.setText(bottom_text.getText().replace("[]", ""));
			  if(curFocus == "W") {
			    curFocus = "N";	    
			    bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf("/")) + "[]" + 
			        bottom_text.getText().substring(bottom_text.getText().indexOf("/")));
			  }
			  else if(curFocus == "N") {
			    curFocus = "D";
			    bottom_text.setText(bottom_text.getText() + "[]");
			  }
			  else if(curFocus == "D") {
			    curFocus = "W";
			    bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf(" ")) + "[]" + 
			        bottom_text.getText().substring(bottom_text.getText().indexOf(" ")));
			  }
			}
			else if (buttonpressed.equals("=")) {
			  if(operationComplete) {
			    //Unsure what to do here, this prevents errors being thrown.
			    return;
			  }
			  Fractions ans = null;
			  bottom_text.setText(bottom_text.getText().replace("[]", ""));
			  op2 = Fractions.parseFractions(bottom_text.getText());
			  if(operator == null) {
			    ans = Fractions.parseFractions(bottom_text.getText());
			  }
			  else if(operator.equals("+"))
			    ans = Calculations.addition(op1, op2);
			  else if (operator.equals("-"))
			    ans = Calculations.subtraction(op1, op2);
			  else if (operator.equals("x"))
			    ans = Calculations.multiplication(op1,  op2);
			  else if (operator.equals("\u00F7"))
			    ans = Calculations.division(op1, op2);
			  top_text.setText(ans.toString());
			  operationComplete = true;
			  bottom_text.setText("[] /");
        curFocus = "W";
			}
			else if (buttonpressed.equals("\u2190")) {
			  try {
			    Integer.parseInt(String.valueOf(bottom_text.getText().charAt(bottom_text.getText().indexOf("[")-1)));
			    System.out.println(Integer.parseInt(String.valueOf(bottom_text.getText().charAt(bottom_text.getText().indexOf("[")-1))));
			    bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf("[")-1) + 
			        bottom_text.getText().substring(bottom_text.getText().indexOf("[")));
			    
			  } catch (StringIndexOutOfBoundsException ex) {
			    bottom_text.setText(bottom_text.getText().replace("[]", ""));
			    if(curFocus == "W") {
			      curFocus = "D";
			      bottom_text.setText(bottom_text.getText() + "[]");   
	        }
	        else if(curFocus == "N") {
	          curFocus = "W";
	          bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf(" ")) + "[]" + 
	              bottom_text.getText().substring(bottom_text.getText().indexOf(" ")));
	        }
	        else if(curFocus == "D") {
	          curFocus = "N";
	          bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf("/")) + "[]" + 
	              bottom_text.getText().substring(bottom_text.getText().indexOf("/")));
	        }
			  } catch (NumberFormatException ex2) {
			    bottom_text.setText(bottom_text.getText().replace("[]", ""));
			    if(curFocus == "W") {
            curFocus = "D";
            bottom_text.setText(bottom_text.getText() + "[]");
          }
          else if(curFocus == "N") {
            curFocus = "W";
            bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf(" ")) + "[]" + 
                bottom_text.getText().substring(bottom_text.getText().indexOf(" ")));
          }
          else if(curFocus == "D") {
            curFocus = "N";
            bottom_text.setText(bottom_text.getText().substring(0, bottom_text.getText().indexOf("/")) + "[]" + 
                bottom_text.getText().substring(bottom_text.getText().indexOf("/")));      
          }
			  }
			} else if (buttonpressed.equals("C")) {
			  bottom_text.setText("[] /");
        curFocus = "W";
			} else if (buttonpressed.equals("R")) {
			  bottom_text.setText("[] /");
        curFocus = "W";
        top_text.setText("");
			}
		}
	}
}


