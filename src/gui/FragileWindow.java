package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import gui.display.Display;
import gui.display.TypesettingStyles;

public class FragileWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	static final Locale LOCALE = Locale.getDefault();
	static final ResourceBundle STRINGS = ResourceBundle.getBundle("gui.Strings");
	public static JFrame frame = new JFrame(STRINGS.getString("CALCULATOR"));
	private static JLabel logoLabel;
	public static JPanel calcHistoryArea = new JPanel();
	public static Display calculatorDisplay = new Display(TypesettingStyles.SLASH);
	


public static void main(String[] args) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 500);
		frame.setLayout(new BorderLayout());
		//frame.setResizable(false);

		new CreateMenu();

		// Fragile logo
		ImageIcon logoIcon = new ImageIcon(FragileWindow.class.getResource("/icons/Fragile_Logo.png"));
		logoLabel = new JLabel(logoIcon);
		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		logoPanel.add(logoLabel);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(logoPanel, BorderLayout.NORTH);
		//Manually change display style here, does not work dynamically yet.
		Container display = calculatorDisplay;
		display.setFont(new Font("Arial", Font.BOLD, 24));
		//topPanel.add(new JLabel(""), BorderLayout.EAST);
		//topPanel.add(new JLabel(""), BorderLayout.WEST);
		topPanel.add(display, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();

		gb.weightx = 1.0;
		gb.weighty = 1.0;

		String[][] buttons = {
				{"R", "C", "\u2190", "+", "\u21F9","\u00B1"},
				{"7", "8", "9", "-", "x\u207F", "Inv"},
				{"4", "5", "6", "x", "", "\u2193"},
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
					button.setFocusPainted(false);
					button.setBackground(Color.white);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							calculatorDisplay.manageButtons(((JButton) e.getSource()).getText());
						}
					});
					buttonsPanel.add(button, gb);
				}
			}
		}
		
		// Key bindings for each physical key
		String[] keys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "PERIOD", "shift EQUALS", "MINUS", "shift 8", "SLASH", "ENTER"};
	    for (String key : keys) {
	        KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
	        // For some reason I can't change input without it throwing a fit so this is what we got
	        // The Display class also doesn't like it if I try to do alternate cases for these for some reason
	        if (key.equals("shift EQUALS")) {
	        	key = "+";
	        }
	        if (key.equals("MINUS")) {
	        	key = "-";
	        }
	        if (key.equals("shift 8")) {
	        	key = "x";
	        }
	        if (key.equals("SLASH")) {
	        	key = "\u00F7";
	        }
	        if (key.equals("PERIOD")) {
	        	key = "Pos";
	        }
	        String input = key;
	        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, input);
	        frame.getRootPane().getActionMap().put(input, new AbstractAction() {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent e) {
	                calculatorDisplay.manageButtons(input);
	            }
	        });
	    }
		
		JWindow secondaryWindow = new JWindow(frame);
	    secondaryWindow.setSize(50, 500);
	    secondaryWindow.setLocationRelativeTo(frame);
	    secondaryWindow.setLocation(frame.getX() + frame.getWidth(), frame.getY());

	    frame.addComponentListener(new ComponentAdapter() {
	        public void componentMoved(ComponentEvent e) {
	            secondaryWindow.setLocation(frame.getX() - 7 + frame.getWidth(), frame.getY() + 100);
	        }

	        public void componentResized(ComponentEvent e) {
	            secondaryWindow.setSize(secondaryWindow.getWidth(), frame.getHeight() - 170);
	            secondaryWindow.setLocation(frame.getX() - 7 + frame.getWidth(), frame.getY() + 100);
	        }
	    });
	    
	    // This is the area for the calculation history and the button to open/close it
	    JPanel containerPanel = new JPanel(new BorderLayout());
	    calcHistoryArea.setLayout(new BoxLayout(calcHistoryArea, BoxLayout.Y_AXIS));
	    JButton arrowButton = new JButton(">");
	    calcHistoryArea.setPreferredSize(new Dimension(200, 500));
	    JScrollPane scrollPane = new JScrollPane(calcHistoryArea);
	    containerPanel.add(scrollPane, BorderLayout.CENTER);
	    arrowButton.setBackground(Color.white);
	    containerPanel.add(arrowButton, BorderLayout.EAST);
	    secondaryWindow.add(containerPanel);
	    arrowButton.setMargin(new Insets(0, 18, 0, 18));
	    arrowButton.setFocusPainted(false);
	    arrowButton.addActionListener(new ActionListener() {
	        Timer timer;
	        public void actionPerformed(ActionEvent e) {
	            if (secondaryWindow.getWidth() == 50) {
	                timer = new Timer(5, new ActionListener() {
	                    public void actionPerformed(ActionEvent evt) {
	                        if (secondaryWindow.getWidth() < 400) {
	                            secondaryWindow.setSize(secondaryWindow.getWidth() + 10, secondaryWindow.getHeight());
	                        } else {
	                            ((Timer) evt.getSource()).stop();
	                            arrowButton.setText("<");
	                        }
	                    }
	                });
	                timer.start();
	            }
	            else {
	                timer = new Timer(5, new ActionListener() {
	                    public void actionPerformed(ActionEvent evt) {
	                        if (secondaryWindow.getWidth() > 50) {
	                            secondaryWindow.setSize(secondaryWindow.getWidth() - 10, secondaryWindow.getHeight());
	                        } else {
	                            ((Timer) evt.getSource()).stop();
	                            arrowButton.setText(">");
	                        }
	                    }
	                });
	                timer.start();
	            }
	        }
	    });
	    
	    
		frame.add(buttonsPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		secondaryWindow.setVisible(true);
		frame.toFront();
	}
}
