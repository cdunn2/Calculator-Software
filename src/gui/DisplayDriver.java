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
	public DisplayDriver() {
		super();
		top_text = new JLabel("top");
		bottom_text = new JLabel("bottom");
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
			Integer.parseInt(buttonpressed);
			bottom_text.setText(botmtext + buttonpressed);
			botmtext = bottom_text.getText();
		}
		catch (NumberFormatException e) {
			if (buttonpressed.equals("x") || buttonpressed.equals("-") || buttonpressed.equals("+") || buttonpressed.equals("\u00F7") ) {
				bottom_text.setText(botmtext + buttonpressed);
				botmtext = bottom_text.getText();
				top_text.setText(botmtext);
				botmtext = "";
				bottom_text.setText(botmtext);
			}
		}
	}
}


