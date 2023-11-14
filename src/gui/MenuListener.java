package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

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
			DelegatingPrintable print = new DelegatingPrintable(FragileWindow.calcHistoryArea);
			PrintController.print(print, null);
		} else if (e.getActionCommand().equals("Help")) {
			
			//Desktop desktop = Desktop.getDesktop();
			try {
			    // Assuming "resources" is the subpackage containing index.html
			    Path tempDir = ResourceCopier.copyResourcesToTemp("temp", "resources");

			    // Print the path to verify
			    System.out.println("Temporary directory: " + tempDir);

			    // Now, you can construct the URI for index.html within the temporary directory
			    URI indexHtmlURI = tempDir.resolve("index.html").toUri();

			    // Open the URI in the default web browser
			    Desktop.getDesktop().browse(indexHtmlURI);
			} catch (IOException | URISyntaxException er) {
			    er.printStackTrace();
			}
//			try {
//				URL url = FragileWindow.class.getResource("/index.html");
//				desktop.browse(url.toURI());
//			} catch (IOException | URISyntaxException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}

	}

}
