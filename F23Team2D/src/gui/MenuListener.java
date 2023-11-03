package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Pie Chart")) {
			// Handle the "Pie Chart" action
			//TODO
		} else if (e.getActionCommand().equals("About")) {
			AboutWindow.main(null);
		} else if (e.getActionCommand().equals("Help")) {
			Desktop desktop = Desktop.getDesktop();
			URI uri;
			try {
				uri = new URI("https://cdunn2.github.io/cs345-html/");
				desktop.browse(uri);
			} catch (URISyntaxException | IOException e1) {
			}
		}

	}

}
