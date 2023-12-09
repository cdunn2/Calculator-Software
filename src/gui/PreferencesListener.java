package gui;

import static gui.FragileWindow.STRINGS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesListener implements ActionListener {
	
	private PreferencesDialog preferencesDialog;
	
	public PreferencesListener(PreferencesDialog preferencesDialog)
	{
		this.preferencesDialog = preferencesDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(STRINGS.getString("APPLY"))) {
			
			// Thousands separators
			//Doesn't work yet
			if (PreferencesDialog.separatorsCheckbox.isSelected()) {
				FragileWindow.calculatorDisplay.separatorsOn(true);
				
			} else if (!PreferencesDialog.separatorsCheckbox.isSelected()) {
				FragileWindow.calculatorDisplay.separatorsOn(false);
			}
			preferencesDialog.dispose();

		} else if (e.getActionCommand().equals(STRINGS.getString("CANCEL"))) {
			preferencesDialog.dispose();
		}
		
	}

}
