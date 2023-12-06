package gui;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import static gui.FragileWindow.*;

import java.awt.event.KeyEvent;

public class CreateMenu {

	private static JFrame frame = FragileWindow.frame;
	
	protected static JCheckBoxMenuItem properItem = new JCheckBoxMenuItem(STRINGS.getString("PROPER"));
	protected static JCheckBoxMenuItem reducedItem = new JCheckBoxMenuItem(STRINGS.getString("REDUCED"));
	
	protected static JRadioButtonMenuItem barItem = new JRadioButtonMenuItem(STRINGS.getString("BAR"));
	protected static JRadioButtonMenuItem slashItem = new JRadioButtonMenuItem(STRINGS.getString("SLASH"), true);
	protected static JRadioButtonMenuItem solidusItem = new JRadioButtonMenuItem(STRINGS.getString("SOLIDUS"));
	
	public CreateMenu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
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
		
		MenuListener listener = new MenuListener();

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
	}

	private static void AddShortcut(JMenuItem item, int key, int modifier)
	{
		item.setAccelerator(KeyStroke.getKeyStroke(key, modifier));
	}

}