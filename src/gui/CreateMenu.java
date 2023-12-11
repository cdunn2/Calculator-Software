package gui;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import static gui.FragileWindow.*;
import java.awt.event.KeyEvent;
import java.util.*;


/**
 * This is the class that creates the menu for the calculator.
 *
 * @author      Connor Dunn
 * @version     1.3
 */
public class CreateMenu {
	
  private Map<String, JMenuItem> menuItems;
	private JCheckBoxMenuItem properItem = new JCheckBoxMenuItem(STRINGS.getString("PROPER"));
	private JCheckBoxMenuItem reducedItem = new JCheckBoxMenuItem(STRINGS.getString("REDUCED"));
	
	private JRadioButtonMenuItem barItem = new JRadioButtonMenuItem(STRINGS.getString("BAR"));
	private JRadioButtonMenuItem slashItem = new JRadioButtonMenuItem(STRINGS.getString("SLASH"), true);
	private JRadioButtonMenuItem solidusItem = new JRadioButtonMenuItem(STRINGS.getString("SOLIDUS"));
	
	private FragileWindow fw;
	
	
	/**
     * This is the constructor for the menu class, where it gets built
     *
     * @param fw    The current fragile window
     */
	public CreateMenu(FragileWindow fw) {
		this.fw = fw;
		JMenuBar menuBar = new JMenuBar();
		fw.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu(STRINGS.getString("FILE"));
		JMenu modeMenu = new JMenu(STRINGS.getString("MODE"));
		JMenu styleMenu = new JMenu(STRINGS.getString("STYLE"));
		JMenu helpMenu = new JMenu(STRINGS.getString("HELP"));
		JMenu preferencesMenu = new JMenu(STRINGS.getString("PREFERENCES"));
		
		menuBar.add(fileMenu);
		menuBar.add(modeMenu);
		menuBar.add(styleMenu);
		menuBar.add(helpMenu);
		menuBar.add(preferencesMenu);

		JMenuItem printItem = new JMenuItem(STRINGS.getString("PRINT"));
		JMenuItem newItem = new JMenuItem(STRINGS.getString("NEW"));
		JMenuItem exitItem = new JMenuItem(STRINGS.getString("EXIT"));
		
		JMenuItem aboutItem = new JMenuItem(STRINGS.getString("ABOUT"));
		JMenuItem helpItem = new JMenuItem(STRINGS.getString("HELP"));
		
		JMenuItem editItem = new JMenuItem(STRINGS.getString("EDIT"));
		JMenuItem openItem = new JMenuItem(STRINGS.getString("OPEN"));
		JMenuItem saveItem = new JMenuItem(STRINGS.getString("SAVE"));
		
		menuItems = new HashMap<String, JMenuItem>();
		menuItems.put(STRINGS.getString("PRINT"), printItem);
		menuItems.put(STRINGS.getString("NEW"), newItem);
		menuItems.put(STRINGS.getString("EXIT"), exitItem);
		menuItems.put(STRINGS.getString("ABOUT"), aboutItem);
		menuItems.put(STRINGS.getString("HELP"), helpItem);
		menuItems.put(STRINGS.getString("PROPER"), properItem);
		menuItems.put(STRINGS.getString("REDUCED"), reducedItem);
		menuItems.put(STRINGS.getString("BAR"), barItem);
		menuItems.put(STRINGS.getString("SLASH"), slashItem);
		menuItems.put(STRINGS.getString("SOLIDUS"), solidusItem);
		menuItems.put(STRINGS.getString("EDIT"), editItem);
		menuItems.put(STRINGS.getString("OPEN"), openItem);
		menuItems.put(STRINGS.getString("SAVE"), saveItem);
	   
		AddShortcut(printItem, KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(newItem, KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(exitItem, KeyEvent.VK_ESCAPE, 0);
		AddShortcut(aboutItem, KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(helpItem, KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(properItem, KeyEvent.VK_P, KeyEvent.SHIFT_DOWN_MASK);
		AddShortcut(reducedItem, KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(barItem, KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(slashItem, KeyEvent.VK_SLASH, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(solidusItem, KeyEvent.VK_SLASH, KeyEvent.SHIFT_DOWN_MASK);
		AddShortcut(editItem, KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(openItem, KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
		AddShortcut(saveItem, KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		
		ButtonGroup group = new ButtonGroup();
		group.add(barItem);
		group.add(slashItem);
		group.add(solidusItem);

		fileMenu.add(printItem);
		fileMenu.add(newItem);
		fileMenu.add(exitItem);
		
		modeMenu.add(properItem);
		modeMenu.add(reducedItem);
		
		styleMenu.add(barItem);
		styleMenu.add(slashItem);
		styleMenu.add(solidusItem);
		
		helpMenu.add(aboutItem);
		helpMenu.add(helpItem);
		
		preferencesMenu.add(editItem);
		preferencesMenu.add(openItem);
		preferencesMenu.add(saveItem);
		
		MenuListener listener = new MenuListener(this.fw);

		printItem.addActionListener(listener);
		newItem.addActionListener(listener);
        exitItem.addActionListener(listener);
        properItem.addActionListener(listener);
        reducedItem.addActionListener(listener);
        barItem.addActionListener(listener);
        slashItem.addActionListener(listener);
        solidusItem.addActionListener(listener);
        aboutItem.addActionListener(listener);
        helpItem.addActionListener(listener);
        editItem.addActionListener(listener);
        openItem.addActionListener(listener);
        saveItem.addActionListener(listener);
	}

	public JCheckBoxMenuItem getReducedItem() {
		return reducedItem;
	}
	
	public JCheckBoxMenuItem getProperItem() {
		return properItem;
	}
	
	public JRadioButtonMenuItem getBarItem() {
		return barItem;
	}
	
	public JRadioButtonMenuItem getSlashItem() {
		return slashItem;
	}
	
	public JRadioButtonMenuItem getSolidusItem() {
		return solidusItem;
	}
	/**
     * This is the private method for adding the shortcut for each menu item
     *
     * @param item    The menu item that the shortcut is being added to
     * @param key    The key to be pressed for the shortcut
     * @param modifier	The modifier shortcut key
     */
	private static void AddShortcut(JMenuItem item, int key, int modifier)
	{
		item.setAccelerator(KeyStroke.getKeyStroke(key, modifier));
	}

	/**
	 * @return the menuItems
	 */
	public Map<String, JMenuItem> getMenuItems()
	{
	  return menuItems;
	}
}
