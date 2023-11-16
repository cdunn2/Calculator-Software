package gui;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import static gui.FragileWindow.*;

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
		menuBar.add(fileMenu);
		menuBar.add(modeMenu);
		menuBar.add(styleMenu);
		menuBar.add(helpMenu);

		JMenuItem printItem = new JMenuItem(STRINGS.getString("PRINT"));
		JMenuItem exitItem = new JMenuItem(STRINGS.getString("EXIT"));
		
		JMenuItem aboutItem = new JMenuItem(STRINGS.getString("ABOUT"));
		JMenuItem helpItem = new JMenuItem(STRINGS.getString("HELP"));
		
		ButtonGroup group = new ButtonGroup();
		group.add(barItem);
		group.add(slashItem);
		group.add(solidusItem);

		fileMenu.add(printItem);
		fileMenu.add(exitItem);
		modeMenu.add(properItem);
		modeMenu.add(reducedItem);
		styleMenu.add(barItem);
		styleMenu.add(slashItem);
		styleMenu.add(solidusItem);
		helpMenu.add(aboutItem);
		helpMenu.add(helpItem);
		
		MenuListener listener = new MenuListener();

		printItem.addActionListener(listener);
        exitItem.addActionListener(listener);
        properItem.addActionListener(listener);
        reducedItem.addActionListener(listener);
        barItem.addActionListener(listener);
        slashItem.addActionListener(listener);
        solidusItem.addActionListener(listener);
        aboutItem.addActionListener(listener);
        helpItem.addActionListener(listener);
	}
}
