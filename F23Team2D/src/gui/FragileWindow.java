package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FragileWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame frame = new JFrame("Fragile Calculator");
	private static JLabel logoLabel;
	

public static void main(String[] args) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 500);
		frame.setLayout(new BorderLayout());

		createMenu();

		// Fragile logo
		ImageIcon logoIcon = new ImageIcon(FragileWindow.class.getResource("/Fragile_Logo.png"));
		logoLabel = new JLabel(logoIcon);
		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		logoPanel.add(logoLabel);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(logoPanel, BorderLayout.NORTH);

		// also display in a different class
		Container display = new DisplayDriver();
		display.setFont(new Font("Arial", Font.BOLD, 24));
		topPanel.add(display, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();

		gb.weightx = 1.0;
		gb.weighty = 1.0;

		String[][] buttons = {
				{"R", "C", "\u2190", "+", "\u00B1"},
				{"7", "8", "9", "-"},
				{"4", "5", "6", "x"},
				{"1", "2", "3", "\u00F7"},
				{"0", "", "Pos", "="}
		};

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				gb.gridx = j;
				gb.gridy = i;
				gb.fill = GridBagConstraints.BOTH;

				if (i == 4 && j == 0) {
					gb.gridwidth = 2;
				} else {
					gb.gridwidth = 1;
				}

				if (!buttons[i][j].isEmpty()) {
					JButton button = new JButton(buttons[i][j]);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							DisplayDriver.bottomManager(((JButton) e.getSource()).getText());
						}
					});
					buttonsPanel.add(button, gb);
				}
			}
		}

		frame.add(buttonsPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	private static void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		//JMenu viewMenu = new JMenu("View");			I took out the pie chart because we will not complete it this sprint
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(fileMenu);
		//menuBar.add(viewMenu);
		menuBar.add(helpMenu);

		JMenuItem exitItem = new JMenuItem("Exit");
		//JMenuItem pieItem = new JMenuItem("Pie Chart");
		JMenuItem aboutItem = new JMenuItem("About");
		JMenuItem helpItem = new JMenuItem("Help");

		fileMenu.add(exitItem);
		//viewMenu.add(pieItem);
		helpMenu.add(aboutItem);
		helpMenu.add(helpItem);
		
		MenuListener listener = new MenuListener();

        exitItem.addActionListener(listener);
        //pieItem.addActionListener(listener);
        aboutItem.addActionListener(listener);
        helpItem.addActionListener(listener);
	}
}
