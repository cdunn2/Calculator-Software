package gui;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
			  bottom_text.setText(botmtext.substring(0, botmtext.indexOf("[]")) + buttonpressed + botmtext.substring(botmtext.indexOf("[]")));
				botmtext = bottom_text.getText();
			}


		}
		catch (NumberFormatException e) {
			if (buttonpressed.equals("x") || buttonpressed.equals("-") || buttonpressed.equals("+")
					|| buttonpressed.equals("\u00F7") )
			{
				bottom_text.setText(botmtext + buttonpressed);
				botmtext = bottom_text.getText();
				botmtext = botmtext.replace("[]", "");
				top_text.setText(botmtext);
				botmtext = "[] /";
				bottom_text.setText(botmtext);
				curFocus = "W";
			}
			else if (buttonpressed.equals("Pos")) {
			  //Check where focus currently is and update it accordingly.
			  if(curFocus == "W") {
			    curFocus = "N";
			    botmtext = botmtext.replace("[]", "");
			    botmtext = (botmtext.substring(0, botmtext.indexOf("/")) + "[]" + botmtext.substring(botmtext.indexOf("/")));
			    bottom_text.setText(botmtext);
			  }
			  else if(curFocus == "N") {
			    curFocus = "D";
			    botmtext = botmtext.replace("[]", "");
          botmtext = botmtext + "[]";
          bottom_text.setText(botmtext);
			  }
			  else if(curFocus == "D") {
			    curFocus = "W";
          botmtext = botmtext.replace("[]", "");
          botmtext = (botmtext.substring(0, botmtext.indexOf(" ")) + "[]" + botmtext.substring(botmtext.indexOf(" ")));
          bottom_text.setText(botmtext);
			  }
			}
		}
	}
}


