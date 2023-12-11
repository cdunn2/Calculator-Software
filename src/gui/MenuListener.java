package gui;

import static gui.FragileWindow.*;

import gui.display.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;


/**
 * This is the class that creates the listener for the menu items.
 *
 * @author      Connor Dunn
 * @version     1.3
 */
public class MenuListener implements ActionListener
{
	private FragileWindow fw;
	
	public static Boolean reduce = false;
	public static Boolean proper = false;
	PreferencesDialog preferencesDialog;
	
	public MenuListener(FragileWindow fw) {
		this.fw = fw;
		this.preferencesDialog = new PreferencesDialog(fw);
		preferencesDialog.setVisible(false);
	}
	
	/**
     * This is the main method in the class that adds behavior to what happens when
     * a menu item is selected.
     *
     * @param e    The current action event
     */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals(STRINGS.getString("EXIT"))) {
			System.exit(0);
		} else if (e.getActionCommand().equals(STRINGS.getString("ABOUT"))) {
			AboutWindow.main(null);
		} else if (e.getActionCommand().equals(STRINGS.getString("NEW"))) {
			FragileWindow.openNewWindow();
		} else if (e.getActionCommand().equals(STRINGS.getString("PRINT"))) {
			DelegatingPrintable print = new DelegatingPrintable(fw.calcHistoryArea);
			PrintController.print(print, null);
		} else if (e.getActionCommand().equals(STRINGS.getString("SAVE"))) {
			preferencesDialog.saveToFile();
		} else if (e.getActionCommand().equals(STRINGS.getString("OPEN"))) {
			preferencesDialog.loadFromFile();
		} else if (e.getActionCommand().equals(STRINGS.getString("EDIT"))) {

			preferencesDialog.setVisible(true);


			//for preferences, use a dialog:
			//https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
			//maybe have a text file to save the preferences
		} else if (e.getActionCommand().equals(STRINGS.getString("HELP"))) {

			try {
				Path tempDir = ResourceCopier.copyResourcesToTemp("temp", "resources");

				URI indexHtmlURI = tempDir.resolve(STRINGS.getString("HTML")).toUri();

				Desktop.getDesktop().browse(indexHtmlURI);

			} catch (IOException | URISyntaxException er) {
				er.printStackTrace();
			}
		}
		if (CreateMenu.properItem.isSelected()) {
			proper = true;
		} else if (!CreateMenu.properItem.isSelected()){
			proper = false;
		}
		if (CreateMenu.reducedItem.isSelected()) {
			reduce = true;
		} else if (!CreateMenu.reducedItem.isSelected()) {
			reduce = false;
		}
		
		if (CreateMenu.barItem.isSelected()) {
			fw.calculatorDisplay.changeStyle(TypesettingStyles.BAR);
		} else if (CreateMenu.slashItem.isSelected()) {
			fw.calculatorDisplay.changeStyle(TypesettingStyles.SLASH);
		} else if (CreateMenu.solidusItem.isSelected()) {
			fw.calculatorDisplay.changeStyle(TypesettingStyles.SOLIDUS);
		}
		
	}

}
