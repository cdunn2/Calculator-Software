package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FragileWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Fragile Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 500);
		frame.setLayout(new BorderLayout());

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(final ActionEvent e) 
			{
				System.exit(0);
			}
		});

		fileMenu.add(exitItem);

		JTextField display = new JTextField();
		display.setEditable(false);
		display.setFont(new Font("Arial", Font.BOLD, 24));
		frame.add(display, BorderLayout.NORTH);


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
					if (button.getText().matches("\\d")) {
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								System.out.println(((JButton) e.getSource()).getText() + " pressed!");
							}
						});
					} else {
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String buttonText = ((JButton) e.getSource()).getText();
								// I'm so sorry for what I'm about to do
								if (buttonText.equals("R")) {
									System.out.println("Reset pressed!");
								} else if (buttonText.equals("C")) {
									System.out.println("Clear pressed!");
								} else if (buttonText.equals("\u2190")) {
									System.out.println("Backspace pressed!");
								} else if (buttonText.equals("+")) {
									System.out.println("+ pressed!");
								} else if (buttonText.equals("-")) {
									System.out.println("- pressed!");
								} else if (buttonText.equals("x")) {
									System.out.println("x pressed!");
								} else if (buttonText.equals("\u00F7")) {
									System.out.println("\u00F7 pressed!");
								} else if (buttonText.equals("=")) {
									System.out.println("= pressed!");
								} else if (buttonText.equals("\u00B1")) {
									System.out.println("\u00B1 pressed!");
								} else if (buttonText.equals("Pos")) { // This one is temporary
									System.out.println("Position pressed!");
								}
							}
						});
					}

					buttonsPanel.add(button, gb);
				}
			}
		}

		frame.add(buttonsPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}
}
