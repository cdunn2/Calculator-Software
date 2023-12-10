package gui;

import static gui.FragileWindow.STRINGS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the class that creates the listener for the preferences dialog.
 *
 * @author      Connor Dunn
 * @version     1.3
 */
public class PreferencesListener implements ActionListener {
	
	private PreferencesDialog preferencesDialog;
	private FragileWindow fw;
	
	/**
     * The constructor for adding the listeners to each preference.
     *
     * @param preferencesDialog    The inputed preferences dialog
     * @param fw					The current fragile window
     */
	public PreferencesListener(PreferencesDialog preferencesDialog, FragileWindow fw)
	{
		this.preferencesDialog = preferencesDialog;
		this.fw = fw;
	}

	/**
     * The constructor for adding the listeners to each preference.
     *
     * @param e    The current action event
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(STRINGS.getString("APPLY"))) {
			
			// Thousands separators
			//Doesn't work yet
			if (PreferencesDialog.separatorsCheckbox.isSelected()) {
				fw.calculatorDisplay.separatorsOn(true);
				
			} else if (!PreferencesDialog.separatorsCheckbox.isSelected()) {
				fw.calculatorDisplay.separatorsOn(false);
			}
			preferencesDialog.dispose();

		} else if (e.getActionCommand().equals(STRINGS.getString("CANCEL"))) {
			preferencesDialog.dispose();
		}
		
	}

}
