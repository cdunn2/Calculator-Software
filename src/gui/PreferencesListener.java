package gui;

import static gui.FragileWindow.STRINGS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

/**
 * This is the class that creates the listener for the preferences dialog.
 * 
 * I used this reference to help me iterate through a hashmap:
 * https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
 *
 * @author      Connor Dunn
 * @version     1.3
 */
public class PreferencesListener implements ActionListener {

	private PreferencesDialog preferencesDialog;
	private FragileWindow fw;
	public Boolean separators;

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
			separatorsChanger();

			// Shortcuts
			applyShortcuts();

			preferencesDialog.dispose();

		} else if (e.getActionCommand().equals(STRINGS.getString("CANCEL"))) {
			preferencesDialog.dispose();
		}

	}

	/**
	 * The private method that changes the thousands separators.
	 */
	private void separatorsChanger() {
		if (PreferencesDialog.separatorsCheckbox.isSelected()) {
			fw.calculatorDisplay.separatorsOn(true);
			separators = true;

		} else if (!PreferencesDialog.separatorsCheckbox.isSelected()) {
			fw.calculatorDisplay.separatorsOn(false);
			separators = false;
		}

	}

	/**
	 * The private method that assigns behavior to the shortcut text fields.
	 */
	private void applyShortcuts() {

		for (Map.Entry<String, JTextField> entry : preferencesDialog.getMenuShortcuts().entrySet())
		{
			String menuItemName = entry.getKey();
			JTextField textField = entry.getValue();
			String shortcut = textField.getText().trim();

			//System.out.print(menuItemName + "\t" + shortcut);
		}

	}

}
