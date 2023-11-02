package gui;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DisplayDriver extends Container
{
	private static final long serialVersionUID = 1L;
	static JLabel top_text;
	static JLabel bottom_text;
	static String botmtext = "";
	String toptext = "";
	static String curFocus = "W";
	
	public DisplayDriver() {
		super();
		top_text = new JLabel(" ");
		bottom_text = new JLabel(" ");
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

			if (bottom_text.getText().isBlank())
			{
				bottom_text.setText(String.format("%d /", num));

				botmtext = bottom_text.getText();
			} else {
			  //Check where focus is and insert number in the appropriate spot.
			  if(curFocus == "W") {
			    System.out.println(curFocus);
			    bottom_text.setText(botmtext.substring(0, botmtext.indexOf(" ")) + buttonpressed + botmtext.substring(botmtext.indexOf(" ")));
			  }
			  else if (curFocus == "N") {
			    System.out.println("N");
			    bottom_text.setText(botmtext.substring(0, botmtext.indexOf("/")) + buttonpressed + botmtext.substring(botmtext.indexOf("/")));
			  }
			  else if(curFocus == "D") {
			    System.out.println("D");
          bottom_text.setText(botmtext + buttonpressed);
			  }
				botmtext = bottom_text.getText();
			}


		}
		catch (NumberFormatException e) {
			if (buttonpressed.equals("x") || buttonpressed.equals("-") || buttonpressed.equals("+")
					|| buttonpressed.equals("\u00F7") )
			{
				bottom_text.setText(botmtext + buttonpressed);
				botmtext = bottom_text.getText();
				top_text.setText(botmtext);
				botmtext = "";
				bottom_text.setText(botmtext);
			}
			else if (buttonpressed.equals("Pos")) {
			  //Check where focus currently is and update it accordingly.
			  if(curFocus == "W")
			    curFocus = "N";
			  else if(curFocus == "N")
			    curFocus = "D";
			  else if(curFocus == "D")
			    curFocus = "W";
			}
		}
	}
}


