package gui;

import static gui.FragileWindow.STRINGS;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;


/**
 * This is the class that creates the preferences dialog GUI.
 * 
 * I used the following resources to help  me with creating this dialog:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
 * https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
 * https://www.youtube.com/watch?v=uaQ1bBoK7HU
 *
 * @author      Connor Dunn
 * @version     1.3
 */
public class PreferencesDialog extends JDialog
{
	
	private static final long serialVersionUID = 1L;
	protected static JCheckBox separatorsCheckbox = new JCheckBox(STRINGS.getString("ENABLED"), true);
	private FragileWindow fw;

	/**
     * This is the constructor for the Preferences class, where it gets built.
     *
     * @param fw    The current fragile window
     */
	public PreferencesDialog(FragileWindow fw) {
        super((JFrame)  null, STRINGS.getString("PREFERENCES"), true);
        this.fw = fw;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel thousandsSeparatorsPanel = new JPanel();
        thousandsSeparatorsPanel.add(new JLabel(STRINGS.getString("ASK_SEPARATORS")));
        thousandsSeparatorsPanel.add(separatorsCheckbox);

        JPanel shortcutsPanel = new JPanel();
        shortcutsPanel.setLayout(new GridLayout(0, 2));

        String[] menuItemNames = 
        		{STRINGS.getString("PRINT"), STRINGS.getString("NEW"),
        		STRINGS.getString("EXIT"), STRINGS.getString("PROPER"),
        		STRINGS.getString("REDUCED"), STRINGS.getString("BAR"),
        		STRINGS.getString("SLASH"), STRINGS.getString("SOLIDUS"),
        		STRINGS.getString("ABOUT"), STRINGS.getString("HELP"),
        		STRINGS.getString("EDIT"), STRINGS.getString("OPEN"),
        		STRINGS.getString("SAVE")
        		};
        for (String menuItemName : menuItemNames) {
            JLabel label = new JLabel(menuItemName + STRINGS.getString("SHORTCUT"));
            JTextField textField = new JTextField();
            shortcutsPanel.add(label);
            shortcutsPanel.add(textField);
        }

        tabbedPane.addTab(STRINGS.getString("TAB_SEPARATORS"), thousandsSeparatorsPanel);
        tabbedPane.addTab(STRINGS.getString("TAB_SHORTCUTS"), shortcutsPanel);

        JPanel buttonsPanel = new JPanel();
        JButton applyButton = new JButton(STRINGS.getString("APPLY"));
        JButton cancelButton = new JButton(STRINGS.getString("CANCEL"));
        
        //Listeners
        PreferencesListener listener = new PreferencesListener(this, this.fw);
        applyButton.addActionListener(listener);
        separatorsCheckbox.addActionListener(listener);

        buttonsPanel.add(applyButton);
        buttonsPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
