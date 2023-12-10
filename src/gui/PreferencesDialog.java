package gui;
import static gui.FragileWindow.STRINGS;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
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
	public static JCheckBox separatorsCheckbox =
			new JCheckBox(STRINGS.getString("ENABLED"), true);
	private PreferencesListener listener;
	private FragileWindow fw;
	private JPanel shortcutsPanel = new JPanel();
	private Map<String, JTextField> menuShortcuts = new HashMap<String, JTextField>();
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
       
       String[] shortcutNames =
       		{STRINGS.getString("PRINT"), STRINGS.getString("NEW"),
       		STRINGS.getString("EXIT"), STRINGS.getString("PROPER"),
       		STRINGS.getString("REDUCED"), STRINGS.getString("BAR"),
       		STRINGS.getString("SLASH"), STRINGS.getString("SOLIDUS"),
       		STRINGS.getString("ABOUT"), STRINGS.getString("HELP"),
       		STRINGS.getString("EDIT"), STRINGS.getString("OPEN"),
       		STRINGS.getString("SAVE")
       		};
      
       listener = new PreferencesListener(this, this.fw);
      
       for (String shortcutName : shortcutNames) {
           JLabel label = new JLabel(shortcutName + " "  + STRINGS.getString("SHORTCUT"));
           JTextField textField = new JTextField();
           shortcutsPanel.add(label);
           shortcutsPanel.add(textField);
           menuShortcuts.put(shortcutName, textField);
       }
       tabbedPane.addTab(STRINGS.getString("TAB_SEPARATORS"), thousandsSeparatorsPanel);
       tabbedPane.addTab(STRINGS.getString("TAB_SHORTCUTS"), shortcutsPanel);
       JPanel buttonsPanel = new JPanel();
       JButton applyButton = new JButton(STRINGS.getString("APPLY"));
       JButton cancelButton = new JButton(STRINGS.getString("CANCEL"));
      
       //Listeners
       applyButton.setActionCommand(STRINGS.getString("APPLY"));
       applyButton.addActionListener(listener);
       cancelButton.setActionCommand(STRINGS.getString("CANCEL"));
       cancelButton.addActionListener(listener);
      
       //separatorsCheckbox.addActionListener(listener);
      
       buttonsPanel.add(applyButton);
       buttonsPanel.add(cancelButton);
       setLayout(new BorderLayout());
       add(tabbedPane, BorderLayout.CENTER);
       add(buttonsPanel, BorderLayout.SOUTH);
       setLocationRelativeTo(null);
       setVisible(true);
   }
	/**
	 * The getter for the shortcuts
	 *
	 * @return the shortcutsPanel
	 */
	public JPanel getShortcutsPanel() {
		return shortcutsPanel;
	}
	/**
	 * The getter for the shortcuts
	 *
	 * @return the menuShortcuts
	 */
	public Map<String, JTextField> getMenuShortcuts() {
		return menuShortcuts;
	}
}

