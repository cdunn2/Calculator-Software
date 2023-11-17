package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import static gui.FragileWindow.*;
import gui.display.*;
import utilities.Calculations;

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
		if (CreateMenu.properItem.isSelected()) {
			//idk  what to  do here
		} else {
			// maybe? Calculations.improper(?);
		}
		if (CreateMenu.reducedItem.isSelected()) {
			//Calculations.reduce(?);
		}
		
		if (CreateMenu.barItem.isSelected()) {
			TypesettingStyles style = TypesettingStyles.BAR;
			FragileWindow.calculatorDisplay.changeStyle(style);
		} else if (CreateMenu.slashItem.isSelected()) {
			TypesettingStyles style = TypesettingStyles.SLASH;
			FragileWindow.calculatorDisplay.changeStyle(style);
		} else if (CreateMenu.solidusItem.isSelected()) {
			TypesettingStyles style = TypesettingStyles.SOLIDUS;
			FragileWindow.calculatorDisplay.changeStyle(style);
		}
		
	}

}
