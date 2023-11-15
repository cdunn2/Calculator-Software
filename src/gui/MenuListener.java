package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import static gui.FragileWindow.*;

public class MenuListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals(STRINGS.getString("EXIT"))) {
			System.exit(0);
		} else if (e.getActionCommand().equals(STRINGS.getString("ABOUT"))) {
			AboutWindow.main(null);
		} else if (e.getActionCommand().equals(STRINGS.getString("PRINT"))) {
			DelegatingPrintable print = new DelegatingPrintable(FragileWindow.calcHistoryArea);
			PrintController.print(print, null);
		} else if (e.getActionCommand().equals(STRINGS.getString("HELP"))) {

			try {
			    Path tempDir = ResourceCopier.copyResourcesToTemp("temp", "resources");

			    URI indexHtmlURI = tempDir.resolve("index.html").toUri();

			    Desktop.getDesktop().browse(indexHtmlURI);
			    
			} catch (IOException | URISyntaxException er) {
			    er.printStackTrace();
			}
		}

	}

}
