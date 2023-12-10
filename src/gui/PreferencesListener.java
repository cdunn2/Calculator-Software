package gui;

import static gui.FragileWindow.STRINGS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesListener implements ActionListener {
	
	private PreferencesDialog preferencesDialog;
	private FragileWindow fw;
	
	public PreferencesListener(PreferencesDialog preferencesDialog, FragileWindow fw)
	{
		this.preferencesDialog = preferencesDialog;
		this.fw = fw;
	}

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
