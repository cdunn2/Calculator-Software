package gui;

import static gui.FragileWindow.STRINGS;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is the class that creates the preferences dialog GUI.
 *
 * I used the following resources to help me with creating this dialog:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
 * https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
 * https://www.youtube.com/watch?v=uaQ1bBoK7HU
 * 
 * and this for saving/loading preferences:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html
 *
 * @author Connor Dunn
 * @version 1.3
 */
public class PreferencesDialog extends JDialog
{
  
  private static final String APPLY = "APPLY";
  private static final String CANCEL = "CANCEL";
  private static final String TRUE = "TRUE";
  private static final String FALSE = "FALSE";
  private static final String SEPARATORS = "separators";
  private static final String FILELOCATION = "./src/gui/Preferences.properties";
  private static JCheckBox separatorsCheckbox = new JCheckBox(STRINGS.getString("ENABLED"), true);
  private static final long serialVersionUID = 1L;
  private PreferencesListener listener;
  private FragileWindow fw;
  private JPanel shortcutsPanel = new JPanel();
  private Map<String, JTextField> menuShortcuts = new HashMap<String, JTextField>();
  private JTextField[] textFieldList;

  /**
   * This is the constructor for the Preferences class, where it gets built.
   *
   * @param fw
   *          The current fragile window
   */
  public PreferencesDialog(final FragileWindow fw)
  {
    super((JFrame) null, STRINGS.getString("PREFERENCES"), true);
    this.fw = fw;
    this.textFieldList = new JTextField[13];
    setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    setSize(400, 300);
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel thousandsSeparatorsPanel = new JPanel();
    thousandsSeparatorsPanel.add(new JLabel(STRINGS.getString("ASK_SEPARATORS")));
    thousandsSeparatorsPanel.add(separatorsCheckbox);

    JPanel shortcutPanel = new JPanel();
    shortcutPanel.setLayout(new GridLayout(0, 2));

    String[] shortcutNames = {STRINGS.getString("PRINT"), STRINGS.getString("NEW"),
        STRINGS.getString("EXIT"), STRINGS.getString("PROPER"), STRINGS.getString("REDUCED"),
        STRINGS.getString("BAR"), STRINGS.getString("SLASH"), STRINGS.getString("SOLIDUS"),
        STRINGS.getString("ABOUT"), STRINGS.getString("HELP"), STRINGS.getString("EDIT"),
        STRINGS.getString("OPEN"), STRINGS.getString("SAVE")};
    listener = new PreferencesListener(this, fw);
    int index = 0;
    for (String menuItemName : shortcutNames)
    {
      JLabel label = new JLabel(menuItemName + " " + STRINGS.getString("SHORTCUT"));
      JTextField textField = new JTextField();
      shortcutPanel.add(label);
      shortcutPanel.add(textField);
      menuShortcuts.put(menuItemName, textField);
      textFieldList[index] = textField;
      index++;
    }

    tabbedPane.addTab(STRINGS.getString("TAB_SEPARATORS"), thousandsSeparatorsPanel);
    tabbedPane.addTab(STRINGS.getString("TAB_SHORTCUTS"), shortcutPanel);
    JPanel buttonsPanel = new JPanel();
    JButton applyButton = new JButton(STRINGS.getString(APPLY));
    JButton cancelButton = new JButton(STRINGS.getString(CANCEL));

    // Listeners
    applyButton.setActionCommand(STRINGS.getString(APPLY));
    applyButton.addActionListener(listener);
    cancelButton.setActionCommand(STRINGS.getString(CANCEL));
    cancelButton.addActionListener(listener);

    // separatorsCheckbox.addActionListener(listener);

    buttonsPanel.add(applyButton);
    buttonsPanel.add(cancelButton);
    setLayout(new BorderLayout());
    add(tabbedPane, BorderLayout.CENTER);
    add(buttonsPanel, BorderLayout.SOUTH);
    setLocationRelativeTo(null);
  }

  /**
   * The getter for the shortcuts.
   *
   * @return the shortcutsPanel
   */
  public JPanel getShortcutsPanel()
  {
    return shortcutsPanel;
  }

  /**
   * Returns the preferences.
   *
   * @return String[] Array containing preferences.
   */
  public String[] getPreferences()
  {
    String[] list = new String[14];
    for (int i = 0; i < textFieldList.length; i++)
    {
      list[i] = textFieldList[i].getText();
    }
    if (separatorsCheckbox.isSelected())
    {
      list[13] = TRUE;
    }
    else
    {
      list[13] = FALSE;
    }
    return list;
  }

  /**
   * Saves preferences to file.
   */
  public void saveToFile()
  {
    try
    {
      Properties properties = new Properties();

      for (Map.Entry<String, JTextField> entry : menuShortcuts.entrySet())
      {
        properties.setProperty(entry.getKey(), entry.getValue().getText());
      }

      properties.setProperty(SEPARATORS, separatorsCheckbox.isSelected() ? TRUE : FALSE);

      try (FileWriter fileWriter = new FileWriter(FILELOCATION))
      {
        properties.store(fileWriter, STRINGS.getString("CALC_PREF"));
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Loads preferences from file.
   */
  public void loadFromFile()
  {
    try
    {
      Properties properties = new Properties();

      try (FileReader fileReader = new FileReader(FILELOCATION))
      {
        properties.load(fileReader);
      }

      for (Map.Entry<String, JTextField> entry : menuShortcuts.entrySet())
      {
        String menuItemName = entry.getKey();
        String shortcut = properties.getProperty(menuItemName, "");
        entry.getValue().setText(shortcut);
      }

      String separatorsValue = properties.getProperty(SEPARATORS, FALSE);
      separatorsCheckbox.setSelected(separatorsValue.equals(TRUE));

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * The getter for the shortcuts.
   *
   * @return the menuShortcuts
   */
  public Map<String, JTextField> getMenuShortcuts()
  {
    return menuShortcuts;
  }

  /**
   * Returns the state of the separators checkbox.
   *
   * @return JCheckBox the state of the separators checkbox.
   */
  public static JCheckBox getSeparatorsCheckbox()
  {
    return separatorsCheckbox;
  }
}
