package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MenuListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("About")) {
			AboutWindow.main(null);
		} else if (e.getActionCommand().equals("Print Session")) {
			//TODO
		} else if (e.getActionCommand().equals("Help")) {
			
			Desktop desktop = Desktop.getDesktop();
			
			try {
				URL url = FragileWindow.class.getResource("/index.html");
				desktop.browse(url.toURI());
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
